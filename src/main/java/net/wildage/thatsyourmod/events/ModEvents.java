package net.wildage.thatsyourmod.events;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.wildage.thatsyourmod.item.ModItems;

public class ModEvents {

    @SubscribeEvent
    public static void onVillagerDeath(LivingDropsEvent event) {

        if (!(event.getEntity() instanceof Villager villager)) return;

        if (!villager.getPersistentData().getBoolean("steinlet")) return;

        event.getDrops().add(
                new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        new ItemStack(ModItems.BABY_HEART.get())
                )
        );
    }
}