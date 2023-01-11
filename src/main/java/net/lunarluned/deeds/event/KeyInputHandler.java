package net.lunarluned.deeds.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.lunarluned.deeds.networking.ModMessages;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_DEVILS = "key.category.devils";
    public static final String KEY_DEVIL_PRIMARY_ABILITY = "key.deeds.devil_primary";
    public static final String KEY_DEVIL_SECONDARY_ABILITY = "key.deeds.devil_secondary";

    public static KeyMapping devilPrimary;
    public static KeyMapping devilSecondary;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(devilPrimary.consumeClick()) {
                ClientPlayNetworking.send(ModMessages.DEVIL_PRIMARY_ID, PacketByteBufs.create());
            }
            if(devilSecondary.consumeClick()) {
                ClientPlayNetworking.send(ModMessages.DEVIL_SECONDARY_ID, PacketByteBufs.create());
            }
        });
    }

    public static void register() {
        devilPrimary = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                KEY_DEVIL_PRIMARY_ABILITY,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_DEVILS
        ));
        devilSecondary = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                KEY_DEVIL_SECONDARY_ABILITY,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                KEY_CATEGORY_DEVILS
        ));
        registerKeyInputs();
    }
}
