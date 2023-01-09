package net.lunarluned.deeds.item.custom;

import net.lunarluned.deeds.sound.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NethercoreSwordItem extends SwordItem {
    public NethercoreSwordItem(Tier tier, int i, float f, Properties properties) {
        super(tier, i, f, properties);
    }

    public InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (!level.isClientSide) {

            BlockPos blockPos = player.getOnPos();
            int m;
            int l;
            int k = blockPos.getX();
            int j = 6;

            // Scans the area for nearby players

            AABB aABB = new AABB(k, l = blockPos.getY(), m = blockPos.getZ(), k + 1, l + 1, m + 1).inflate(j).expandTowards(0.0, level.getHeight(), 0.0);
            List<LivingEntity> nearbyEntities = level.getEntitiesOfClass(LivingEntity.class, aABB);

            for (LivingEntity livingEntity : nearbyEntities) {
                livingEntity.setSecondsOnFire(4);
            }
            player.getItemInHand(interactionHand).hurtAndBreak(1, player, p -> p.broadcastBreakEvent(interactionHand));
            player.getCooldowns().addCooldown(this, 60);
            level.playSound(null, player.getOnPos().getX(), player.getOnPos().getY(), player.getOnPos().getZ(), ModSoundEvents.ITEM_NETHERCORE_SWORD_SWIRL, SoundSource.NEUTRAL, 1, 1);

            if (player.level instanceof ServerLevel) {
                for (double x = -6; x <= 6; x = x + 1) {
                    double y = Math.sqrt(36 - x * x);
                    ((ServerLevel) player.level).sendParticles(ParticleTypes.FLAME, player.getX() + x, player.getY(0.5D), player.getZ() + y, 0, 1, 0.0D, 1, 0.0D);
                    ((ServerLevel) player.level).sendParticles(ParticleTypes.SWEEP_ATTACK, player.getX() + x, player.getY(0.5D), player.getZ() + y, 0, 1, 0.0D, 1, 0.0D);
                    ((ServerLevel) player.level).sendParticles(ParticleTypes.FLAME, player.getX() + x, player.getY(0.5D), player.getZ() - y, 0, 1, 0.0D, 1, 0.0D);
                    ((ServerLevel) player.level).sendParticles(ParticleTypes.SWEEP_ATTACK, player.getX() + x, player.getY(0.5D), player.getZ() - y, 0, 1, 0.0D, 1, 0.0D);
                }
            }

        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

}
