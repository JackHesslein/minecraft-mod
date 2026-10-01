package com.jackhesslein.mcreimagined;

import org.slf4j.Logger;

import com.jackhesslein.mcreimagined.item.ModItems;
import com.jackhesslein.mcreimagined.progression.ProgressionCommand;
import com.mojang.logging.LogUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(MinecraftReimagined.MOD_ID)
public final class MinecraftReimagined {
    public static final String MOD_ID = "minecraftreimagined";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
    private static final DeferredRegister.Items ITEMS = ModItems.ITEMS;
    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, MOD_ID);
    public static final DeferredHolder<SoundEvent, SoundEvent> FLASHBANG_RINGING = SOUNDS.register(
            "flashbang_ringing",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MOD_ID, "flashbang_ringing"))
    );
    public static final DeferredHolder<SoundEvent, SoundEvent> WITHER_FLASH_RINGING = SOUNDS.register(
            "wither_flash_ringing",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MOD_ID, "wither_flash_ringing"))
    );

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
    public static final DeferredItem<FlashbangItem> FLASHBANG = ITEMS.register(
            "flashbang",
            () -> new FlashbangItem(new Item.Properties().stacksTo(16))
    );

    public MinecraftReimagined(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ModItems.register(modEventBus);
        SOUNDS.register(modEventBus);
        modEventBus.addListener(this::addCreativeTabItems);
        modEventBus.addListener(this::registerPayloads);
        NeoForge.EVENT_BUS.addListener(this::upgradeCraftingTable);
        NeoForge.EVENT_BUS.addListener(this::flashWitherSkull);
        NeoForge.EVENT_BUS.addListener(ProgressionCommand::onCommandRegister);
        LOGGER.info("MinecraftReimagined initialized");
    }

    private void flashWitherSkull(ProjectileImpactEvent event) {
        if (event.getProjectile() instanceof WitherSkull skull
                && skull.getOwner() instanceof WitherBoss
                && skull.level() instanceof ServerLevel level
                && event.getRayTraceResult().getType() != HitResult.Type.MISS
                && level.getRandom().nextFloat() < 0.15F) {
            // The skull's normal hit and explosion still happen after this event.
            FlashbangEffects.flash(level, event.getRayTraceResult().getLocation(), level.getRandom(), true);
        }
    }

    private void registerPayloads(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToClient(FlashbangFlashPayload.TYPE, FlashbangFlashPayload.STREAM_CODEC,
                (payload, context) -> com.jackhesslein.mcreimagined.client.FlashbangOverlay.flash(payload.witherSkull()));
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
        ParticleOptions upgradeParticle = null;
        if (stack.is(TIER_ONE_UPGRADE_TEMPLATE.get()) && current == Blocks.CRAFTING_TABLE) {
            upgrade = TIER_ONE_CRAFTING_TABLE.get();
        } else if (stack.is(TIER_TWO_UPGRADE_TEMPLATE.get()) && current == TIER_ONE_CRAFTING_TABLE.get()) {
            upgrade = TIER_TWO_CRAFTING_TABLE.get();
            upgradeParticle = ParticleTypes.FLAME;
        } else if (stack.is(TIER_THREE_UPGRADE_TEMPLATE.get()) && current == TIER_TWO_CRAFTING_TABLE.get()) {
            upgrade = TIER_THREE_CRAFTING_TABLE.get();
            upgradeParticle = ParticleTypes.DRAGON_BREATH;
        }

        if (upgrade == null) {
            return;
        }

        if (!level.isClientSide()) {
            level.setBlock(pos, upgrade.defaultBlockState(), 3);
            if (upgrade == TIER_ONE_CRAFTING_TABLE.get()) {
                level.blockEvent(pos, upgrade, 1, 0);
            } else {
                ((ServerLevel) level).sendParticles(upgradeParticle, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                        24, 0.65, 0.35, 0.65, 0.02);
            }
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
        } else if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(FLASHBANG);
        }
    }
}
