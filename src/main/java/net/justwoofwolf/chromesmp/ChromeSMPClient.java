package net.justwoofwolf.chromesmp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.justwoofwolf.chromesmp.client.ArmorHudOverlay;
import net.justwoofwolf.chromesmp.event.KeyInputHandler;
import net.justwoofwolf.chromesmp.networking.ModMessages;
import net.justwoofwolf.chromesmp.screen.InstallationTableScreen;
import net.justwoofwolf.chromesmp.screen.ModScreenHandlers;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class ChromeSMPClient implements ClientModInitializer {
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
