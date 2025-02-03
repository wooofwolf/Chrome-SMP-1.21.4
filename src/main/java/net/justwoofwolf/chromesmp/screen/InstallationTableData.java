package net.justwoofwolf.chromesmp.screen;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record InstallationTableData(BlockPos pos) {
    public static final PacketCodec<RegistryByteBuf, InstallationTableData> PACKET_CODEC =
            PacketCodec.tuple(BlockPos.PACKET_CODEC, InstallationTableData::pos, InstallationTableData::new);
}
