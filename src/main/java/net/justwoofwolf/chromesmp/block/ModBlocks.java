package net.justwoofwolf.chromesmp.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.justwoofwolf.chromesmp.ChromeSMP;
import net.justwoofwolf.chromesmp.block.custom.InstallationTableBlock;
import net.justwoofwolf.chromesmp.item.ModItemGroups;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final RegistryKey<Block> INSTALLATION_TABLE_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(ChromeSMP.MOD_ID, "installation_table")
    );

    public static final Block INSTALLATION_TABLE = register(
            new InstallationTableBlock(AbstractBlock.Settings.create().registryKey(INSTALLATION_TABLE_KEY)
                    .requiresTool()
                    .strength(3.5F)),
            INSTALLATION_TABLE_KEY,
            true
    );

    public static Block register(Block block, RegistryKey<Block> blockKey, boolean shouldRegisterItem) {
        // Check if block should register item
        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, blockKey.getValue());

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    public static void registerModBlocks() {
        ChromeSMP.LOGGER.info("Registering Mod Blocks for " + ChromeSMP.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.CHROMESMP_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(ModBlocks.INSTALLATION_TABLE.asItem());
        });
    }
}
