package com.jackhesslein.mcreimagined.progression;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData; 

public class ProgressionData extends SavedData {
    private static final String TIER_KEY = "tier";

    private static final SavedData.Factory<ProgressionData> FACTORY =
        new SavedData.Factory<>(ProgressionData::new, ProgressionData::load, null);

    private int tier = 0;


    public int getTier() {
        return tier;
    }

    public void setTier(int newTier) {
        this.tier = newTier;
        setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt(TIER_KEY, tier);
        return tag;
    }

    public static ProgressionData load(CompoundTag tag, HolderLookup.Provider registries) {
        ProgressionData data = new ProgressionData();
        data.tier = tag.getInt(TIER_KEY);
        return data;
    }

    public static ProgressionData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(FACTORY, "minecraftreimagined_progression");
    }

}
