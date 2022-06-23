package net.lunarluned.chaos.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.lunarluned.chaos.Chaos;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup CHAOS = FabricItemGroupBuilder.build(new Identifier(Chaos.MOD_ID, "chaos"),
            () -> new ItemStack(ModItems.EMPTY_CHAOS_EMERALD));
}
