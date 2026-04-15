package net.wildage.thatsyourmod.item;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wildage.thatsyourmod.ThatsYourDecisionMod;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ThatsYourDecisionMod.MODID);

    public static final DeferredItem<Item> COGITO = ITEMS.register("cogito", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ENKEPHALIN = ITEMS.register("enkephalin", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DIDDIUM_HELMET =
            ITEMS.register("diddium_helmet",
                    () -> new ArmorItem(Holder.direct(ModArmorMaterials.DIDDIUM), ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> DIDDIUM_CHESTPLATE =
            ITEMS.register("diddium_chestplate",
                    () -> new ArmorItem(Holder.direct(ModArmorMaterials.DIDDIUM), ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> DIDDIUM_LEGGINGS =
            ITEMS.register("diddium_leggings",
                    () -> new ArmorItem(Holder.direct(ModArmorMaterials.DIDDIUM), ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> DIDDIUM_BOOTS =
            ITEMS.register("diddium_boots",
                    () -> new ArmorItem(Holder.direct(ModArmorMaterials.DIDDIUM), ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> BABY_HEART = ITEMS.register("baby_heart", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BABY_POWDER = ITEMS.register("baby_powder", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIDDY_DANDELION = ITEMS.register("diddy_dandelion", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> BABY_OIL = ITEMS.register("baby_oil", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_DIDDIUM = ITEMS.register("raw_diddium", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}