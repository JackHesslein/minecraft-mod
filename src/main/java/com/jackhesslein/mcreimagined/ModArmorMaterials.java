package com.jackhesslein.mcreimagined;

import java.util.EnumMap;
import java.util.List;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModArmorMaterials {
    private static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, MinecraftReimagined.MOD_ID);

    // No armor layers: the worn look is the 3D item model drawn by ScubaDiverHelmetLayer.
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SCUBA_DIVER = ARMOR_MATERIALS.register(
            "scuba_diver",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), defense -> {
                        defense.put(ArmorItem.Type.BOOTS, 1);
                        defense.put(ArmorItem.Type.LEGGINGS, 2);
                        defense.put(ArmorItem.Type.CHESTPLATE, 3);
                        defense.put(ArmorItem.Type.HELMET, 2);
                        defense.put(ArmorItem.Type.BODY, 3);
                    }),
                    9,
                    SoundEvents.ARMOR_EQUIP_IRON,
                    () -> Ingredient.of(Items.COPPER_INGOT),
                    List.of(),
                    0.0F,
                    0.0F
            )
    );

    private ModArmorMaterials() {
    }

    public static void register(IEventBus modEventBus) {
        ARMOR_MATERIALS.register(modEventBus);
    }
}
