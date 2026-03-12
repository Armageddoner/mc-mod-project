package net.wildage.thatsyourmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wildage.thatsyourmod.ThatsYourDecisionMod;
import net.wildage.thatsyourmod.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ThatsYourDecisionMod.MODID);

    public static final Supplier<CreativeModeTab> THATS_YOUR_ITEMS_TAB = CREATIVE_MODE_TAB.register("thats_your_items_tab", () -> CreativeModeTab.builder()
            .icon(()-> new ItemStack(ModItems.COGITO.get()))
            .title(Component.translatable("creativetab.thatsyourdecisionllc.thats_your_items"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.COGITO);
                output.accept(ModItems.Enkephalin);
            })
            .build());

    public static final Supplier<CreativeModeTab> THATS_YOUR_BLOCK_TAB = CREATIVE_MODE_TAB.register("thats_your_blocks_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ThatsYourDecisionMod.MODID, "thats_your_items_tab"))
            .icon(()-> new ItemStack(ModBlocks.EPSTEIN_BLOCK.get()))
            .title(Component.translatable("creativetab.thatsyourdecisionllc.thats_your_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.EPSTEIN_BLOCK);
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
