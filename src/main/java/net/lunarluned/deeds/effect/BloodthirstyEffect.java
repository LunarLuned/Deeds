package net.lunarluned.deeds.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class BloodthirstyEffect extends MobEffect {
    protected BloodthirstyEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
   /*/     if (pLivingEntity instanceof Player player && player.getRandom().nextInt(100) <= 50) {

            LivingEntity livingEntity = LivingEntity;
            if (player.doHurtTarget(livingEntity))
        }/*/
    }

}
