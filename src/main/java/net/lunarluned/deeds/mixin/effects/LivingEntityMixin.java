package net.lunarluned.deeds.mixin.effects;

import net.lunarluned.deeds.effect.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow @Nullable public abstract MobEffectInstance getEffect(MobEffect mobEffect);

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }


    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    private void heal(CallbackInfo ci) {
        if(this.getEffect(ModEffects.STAGNATED) != null) {
            ci.cancel();
        }
    }
}
