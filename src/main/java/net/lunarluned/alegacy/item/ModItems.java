package net.lunarluned.alegacy.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lunarluned.alegacy.ALegacy;
import net.lunarluned.alegacy.item.custom.ModPickaxeItem;
import net.lunarluned.alegacy.item.custom.NethercoreSwordItem;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {

    public static final Item NETHERCORE_INGOT = registerItem("nethercore_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.ALEGACY).fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item NETHERCORE_PICKAXE = registerItem("nethercore_pickaxe",
            new ModPickaxeItem(ModToolMaterials.NETHERCORE,2, -2.7f, new FabricItemSettings().group(ModItemGroup.ALEGACY).fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item NETHERCORE_SWORD = registerItem("nethercore_sword",
            new NethercoreSwordItem(ModToolMaterials.NETHERCORE,3, -2.4f, new FabricItemSettings().group(ModItemGroup.ALEGACY).fireproof().rarity(Rarity.UNCOMMON)));


    public static final Item NETHERCORE_HELMET = registerItem("nethercore_helmet",
            new ArmorItem(ModArmorMaterials.NETHERCORE, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.ALEGACY)));

    public static final Item NETHERCORE_CHESTPLATE = registerItem("nethercore_chestplate",
            new ArmorItem(ModArmorMaterials.NETHERCORE, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.ALEGACY)));

    public static final Item NETHERCORE_LEGGINGS = registerItem("nethercore_leggings",
            new ArmorItem(ModArmorMaterials.NETHERCORE, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.ALEGACY)));

    public static final Item NETHERCORE_BOOTS = registerItem("nethercore_boots",
            new ArmorItem(ModArmorMaterials.NETHERCORE, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.ALEGACY)));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new ResourceLocation(ALegacy.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ALegacy.LOGGER.info("Registering mod items for " + ALegacy.MOD_ID);
    }

}
