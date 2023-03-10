package net.lunarluned.deeds.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lunarluned.deeds.Deeds;
import net.lunarluned.deeds.item.custom.*;
import net.lunarluned.deeds.item.custom.blood_devil.BloodHammerItem;
import net.lunarluned.deeds.item.custom.blood_devil.BloodSwordItem;
import net.lunarluned.deeds.item.custom.blood_devil.BottledBloodItem;
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



    public static final Item BLOOD_DEVIL_CONTRACT = registerItem("blood_devil_contract",
            new BloodContractItem(new FabricItemSettings().group(ModItemGroup.DEEDS).fireproof()));

    public static final Item BLOOD_SWORD = registerItem("blood_sword",
            new BloodSwordItem(ModToolMaterials.BLOOD,4, -2.4f, new FabricItemSettings().maxDamage(3).group(ModItemGroup.DEEDS)));

    public static final Item BLOOD_HAMMER = registerItem("blood_hammer",
            new BloodHammerItem(ModToolMaterials.BLOOD,5, -2.8f, new FabricItemSettings().maxDamage(3).group(ModItemGroup.DEEDS)));


    public static final Item BOTTLED_BLOOD = registerItem("bottled_blood",
            new BottledBloodItem(new FabricItemSettings().group(ModItemGroup.DEEDS)));



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
