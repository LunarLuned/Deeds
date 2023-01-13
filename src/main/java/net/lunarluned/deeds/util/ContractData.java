package net.lunarluned.deeds.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.lunarluned.deeds.networking.ModMessages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class ContractData {

    public static int setContracttoFox(IEntityDataSaver player, int amount) {
        CompoundTag nbt = player.getPersistentData();
        int contract = nbt.getInt("contract");
        if(contract + amount != 1){
            contract = 1;
        }
        nbt.putInt("contract", contract);
        syncContract(contract, (ServerPlayer) player);
        return contract;
    }

    public static int setContracttoBlood(IEntityDataSaver player, int amount) {
        CompoundTag nbt = player.getPersistentData();
        int contract = nbt.getInt("contract");
        if(contract + amount != 2){
            contract = 2;
        }

        nbt.putInt("contract", contract);
        syncContract(contract, (ServerPlayer) player);
        return contract;
    }

    public static int removeContract(IEntityDataSaver player, int amount) {
        CompoundTag nbt = player.getPersistentData();
        int contract = nbt.getInt("contract");
        if(contract + amount >= 1){
            contract = 0;
        }
        syncContract(contract, (ServerPlayer) player);
        return contract;
    }

    public static int addSRC(IEntityDataSaver player, int amount) {
        CompoundTag nbt = player.getPersistentData();
        int src = nbt.getInt("src");
        if(src + amount >= 10){
            src = 10;
        } else {
            src += amount;
        }

        nbt.putInt("src", src);
        syncSRC(src, (ServerPlayer) player);
        return src;

    }
    public static int removeSRC(IEntityDataSaver player, int amount) {
        CompoundTag nbt = player.getPersistentData();
        int src = nbt.getInt("src");
        if(src - amount < 0){
            src = 0;
        } else {
            src -= amount;
        }

        nbt.putInt("src", src);
        syncSRC(src, (ServerPlayer) player);
        return src;

    }

    public static void syncSRC(int src, ServerPlayer player) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(src);
        ServerPlayNetworking.send(player, ModMessages.SRC_SYNC_ID, buffer);
    }
    public static void syncContract(int contract, ServerPlayer player) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(contract);
        ServerPlayNetworking.send(player, ModMessages.CONTRACT_SYNC_ID, buffer);
    }
}
