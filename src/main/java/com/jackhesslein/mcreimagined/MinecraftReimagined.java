package com.jackhesslein.mcreimagined;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(MinecraftReimagined.MOD_ID)
public final class MinecraftReimagined {
    public static final String MOD_ID = "minecraftreimagined";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MinecraftReimagined() {
        LOGGER.info("MinecraftReimagined initialized");
    }
}
