package com.jackhesslein.mcreimagined;

import org.slf4j.Logger;

import com.jackhesslein.mcreimagined.item.ModItems;
import com.jackhesslein.mcreimagined.progression.ProgressionCommand;
import com.mojang.logging.LogUtils;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(MinecraftReimagined.MOD_ID)
public final class MinecraftReimagined {
    public static final String MOD_ID = "minecraftreimagined";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);

    public static final DeferredBlock<UpgradedCraftingTableBlock> UPGRADED_CRAFTING_TABLE = BLOCKS.registerBlock(
            "upgraded_crafting_table",
            UpgradedCraftingTableBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)
    );
    public static final DeferredItem<BlockItem> UPGRADED_CRAFTING_TABLE_ITEM = ModItems.ITEMS.registerSimpleBlockItem(
            "upgraded_crafting_table",
            UPGRADED_CRAFTING_TABLE
    );

    public MinecraftReimagined(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ModItems.register(modEventBus);
        modEventBus.addListener(this::addCreativeTabItems);
        LOGGER.info("MinecraftReimagined initialized");
        NeoForge.EVENT_BUS.addListener(ProgressionCommand::onCommandRegister);
    }

    private void addCreativeTabItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(UPGRADED_CRAFTING_TABLE_ITEM);
        }
    }
}
