package net.lunarluned.alegacy.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.lunarluned.alegacy.ALegacy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroup {
        public static final CreativeModeTab ALEGACY = FabricItemGroupBuilder.build(new ResourceLocation(ALegacy.MOD_ID, "alegacy"),
            () -> new ItemStack(ModItems.NETHERCORE_INGOT));
}
