package net.wildage.thatsyourmod.mixin;

import net.minecraft.world.inventory.AnvilMenu;
import net.wildage.thatsyourmod.menu.ReinforcedAnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {

    /**
     * Replaces the hardcoded 40 level cap in the Anvil Menu logic.
     */
    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 40))
    private int reinforcedAnvil$removeLevelCap(int originalLimit) {
        // Optional: If you ONLY want to remove the limit for your custom block:
         if (!((Object) this instanceof ReinforcedAnvilMenu)) {
             return originalLimit; // Keep 40 for vanilla anvils
         }

        return Integer.MAX_VALUE; // Allow unlimited levels
    }
}