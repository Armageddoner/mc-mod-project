package net.wildage.thatsyourmod.menu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.wildage.thatsyourmod.ThatsYourDecisionMod;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, ThatsYourDecisionMod.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<ReinforcedGrindstoneMenu>> REINFORCED_GRINDSTONE_MENU =
            MENUS.register("reinforced_grindstone_menu",
                    () -> new MenuType<>(ReinforcedGrindstoneMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}