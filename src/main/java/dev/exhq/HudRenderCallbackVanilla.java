package dev.exhq;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.DrawContext;

@FunctionalInterface
public interface HudRenderCallbackVanilla {

    void renderHudElements(DrawContext context, float tickDelta);

    Event<HudRenderCallbackVanilla> EVENT = EventFactory.createArrayBacked(HudRenderCallbackVanilla.class, hudRenderCallbackVanillas -> (context, tickDelta) -> {
        for (HudRenderCallbackVanilla hudRenderCallbackVanilla : hudRenderCallbackVanillas) {
            hudRenderCallbackVanilla.renderHudElements(context, tickDelta);
        }
    });
}
