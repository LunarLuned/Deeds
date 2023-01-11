package net.lunarluned.deeds.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.lunarluned.deeds.util.IEntityDataSaver;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public class SecondaryC2SPacket {
    private static final String MESSAGE_DEBUG = "message.deeds.debug";

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

        if (contract == 1) {
            player.displayClientMessage(Component.translatable(MESSAGE_DEBUG)
                    .withStyle(Style.EMPTY.withColor(ChatFormatting.WHITE)), true);
        }
    }
}