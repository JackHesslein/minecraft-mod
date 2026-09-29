package com.jackhesslein.mcreimagined;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
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

    public static final DeferredBlock<TierOneCraftingTableBlock> TIER_ONE_CRAFTING_TABLE = BLOCKS.registerBlock(
            "tier_one_crafting_table",
            TierOneCraftingTableBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)
    );
    public static final DeferredItem<BlockItem> TIER_ONE_CRAFTING_TABLE_ITEM = ITEMS.registerSimpleBlockItem(
            "tier_one_crafting_table",
            TIER_ONE_CRAFTING_TABLE
    );

    public static final DeferredBlock<TierTwoCraftingTableBlock> TIER_TWO_CRAFTING_TABLE = BLOCKS.registerBlock(
            "tier_two_crafting_table",
            TierTwoCraftingTableBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)
                    .sound(SoundType.NETHER_WOOD)
    );
    public static final DeferredItem<BlockItem> TIER_TWO_CRAFTING_TABLE_ITEM = ITEMS.registerSimpleBlockItem(
            "tier_two_crafting_table",
            TIER_TWO_CRAFTING_TABLE
    );

    public static final DeferredBlock<TierThreeCraftingTableBlock> TIER_THREE_CRAFTING_TABLE = BLOCKS.registerBlock(
            "tier_three_crafting_table",
            TierThreeCraftingTableBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)
                    .strength(30.0F, 1200.0F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
    );
    public static final DeferredItem<BlockItem> TIER_THREE_CRAFTING_TABLE_ITEM = ITEMS.registerSimpleBlockItem(
            "tier_three_crafting_table",
            TIER_THREE_CRAFTING_TABLE
    );

    public static final DeferredItem<CraftingTableUpgradeItem> TIER_ONE_UPGRADE_TEMPLATE = ITEMS.register(
            "tier_one_upgrade_template",
            () -> new CraftingTableUpgradeItem(new Item.Properties(), "tooltip.minecraftreimagined.tier_one_upgrade_template")
    );
    public static final DeferredItem<CraftingTableUpgradeItem> TIER_TWO_UPGRADE_TEMPLATE = ITEMS.register(
            "tier_two_upgrade_template",
            () -> new CraftingTableUpgradeItem(new Item.Properties(), "tooltip.minecraftreimagined.tier_two_upgrade_template")
    );
    public static final DeferredItem<CraftingTableUpgradeItem> TIER_THREE_UPGRADE_TEMPLATE = ITEMS.register(
            "tier_three_upgrade_template",
            () -> new CraftingTableUpgradeItem(new Item.Properties(), "tooltip.minecraftreimagined.tier_three_upgrade_template")
    );

    public MinecraftReimagined(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        modEventBus.addListener(this::addCreativeTabItems);
        NeoForge.EVENT_BUS.addListener(this::upgradeCraftingTable);
        LOGGER.info("MinecraftReimagined initialized");
    }

    private void upgradeCraftingTable(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        if (!player.isShiftKeyDown()) {
            return;
        }

        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Block current = level.getBlockState(pos).getBlock();
        ItemStack stack = event.getItemStack();
        Block upgrade = null;
        if (stack.is(TIER_ONE_UPGRADE_TEMPLATE.get()) && current == Blocks.CRAFTING_TABLE) {
            upgrade = TIER_ONE_CRAFTING_TABLE.get();
        } else if (stack.is(TIER_TWO_UPGRADE_TEMPLATE.get()) && current == TIER_ONE_CRAFTING_TABLE.get()) {
            upgrade = TIER_TWO_CRAFTING_TABLE.get();
        } else if (stack.is(TIER_THREE_UPGRADE_TEMPLATE.get()) && current == TIER_TWO_CRAFTING_TABLE.get()) {
            upgrade = TIER_THREE_CRAFTING_TABLE.get();
        }

        if (upgrade == null) {
            return;
        }

        if (!level.isClientSide()) {
            level.setBlock(pos, upgrade.defaultBlockState(), 3);
            level.playSound(null, pos, upgrade.defaultBlockState().getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
        event.setCanceled(true);
    }

    private void addCreativeTabItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(TIER_ONE_CRAFTING_TABLE_ITEM);
            event.accept(TIER_TWO_CRAFTING_TABLE_ITEM);
            event.accept(TIER_THREE_CRAFTING_TABLE_ITEM);
            event.accept(TIER_ONE_UPGRADE_TEMPLATE);
            event.accept(TIER_TWO_UPGRADE_TEMPLATE);
            event.accept(TIER_THREE_UPGRADE_TEMPLATE);
        }
    }
}
