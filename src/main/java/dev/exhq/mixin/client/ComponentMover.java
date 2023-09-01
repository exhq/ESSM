package dev.exhq.mixin.client;

import dev.exhq.ESSMhud;
import dev.exhq.EchosShittySkyBlockMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class ComponentMover {

    @Shadow
    private int scaledWidth;

    @Shadow
    private int scaledHeight;

    @Shadow
    private @Nullable Text overlayMessage;

    @Inject(method = "renderHotbar", at = @At("HEAD"))
    private void resetHotbarPos(float tickDelta, DrawContext context, CallbackInfo ci) {
        context.getMatrices().push();
        context.getMatrices().translate((float) -scaledWidth / 2 + 91, -scaledHeight + 22, 0);
        ESSMhud.hotBarPos.applyTransformations(context.getMatrices());
    }

    @Inject(method = "renderHotbar", at = @At("TAIL"))
    private void hotbarMove(float tickDelta, DrawContext context, CallbackInfo ci) {
        context.getMatrices().pop();
    }


    @Inject(method = "renderExperienceBar", at = @At("HEAD"))
    private void resetXP(DrawContext context, int x, CallbackInfo ci) {
        context.getMatrices().push();
        context.getMatrices().translate((float) -scaledWidth / 2 + 91, -scaledHeight + 28, 0);
        ESSMhud.Xp.applyTransformations(context.getMatrixStack());
    }

    @Inject(method = "renderExperienceBar", at = @At("TAIL"))
    private void XPMove(DrawContext context, int x, CallbackInfo ci) {
        context.getMatrices().pop();
    }


    @Inject(method = "renderHealthBar", at = @At("HEAD"))
    private void heartMove(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        context.getMatrices().push();
        context.getMatrices().translate((float) -x, -y, 0);
        if (!EchosShittySkyBlockMod.CONFIG.disableVanillaHealthbar()) {
            ESSMhud.heartPos.applyTransformations(context.getMatrixStack());
        } else {
            context.getMatrices().translate(10000, 0, 0);
        }
    }

    @Inject(method = "renderHealthBar", at = @At("TAIL"))
    private void resetHeart(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        context.getMatrices().pop();
    }

    @Unique
    boolean didPushForFood = false;

    @Inject(method = "renderStatusBars", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = "ldc=food"))
    private void removeHunger(DrawContext context, CallbackInfo ci) {
        context.getMatrices().push();
        context.getMatrices().translate(10000, 0, 0);
        didPushForFood = true;
    }


    @Inject(method = "renderStatusBars", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = "ldc=air"))
    private void restoreHunger(DrawContext context, CallbackInfo ci) {
        if (didPushForFood) {
            context.getMatrices().pop();
            didPushForFood = false;
        }
    }


    @Inject(method = "renderStatusBars", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V", args = "ldc=armor"))
    private void removeArmor(DrawContext context, CallbackInfo ci) {
        context.getMatrices().push();
        context.getMatrices().translate(10000, 0, 0);
    }


    @Inject(method = "renderStatusBars", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = "ldc=health"))
    private void restoreArmor(DrawContext context, CallbackInfo ci) {
        context.getMatrices().pop();
    }

    @Inject(method = "setOverlayMessage", at = @At("TAIL"))
    private void removeOverlay(Text message, boolean tinted, CallbackInfo ci) {
        if (EchosShittySkyBlockMod.CONFIG.DisableActionbar()) {
            this.overlayMessage = Text.of("");
        } else {
            return;
        }

    }


}
