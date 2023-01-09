package net.lunarluned.deeds.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class PrimaryC2SPacket {
    private static final String MESSAGE_FOX_DEVIL_ATTACK = "message.deeds.fox_devil";
    private static final String MESSAGE_NO_CONTRACT = "message.deeds.no_devil";

    public static void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler,
                               FriendlyByteBuf buf, PacketSender responseSender) {
        // Everything here happens ONLY on the Server!
        ServerLevel world = player.getLevel();
        //replace THIS with if the player has the fox devil. this is how we'll do the devil stuffs, all in one packet baybee
        CompoundTag nbt = ((IEntityDataSaver) player).getPersistentData();
        int src = nbt.getInt("src");
        nbt = ((IEntityDataSaver) player).getPersistentData();
        int contract = nbt.getInt("contract");

        if(contract == 1) {
            if(src >= 3) {

                player.displayClientMessage(Component.translatable(MESSAGE_FOX_DEVIL_ATTACK)
                        .withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED)), true);

                // Play the sound
                world.playSound(null, player.getOnPos(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS,
                        0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                Minecraft client = Minecraft.getInstance();
                ContractData.removeSRC(((IEntityDataSaver) player), 1);

                BlockPos blockPos = player.getOnPos();
                int m;
                int l;
                int k = blockPos.getX();
                int j = 8;

                AABB aABB = new AABB(k, l = blockPos.getY(), m = blockPos.getZ(), k + 1, l + 1, m + 1).inflate(j).expandTowards(0.0, world.getHeight(), 0.0);
                List<LivingEntity> nearbyEntities = world.getEntitiesOfClass(LivingEntity.class, aABB);

                for (LivingEntity livingEntities : nearbyEntities) {
                    EntityType.EVOKER_FANGS.spawn(((ServerLevel) player.level), null, null, player, livingEntities.blockPosition(), MobSpawnType.MOB_SUMMONED, true, false);
                }
            }


        } else {

            ContractData.syncSRC(((IEntityDataSaver) player).getPersistentData().getInt("src"), player);

        }
    }

    private static boolean isAroundWaterThem(ServerPlayer player, ServerLevel world, int size) {
        return BlockPos.betweenClosedStream(player.getBoundingBox().inflate(size))
                .map(world::getBlockState).filter(state -> state.is(Blocks.WATER)).toArray().length > 0;
    }

}
