package net.justwoofwolf.chromesmp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.justwoofwolf.chromesmp.client.ArmorHudOverlay;
import net.justwoofwolf.chromesmp.event.KeyInputHandler;
import net.justwoofwolf.chromesmp.networking.ModMessages;
import net.justwoofwolf.chromesmp.screen.InstallationTableScreen;
import net.justwoofwolf.chromesmp.screen.ModScreenHandlers;
import net.justwoofwolf.chromesmp.util.PlayerData;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ChromeSMPClient implements ClientModInitializer {
    public static PlayerData playerData = new PlayerData();

    @Override
    public void onInitializeClient() {
        // Register key input handler
        KeyInputHandler.register();
        ModMessages.registerS2CPackets();

        // Register armor HUD renderer
        HudRenderCallback.EVENT.register(((drawContext, renderTickCounter) -> {
            new ArmorHudOverlay().onHudRender(drawContext, renderTickCounter);
        }));

        HandledScreens.register(ModScreenHandlers.INSTALLATION_TABLE_SCREEN_HANDLER, InstallationTableScreen::new);
    }
}
