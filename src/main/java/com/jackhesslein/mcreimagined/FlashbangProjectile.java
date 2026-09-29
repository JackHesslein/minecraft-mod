package com.jackhesslein.mcreimagined;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.EntityHitResult;

public final class FlashbangProjectile extends Snowball {
    private static final float IMPACT_DAMAGE = 0.1F;

    public FlashbangProjectile(Level level, LivingEntity owner) {
        super(level, owner);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!level().isClientSide() && !(result.getEntity() instanceof ServerPlayer)) {
            result.getEntity().hurt(damageSources().thrown(this, getOwner()), IMPACT_DAMAGE);
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if (level() instanceof ServerLevel serverLevel) {
            FlashbangEffects.flash(serverLevel, result.getLocation(), random, false);
            for (ServerPlayer player : serverLevel.players()) {
                if (player.distanceToSqr(result.getLocation()) <= FlashbangEffects.RADIUS_SQUARED) {
                    player.hurt(damageSources().thrown(this, getOwner()), IMPACT_DAMAGE);
                }
            }
            discard();
        }
    }
}
