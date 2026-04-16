package net.wildage.thatsyourmod.events;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.wildage.thatsyourmod.item.ModItems;

public class ItemSlideEvents {

    @SubscribeEvent
    public static void onItemTick(EntityTickEvent.Post event) {

        if (!(event.getEntity() instanceof ItemEntity item)) return;

        // Only run on server (prevents desync issues)
        if (item.level().isClientSide()) return;

        // Only apply to your custom items (edit/add more if needed)
        if (!isDiddiumItem(item)) return;

        // Only apply when item is on the ground
        if (!item.onGround()) return;

        Vec3 motion = item.getDeltaMovement();

        // Slight boost to counteract vanilla friction
        double boost = 1.02;

        double newX = motion.x * boost;
        double newZ = motion.z * boost;

        // Clamp speed so it doesn't go infinite
        double maxSpeed = 0.5;

        newX = clamp(newX, -maxSpeed, maxSpeed);
        newZ = clamp(newZ, -maxSpeed, maxSpeed);

        item.setDeltaMovement(newX, motion.y, newZ);
    }

    private static boolean isDiddiumItem(ItemEntity item) {
        return item.getItem().is(ModItems.DIDDIUM_INGOT.get())
                || item.getItem().is(ModItems.RAW_DIDDIUM.get());
        // add more items here if you want them to slide
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}