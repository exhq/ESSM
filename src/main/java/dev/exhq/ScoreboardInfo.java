package dev.exhq;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;


public class ScoreboardInfo implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if(false)
        drawContext.fill(0, (MinecraftClient.getInstance().getWindow().getScaledHeight()) -50,
                MinecraftClient.getInstance().getWindow().getScaledWidth(), MinecraftClient.getInstance().getWindow().getScaledHeight(), -1);

        drawContext.getMatrices().push();
        ESSMhud.PurseAmount.applyTransformations(drawContext.getMatrices());
        drawContext.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, EchosShittySkyBlockMod.coinLogo, 0, 0, 0xfffca800);
        drawContext.getMatrices().pop();
    }
}
