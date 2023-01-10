package net.lunarluned.deeds.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AbstractContractItem extends Item {


    public AbstractContractItem(Properties properties) {
        super(properties);
    }

    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> list, @NotNull TooltipFlag tooltipFlag) {
        list.add(this.getPAbility().withStyle(ChatFormatting.WHITE));
        list.add(this.getSAbility().withStyle(ChatFormatting.GRAY));
        list.add(this.getTradeoff().withStyle(ChatFormatting.RED));
    }
    public MutableComponent getPAbility() {
        return Component.translatable(this.getDescriptionId() + ".pability");
    }
    public MutableComponent getSAbility() {
        return Component.translatable(this.getDescriptionId() + ".sability");
    }
    public MutableComponent getTradeoff() {
        return Component.translatable(this.getDescriptionId() + ".trade");
    }
}
