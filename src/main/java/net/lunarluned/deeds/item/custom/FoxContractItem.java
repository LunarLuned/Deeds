package net.lunarluned.deeds.item.custom;

import net.lunarluned.deeds.util.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static net.lunarluned.deeds.util.ContractData.*;

public class FoxContractItem extends Item {
    public FoxContractItem(Properties properties) {
        super(properties);
    }
//
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        CompoundTag nbt = ((IEntityDataSaver) player).getPersistentData();
        int contract = nbt.getInt("contract");

        if (!level.isClientSide) {
            if (contract == 0) {
                setContracttoFox((IEntityDataSaver) player, 0);
                syncContract(contract, (ServerPlayer) player);
                itemStack.shrink(1);
                player.getCooldowns().addCooldown(this, 500);
                level.playSound(null, player.getOnPos().getX(), player.getOnPos().getY(), player.getOnPos().getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.NEUTRAL, 1, 1);
            }
        }
        return super.use(level, player, interactionHand);
    }
}
