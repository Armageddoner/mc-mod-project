package net.wildage.thatsyourmod.block.modblocks;

import net.wildage.thatsyourmod.menu.ReinforcedAnvilMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ReinforcedAnvil extends AnvilBlock {

    public ReinforcedAnvil(Properties properties) {
        super(properties);
    }

    /**
     * Handles the player right-clicking the block.
     * NeoForge uses useWithoutItem for interactions that don't depend on the item in hand.
     */
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            // Opens the menu provider defined below
            player.openMenu(state.getMenuProvider(level, pos));
            return InteractionResult.CONSUME;
        }
    }

    /**
     * Tells the game WHICH menu to open when the player interacts with the block.
     * We point this to the ReinforcedAnvilMenu we created earlier.
     */
    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider((containerId, playerInventory, player) -> {
            return new ReinforcedAnvilMenu(containerId, playerInventory, ContainerLevelAccess.create(level, pos));
        }, Component.translatable("block.thatsyourdecisionllc.reinforced_anvil"));
    }
}