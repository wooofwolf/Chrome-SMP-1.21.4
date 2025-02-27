package net.justwoofwolf.chromesmp.networking.payload;

import net.justwoofwolf.chromesmp.networking.ModMessages;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public record ActivateInstallationPayload(int id) implements CustomPayload {
    public static final CustomPayload.Id<ActivateInstallationPayload> ID = new CustomPayload.Id<>(ModMessages.ACTIVATE_INSTALLATION_ID);
    public static final PacketCodec<PacketByteBuf, ActivateInstallationPayload> CODEC = PacketCodec.of(
            (value, buf) -> buf.writeInt(value.id),
            buf -> new ActivateInstallationPayload(buf.readInt())
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void receive(MinecraftServer server, ServerPlayerEntity player) {
        EntityType.COW.spawn((ServerWorld)player.getWorld(), player.getBlockPos(), SpawnReason.TRIGGERED);

        switch (this.id) {
            case 0 -> player.sendMessage(Text.literal("Activated Armor Slot 0"));
            case 1 -> player.sendMessage(Text.literal("Activated Armor Slot 1"));
            case 2 -> player.sendMessage(Text.literal("Activated Armor Slot 2"));
            case 3 -> player.sendMessage(Text.literal("Activated Armor Slot 3"));
        }
    }
}
