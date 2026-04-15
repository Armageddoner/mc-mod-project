package net.wildage.thatsyourmod.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
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