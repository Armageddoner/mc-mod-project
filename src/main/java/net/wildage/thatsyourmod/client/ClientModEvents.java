package net.wildage.thatsyourmod.client;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

import net.wildage.thatsyourmod.ThatsYourDecisionMod;
import net.wildage.thatsyourmod.menu.ModMenus;
import net.wildage.thatsyourmod.client.screen.ReinforcedGrindstoneScreen;

@EventBusSubscriber(modid = ThatsYourDecisionMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {

        event.register(
                ModMenus.REINFORCED_GRINDSTONE_MENU.get(),
                ReinforcedGrindstoneScreen::new
        );
    }
}