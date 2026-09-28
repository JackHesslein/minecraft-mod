package com.jackhesslein.mcreimagined;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;

public final class TierOneCraftingTableMenu extends CraftingMenu {
    private final ContainerLevelAccess access;

    public TierOneCraftingTableMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(containerId, inventory, access);
        this.access = access;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, MinecraftReimagined.TIER_ONE_CRAFTING_TABLE.get());
    }
}
