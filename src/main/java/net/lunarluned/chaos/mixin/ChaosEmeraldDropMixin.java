package net.lunarluned.chaos.mixin;

import net.lunarluned.chaos.misc.ChaosTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ChaosEmeraldDropMixin extends Entity {
    @Shadow
    public abstract ItemStack getStack();

    protected ChaosEmeraldDropMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("TAIL"), method = "tick()V")
    private void dropItem(CallbackInfo info) {
        // Detects if an item is in the tag to disable gravity for it
        if (getStack().isIn(ChaosTags.GRAVITY_DISOBEYING_ITEMS)) {
            setNoGravity(true);
            this.setVelocity(this.getVelocity().multiply(0.96D, 0.96D, 0.96D));
        }
    }

}
