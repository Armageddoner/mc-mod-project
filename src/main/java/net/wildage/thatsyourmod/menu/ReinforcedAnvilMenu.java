package net.wildage.thatsyourmod.menu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.state.BlockState;
import net.wildage.thatsyourmod.block.ModBlocks;

public class ReinforcedAnvilMenu extends AnvilMenu {

    // 1. Client-side constructor (used when the server tells the client to open the menu)
    public ReinforcedAnvilMenu(int containerId, Inventory playerInventory) {
        super(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    // 2. Server-side constructor (used when the player actually clicks the block)
    public ReinforcedAnvilMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(containerId, playerInventory, access);
    }

    /**
     * 3. The most important part for custom crafting blocks.
     * We must override this so the game doesn't instantly close the menu
     * because it can't find a vanilla minecraft:anvil block.
     */
    @Override
    public boolean stillValid(Player player) {
        return this.access.evaluate((level, pos) -> {
            BlockState state = level.getBlockState(pos);

            // REPLACE 'ModBlocks.REINFORCED_ANVIL.get()' with your actual block reference!
            boolean isOurAnvil = state.is(ModBlocks.REINFORCED_ANVIL.get());

            // Checks if the player is within 8 blocks (64.0D is 8 squared)
            boolean isCloseEnough = player.distanceToSqr((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D) <= 64.0D;

            return isOurAnvil && isCloseEnough;
        }, true);
    }
}