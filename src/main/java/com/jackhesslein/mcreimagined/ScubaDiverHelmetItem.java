package com.jackhesslein.mcreimagined;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;

public final class ScubaDiverHelmetItem extends ArmorItem {
    public ScubaDiverHelmetItem(Properties properties) {
        super(ModArmorMaterials.SCUBA_DIVER, Type.HELMET, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        // Runs on both sides so the client's air bubbles stay full instead of flickering.
        if (entity instanceof LivingEntity wearer
                && wearer.getItemBySlot(EquipmentSlot.HEAD) == stack
                && wearer.isEyeInFluidType(NeoForgeMod.WATER_TYPE.value())) {
            wearer.setAirSupply(wearer.getMaxAirSupply());
        }
    }
}
