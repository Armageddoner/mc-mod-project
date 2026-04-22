package net.wildage.thatsyourmod.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wildage.thatsyourmod.item.custom.RegreiumBootsItem;

public class RegreiumBoots {
    public static final DeferredRegister.Items ITEMS =
        DeferredRegister.createItems("thatsyourdecisionllc");

    public static final DeferredItem<ArmorItem> REGREIUM_BOOTS = ITEMS.register("regreium_boots",
            () -> new RegreiumBootsItem(
                    ModArmorMaterials.REGREIUM,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().stacksTo(1)
            ));
}

