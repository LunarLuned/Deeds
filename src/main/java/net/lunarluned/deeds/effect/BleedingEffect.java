package net.lunarluned.deeds.effect;

import net.lunarluned.deeds.Deeds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class BleedingEffect extends MobEffect {
    public BleedingEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player && player.getRandom().nextInt(100) <= 5) {
            if (player.getHealth() >= 4) {
                player.level.playSound(null, player.getOnPos(), SoundEvents.BOAT_PADDLE_WATER, SoundSource.PLAYERS, 1.0F, 1.0F);
                entity.hurt(new Deeds.DemonicDamageSource(), 1);
                super.applyEffectTick(entity, amplifier);
            }
            if (player.getHealth() <= 5) {
                if (player.getRandom().nextInt(100) <= 32 && !player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) || !player.hasEffect(ModEffects.STAGNATED)) {
                    player.addEffect(new MobEffectInstance(ModEffects.STAGNATED, 120, 0));
                } else if (player.getRandom().nextInt(100) >= 72 && !player.hasEffect(ModEffects.STAGNATED) || !player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) ) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140, 0));
                }
            }
        } else if (!(entity instanceof Player)) {
            entity.hurt(new Deeds.DemonicDamageSource(), 1);
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140, 0));
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

}
