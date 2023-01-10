package net.lunarluned.deeds.item.custom;

import net.lunarluned.deeds.util.ContractData;
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
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class SrcSacItem extends Item {

    public SrcSacItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(@NotNull Level world, Player user, @NotNull InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        CompoundTag nbt = ((IEntityDataSaver) user).getPersistentData();
        int src = nbt.getInt("src");

        if (!world.isClientSide) {
            IEntityDataSaver dataPlayer = ((IEntityDataSaver) user);
            ContractData.syncSRC(src, (ServerPlayer) dataPlayer);
            ContractData.addSRC(dataPlayer, 1);
                user.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
                itemStack.shrink(1);
                world.playSound(null, user.getOnPos().getX(), user.getOnPos().getY(), user.getOnPos().getZ(), SoundEvents.SCULK_BLOCK_CHARGE, SoundSource.NEUTRAL, 1, 1 / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        }
        return super.use(world, user, hand);
    }
}
