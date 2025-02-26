package net.justwoofwolf.chromesmp.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.justwoofwolf.chromesmp.ChromeSMP;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item TEST_CARTRIDGE = register("test_cartridge");
    public static final Item HASTE_CARTRIDGE = register("haste_cartridge");

    // Register the item
    public static Item register(String name) {
        RegistryKey<Item> ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ChromeSMP.MOD_ID, name));
        Item item = new Item(new Item.Settings().registryKey(ITEM_KEY));

        return Registry.register(Registries.ITEM, ITEM_KEY.getValue(), item);
    }

    public static void registerModItems() {
        ChromeSMP.LOGGER.info("Registering Mod Items for " + ChromeSMP.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.CHROMESMP_ITEM_GROUP_KEY)
                .register((itemGroup) -> itemGroup.add(ModItems.TEST_CARTRIDGE));
    }
}
