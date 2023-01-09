package net.lunarluned.deeds;

import net.fabricmc.api.ModInitializer;
import net.lunarluned.deeds.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ALegacy implements ModInitializer {
	public static final String MOD_ID = "alegacy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}
