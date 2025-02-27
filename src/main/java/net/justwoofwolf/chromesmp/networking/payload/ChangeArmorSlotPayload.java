package net.justwoofwolf.chromesmp.networking.payload;

import net.justwoofwolf.chromesmp.ChromeSMPClient;
import net.justwoofwolf.chromesmp.networking.ModMessages;
import net.justwoofwolf.chromesmp.util.PlayerData;
import net.justwoofwolf.chromesmp.util.StateSaverAndLoader;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public record ChangeArmorSlotPayload(int id) implements CustomPayload {
    public static final Id<ChangeArmorSlotPayload> ID = new Id<>(ModMessages.CHANGE_ARMOR_SLOT_ID);
    public static final PacketCodec<PacketByteBuf, ChangeArmorSlotPayload> CODEC = PacketCodec.of(
            (value, buf) -> buf.writeInt(value.id),
            buf -> new ChangeArmorSlotPayload(buf.readInt())
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void receive(MinecraftServer server, ServerPlayerEntity player) {
        StateSaverAndLoader serverState = StateSaverAndLoader.getServerState(server);

        int armorSlotId = StateSaverAndLoader.getPlayerState(player).armorSlotId + this.id;
        if (armorSlotId > 3) armorSlotId = 0;
        if (armorSlotId < 0) armorSlotId = 3;

        PlayerData newPlayerData = new PlayerData();
        newPlayerData.armorSlotId = armorSlotId;

        ChromeSMPClient.playerData.armorSlotId = armorSlotId;

        serverState.players.put(player.getUuid(), newPlayerData);
    }
}
