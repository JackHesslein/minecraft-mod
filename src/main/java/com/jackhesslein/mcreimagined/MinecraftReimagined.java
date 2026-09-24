package com.jackhesslein.mcreimagined;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(MinecraftReimagined.MOD_ID)
public final class MinecraftReimagined {
    public static final String MOD_ID = "minecraftreimagined";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    public static final DeferredBlock<UpgradedCraftingTableBlock> UPGRADED_CRAFTING_TABLE = BLOCKS.registerBlock(
            "upgraded_crafting_table",
            UpgradedCraftingTableBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)
    );
    public static final DeferredItem<BlockItem> UPGRADED_CRAFTING_TABLE_ITEM = ITEMS.registerSimpleBlockItem(
            "upgraded_crafting_table",
            UPGRADED_CRAFTING_TABLE
    );

    public MinecraftReimagined(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        modEventBus.addListener(this::addCreativeTabItems);
        LOGGER.info("MinecraftReimagined initialized");
    }

    private void addCreativeTabItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(UPGRADED_CRAFTING_TABLE_ITEM);
        }
    }
}
