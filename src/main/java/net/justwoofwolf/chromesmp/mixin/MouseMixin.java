package net.justwoofwolf.chromesmp.mixin;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.justwoofwolf.chromesmp.client.ArmorHudOverlay;
import net.justwoofwolf.chromesmp.event.KeyInputHandler;
import net.justwoofwolf.chromesmp.networking.payload.ActivateInstallationPayload;
import net.justwoofwolf.chromesmp.networking.payload.ChangeArmorSlotPayload;
import net.justwoofwolf.chromesmp.util.StateSaverAndLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(at = @At("HEAD"), method = "onMouseScroll", cancellable = true)
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (KeyInputHandler.scrollKey.isPressed()) {
            if (vertical > 0) {
                ClientPlayNetworking.send(new ChangeArmorSlotPayload(-1));

                ci.cancel();
            } else if (vertical < 0) {
                ClientPlayNetworking.send(new ChangeArmorSlotPayload(1));

                ci.cancel();
            }
        }
    }
}
