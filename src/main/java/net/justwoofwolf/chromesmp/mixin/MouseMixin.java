package net.justwoofwolf.chromesmp.mixin;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.justwoofwolf.chromesmp.ChromeSMPClient;
import net.justwoofwolf.chromesmp.event.KeyInputHandler;
import net.justwoofwolf.chromesmp.networking.payload.ChangeArmorSlotPayload;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
    @Inject(at = @At("HEAD"), method = "onMouseScroll", cancellable = true)
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (KeyInputHandler.scrollKey.isPressed()) {
            if (vertical > 0) {
                ChromeSMPClient.playerData.armorSlotId -= 1;

                if (ChromeSMPClient.playerData.armorSlotId < 0) ChromeSMPClient.playerData.armorSlotId = 3;
            } else if (vertical < 0) {
                ChromeSMPClient.playerData.armorSlotId += 1;

                if (ChromeSMPClient.playerData.armorSlotId > 3) ChromeSMPClient.playerData.armorSlotId = 0;
            }

            ci.cancel();
        }
    }
}
