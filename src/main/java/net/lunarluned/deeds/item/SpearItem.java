package net.lunarluned.deeds.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lunarluned.deeds.Deeds;
import net.lunarluned.deeds.common.registry.entity.BloodSpearEntity;
import net.lunarluned.deeds.common.registry.entity.ModSpear;
import net.lunarluned.deeds.item.custom.BloodSpearItem;
import net.minecraft.core.Position;
import net.minecraft.core.Registry;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

import java.util.Objects;

public class SpearItem {

    public static Item BLOOD_SPEAR;

    public static void init() {
        BLOOD_SPEAR = registerTrident(new BloodSpearItem((new FabricItemSettings().group(ModItemGroup.DEEDS).maxDamage(3)), ModSpear.BLOODSPEAR), "blood_spear", true);
    }

    public static BloodSpearItem registerTrident(BloodSpearItem item, String name, boolean registerDispenserBehavior) {
        Registry.register(Registry.ITEM, Deeds.MOD_ID + ":" + name, item);
        if (registerDispenserBehavior) {
            DispenserBlock.registerBehavior(item, new AbstractProjectileDispenseBehavior() {
                @Override
                protected Projectile getProjectile(Level level, Position position, ItemStack itemStack) {
                    BloodSpearEntity bloodSpear = Objects.requireNonNull(item.getEntityType().create(level));
                    bloodSpear.setPos(position.x(), position.y(), position.z());
                    itemStack.shrink(1);
                    return bloodSpear;
                }
            });
        }

        return item;
    }

}
