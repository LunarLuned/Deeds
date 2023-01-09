package net.lunarluned.deeds.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.lunarluned.deeds.util.ContractData;
import net.lunarluned.deeds.util.IEntityDataSaver;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PrimaryC2SPacket {
    private static final String MESSAGE_FOX_DEVIL_ATTACK = "message.deeds.fox_devil";
    private static final String MESSAGE_NO_CONTRACT = "message.deeds.no_devil";

    public static void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler,
                               FriendlyByteBuf buf, PacketSender responseSender) {
        // Everything here happens ONLY on the Server!
        ServerLevel world = player.getLevel();
        //replace THIS with if the player has the fox devil. this is how we'll do the devil stuffs, all in one packet baybee
        if(isAroundWaterThem(player, world, 2)) {


            player.displayClientMessage(Component.translatable(MESSAGE_FOX_DEVIL_ATTACK)
                    .withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED)), true);

            // Play the sound
            world.playSound(null, player.getOnPos(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS,
                    0.5F, world.random.nextFloat() * 0.1F + 0.9F);
            LivingEntity livingEntity = player;
            double d = Math.min(livingEntity.getY(), player.getY());
            double e = Math.max(livingEntity.getY(), player.getY()) + 1.0;
            float f = (float)Mth.atan2(livingEntity.getZ() - player.getZ(), livingEntity.getX() - player.getX());
            int i = 1;
            float g = f + (float)i * 3.1415927F * 0.4F;
            EntityType.EVOKER_FANGS.spawn(((ServerLevel) player.level), null, null, player, livingEntity.getOnPos(), MobSpawnType.MOB_SUMMONED, true, false);
            ContractData.removeSRC((IEntityDataSaver) ((IEntityDataSaver) player).getPersistentData(), 1);
            player.displayClientMessage(Component.literal("SRC" + ((IEntityDataSaver) player).getPersistentData().getInt("src"))
                    .withStyle(Style.EMPTY.withColor(ChatFormatting.AQUA)), true);
        } else {
            // Notify the player
            player.displayClientMessage(Component.translatable(MESSAGE_NO_CONTRACT)
                    .withStyle(Style.EMPTY.withColor(ChatFormatting.RED)), true);

        }
    }

    private static boolean isAroundWaterThem(ServerPlayer player, ServerLevel world, int size) {
        return BlockPos.betweenClosedStream(player.getBoundingBox().inflate(size))
                .map(world::getBlockState).filter(state -> state.is(Blocks.WATER)).toArray().length > 0;
    }

    public static void FoxKon(double d, double e, double f, double g, float h, int i, ServerPlayer player) {
        BlockPos blockPos = new BlockPos(d, g, e);
        boolean bl = false;
        double j = 0.0;

        do {
            BlockPos blockPos2 = blockPos.below();
            BlockState blockState = player.level.getBlockState(blockPos2);
            if (blockState.isFaceSturdy(player.level, blockPos2, Direction.UP)) {
                if (!player.level.isEmptyBlock(blockPos)) {
                    BlockState blockState2 = player.level.getBlockState(blockPos);
                    VoxelShape voxelShape = blockState2.getCollisionShape(player.level, blockPos);
                    if (!voxelShape.isEmpty()) {
                        j = voxelShape.max(Direction.Axis.Y);
                    }
                }

                bl = true;
                break;
            }

            blockPos = blockPos.below();
        } while(blockPos.getY() >= Mth.floor(f) - 1);

        if (bl) {
            player.level.addFreshEntity(new EvokerFangs(player.level, d, (double)blockPos.getY() + j, e, h, i, player));
        }

    }
}
