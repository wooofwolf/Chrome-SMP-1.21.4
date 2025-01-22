package net.justwoofwolf.chromesmp.screen;

import net.justwoofwolf.chromesmp.ChromeSMP;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<InstallationTableScreenHandler> INSTALLATION_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(ChromeSMP.MOD_ID, "installation_table"),
                    new ScreenHandlerType<>(InstallationTableScreenHandler::new, FeatureSet.empty()));

    public static void registerModScreenHandlers() {
        ChromeSMP.LOGGER.info("Registering Mod Screen Handlers for " + ChromeSMP.MOD_ID);
    }
}
