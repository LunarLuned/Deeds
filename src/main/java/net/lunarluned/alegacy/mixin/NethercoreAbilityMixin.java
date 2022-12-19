package net.lunarluned.alegacy.mixin;

import net.lunarluned.alegacy.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class NethercoreAbilityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {

        ServerPlayer player = (ServerPlayer) (Object) this;
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);

        if ((helmet.is(ModItems.NETHERCORE_HELMET)) && chest.is(ModItems.NETHERCORE_CHESTPLATE) && legs.is(ModItems.NETHERCORE_LEGGINGS) && feet.is(ModItems.NETHERCORE_BOOTS)) {

            //extinguish user if on fire
            if (player.isOnFire()) {
                player.setSecondsOnFire(0);
            }

            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10, 10, true, true, true));

        }
    }
}
