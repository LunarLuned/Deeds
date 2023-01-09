package net.lunarluned.deeds.sound;

import net.lunarluned.deeds.Deeds;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {

    public static final SoundEvent ITEM_NETHERCORE_SWORD_SWIRL = registerSoundEvent("item.nethercore_sword.swirl");

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(Deeds.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds() {
        System.out.println("Registering Sounds for " + Deeds.MOD_ID);
    }
}
