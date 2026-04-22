package net.wildage.thatsyourmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DwarvenApple extends Item {

    public DwarvenApple(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, net.minecraft.world.entity.LivingEntity target, InteractionHand hand) {

        if (!(target instanceof Villager villager)) {
            return InteractionResult.PASS;
        }

        if (!player.isCrouching()) {
            return InteractionResult.PASS;
        }

        villager.getPersistentData().putBoolean("dwarf", true);
        villager.setBaby(true);
        villager.setCustomName(Component.literal("Dwarf"));

        return InteractionResult.SUCCESS;
    }
}