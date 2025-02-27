package net.justwoofwolf.chromesmp.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.justwoofwolf.chromesmp.ChromeSMP;
import net.justwoofwolf.chromesmp.networking.payload.ActivateInstallationPayload;
import net.justwoofwolf.chromesmp.networking.payload.ChangeArmorSlotPayload;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier ACTIVATE_INSTALLATION_ID = Identifier.of(ChromeSMP.MOD_ID, "activate_installation");
    public static final Identifier CHANGE_ARMOR_SLOT_ID = Identifier.of(ChromeSMP.MOD_ID, "change_armor_slot");

    public static void registerC2SPackets() {
        PayloadTypeRegistry.playC2S().register(ActivateInstallationPayload.ID, ActivateInstallationPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(ChangeArmorSlotPayload.ID, ChangeArmorSlotPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ActivateInstallationPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                payload.receive(context.server(), context.player());
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(ChangeArmorSlotPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                payload.receive(context.server(), context.player());
            });
        });
    }

    public static void registerS2CPackets() {

    }
}
