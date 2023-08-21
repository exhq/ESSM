package dev.exhq;

import moe.nea.jarvis.api.JarvisHud;
import moe.nea.jarvis.api.JarvisScalable;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ESSMhud implements JarvisHud, JarvisScalable {
    private final PosData posdata;
    public static List<JarvisHud> allhud = new ArrayList<>();
    private final Text label;
    private final int width;
    private final int height;


    public ESSMhud(Text label, int width, int height, double defaultX, double defaultY){

        allhud.add(this);
        this.label = label;
        this.width = width;
        this.height = height;
        posdata = new PosData();
        posdata.x = defaultX;
        posdata.y = defaultY;
        posdata.scale = 1f;
    }
    public ESSMhud(Text label, int width, int height, PosData posdata){

        allhud.add(this);
        this.label = label;
        this.width = width;
        this.height = height;
        this.posdata = posdata;
    }
    public static class PosData{
        double x;
        double y;
        float scale = 1f;
    }


    @Override
    public double getX() {
        return posdata.x;
    }

    @Override
    public void setX(double newX) {
        posdata.x = newX;
    }

    @Override
    public double getY() {
        return posdata.y;
    }

    @Override
    public void setY(double newY) {
        posdata.y = newY;
    }

    @Override
    public @NotNull Text getLabel() {
        return label;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
    public static ESSMhud hotBarPos = new ESSMhud(Text.of("Hotbar"), 182,22, 1,1);

    public static ESSMhud heartPos = new ESSMhud(Text.of("Heart"), 182,22, EchosShittySkyBlockMod.CONFIG.Heart());
    public static ESSMhud Xp = new ESSMhud(Text.of("XP"), 182,5, EchosShittySkyBlockMod.CONFIG.XP());
    public static ESSMhud Trolley = new ESSMhud(Text.of("Health"), 128,16, EchosShittySkyBlockMod.CONFIG.Health());
    public static ESSMhud Trolleymana = new ESSMhud(Text.of("Mana"), 128,16, EchosShittySkyBlockMod.CONFIG.Mana());
    public static ESSMhud UsedMana = new ESSMhud(Text.of("UsedMana"), 128,16,  EchosShittySkyBlockMod.CONFIG.UsedMana());



    @Override
    public float getScale() {
        return posdata.scale;
    }

    @Override
    public void setScale(float newScale) {
        posdata.scale = newScale;
    }
}
