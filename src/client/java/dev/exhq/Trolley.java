package dev.exhq;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class Trolley implements HudRenderCallback {
    private static final Identifier TROLLEYEMPTY = new Identifier(EchosShittySkyBlockMod.MOD_ID, "textures/trolley/trolleyempty.png");
    private static final Identifier TROLLEY = new Identifier(EchosShittySkyBlockMod.MOD_ID, "textures/trolley/trolley.png");

    private static final Identifier TROLLEYMANAEMPTY = new Identifier(EchosShittySkyBlockMod.MOD_ID, "textures/trolley/trolleymanaempty.png");
    private static final Identifier TROLLEYMANA = new Identifier(EchosShittySkyBlockMod.MOD_ID, "textures/trolley/trolleymana.png");
    public static int healthprogress = 128;

    public static int manaprogress = 128;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int y = 0;
        drawContext.getMatrices().push();
        ESSMhud.Trolley.applyTransformations(drawContext.getMatrices());
        drawContext.drawTexture(TROLLEYEMPTY, x,y,0,0,128,16, 128,16);
        drawContext.drawTexture(TROLLEY, x,y,0,0, healthprogress,16, 128,16);
        drawContext.getMatrices().pop();

        drawContext.getMatrices().push();
        ESSMhud.Trolleymana.applyTransformations(drawContext.getMatrices());
        drawContext.drawTexture(TROLLEYMANAEMPTY, x,y,0,0,128,16, 128,16);
        drawContext.drawTexture(TROLLEYMANA, x,y,0,0, manaprogress,16, 128,16);
        drawContext.getMatrices().pop();



    }
}
