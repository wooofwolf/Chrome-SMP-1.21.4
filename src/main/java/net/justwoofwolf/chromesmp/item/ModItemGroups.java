package net.justwoofwolf.chromesmp.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.justwoofwolf.chromesmp.ChromeSMP;
import net.justwoofwolf.chromesmp.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> CHROMESMP_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(ChromeSMP.MOD_ID, "item_group"));
    public static final ItemGroup CHROMESMP_ITEM_GROUP = register(
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.INSTALLATION_TABLE.asItem()))
                    .displayName(Text.translatable("itemgroup.chromesmp.chromesmp_item_group"))
                    .build(),
            CHROMESMP_ITEM_GROUP_KEY
    );

    public static ItemGroup register(ItemGroup itemGroup, RegistryKey<ItemGroup> itemGroupKey) {
        return Registry.register(Registries.ITEM_GROUP, itemGroupKey, itemGroup);
    }

    public static void registerItemGroups() {
        ChromeSMP.LOGGER.info("Registering Item Groups for " + ChromeSMP.MOD_ID);
    }
}
