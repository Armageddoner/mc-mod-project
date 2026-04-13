package net.wildage.thatsyourmod.mixin.client;

import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.wildage.thatsyourmod.client.screen.ReinforcedAnvilScreen;
import net.wildage.thatsyourmod.menu.ReinforcedAnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {

    /**
     * Replaces the hardcoded 40 level cap in the Anvil UI renderer.
     * This prevents the "Too Expensive!" red text from showing.
     */
//    @ModifyConstant(method = "renderLabels", constant = @Constant(intValue = 40))
    @ModifyConstant(
            method = "renderLabels(Lnet/minecraft/client/gui/GuiGraphics;II)V",
            constant = @Constant(intValue = 40),
            require = 1
    )
    private int reinforcedAnvil$removeUITextCap(int originalLimit) {
        return Integer.MAX_VALUE;
    }
//    private int reinforcedAnvil$removeUITextCap(int originalLimit) {
//        // Optional: Ensure this only applies to your custom screen if you made one:
//         if (!((Object) this instanceof ReinforcedAnvilScreen)) {
//             return originalLimit;
//         }
//
//        return Integer.MAX_VALUE;
//    }
}