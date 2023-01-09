package net.lunarluned.deeds.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.lunarluned.deeds.Deeds;
import net.lunarluned.deeds.util.IEntityDataSaver;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class SRCHudOverlay implements HudRenderCallback {

    private static final ResourceLocation FULL_SRC = new ResourceLocation(Deeds.MOD_ID, "textures/hud/full_src.png");
    private static final ResourceLocation EMPTY_SRC = new ResourceLocation(Deeds.MOD_ID, "textures/hud/empty_src.png");

    @Override
    public void onHudRender(PoseStack matrixStack, float tickDelta) {
        int x = 0;
        int y = 0;
        Minecraft client = Minecraft.getInstance();
        if (client != null) {
            int width = client.getWindow().getGuiScaledWidth();
            int height = client.getWindow().getGuiScaledHeight();


            x = width / 2;
            y = height;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_SRC);
        for(int i = 0; i < 10; i++) {
            GuiComponent.blit(matrixStack,x + 135 + (i * 9),y - 14,0,0,12,12,
                    12,12);
        }
        RenderSystem.setShaderTexture(0, FULL_SRC);
        for(int i = 0; i < 10; i++) {
            assert Minecraft.getInstance().player != null;
            if(((IEntityDataSaver) Minecraft.getInstance().player).getPersistentData().getInt("src") > i) {
                GuiComponent.blit(matrixStack,x + 135 + (i * 9),y - 14,0,0,12,12,
                        12,12);
            } else {
                break;
            }
        }
    }
}
