package dev.exhq.mixin.client;


import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text text) {
        super(text);
    }

    Text idk = Text.of("gay sex");

    @Inject(method = "init", at = @At("RETURN"))
    private void addText(CallbackInfo ci) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment())
            addDrawableChild(new TextWidget(4, 5, textRenderer.getWidth(idk) + 4, 20, idk, textRenderer));
    }
}