package net.lunarluned.deeds.item.custom.blood_devil;

import net.lunarluned.deeds.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class BloodSwordItem extends SwordItem {
    public BloodSwordItem(Tier tier, int i, float f, Properties properties) {
        super(tier, i, f, properties);
    }

    public boolean hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity2) {
        itemStack.hurtAndBreak(1, livingEntity2, (livingEntityx) -> {
            livingEntityx.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        livingEntity.addEffect(new MobEffectInstance(ModEffects.BLEEDING, 120, 0));
        return true;
    }

}
