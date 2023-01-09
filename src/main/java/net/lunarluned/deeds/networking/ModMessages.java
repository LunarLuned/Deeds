package net.lunarluned.deeds.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.lunarluned.deeds.Deeds;
import net.lunarluned.deeds.networking.packet.PrimaryC2SPacket;
import net.lunarluned.deeds.networking.packet.SrcSyncDataS2CPacket;
import net.minecraft.resources.ResourceLocation;

public class ModMessages {
    public static final ResourceLocation DEVIL_PRIMARY_ID = new ResourceLocation(Deeds.MOD_ID, "devil_primary");
    public static final ResourceLocation SRC_SYNC_ID = new ResourceLocation(Deeds.MOD_ID, "src_sync");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(DEVIL_PRIMARY_ID, PrimaryC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(SRC_SYNC_ID, SrcSyncDataS2CPacket::receive);

    }
}
