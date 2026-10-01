package com.jackhesslein.mcreimagined.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;

public final class TierOneUpgradeParticles {
    private TierOneUpgradeParticles() {
    }

    public static void spawn(BlockPos pos) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) {
            return;
        }

        RandomSource random = minecraft.level.random;
        for (int i = 0; i < 24; i++) {
            double x = pos.getX() + 0.5 + random.nextGaussian() * 0.65;
            double y = pos.getY() + 1.0 + random.nextGaussian() * 0.35;
            double z = pos.getZ() + 0.5 + random.nextGaussian() * 0.65;
            Particle particle = minecraft.particleEngine.createParticle(ParticleTypes.ENCHANT, x, y, z,
                    random.nextGaussian() * 0.02, random.nextGaussian() * 0.02, random.nextGaussian() * 0.02);
            if (particle != null) {
                particle.scale(2.0F);
            }
        }
    }
}
