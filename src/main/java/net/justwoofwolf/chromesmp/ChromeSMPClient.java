package net.justwoofwolf.chromesmp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.justwoofwolf.chromesmp.client.ArmorHudOverlay;
import net.justwoofwolf.chromesmp.screen.InstallationTableScreen;
import net.justwoofwolf.chromesmp.screen.ModScreenHandlers;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ChromeSMPClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register armor HUD renderer
        HudRenderCallback.EVENT.register(((drawContext, renderTickCounter) -> {
            new ArmorHudOverlay().onHudRender(drawContext, renderTickCounter);
        }));

        HandledScreens.register(ModScreenHandlers.INSTALLATION_TABLE_SCREEN_HANDLER, InstallationTableScreen::new);
    }
}
