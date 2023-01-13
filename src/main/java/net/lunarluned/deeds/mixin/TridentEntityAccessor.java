package net.lunarluned.deeds.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ThrownTrident.class)
public interface TridentEntityAccessor {
    @Accessor("ID_LOYALTY")
    static EntityDataAccessor<Byte> deeds$getLoyalty() {
        return null;
    }

    @Accessor("ID_FOIL")
    static EntityDataAccessor<Boolean> deeds$getEnchanted() {
        return null;
    }

    @Accessor("tridentItem")
    ItemStack deeds$getTridentStack();

    @Accessor("tridentItem")
    void deeds$setTridentStack(ItemStack stack);

    @Accessor("dealtDamage")
    boolean deeds$hasDealtDamage();

    @Accessor("dealtDamage")
    void deeds$setDealtDamage(boolean dealtDamage);
}