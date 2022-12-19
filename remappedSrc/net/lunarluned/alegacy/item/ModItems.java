package net.lunarluned.alegacy.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lunarluned.alegacy.ALegacy;
import net.lunarluned.alegacy.item.custom.ModPickaxeItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item NETHERCORE_INGOT = registerItem("nethercore_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.ALEGACY).fireproof().rarity(Rarity.UNCOMMON).maxCount(64)));
    public static final Item NETHERCORE_PICKAXE = registerItem("nethercore_pickaxe",
            new ModPickaxeItem(new FabricItemSettings().group(ModItemGroup.ALEGACY).fireproof().rarity(Rarity.UNCOMMON).maxCount(64)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ALegacy.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ALegacy.LOGGER.info("Registering mod items for " + ALegacy.MOD_ID);
    }

}
