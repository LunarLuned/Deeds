package net.lunarluned.deeds.item.custom;

import net.lunarluned.deeds.common.registry.entity.BloodSpearEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;

import static net.lunarluned.deeds.common.registry.entity.ModSpear.BLOODSPEAR;

public class BloodSpearItem extends TridentItem {
    EntityType<? extends BloodSpearEntity> type;

    public BloodSpearItem(Properties properties, EntityType<? extends BloodSpearEntity> entityType) {
        super(properties);
        this.type = entityType;
    }

    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i) {
        if (livingEntity instanceof Player player) {
            if (!level.isClientSide) {
                itemStack.hurtAndBreak(1, player, (playerx) -> playerx.broadcastBreakEvent(livingEntity.getUsedItemHand()));
                BloodSpearEntity bloodSpear = new BloodSpearEntity(BLOODSPEAR, level);
                bloodSpear.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F * 0.5F, 1.0F);
                if (player.getAbilities().instabuild) {
                    bloodSpear.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }

                level.addFreshEntity(bloodSpear);
                level.playSound((Player) null, bloodSpear, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                if (!player.getAbilities().instabuild) {
                    player.getInventory().removeItem(itemStack);
                }
            }

            player.awardStat(Stats.ITEM_USED.get(this));
        }
        }

    public EntityType<? extends BloodSpearEntity> getEntityType() {
        return type;
    }
}
