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
                output.accept(ModItems.ENKEPHALIN);
                output.accept(ModItems.STEINLET_HEART);
                output.accept(ModItems.BABY_POWDER);
                output.accept(ModItems.DIDDY_DANDELION);
                output.accept(ModItems.BABY_OIL);
                output.accept(ModItems.RAW_DIDDIUM);
                output.accept(ModItems.DIDDIUM_INGOT);
            })
            .build());

    public static final Supplier<CreativeModeTab> THATS_YOUR_EQUIPMENT_TAB = CREATIVE_MODE_TAB.register("thats_your_equipment_tab", () -> CreativeModeTab.builder()
            .icon(()-> new ItemStack(ModItems.DIDDIUM_CHESTPLATE.get()))
            .title(Component.translatable("creativetab.thatsyourdecisionllc.thats_your_equipment"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.DIDDIUM_HELMET);
                output.accept(ModItems.DIDDIUM_CHESTPLATE);
                output.accept(ModItems.DIDDIUM_LEGGINGS);
                output.accept(ModItems.DIDDIUM_BOOTS);
                output.accept(ModItems.DIDDIUM_PICKAXE);
                output.accept(ModItems.DIDDIUM_SHOVEL);
                output.accept(ModItems.DIDDIUM_AXE);
                output.accept(ModItems.DIDDIUM_SWORD);
                output.accept(ModItems.DIDDIUM_HOE);
            })
            .build());

    public static final Supplier<CreativeModeTab> THATS_YOUR_BLOCK_TAB = CREATIVE_MODE_TAB.register("thats_your_blocks_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ThatsYourDecisionMod.MODID, "thats_your_items_tab"))
            .icon(()-> new ItemStack(ModBlocks.EPSTEIN_BLOCK.get()))
            .title(Component.translatable("creativetab.thatsyourdecisionllc.thats_your_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.EPSTEIN_BLOCK);
                output.accept(ModBlocks.REINFORCED_GRINDSTONE);
                output.accept(ModBlocks.REINFORCED_ANVIL);
                output.accept(ModBlocks.DIDDIUM_ORE);
                output.accept(ModBlocks.DEEPSLATE_DIDDIUM_ORE);
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
