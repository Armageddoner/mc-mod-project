package net.wildage.thatsyourmod.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.wildage.thatsyourmod.block.ModBlocks;
import net.wildage.thatsyourmod.item.ModItems;

public class DwarvenEssenceEvents {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {

        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos origin = event.getPos();
        ItemStack held = event.getItemStack();

        if (level.isClientSide()) return;

        if (!held.is(ModItems.DWARVEN_ESSENCE.get())) return;

        BlockState clickedState = level.getBlockState(origin);

        // Only trigger on iron ore types
        if (!isIronOre(clickedState)) return;

        int radius = 3;

        for (BlockPos pos : BlockPos.betweenClosed(
                origin.offset(-radius, -radius, -radius),
                origin.offset(radius, radius, radius))) {

            BlockState state = level.getBlockState(pos);

            if (isIronOre(state)) {
                level.setBlock(pos,
                        ModBlocks.REGREIUM_ORE.get().defaultBlockState(),
                        3
                );
            }
        }

        // consume item (optional)
        if (!player.getAbilities().instabuild) {
            held.shrink(1);
        }

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }

    private static boolean isIronOre(BlockState state) {
        return state.is(Blocks.IRON_ORE) || state.is(Blocks.DEEPSLATE_IRON_ORE);
    }
}