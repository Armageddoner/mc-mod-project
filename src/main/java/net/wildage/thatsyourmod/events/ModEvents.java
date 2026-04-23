package net.wildage.thatsyourmod.events;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.wildage.thatsyourmod.item.ModItems;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;

public class ModEvents {

    @SubscribeEvent
    public static void onVillagerDeath(LivingDropsEvent event) {

        if (!(event.getEntity() instanceof Villager villager)) return;

        if (!villager.getPersistentData().getBoolean("dwarf")) return;

        event.getDrops().add(
                new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        new ItemStack(ModItems.DWARF_HEART.get())
                )
        );
    }
    @SubscribeEvent
    public static void onFall(LivingFallEvent event) {

        if (!(event.getEntity() instanceof Player player)) return;

        var boots = player.getItemBySlot(EquipmentSlot.FEET);

        if (boots.is(ModItems.REGREIUM_BOOTS.get())) {

            float fallDistance = event.getDistance();

            // cancel vanilla fall damage
            event.setCanceled(true);

            // exponential curve scaling
            float x = fallDistance / 10.0f;
            float curve = (float) Math.exp(x);
            System.out.println("curve Value" + curve);
            // VERY small falls = lethal scaling
            if (fallDistance <= 1) {

                float result = curve * 20.0f;
                player.hurt(player.damageSources().fall(), result);
                return;
            }
            if (fallDistance <= 2) {

                float result = curve * 10f;
                player.hurt(player.damageSources().fall(), result);
                return;
            }
            if (fallDistance <= 3) {

                float result = curve * 7f;
                player.hurt(player.damageSources().fall(), result);
                return;
            }
            if (fallDistance <= 4) {

                float result = curve * 5f;
                player.hurt(player.damageSources().fall(), result);
                return;
            }
            if (fallDistance <= 5) {

                float result = curve * 3f;
                player.hurt(player.damageSources().fall(), result);
                return;
            }
            if (fallDistance <= 6) {

                float result = curve * 2f;
                player.hurt(player.damageSources().fall(), result);
                return;
            }
            if (fallDistance <= 7) {

                float result = curve * 1.5f;
                player.hurt(player.damageSources().fall(), result);
                return;
            }
            // normal fall damage range
            if (fallDistance <= 9.99) {

                float result = curve * 1f;
                player.hurt(player.damageSources().fall(), result);
                return;
            }

            // big fall = healing system
            if (fallDistance >= 10) {
                float maxHealth = player.getMaxHealth();
                float currentHealth = player.getHealth();

                float healAmount = (curve - 2.0f) * 2.0f;

                // apply heal
                player.heal(healAmount);

                float absorption = Math.max(0f, fallDistance - 20f);
                System.out.println("absorption about " + absorption);
                player.setAbsorptionAmount(player.getAbsorptionAmount() + absorption);
                //CheeseBurger

            }


        }
    }


}