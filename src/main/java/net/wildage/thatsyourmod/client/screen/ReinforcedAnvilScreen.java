package net.wildage.thatsyourmod.client.screen;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.wildage.thatsyourmod.ThatsYourDecisionMod;
import net.wildage.thatsyourmod.menu.ReinforcedAnvilMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ReinforcedAnvilScreen extends AbstractContainerScreen<ReinforcedAnvilMenu> {

    // The path to your custom GUI texture
    // Note: If you are on NeoForge 1.21+, use ResourceLocation.fromNamespaceAndPath() instead
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    ThatsYourDecisionMod.MODID,
                    "textures/gui/reinforced_anvil.png"
            );
    public ReinforcedAnvilScreen(ReinforcedAnvilMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        // You can adjust the title label position here if your texture requires it
        this.titleLabelX = 60;
    }

    /**
     * Overriding this method tells the game to use your texture instead of the vanilla one.
     */
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        // Draws your custom background
        guiGraphics.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);

        // Draws the red "X" over the arrow if the player doesn't have the materials/levels
        guiGraphics.blit(TEXTURE, i + 59, j + 20, 0, this.imageHeight + (this.menu.getSlot(0).hasItem() ? 0 : 16), 110, 16);

        // Vanilla logic to draw the red cross-out if the anvil output is invalid
        if ((this.menu.getSlot(0).hasItem() || this.menu.getSlot(1).hasItem()) && !this.menu.getSlot(2).hasItem()) {
            guiGraphics.blit(TEXTURE, i + 99, j + 45, this.imageWidth, 0, 28, 21);
        }
    }
}