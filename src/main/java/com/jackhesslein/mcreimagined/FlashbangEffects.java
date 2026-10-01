package com.jackhesslein.mcreimagined;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

public final class FlashbangEffects {
    public static final double RADIUS_SQUARED = 25.0;

    private FlashbangEffects() {
    }

    public static void flash(ServerLevel level, Vec3 pos, RandomSource random, boolean witherSkull) {
        for (int i = 0; i < 50; i++) {
            double x, y, z;
            do {
                x = (random.nextDouble() * 2.0 - 1.0) * 5.0;
                y = (random.nextDouble() * 2.0 - 1.0) * 5.0;
                z = (random.nextDouble() * 2.0 - 1.0) * 5.0;
            } while (x * x + y * y + z * z > RADIUS_SQUARED);
            level.sendParticles(ParticleTypes.FLASH, pos.x + x, pos.y + y, pos.z + z,
                    1, 0.0, 0.0, 0.0, 0.0);
            level.sendParticles(ParticleTypes.END_ROD, pos.x + x, pos.y + y, pos.z + z,
                    1, 0.0, 0.0, 0.0, 0.0);
        }

        if (!witherSkull) {
            level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.GENERIC_EXPLODE,
                    SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        for (ServerPlayer player : level.players()) {
            if (player.distanceToSqr(pos) <= RADIUS_SQUARED) {
                PacketDistributor.sendToPlayer(player, new FlashbangFlashPayload(witherSkull));
            }
        }
    }
}
