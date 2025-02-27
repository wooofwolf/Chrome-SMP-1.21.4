package net.justwoofwolf.chromesmp.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.justwoofwolf.chromesmp.ChromeSMPClient;
import net.justwoofwolf.chromesmp.networking.payload.ActivateInstallationPayload;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_CHROMESMP = "key.category.chromesmp";
    public static final String KEY_ACTIVATE_INSTALLATION = "key.chromesmp.activate_installation";
    public static final String KEY_SCROLL_UP = "key.chromesmp.scroll";

    public static KeyBinding activateInstallationKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            KEY_ACTIVATE_INSTALLATION,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_X,
            KEY_CATEGORY_CHROMESMP
    ));

    public static KeyBinding scrollKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            KEY_SCROLL_UP,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_ALT,
            KEY_CATEGORY_CHROMESMP
    ));

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            while (activateInstallationKey.wasPressed()) {
                minecraftClient.player.sendMessage(Text.literal("Activated installation ability!"), false);

                ClientPlayNetworking.send(new ActivateInstallationPayload(ChromeSMPClient.playerData.armorSlotId));
            }
        });
    }

    public static void register() {
        registerKeyInputs();
    }
}
