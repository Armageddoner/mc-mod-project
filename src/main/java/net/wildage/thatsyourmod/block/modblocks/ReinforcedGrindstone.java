package net.wildage.thatsyourmod.block.modblocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.SimpleMenuProvider;

public class ReinforcedGrindstone extends GrindstoneBlock {

    public ReinforcedGrindstone(Properties properties) {
        super(properties);
    }

    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider(
                (id, inventory, player) -> new GrindstoneMenu(id, inventory),
                Component.translatable("Reinforced Grindstone")
        );
    }
}