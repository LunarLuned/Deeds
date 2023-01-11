package net.lunarluned.deeds.effect;

import net.lunarluned.deeds.Deeds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class BleedingEffect extends MobEffect {
    protected BleedingEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Player player && player.getRandom().nextInt(3) <= 3) {

            player.hurt(new Deeds.DemonicDamageSource(), 1);
            player.level.playSound(null, player.getOnPos(), SoundEvents.BOAT_PADDLE_WATER, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

}
