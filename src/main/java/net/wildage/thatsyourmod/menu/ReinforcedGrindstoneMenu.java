package net.wildage.thatsyourmod.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class ReinforcedGrindstoneMenu extends AbstractContainerMenu {

    private final Container inputSlots = new SimpleContainer(2);
    private final ResultContainer resultSlots = new ResultContainer();

    public ReinforcedGrindstoneMenu(int id, Inventory playerInventory) {
        super(ModMenus.REINFORCED_GRINDSTONE_MENU.get(), id);

        // input slot 1
        this.addSlot(new Slot(inputSlots, 0, 49, 19));

        // input slot 2
        this.addSlot(new Slot(inputSlots, 1, 49, 40));

        // output slot
        this.addSlot(new Slot(resultSlots, 2, 129, 34) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        // player inventory slots
        addPlayerInventory(playerInventory);
    }

    private void createResult() {

        ItemStack input1 = inputSlots.getItem(0);
        ItemStack input2 = inputSlots.getItem(1);

        if (input1.isEmpty()) {
            resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }

        ItemStack result = input1.copy();

        // remove repair cost
        result.set(net.minecraft.core.component.DataComponents.REPAIR_COST, 0);

        if (!input2.isEmpty()) {

            int repairAmount = result.getMaxDamage() / 4;

            result.setDamageValue(
                    Math.max(0, result.getDamageValue() - repairAmount)
            );
        }

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
        return ItemStack.EMPTY;
    }
}