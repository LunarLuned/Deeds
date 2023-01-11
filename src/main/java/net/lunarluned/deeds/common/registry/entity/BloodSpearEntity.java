package net.lunarluned.deeds.common.registry.entity;

import net.lunarluned.deeds.effect.ModEffects;
import net.lunarluned.deeds.mixin.TridentEntityAccessor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class BloodSpearEntity extends ThrownTrident {
    private ItemStack SpearItem;

    public BloodSpearEntity(EntityType<? extends BloodSpearEntity> entityType, Level level) {
        super(entityType, level);
        //fill in, replace with blood spear
        this.SpearItem = new ItemStack(net.lunarluned.deeds.item.SpearItem.BLOOD_SPEAR);
    }



    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity target) {
        super.doPostHurtEffects(target);
        target.addEffect(new MobEffectInstance(ModEffects.BLEEDING, 10, 1));
    }

    public void setSpearAttributes(Level world, LivingEntity owner, ItemStack stack) {
        this.setTridentStack(stack.copy());
    }

    protected float getWaterInertia() {
        return 0.01f;
    }

    public ItemStack getTridentItem() {
        return ((TridentEntityAccessor) this).deeds$getTridentStack();
    }

    public void setTridentStack(ItemStack tridentStack) {
        ((TridentEntityAccessor) this).deeds$setTridentStack(tridentStack);
    }

    protected void setDealtDamage(boolean dealtDamage) {
        ((TridentEntityAccessor) this).deeds$setDealtDamage(dealtDamage);
    }

    protected boolean hasDealtDamage() {
        return ((TridentEntityAccessor) this).deeds$hasDealtDamage();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        float f = 10.0F;
        if (entity instanceof LivingEntity livingEntity) {
            f += EnchantmentHelper.getDamageBonus(this.SpearItem, livingEntity.getMobType());
        }
        Entity entity2 = this.getOwner();
        DamageSource damageSource = DamageSource.trident(this, (Entity)(entity2 == null ? this : entity2));

        super.onHitEntity(entityHitResult);
    }
}
