package net.wildage.thatsyourmod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wildage.thatsyourmod.ThatsYourDecisionMod;
import net.wildage.thatsyourmod.block.modblocks.ReinforcedAnvil;
import net.wildage.thatsyourmod.block.modblocks.ReinforcedGrindstone;
import net.wildage.thatsyourmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ThatsYourDecisionMod.MODID);


    public static final DeferredBlock<Block> ETHAN_BLOCK = registerBlock("ethan_block", () -> new Block(BlockBehaviour.Properties.of().strength(50f, 6f).requiresCorrectToolForDrops().sound(SoundType.ANVIL)));
    public static final DeferredBlock<Block> REINFORCED_GRINDSTONE = registerBlock("reinforced_grindstone", () -> new ReinforcedGrindstone(BlockBehaviour.Properties.of().strength(5f, 6f).requiresCorrectToolForDrops().sound(SoundType.SLIME_BLOCK)));
    public static final DeferredBlock<Block> REINFORCED_ANVIL = registerBlock("reinforced_anvil", () -> new ReinforcedAnvil(BlockBehaviour.Properties.of().strength(6f, 6f).requiresCorrectToolForDrops().sound(SoundType.ANVIL)));

    public static final DeferredBlock<Block> REGREIUM_ORE = registerBlock("regreium_ore", () -> new Block(BlockBehaviour.Properties.of().strength(3f, 6f).requiresCorrectToolForDrops().sound(SoundType.SLIME_BLOCK)));
    public static final DeferredBlock<Block> DEEPSLATE_REGREIUM_ORE = registerBlock("deepslate_regreium_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.5f, 6f).requiresCorrectToolForDrops().sound(SoundType.SLIME_BLOCK)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
