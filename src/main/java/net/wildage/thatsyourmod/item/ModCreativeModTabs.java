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

    public static final Supplier<CreativeModeTab> THATS_YOUR_BLOCKS_TAB = CREATIVE_MODE_TAB.register("thats_your_blocks_tab", () -> CreativeModeTab.builder()
            .icon(()-> new ItemStack(ModBlocks.EPSTEIN_BLOCK.get()))
            .title(Component.translatable("creativetab.thatsyourdecisionllc.thats_your_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.EPSTEIN_BLOCK);
                output.accept(ModBlocks.REGREIUM_ORE);
                output.accept(ModBlocks.DEEPSLATE_REGREIUM_ORE);
            })
            .build());

    public static final Supplier<CreativeModeTab> THATS_YOUR_FUNCTIONAL_BLOCKS_TAB = CREATIVE_MODE_TAB.register("thats_your_functional_blocks_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ThatsYourDecisionMod.MODID, "thats_your_blocks_tab"))
            .icon(()-> new ItemStack(ModBlocks.REINFORCED_ANVIL.get()))
            .title(Component.translatable("creativetab.thatsyourdecisionllc.thats_your_functional_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.REINFORCED_GRINDSTONE);
                output.accept(ModBlocks.REINFORCED_ANVIL);
            })
            .build());

    public static final Supplier<CreativeModeTab> THATS_YOUR_TOOLS_TAB = CREATIVE_MODE_TAB.register("thats_your_tools_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ThatsYourDecisionMod.MODID, "thats_your_functional_blocks_tab"))
            .icon(()-> new ItemStack(ModItems.REGREIUM_PICKAXE.get()))
            .title(Component.translatable("creativetab.thatsyourdecisionllc.thats_your_tools"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.REGREIUM_SHOVEL);
                output.accept(ModItems.REGREIUM_PICKAXE);
                output.accept(ModItems.REGREIUM_AXE);
                output.accept(ModItems.REGREIUM_HOE);
            })
            .build());

    public static final Supplier<CreativeModeTab> THATS_YOUR_COMBAT_TAB = CREATIVE_MODE_TAB.register("thats_your_combat_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ThatsYourDecisionMod.MODID, "thats_your_tools_tab"))
            .icon(()-> new ItemStack(ModItems.REGREIUM_SWORD.get()))
            .title(Component.translatable("creativetab.thatsyourdecisionllc.thats_your_combat"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.REGREIUM_HELMET);
                output.accept(ModItems.REGREIUM_CHESTPLATE);
                output.accept(ModItems.REGREIUM_LEGGINGS);
                output.accept(ModItems.REGREIUM_BOOTS);
                output.accept(ModItems.REGREIUM_SWORD);
                output.accept(ModItems.REGREIUM_AXE);
            })
            .build());

    public static final Supplier<CreativeModeTab> THATS_YOUR_ITEMS_TAB = CREATIVE_MODE_TAB.register("thats_your_items_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ThatsYourDecisionMod.MODID, "thats_your_combat_tab"))
            .icon(()-> new ItemStack(ModItems.COGITO.get()))
            .title(Component.translatable("creativetab.thatsyourdecisionllc.thats_your_items"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.COGITO);
                output.accept(ModItems.ENKEPHALIN);
                output.accept(ModItems.DWARVEN_APPLE);
                output.accept(ModItems.DWARVEN_ESSENCE);
            })
            .build());

    public static final Supplier<CreativeModeTab> THATS_YOUR_INGREDIENTS_TAB = CREATIVE_MODE_TAB.register("thats_your_ingredients_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ThatsYourDecisionMod.MODID, "thats_your_items_tab"))
            .icon(()-> new ItemStack(ModItems.DWARVENIUM.get()))
            .title(Component.translatable("creativetab.thatsyourdecisionllc.thats_your_ingredients"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.DWARF_HEART);
                output.accept(ModItems.DWARVENIUM);
                output.accept(ModItems.RAW_REGREIUM);
                output.accept(ModItems.REGREIUM_INGOT);
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
