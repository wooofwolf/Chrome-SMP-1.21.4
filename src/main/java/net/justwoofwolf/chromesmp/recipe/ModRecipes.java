package net.justwoofwolf.chromesmp.recipe;

import net.justwoofwolf.chromesmp.ChromeSMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes {
    public static final List<InstallationRecipe> RECIPE_LIST = new ArrayList<>();

    public static final InstallationRecipe TEST_RECIPE = new InstallationRecipe(Items.STONE, Items.WOODEN_PICKAXE, Items.DIAMOND, RECIPE_LIST);

    public static void registerModRecipes() {
        ChromeSMP.LOGGER.info("Registering Mod Recipes for " + ChromeSMP.MOD_ID);
    }

    public static ItemStack getResult(Item input, Item installation) {
        for (int i = 0; i < RECIPE_LIST.size(); ++i) {
            if (RECIPE_LIST.get(i).isSameRecipe(input, installation)) {
                return RECIPE_LIST.get(i).getResult();
            }
        }

        return null;
    }
}
