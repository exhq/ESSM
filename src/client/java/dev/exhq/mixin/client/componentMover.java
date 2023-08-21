package dev.exhq.mixin.client;

import dev.exhq.ESSMhud;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class componentMover {

    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Inject(method = "renderHotbar", at = @At("HEAD"))
    private void ResetHotbarPos(float tickDelta, DrawContext context, CallbackInfo ci){
        context.getMatrices().push();
        context.getMatrices().translate((float) -scaledWidth /2+91, -scaledHeight+22,0);
        ESSMhud.hotBarPos.applyTransformations(context.getMatrixStack());
    }
    @Inject(method = "renderHotbar", at = @At("TAIL"))
    private void HotbarMove(float tickDelta, DrawContext context, CallbackInfo ci){
        context.getMatrices().pop();
    }


    @Inject(method = "renderExperienceBar", at = @At("HEAD"))
    private void ResetXP(DrawContext context, int x, CallbackInfo ci){
        context.getMatrices().push();
        context.getMatrices().translate((float) -scaledWidth /2+91, -scaledHeight+28,0);
        ESSMhud.Xp.applyTransformations(context.getMatrixStack());
    }
    @Inject(method = "renderExperienceBar", at = @At("TAIL"))
    private void XPMove(DrawContext context, int x, CallbackInfo ci){
        context.getMatrices().pop();
    }


    @Inject(method = "renderHealthBar", at = @At("HEAD"))
    private void ResetHeart(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci){
        context.getMatrices().push();
        context.getMatrices().translate((float) -x, -y,0);
        ESSMhud.heartPos.applyTransformations(context.getMatrixStack());
    }
    @Inject(method = "renderHealthBar", at = @At("TAIL"))
    private void HeartMove(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci){
        context.getMatrices().pop();
    }

    @Inject(method = "renderStatusBars", at = @At(value = "INVOKE_STRING",target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V",args = "ldc=food"))
    private void RemoveHunger(DrawContext context, CallbackInfo ci){
        context.getMatrices().push();
        context.getMatrices().translate(10000,0,0);
    }


    @Inject(method = "renderStatusBars", at = @At(value = "INVOKE_STRING",target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V",args = "ldc=air"))
    private void RestoreAir(DrawContext context, CallbackInfo ci){
        context.getMatrices().pop();
    }

}
