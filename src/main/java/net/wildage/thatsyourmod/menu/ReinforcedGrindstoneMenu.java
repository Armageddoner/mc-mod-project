package net.wildage.thatsyourmod.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class ReinforcedGrindstoneMenu extends AbstractContainerMenu {

    private final Container inputSlots = new SimpleContainer(2) {
        @Override
        public void setChanged() {
            super.setChanged();
            ReinforcedGrindstoneMenu.this.slotsChanged(this);
        }
    };

    public int getCurrentRepairCost() {
        ItemStack tool = inputSlots.getItem(0);
        return tool.getOrDefault(net.minecraft.core.component.DataComponents.REPAIR_COST, 0);
    }

    private int calculatePredictedCost(ItemStack tool, ItemStack material) {

        if (tool.isEmpty() || material.isEmpty()) {
            return tool.getOrDefault(net.minecraft.core.component.DataComponents.REPAIR_COST, 0);
        }

        int cost = tool.getOrDefault(net.minecraft.core.component.DataComponents.REPAIR_COST, 0);

        int materialsUsed = material.getCount();
        int costReduced = 0;

        while (materialsUsed > 0 && cost > 0) {
            cost -= 1;
            materialsUsed--;
            costReduced++;
        }

        return Math.max(0, cost);
    }

    public int getPredictedRepairCost() {
        return calculatePredictedCost(
                inputSlots.getItem(0),
                inputSlots.getItem(1)
        );
    }

    private int getRepairPerItem(ItemStack tool) {
        return Math.max(1, tool.getMaxDamage() / 4);
    }

    private final ResultContainer resultSlots = new ResultContainer();

    public ReinforcedGrindstoneMenu(int id, Inventory playerInventory) {
        super(ModMenus.REINFORCED_GRINDSTONE_MENU.get(), id);

        // input slot 1
        this.addSlot(new Slot(inputSlots, 0, 49, 19));

        // input slot 2
        this.addSlot(new Slot(inputSlots, 1, 49, 40) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                ItemStack tool = inputSlots.getItem(0);

                // allow placing anything if no tool yet
                if (tool.isEmpty()) return true;

                // only allow valid repair materials
                return tool.getItem().isValidRepairItem(tool, stack)
                        && tool.getItem() != stack.getItem();
            }
        });

        // output slot
        this.addSlot(new Slot(resultSlots, 0, 129, 34) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {

                ItemStack input1 = inputSlots.getItem(0);
                ItemStack input2 = inputSlots.getItem(1);

                if (!input2.isEmpty() && input1.isDamageableItem()) {

                    int repairPerItem = input1.getMaxDamage() / 4;

                    int damage = input1.getDamageValue();
                    int cost = input1.getOrDefault(net.minecraft.core.component.DataComponents.REPAIR_COST, 0);

                    int materialsUsed = 0;

                    while (materialsUsed < input2.getCount() && (damage > 0 || cost > 0)) {
                        damage -= repairPerItem;
                        cost -= 1;

                        materialsUsed++;
                    }

                    // consume materials
                    input2.shrink(materialsUsed);

                    // apply final result
                    input1.setDamageValue(Math.max(0, damage));
                    input1.set(net.minecraft.core.component.DataComponents.REPAIR_COST, Math.max(0, cost));
                }

                // damage the tool (apply repair)
                input1.setDamageValue(stack.getDamageValue());

                inputSlots.setItem(0, ItemStack.EMPTY);

                super.onTake(player, stack);
            }
        });

        // player inventory slots
        addPlayerInventory(playerInventory);
    }

    private void createResult() {

        ItemStack input1 = inputSlots.getItem(0);
        ItemStack input2 = inputSlots.getItem(1);

        System.out.println("createResult called");

        if (input1.isEmpty()) {
            resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }

        // must be damageable item
        if (!input1.isDamageableItem()) {
            resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }

        // must have material
        if (input2.isEmpty()) {
            resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }

        // basic material match (TEMP: allows diamonds for testing)
        boolean validMaterial =
                input2.is(net.minecraft.world.item.Items.DIAMOND);

        if (!validMaterial) {
            resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }

        ItemStack result = input1.copy();

        int repairPerItem = getRepairPerItem(input1);

        int materialsUsed = Math.min(input2.getCount(), 100);

        int damage = input1.getDamageValue();
        int cost = input1.getOrDefault(
                net.minecraft.core.component.DataComponents.REPAIR_COST, 0
        );


        while (materialsUsed > 0 && (damage > 0 || cost > 0)) {
            damage -= repairPerItem;
            cost -= 1;

            materialsUsed--;
        }

        // remove repair cost
        result.setDamageValue(Math.max(0, damage));
        result.set(net.minecraft.core.component.DataComponents.REPAIR_COST, Math.max(0, cost));

        resultSlots.setItem(0, result);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    private void addPlayerInventory(Inventory inventory) {

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(
                        inventory,
                        col + row * 9 + 9,
                        8 + col * 18,
                        84 + row * 18
                ));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(
                    inventory,
                    col,
                    8 + col * 18,
                    142
            ));
        }
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        createResult();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {

        ItemStack empty = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot == null || !slot.hasItem()) {
            return empty;
        }

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        // OUTPUT SLOT
        if (index == 2) {
            if (!this.moveItemStackTo(stack, 3, 39, true)) {
                return empty;
            }
            slot.onQuickCraft(stack, copy);
        }

        // PLAYER INVENTORY
        else if (index >= 3) {

            ItemStack tool = inputSlots.getItem(0);

            // Try to move into tool slot
            if (tool.isEmpty() && stack.isDamageableItem()) {
                if (!this.moveItemStackTo(stack, 0, 1, false)) {
                    return empty;
                }
            }

            // Try to move into material slot
            else if (tool.getItem().isValidRepairItem(tool, stack)) {
                if (!this.moveItemStackTo(stack, 1, 2, false)) {
                    return empty;
                }
            }

            // Move between inventory ↔ hotbar
            else if (index < 30) {
                if (!this.moveItemStackTo(stack, 30, 39, false)) {
                    return empty;
                }
            } else {
                if (!this.moveItemStackTo(stack, 3, 30, false)) {
                    return empty;
                }
            }
        }

        // INPUT SLOTS → PLAYER
        else {
            if (!this.moveItemStackTo(stack, 3, 39, false)) {
                return empty;
            }
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        if (stack.getCount() == copy.getCount()) {
            return empty;
        }

        slot.onTake(player, stack);

        return copy;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);

        // drop or return items from input slots
        for (int i = 0; i < inputSlots.getContainerSize(); i++) {
            ItemStack stack = inputSlots.getItem(i);

            if (!stack.isEmpty()) {
                player.getInventory().placeItemBackInInventory(stack);
                inputSlots.setItem(i, ItemStack.EMPTY);
            }
        }
    }
}