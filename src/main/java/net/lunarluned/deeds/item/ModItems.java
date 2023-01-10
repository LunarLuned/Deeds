package net.lunarluned.deeds.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lunarluned.deeds.item.custom.FoxContractItem;
import net.lunarluned.deeds.Deeds;
import net.lunarluned.deeds.item.custom.ModPickaxeItem;
import net.lunarluned.deeds.item.custom.NethercoreSwordItem;
import net.lunarluned.deeds.item.custom.SrcSacItem;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {

    public static final Item NETHERCORE_INGOT = registerItem("nethercore_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.DEEDS).fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item NETHERCORE_PICKAXE = registerItem("nethercore_pickaxe",
            new ModPickaxeItem(ModToolMaterials.NETHERCORE,2, -2.7f, new FabricItemSettings().group(ModItemGroup.DEEDS).fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item NETHERCORE_SWORD = registerItem("nethercore_sword",
            new NethercoreSwordItem(ModToolMaterials.NETHERCORE,3, -2.4f, new FabricItemSettings().group(ModItemGroup.DEEDS).fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item FOX_DEVIL_CONTRACT = registerItem("fox_devil_contract",
            new FoxContractItem(new FabricItemSettings().group(ModItemGroup.DEEDS).fireproof()));

    public static final Item SRC_SAC = registerItem("src_sac",
            new SrcSacItem(new FabricItemSettings().group(ModItemGroup.DEEDS).fireproof()));

    public static final Item NETHERCORE_HELMET = registerItem("nethercore_helmet",
            new ArmorItem(ModArmorMaterials.NETHERCORE, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.DEEDS)));

    public static final Item NETHERCORE_CHESTPLATE = registerItem("nethercore_chestplate",
            new ArmorItem(ModArmorMaterials.NETHERCORE, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.DEEDS)));

    public static final Item NETHERCORE_LEGGINGS = registerItem("nethercore_leggings",
            new ArmorItem(ModArmorMaterials.NETHERCORE, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.DEEDS)));

    public static final Item NETHERCORE_BOOTS = registerItem("nethercore_boots",
            new ArmorItem(ModArmorMaterials.NETHERCORE, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.DEEDS)));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new ResourceLocation(Deeds.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Deeds.LOGGER.info("Registering mod items for " + Deeds.MOD_ID);
    }

}
