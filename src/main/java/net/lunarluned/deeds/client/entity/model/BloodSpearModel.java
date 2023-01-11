package net.lunarluned.deeds.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class BloodSpearModel extends Model {


    public BloodSpearModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.Spear = root.getChild("Spear");
    }

    private final ModelPart Spear;

    public static LayerDefinition getLayerDefinition() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Spear = partdefinition.addOrReplaceChild("Spear", CubeListBuilder.create().texOffs(4, 0).addBox(0.5F, -32.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-0.5F, -33.0F, -0.5F, 1.0F, 33.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 9).addBox(-2.5F, -30.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 0).addBox(-1.5F, -32.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 9).addBox(1.5F, -30.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Spear.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}

