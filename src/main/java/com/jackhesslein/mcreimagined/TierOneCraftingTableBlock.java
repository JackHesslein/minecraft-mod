package com.jackhesslein.mcreimagined;

import com.jackhesslein.mcreimagined.client.TierOneUpgradeParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;

public final class TierOneCraftingTableBlock extends CraftingTableBlock {
    public TierOneCraftingTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param) {
        if (id == 1) {
            if (level.isClientSide()) {
                TierOneUpgradeParticles.spawn(pos);
            }
            return true;
        }
        return super.triggerEvent(state, level, pos, id, param);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider(
                (containerId, inventory, player) -> new TierOneCraftingTableMenu(
                        containerId,
                        inventory,
                        ContainerLevelAccess.create(level, pos)
                ),
                Component.translatable("container.crafting")
        );
    }
}
