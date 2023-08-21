package dev.exhq;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class Trolley implements HudRenderCallback {
    private static final Identifier TROLLEY = new Identifier(EchosShittySkyBlockMod.MOD_ID, "textures/trolley/trolley.png");


    public static int progress = 128;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int y = 0;
        drawContext.getMatrices().push();
        ESSMhud.Trolley.applyTransformations(drawContext.getMatrices());
        drawContext.drawTexture(TROLLEY, x,y,0,0,progress,128, 128,128);
        drawContext.getMatrices().pop();
    }
}
