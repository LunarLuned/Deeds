package net.lunarluned.deeds;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.lunarluned.deeds.event.KeyInputHandler;
import net.lunarluned.deeds.event.PlayerTickHandler;
import net.lunarluned.deeds.networking.ModMessages;

public class DeedsClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        ModMessages.registerC2SPackets();
        ModMessages.registerS2CPackets();

        ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
    }

}
