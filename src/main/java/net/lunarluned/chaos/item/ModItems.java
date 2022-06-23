package net.lunarluned.chaos.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lunarluned.chaos.Chaos;
import net.lunarluned.chaos.item.custom.ChaosEmeraldItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item EMPTY_CHAOS_EMERALD = registerItem("empty_chaos_emerald",
            new ChaosEmeraldItem(new FabricItemSettings().group(ModItemGroup.CHAOS).fireproof().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item GREEN_CHAOS_EMERALD = registerItem("green_chaos_emerald",
            new ChaosEmeraldItem(new FabricItemSettings().group(ModItemGroup.CHAOS).fireproof().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item BLUE_CHAOS_EMERALD = registerItem("blue_chaos_emerald",
            new ChaosEmeraldItem(new FabricItemSettings().group(ModItemGroup.CHAOS).fireproof().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item LIGHT_BLUE_CHAOS_EMERALD = registerItem("light_blue_chaos_emerald",
            new ChaosEmeraldItem(new FabricItemSettings().group(ModItemGroup.CHAOS).fireproof().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item PURPLE_CHAOS_EMERALD = registerItem("purple_chaos_emerald",
            new ChaosEmeraldItem(new FabricItemSettings().group(ModItemGroup.CHAOS).fireproof().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item RED_CHAOS_EMERALD = registerItem("red_chaos_emerald",
            new ChaosEmeraldItem(new FabricItemSettings().group(ModItemGroup.CHAOS).fireproof().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item YELLOW_CHAOS_EMERALD = registerItem("yellow_chaos_emerald",
            new ChaosEmeraldItem(new FabricItemSettings().group(ModItemGroup.CHAOS).fireproof().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item WHITE_CHAOS_EMERALD = registerItem("white_chaos_emerald",
            new ChaosEmeraldItem(new FabricItemSettings().group(ModItemGroup.CHAOS).fireproof().rarity(Rarity.EPIC).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Chaos.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Chaos.LOGGER.info("Registering mod items for " + Chaos.MOD_ID);
    }

}
