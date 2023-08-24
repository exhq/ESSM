package dev.exhq;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;



public class ScoreboardInfo implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        drawContext.getMatrices().push();
        drawContext.fill(0, (MinecraftClient.getInstance().getWindow().getHeight())-200, MinecraftClient.getInstance().getWindow().getWidth(), MinecraftClient.getInstance().getWindow().getHeight(), -1);
        drawContext.getMatrices().pop();




        drawContext.getMatrices().push();
        ESSMhud.PurseAmount.applyTransformations(drawContext.getMatrices());
        drawContext.drawTextWithShadow(MinecraftClient.getInstance().textRenderer,EchosShittySkyBlockMod.coinLogo, 0,0, 0xfffca800);
        drawContext.getMatrices().pop();
    }
}