package net.lunarluned.deeds.item.custom.blood_devil;

import net.lunarluned.deeds.util.IEntityDataSaver;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class BottledBloodItem extends Item {

    public BottledBloodItem(Item.Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        CompoundTag nbt = ((IEntityDataSaver) livingEntity).getPersistentData();
        int contract = nbt.getInt("contract");

        if (contract == 2) {

            if (livingEntity instanceof Player && !((Player) livingEntity).getAbilities().instabuild) {
                itemStack.shrink(1);
            }

            if (!level.isClientSide) {
                livingEntity.removeAllEffects();
                livingEntity.addEffect(new MobEffectInstance(MobEffects.HEAL, 10, 1));
            }
        } else if (contract == 0) {
            if (livingEntity instanceof Player player && player.getRandom().nextInt(100) <= 50) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0));
            }
        }
        return itemStack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : itemStack;
    }

    public int getUseDuration(ItemStack itemStack) {
        return 16;
    }

    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }
}
