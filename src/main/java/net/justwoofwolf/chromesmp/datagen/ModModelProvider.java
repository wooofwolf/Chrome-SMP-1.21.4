package net.justwoofwolf.chromesmp.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.justwoofwolf.chromesmp.block.ModBlocks;
import net.justwoofwolf.chromesmp.item.ModItems;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerDispenserLikeOrientable(ModBlocks.INSTALLATION_TABLE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BLANK_CARTRIDGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HASTE_CARTRIDGE, Models.GENERATED);
    }
}
