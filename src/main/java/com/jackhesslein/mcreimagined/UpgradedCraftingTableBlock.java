package com.jackhesslein.mcreimagined;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;

public final class UpgradedCraftingTableBlock extends CraftingTableBlock {
    public UpgradedCraftingTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider(
                (containerId, inventory, player) -> new UpgradedCraftingTableMenu(
                        containerId,
                        inventory,
                        ContainerLevelAccess.create(level, pos)
                ),
                Component.translatable("container.crafting")
        );
    }
}
