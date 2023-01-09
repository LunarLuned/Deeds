package net.lunarluned.deeds.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.lunarluned.deeds.util.ContractData;
import net.lunarluned.deeds.util.IEntityDataSaver;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.Random;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (new Random().nextFloat() <= 0.005f) {
                IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
                ContractData.addSRC(dataPlayer, 1);
                player.displayClientMessage(Component.literal("Added 1 SRC"), false);
            }
        }
    }
}
