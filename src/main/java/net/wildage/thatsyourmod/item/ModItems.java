package net.wildage.thatsyourmod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wildage.thatsyourmod.ThatsYourDecisionMod;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ThatsYourDecisionMod.MODID);

    public static final DeferredItem<Item> COGITO = ITEMS.register("cogito", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> Enkephalin = ITEMS.register("enkephalin", () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
