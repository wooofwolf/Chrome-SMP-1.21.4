package net.justwoofwolf.chromesmp.recipe;

import net.justwoofwolf.chromesmp.ChromeSMP;
import net.justwoofwolf.chromesmp.util.JsonFileHelper;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModRecipes {
    public static final List<InstallationRecipe> RECIPE_LIST = new ArrayList<>();

    public static InstallationRecipe TREE_FELLER_RECIPE;

    public static void registerModRecipes() {
        ChromeSMP.LOGGER.info("Registering Mod Recipes for " + ChromeSMP.MOD_ID);

        String[] fieldNames = {"input", "installation", "output"};
        String[] values = {"axes", "test_cartridge", "tree_feller"};
        try {
            JsonFileHelper.writeJson(fieldNames, values, "tree_feller.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            TREE_FELLER_RECIPE = new InstallationRecipe(JsonFileHelper.readJson("input", "tree_feller.json"),
                    JsonFileHelper.readJson("installation", "tree_feller.json"),
                    JsonFileHelper.readJson("output", "tree_feller.json")
                    ,RECIPE_LIST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean hasResult(ItemStack input, ItemStack installation) {
        for (int i = 0; i < RECIPE_LIST.size(); ++i) {
            if (RECIPE_LIST.get(i).isSameRecipe(input, installation)) {
                return true;
            }
        }

        return false;
    }

    public static ItemStack getResult(ItemStack input, ItemStack installation) {
        for (int i = 0; i < RECIPE_LIST.size(); ++i) {
            if (RECIPE_LIST.get(i).isSameRecipe(input, installation)) {
                return RECIPE_LIST.get(i).getResult(input);
            }
        }

        return null;
    }

    public static ComponentType<Integer> getTag(ItemStack input, ItemStack installation) {
        for (int i = 0; i < RECIPE_LIST.size(); ++i) {
            if (RECIPE_LIST.get(i).isSameRecipe(input, installation)) {
                return RECIPE_LIST.get(i).getTag();
            }
        }

        return null;
    }
}
