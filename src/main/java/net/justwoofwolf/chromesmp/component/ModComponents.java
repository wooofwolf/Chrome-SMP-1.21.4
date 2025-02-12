package net.justwoofwolf.chromesmp.component;

import com.mojang.serialization.Codec;
import net.justwoofwolf.chromesmp.ChromeSMP;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static final ComponentType<Integer> TREE_FELLER_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(ChromeSMP.MOD_ID, "tree_feller"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static void registerModComponents() {
        ChromeSMP.LOGGER.info("Registering Mod Components for " + ChromeSMP.MOD_ID);
    }
}
