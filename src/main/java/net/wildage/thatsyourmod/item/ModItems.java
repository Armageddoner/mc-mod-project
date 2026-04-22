package net.wildage.thatsyourmod.item;

import net.minecraft.core.Holder;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wildage.thatsyourmod.ThatsYourDecisionMod;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ThatsYourDecisionMod.MODID);

    public static final DeferredItem<Item> COGITO = ITEMS.register("cogito", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ENKEPHALIN = ITEMS.register("enkephalin", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> REGREIUM_HELMET = ITEMS.register("regreium_helmet", () -> new ArmorItem(Holder.direct(ModArmorMaterials.REGREIUM.value()), ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> REGREIUM_CHESTPLATE = ITEMS.register("regreium_chestplate", () -> new ArmorItem(Holder.direct(ModArmorMaterials.REGREIUM.value()), ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> REGREIUM_LEGGINGS = ITEMS.register("regreium_leggings", () -> new ArmorItem(Holder.direct(ModArmorMaterials.REGREIUM.value()), ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> REGREIUM_BOOTS = ITEMS.register("regreium_boots", () -> new ArmorItem(Holder.direct(ModArmorMaterials.REGREIUM.value()), ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> REGREIUM_PICKAXE = ITEMS.register("regreium_pickaxe", () -> new PickaxeItem(ModToolTiers.REGREIUM, new Item.Properties()));
    public static final DeferredItem<Item> REGREIUM_SWORD = ITEMS.register("regreium_sword", () -> new SwordItem(ModToolTiers.REGREIUM, new Item.Properties()));
    public static final DeferredItem<Item> REGREIUM_AXE = ITEMS.register("regreium_axe", () -> new AxeItem(ModToolTiers.REGREIUM, new Item.Properties()));
    public static final DeferredItem<Item> REGREIUM_SHOVEL = ITEMS.register("regreium_shovel", () -> new ShovelItem(ModToolTiers.REGREIUM, new Item.Properties()));
    public static final DeferredItem<Item> REGREIUM_HOE = ITEMS.register("regreium_hoe", () -> new HoeItem(ModToolTiers.REGREIUM, new Item.Properties()));


    public static final DeferredItem<Item> DWARVENIUM = ITEMS.register("dwarvenium", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DWARVEN_APPLE = ITEMS.register("dwarven_apple", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> DWARF_HEART = ITEMS.register("dwarf_heart", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DWARVEN_ESSENCE = ITEMS.register("dwarven_essence", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_REGREIUM = ITEMS.register("raw_regreium", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REGREIUM_INGOT = ITEMS.register("regreium_ingot", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}