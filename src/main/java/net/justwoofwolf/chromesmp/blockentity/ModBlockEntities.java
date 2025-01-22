package net.justwoofwolf.chromesmp.blockentity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.justwoofwolf.chromesmp.ChromeSMP;
import net.justwoofwolf.chromesmp.block.ModBlocks;
import net.justwoofwolf.chromesmp.blockentity.custom.InstallationTableBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<InstallationTableBlockEntity> INSTALLATION_TABLE_BLOCK_ENTITY =
            register("installation_table", InstallationTableBlockEntity::new, ModBlocks.INSTALLATION_TABLE);

    private static <T extends BlockEntity> BlockEntityType<T> register(String name,
                                                                       FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
                                                                       Block... blocks) {
        Identifier id = Identifier.of(ChromeSMP.MOD_ID, name);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void registerModBlockEntities() {
        ChromeSMP.LOGGER.info("Registering Mod Block Entities for " + ChromeSMP.MOD_ID);
    }
}
