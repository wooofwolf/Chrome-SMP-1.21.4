package net.justwoofwolf.chromesmp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.justwoofwolf.chromesmp.client.ArmorHudOverlay;

public class ChromeSMPClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register armor HUD renderer
        HudRenderCallback.EVENT.register(((drawContext, renderTickCounter) -> {
            new ArmorHudOverlay().onHudRender(drawContext, renderTickCounter);
        }));
    }
}
