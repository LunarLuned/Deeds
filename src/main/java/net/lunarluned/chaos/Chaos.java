package net.lunarluned.chaos;

import net.fabricmc.api.ModInitializer;
import net.lunarluned.chaos.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chaos implements ModInitializer {
	public static final String MOD_ID = "chaos";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}
