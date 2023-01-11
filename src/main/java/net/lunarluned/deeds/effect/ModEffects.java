package net.lunarluned.deeds.effect;

import net.lunarluned.deeds.Deeds;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {

    public static MobEffect STUNNED;
    public static MobEffect BLEEDING;
    public static MobEffect STAGNATED;


    public static MobEffect registerStunnedStatusEffect(String name) {
        return Registry.register(Registry.MOB_EFFECT, new ResourceLocation(Deeds.MOD_ID, name), new StunnedEffect(MobEffectCategory.HARMFUL, 	12703983));
    }

    public static MobEffect registerBleedingStatusEffect(String name) {
        return Registry.register(Registry.MOB_EFFECT, new ResourceLocation(Deeds.MOD_ID, name), new BleedingEffect(MobEffectCategory.HARMFUL, 	16744576));
    }

    public static MobEffect registerStagnatedStatusEffect(String name) {
        return Registry.register(Registry.MOB_EFFECT, new ResourceLocation(Deeds.MOD_ID, name), new StagnatedEffect(MobEffectCategory.HARMFUL, 9426908));
    }


    public static void registerEffects() {
        STUNNED = registerStunnedStatusEffect("stunned");
        BLEEDING = registerBleedingStatusEffect("bleeding");
        STAGNATED = registerStagnatedStatusEffect("stagnated");

    }
}
