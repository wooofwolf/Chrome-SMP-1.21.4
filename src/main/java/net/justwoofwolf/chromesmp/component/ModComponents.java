package net.justwoofwolf.chromesmp.component;

import com.mojang.serialization.Codec;
import net.justwoofwolf.chromesmp.ChromeSMP;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModComponents {
    public static Map<ComponentType<Integer>, String> MOD_COMPONENTS = new HashMap<>();

    public static final ComponentType<Integer> HASTE_COMPONENT = register("haste");
    public static final ComponentType<Integer> TREE_FELLER_COMPONENT = register("tree_feller");

    public static ComponentType<Integer> register(String id) {
        ComponentType<Integer> newComponent = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(ChromeSMP.MOD_ID, id),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
        );

        MOD_COMPONENTS.put(newComponent, id);
        return newComponent;
    }

    public static void registerModComponents() {
        ChromeSMP.LOGGER.info("Registering Mod Components for " + ChromeSMP.MOD_ID);
    }
}
