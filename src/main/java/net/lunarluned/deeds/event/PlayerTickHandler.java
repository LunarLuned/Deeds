package net.lunarluned.deeds.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.lunarluned.deeds.util.ContractData;
import net.lunarluned.deeds.util.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Random;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (new Random().nextFloat() <= 0.001f) {
                IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
                ContractData.addSRC(dataPlayer, 1);
            }
            IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
            CompoundTag nbt = dataPlayer.getPersistentData();
            int contract = nbt.getInt("contract");
            if (contract == 2 && player.getMaxHealth() == 20) {
                (player.getAttribute(Attributes.MAX_HEALTH)).addTransientModifier(new AttributeModifier("blooddevilhealth", -2, AttributeModifier.Operation.ADDITION));
            }

        }
    }
}
