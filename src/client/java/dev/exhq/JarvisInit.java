package dev.exhq;

import moe.nea.jarvis.api.JarvisHud;
import moe.nea.jarvis.api.JarvisPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.List;

public class JarvisInit implements JarvisPlugin {
    @Override
    public @NotNull String getModId() {
        return "essm";
    }

    @Override
    public @NotNull @Unmodifiable List<@NotNull JarvisHud> getAllHuds() {
        return ESSMhud.allhud;
    }
}
