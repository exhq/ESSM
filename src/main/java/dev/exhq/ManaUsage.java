package dev.exhq;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class ManaUsage implements HudRenderCallbackVanilla {
    public static boolean showMana = false;
    public static String ManaUsage = "0";

    @Override
    public void renderHudElements(DrawContext drawContext, float tickDelta) {
        if (showMana){
            drawContext.getMatrices().push();
            ESSMhud.UsedMana.applyTransformations(drawContext.getMatrices());
            drawContext.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, ManaUsage, 0,0, 0xff54fcfc);
            drawContext.getMatrices().pop();
        }
    }
}
