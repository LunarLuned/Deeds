package net.lunarluned.deeds.client.render.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lunarluned.deeds.client.ModEntityRenderer;
import net.lunarluned.deeds.client.entity.model.BloodSpearModel;
import net.lunarluned.deeds.common.registry.entity.BloodSpearEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class BloodSpearEntityRenderer extends EntityRenderer<BloodSpearEntity> {
    public static final ResourceLocation TRIDENT_LOCATION = new ResourceLocation("textures/entity/blood_spear.png");
    private final BloodSpearModel model;

    public BloodSpearEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new BloodSpearModel(context.bakeLayer(ModEntityRenderer.BLOODSPEAR));
    }

    @Override
    public void render(BloodSpearEntity bloodSpear, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.pushPose();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(g, bloodSpear.yRotO, bloodSpear.getYRot()) - 90.0F));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(g, bloodSpear.xRotO, bloodSpear.getXRot()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.model.renderType(this.getTextureLocation(bloodSpear)), false, bloodSpear.isFoil());
        this.model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(bloodSpear, f, g, poseStack, multiBufferSource, i);
    }

    public ResourceLocation getTextureLocation(BloodSpearEntity bloodSpear) {
        return TRIDENT_LOCATION;
    }
}
