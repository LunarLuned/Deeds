package net.lunarluned.deeds.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.lunarluned.deeds.Deeds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroup {
        public static final CreativeModeTab DEEDS = FabricItemGroupBuilder.build(new ResourceLocation(Deeds.MOD_ID, "deeds"),
            () -> new ItemStack(ModItems.FOX_DEVIL_CONTRACT));
}
