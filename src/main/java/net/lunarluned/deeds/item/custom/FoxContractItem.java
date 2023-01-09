package net.lunarluned.deeds.item.custom;

import net.lunarluned.deeds.util.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static net.lunarluned.deeds.util.ContractData.setContracttoFox;
import static net.lunarluned.deeds.util.ContractData.syncContract;

public class FoxContractItem extends Item {
    public FoxContractItem(Properties properties) {
        super(properties);
    }
//
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, InteractionHand interactionHand) {
        if (!level.isClientSide) {
            CompoundTag nbt = ((IEntityDataSaver) player).getPersistentData();
            int contract = nbt.getInt("contract");

            setContracttoFox((IEntityDataSaver) player, 0);
            syncContract(contract, (ServerPlayer) player);
        }
        return super.use(level, player, interactionHand);
    }
}
