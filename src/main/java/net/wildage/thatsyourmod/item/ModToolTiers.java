package net.wildage.thatsyourmod.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class ModToolTiers {

    public static final Tier REGREIUM = new Tier() {

        @Override
        public int getUses() {
            return 250; // iron durability
        }

        @Override
        public float getSpeed() {
            return 12.0f; // gold mining speed
        }

        @Override
        public float getAttackDamageBonus() {
            return 2.0f; // iron base damage
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return net.minecraft.tags.BlockTags.INCORRECT_FOR_IRON_TOOL;
        }

        public int getLevel() {
            return 2; // iron tier (can mine diamonds, etc.)
        }

        @Override
        public int getEnchantmentValue() {
            return 22; // gold enchantability (optional but nice)
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ModItems.REGREIUM_INGOT.get());
        }
    };
} // comment