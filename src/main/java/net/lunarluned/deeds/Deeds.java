package net.lunarluned.deeds;

import net.fabricmc.api.ModInitializer;
import net.lunarluned.deeds.common.registry.entity.ModSpear;
import net.lunarluned.deeds.effect.ModEffects;
import net.lunarluned.deeds.item.ModItems;
import net.lunarluned.deeds.item.SpearItem;
import net.lunarluned.deeds.sound.ModSoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deeds implements ModInitializer {
	public static final String MOD_ID = "deeds";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModSoundEvents.registerSounds();
		ModEffects.registerEffects();
		ModSpear.init();
		SpearItem.init();


	}

	// Damage Sources

	public static class DemonicDamageSource extends DamageSource {

		public DemonicDamageSource() {
			super("demonic");
			bypassArmor();
			bypassEnchantments();

		}
	}
}
