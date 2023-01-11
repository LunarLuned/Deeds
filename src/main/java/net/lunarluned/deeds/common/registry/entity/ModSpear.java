package net.lunarluned.deeds.common.registry.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.lunarluned.deeds.Deeds;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModSpear {
        public static EntityType<BloodSpearEntity> BLOODSPEAR;

    public static void init() {
        BLOODSPEAR = register("bloodspear", createEntityType(BloodSpearEntity::new));
    }

    private static <T extends Entity> EntityType<T> register(String s, EntityType<T> bombEntityType) {
        return Registry.register(Registry.ENTITY_TYPE, Deeds.MOD_ID + ":" + s, bombEntityType);
    }

    private static <T extends Entity> EntityType<T> createEntityType(EntityType.EntityFactory<T> factory) {
        return FabricEntityTypeBuilder.create(MobCategory.MISC, factory).dimensions(EntityDimensions.scalable(0.5f, 0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build();
    }
}
