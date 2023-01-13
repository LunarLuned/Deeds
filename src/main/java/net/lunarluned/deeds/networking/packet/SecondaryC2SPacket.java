package net.lunarluned.deeds.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.lunarluned.deeds.effect.ModEffects;
import net.lunarluned.deeds.util.ContractData;
import net.lunarluned.deeds.util.IEntityDataSaver;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class SecondaryC2SPacket {
    private static final String MESSAGE_DEBUG = "message.deeds.debug";

    private static final String MESSAGE_FOX_DEVIL_SWALLOW_ATTACK = "message.deeds.fox_devil_secondary";
    private static final String MESSAGE_NO_CONTRACT = "message.deeds.no_devil";
    private static final String MESSAGE_LOW_SRC = "message.deeds.low_src";

    public static void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler,
                               FriendlyByteBuf buf, PacketSender responseSender) {
        // Everything here happens ONLY on the Server!
        ServerLevel world = player.getLevel();

        CompoundTag nbt = ((IEntityDataSaver) player).getPersistentData();
        int src = nbt.getInt("src");
        nbt = ((IEntityDataSaver) player).getPersistentData();
        int contract = nbt.getInt("contract");

        if ((contract == 0) && (src <= 11)) {

            ContractData.syncSRC(((IEntityDataSaver) player).getPersistentData().getInt("src"), player);
            player.displayClientMessage(Component.translatable(MESSAGE_NO_CONTRACT)
                    .withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED)), true);
        }

        //FOX DEVIL CODE

        if(contract == 1) {
            if(src >= 8) {



                    // Play the sound
                    Minecraft client = Minecraft.getInstance();

                    BlockPos blockPos = player.getOnPos();
                    int m;
                    int l;
                    int k = blockPos.getX();
                    int j = 8;

                    AABB aABB = new AABB(k, l = blockPos.getY(), m = blockPos.getZ(), k + 1, l + 1, m + 1).inflate(j).expandTowards(0.0, world.getHeight(), 0.0);
                    List<LivingEntity> nearbyEntities = world.getEntitiesOfClass(LivingEntity.class, aABB);

                    for (LivingEntity livingEntities : nearbyEntities) {

                        if (!(livingEntities == player) && livingEntities.hasEffect(ModEffects.STUNNED)) {
                            //this way itll only activate the ability and use the SRC if any entities are found around
                            livingEntities.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 3, true, true));
                            ContractData.removeSRC(((IEntityDataSaver) player), 8);
                            world.playSound(null, player.getOnPos(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS,
                                    0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                            player.displayClientMessage(Component.translatable(MESSAGE_FOX_DEVIL_SWALLOW_ATTACK)
                                    .withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED)), true);
                            player.addEffect(new MobEffectInstance(ModEffects.BLEEDING, 200, 0));
                            player.addEffect(new MobEffectInstance(ModEffects.STAGNATED, 450, 0));

                            livingEntities.hurt(DamageSource.MAGIC, 12);
                            EntityType.EXPERIENCE_ORB.spawn(((ServerLevel) player.level), null, null, player, livingEntities.blockPosition(), MobSpawnType.MOB_SUMMONED, true, false);
                            EntityType.EXPERIENCE_ORB.spawn(((ServerLevel) player.level), null, null, player, livingEntities.blockPosition(), MobSpawnType.MOB_SUMMONED, true, false);
                            EntityType.EXPERIENCE_ORB.spawn(((ServerLevel) player.level), null, null, player, livingEntities.blockPosition(), MobSpawnType.MOB_SUMMONED, true, false);
                        }
                }
            }
            if (src <= 7) {
                player.displayClientMessage(Component.translatable(MESSAGE_LOW_SRC)
                        .withStyle(Style.EMPTY.withColor(ChatFormatting.AQUA)), true);
            }


        }
    }
}