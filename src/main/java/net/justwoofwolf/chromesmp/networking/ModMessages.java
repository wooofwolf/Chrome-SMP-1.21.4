package net.justwoofwolf.chromesmp.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.justwoofwolf.chromesmp.ChromeSMP;
import net.justwoofwolf.chromesmp.networking.payload.ActivateInstallationPayload;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier ACTIVATE_INSTALLATION_ID = Identifier.of(ChromeSMP.MOD_ID, "activate_installation");

    public static void registerC2SPackets() {
        PayloadTypeRegistry.playC2S().register(ActivateInstallationPayload.ID, ActivateInstallationPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ActivateInstallationPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                ActivateInstallationPayload.receive(context.server(), context.player());
            });
        });
    }

    public static void registerS2CPackets() {

    }
}
