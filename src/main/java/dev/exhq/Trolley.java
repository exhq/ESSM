package dev.exhq;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class Trolley implements HudRenderCallback {
    private static final Identifier TROLLEYEMPTY = new Identifier(EchosShittySkyBlockMod.MOD_ID, "textures/trolley/empty.png");
    private static final Identifier TROLLEY = new Identifier(EchosShittySkyBlockMod.MOD_ID, "textures/trolley/heart.png");

    private static final Identifier TROLLEYMANA = new Identifier(EchosShittySkyBlockMod.MOD_ID, "textures/trolley/mana.png");
    private static final Identifier SKILL = new Identifier(EchosShittySkyBlockMod.MOD_ID, "textures/trolley/skill.png");
    public static int healthprogress = 128;

    public static int manaprogress = 128;

    public static int skillprogress = 128;
    public static int skillprogressPercentage = 100;
    public static String Skilltext = "";
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int y = 0;
        drawContext.getMatrices().push();
        ESSMhud.Trolley.applyTransformations(drawContext.getMatrices());
        drawContext.drawTexture(TROLLEYEMPTY, x,y,0,0,128,4, 128,4);
        drawContext.drawTexture(TROLLEY, x,y,0,0, healthprogress,4, 128,4);
        drawContext.getMatrices().pop();

        drawContext.getMatrices().push();
        ESSMhud.Trolleymana.applyTransformations(drawContext.getMatrices());
        drawContext.drawTexture(TROLLEYEMPTY, x,y,0,0,128,4, 128,4);
        drawContext.drawTexture(TROLLEYMANA, x,y,0,0, manaprogress,4, 128,4);
        drawContext.getMatrices().pop();

        if (EchosShittySkyBlockMod.shouldShowskill){
            drawContext.getMatrices().push();
            ESSMhud.Skill.applyTransformations(drawContext.getMatrices());
            drawContext.drawText(MinecraftClient.getInstance().textRenderer,Skilltext, x,y-10,0xff10d275, true);
            drawContext.drawTexture(TROLLEYEMPTY, x,y,0,0,128,4, 128,4);
            drawContext.drawTexture(SKILL, x,y,0,0, skillprogress,4, 128,4);
            drawContext.getMatrices().pop();
        }


    }
}
