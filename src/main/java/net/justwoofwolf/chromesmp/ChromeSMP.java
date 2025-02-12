package net.justwoofwolf.chromesmp;

import net.fabricmc.api.ModInitializer;
import net.justwoofwolf.chromesmp.block.ModBlocks;
import net.justwoofwolf.chromesmp.blockentity.ModBlockEntities;
import net.justwoofwolf.chromesmp.component.ModComponents;
import net.justwoofwolf.chromesmp.item.ModItemGroups;
import net.justwoofwolf.chromesmp.item.ModItems;
import net.justwoofwolf.chromesmp.recipe.ModRecipes;
import net.justwoofwolf.chromesmp.screen.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeSMP implements ModInitializer {
	public static final String MOD_ID = "chromesmp";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();

		ModRecipes.registerModRecipes();

		ModComponents.registerModComponents();

		ModScreenHandlers.registerModScreenHandlers();
	}
}