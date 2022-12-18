package net.lunarluned.alegacy.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.world.World;

public class EmeraldTestEntity extends FlyingEntity {
    protected EmeraldTestEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }
}
