package net.wildage.thatsyourmod.events;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.wildage.thatsyourmod.item.ModItems;

public class SteinletEvents {

    @SubscribeEvent
    public static void onVillagerInteract(PlayerInteractEvent.EntityInteractSpecific event) {

        Player player = event.getEntity();

        if (player.level().isClientSide()) return;

        if (!(event.getTarget() instanceof Villager villager)) return;

        if (!player.isCrouching()) return;

        if (!player.getItemInHand(event.getHand()).is(ModItems.DIDDY_DANDELION.get())) return;

        villager.getPersistentData().putBoolean("steinlet", true);
        villager.setBaby(true);

        event.setCanceled(true);
        event.setCancellationResult(net.minecraft.world.InteractionResult.SUCCESS);
    }
}