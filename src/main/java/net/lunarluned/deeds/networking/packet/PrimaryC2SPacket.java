package net.lunarluned.deeds.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.lunarluned.deeds.Deeds;
import net.lunarluned.deeds.effect.ModEffects;
import net.lunarluned.deeds.item.ModItems;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class PrimaryC2SPacket {
    private static final String MESSAGE_FOX_DEVIL_ATTACK = "message.deeds.fox_devil";

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
            if(src >= 4) {

                if (!player.hasEffect(ModEffects.STAGNATED)) {


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

                        if (!(livingEntities == player)) {
                            //this way itll only activate the ability and use the SRC if any entities are found around
                            player.displayClientMessage(Component.translatable(MESSAGE_FOX_DEVIL_ATTACK)
                                    .withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED)), true);
                            world.playSound(null, player.getOnPos(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS,
                                    0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                            ContractData.removeSRC(((IEntityDataSaver) player), 4);

                            livingEntities.hurt(DamageSource.MAGIC, 6);
                            livingEntities.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 190, 1, true, true));
                            livingEntities.addEffect(new MobEffectInstance(ModEffects.STUNNED, 190, 0, true, true));
                            EntityType.EVOKER_FANGS.spawn(((ServerLevel) player.level), null, null, player, livingEntities.blockPosition(), MobSpawnType.MOB_SUMMONED, true, false);

                        }
                        player.hurt(new Deeds.DemonicDamageSource(), 2.0F);
                        player.addEffect(new MobEffectInstance(ModEffects.STAGNATED, 450, 0));
                    }
                }
            }
            if (src <= 3) {
                player.displayClientMessage(Component.translatable(MESSAGE_LOW_SRC)
                        .withStyle(Style.EMPTY.withColor(ChatFormatting.AQUA)), true);
            }


        }

        // BLOOD DEVIL CODE

        if(contract == 2) {
            if(src >= 4) {

                if (player.getRandom().nextInt(3) == 1) {
                    player.addItem(new ItemStack(ModItems.BLOOD_SWORD));
                    ContractData.removeSRC(((IEntityDataSaver) player), 4);
                    world.playSound(null, player.getOnPos(), SoundEvents.HOSTILE_HURT, SoundSource.PLAYERS,
                            0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                    player.hurt(new Deeds.DemonicDamageSource(), 2);
                } else if (player.getRandom().nextInt(3) == 2) {
                    //replace with Blood Hammer
                    player.addItem(new ItemStack(ModItems.BLOOD_HAMMER));
                    ContractData.removeSRC(((IEntityDataSaver) player), 4);
                    world.playSound(null, player.getOnPos(), SoundEvents.HOSTILE_HURT, SoundSource.PLAYERS,
                            0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                    player.hurt(new Deeds.DemonicDamageSource(), 2);
                } else if (player.getRandom().nextInt(3) == 3) {
                    //replace with Blood Spear
                    player.addItem(new ItemStack(ModItems.BOTTLED_BLOOD));
                    world.playSound(null, player.getOnPos(), SoundEvents.HOSTILE_HURT, SoundSource.PLAYERS,
                            0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                    ContractData.removeSRC(((IEntityDataSaver) player), 4);
                    player.hurt(new Deeds.DemonicDamageSource(), 2);
                }


            }
            if (src <= 3) {
                player.displayClientMessage(Component.translatable(MESSAGE_LOW_SRC)
                        .withStyle(Style.EMPTY.withColor(ChatFormatting.AQUA)), true);
            }


        }
        }
    }
