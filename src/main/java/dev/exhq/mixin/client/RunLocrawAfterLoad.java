package dev.exhq.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DownloadingTerrainScreen.class)
public class RunLocrawAfterLoad {
    @Inject(method = "close", at = @At("HEAD"))
    public void onClose(CallbackInfo ci) {
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of("while the screen was loading, i was having gay sex with your mother"));
    }
}
