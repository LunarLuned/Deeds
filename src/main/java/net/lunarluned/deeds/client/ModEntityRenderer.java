package net.lunarluned.deeds.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.lunarluned.deeds.Deeds;
import net.lunarluned.deeds.client.entity.model.BloodSpearModel;
import net.lunarluned.deeds.client.render.entities.BloodSpearEntityRenderer;
import net.lunarluned.deeds.common.registry.entity.ModSpear;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class ModEntityRenderer {
    public static final ModelLayerLocation BLOODSPEAR = new ModelLayerLocation(new ResourceLocation(Deeds.MOD_ID, "blood_spear"), "main");

    public static void registerRenderers() {
        EntityRendererRegistry.register(ModSpear.BLOODSPEAR, BloodSpearEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(BLOODSPEAR, BloodSpearModel::getLayerDefinition);
    }

}
