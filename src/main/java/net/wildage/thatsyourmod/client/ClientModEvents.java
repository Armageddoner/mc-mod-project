package net.wildage.thatsyourmod.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

import net.wildage.thatsyourmod.ThatsYourDecisionMod;
import net.wildage.thatsyourmod.client.screen.ReinforcedAnvilScreen;
import net.wildage.thatsyourmod.client.screen.ReinforcedGrindstoneScreen;
import net.wildage.thatsyourmod.menu.ModMenus;

@EventBusSubscriber(modid = ThatsYourDecisionMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {

        event.register(
                ModMenus.REINFORCED_GRINDSTONE_MENU.get(),
                ReinforcedGrindstoneScreen::new
        );

        event.register(
                ModMenus.REINFORCED_ANVIL_MENU.get(),
                ReinforcedAnvilScreen::new
        );
    }
}