package net.wildage.thatsyourmod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ArmorItem.Type;

import java.util.List;
import java.util.Map;

public class ModArmorMaterials {

    public static final ArmorMaterial DIDDIUM = new ArmorMaterial(
            Map.of(
                    Type.BOOTS, 2,
                    Type.LEGGINGS, 5,
                    Type.CHESTPLATE, 6,
                    Type.HELMET, 2
            ),
            15,
            SoundEvents.ARMOR_EQUIP_IRON,

            () -> Ingredient.of(net.minecraft.world.item.Items.IRON_INGOT),

            List.of(
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath("thatsyourdecisionllc", "diddium")
                    )
            ),

            1.0F, // toughness
            0.0F  // knockback resistance
    );
}