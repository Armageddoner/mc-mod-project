package net.wildage.thatsyourmod.events;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.wildage.thatsyourmod.item.ModItems;

public class DwarfEvents {

    @SubscribeEvent
    public static void onVillagerInteract(PlayerInteractEvent.EntityInteractSpecific event) {

        Player player = event.getEntity();
        Level level = player.level();

        if (level.isClientSide()) return;

        if (!(event.getTarget() instanceof Villager villager)) return;

        if (!player.isCrouching()) return;

        if (!player.getItemInHand(event.getHand()).is(ModItems.DWARVEN_APPLE.get())) return;

        // 🚫 Prevent re-conversion
        if (villager.getPersistentData().getBoolean("dwarf")) return;

        // Mark as dwarf
        villager.getPersistentData().putBoolean("dwarf", true);

        // Force baby
        villager.setBaby(true);

        // Name it
        villager.setCustomName(Component.literal("Dwarf"));
        villager.setCustomNameVisible(true);

        // Play sound
        level.playSound(
                null,
                villager.blockPosition(),
                SoundEvents.ZOMBIE_VILLAGER_CURE,
                SoundSource.NEUTRAL,
                1.0f,
                1.0f
        );

        // ✅ ALWAYS consume item (unless creative)
        if (!player.getAbilities().instabuild) {
            player.getItemInHand(event.getHand()).shrink(1);
        }

        event.setCanceled(true);
        event.setCancellationResult(net.minecraft.world.InteractionResult.SUCCESS);
    }

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {

        if (!(event.getEntity() instanceof Villager villager)) return;

        if (!villager.getPersistentData().getBoolean("dwarf")) return;

        // Force baby state
        if (!villager.isBaby()) {
            villager.setBaby(true);
        }

        // Lock age so it never grows up
        villager.setAge(-24000);
    }
}