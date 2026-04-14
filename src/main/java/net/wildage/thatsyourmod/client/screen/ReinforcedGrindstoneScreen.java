package net.wildage.thatsyourmod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import net.minecraft.world.item.ItemStack;
import net.wildage.thatsyourmod.ThatsYourDecisionMod;
import net.wildage.thatsyourmod.menu.ReinforcedGrindstoneMenu;

public class ReinforcedGrindstoneScreen extends AbstractContainerScreen<ReinforcedGrindstoneMenu> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    ThatsYourDecisionMod.MODID,
                    "textures/gui/reinforced_grindstone.png"
            );

    public ReinforcedGrindstoneScreen(ReinforcedGrindstoneMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {

        ItemStack tool = this.menu.getSlot(0).getItem();
        ItemStack material = this.menu.getSlot(1).getItem();

        // Only show "Cost" when tool is pres`ent
        if (!tool.isEmpty()) {

            int current = this.menu.getCurrentRepairCost();

            guiGraphics.drawString(
                    this.font,
                    "Cost: " + current,
                    40,
                    6,
                    0x404040,
                    false
            );
        }

        // Only show "After" when BOTH inputs are present
        if (!tool.isEmpty() && !material.isEmpty()) {

            int predicted = this.menu.getPredictedRepairCost();

            guiGraphics.drawString(
                    this.font,
                    "After: " + predicted,
                    118,
                    53,
                    0x404040,
                    false
            );
        }
    }
}