package net.lunarluned.deeds.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.lunarluned.deeds.ALegacy;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
        public static final ItemGroup ALEGACY = FabricItemGroupBuilder.build(new Identifier(ALegacy.MOD_ID, "alegacy"),
            () -> new ItemStack(ModItems.NETHERCORE_INGOT));
}
