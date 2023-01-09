package net.lunarluned.deeds.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.lunarluned.deeds.networking.ModMessages;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;

public class ClientPlayConnectionJoin implements ClientPlayConnectionEvents.Join {


    @Override
    public void onPlayReady(ClientPacketListener handler, PacketSender sender, Minecraft client) {
        ClientPlayNetworking.send(ModMessages.SRC_SYNC_ID, PacketByteBufs.create());
    }
}


