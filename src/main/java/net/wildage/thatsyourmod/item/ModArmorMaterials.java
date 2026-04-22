package net.wildage.thatsyourmod.item;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;

public class ModArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, "thatsyourdecisionllc");

    public static final Holder<ArmorMaterial> REGREIUM = ARMOR_MATERIALS.register("regreium",
            () -> new ArmorMaterial(
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
                                    ResourceLocation.fromNamespaceAndPath("thatsyourdecisionllc", "regreium")
                            )
                    ),
                    1.0F,
                    0.0F
            )
    );
}