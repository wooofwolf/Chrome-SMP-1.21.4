package net.justwoofwolf.chromesmp.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class InstallationRecipe {
    private Item inputItem;
    private Item installationItem;
    private Item outputItem;

    public InstallationRecipe(Item input, Item installation, Item output, List<InstallationRecipe> masterList) {
        this.inputItem = input;
        this.installationItem = installation;
        this.outputItem = output;

        masterList.add(this);
    }

    public boolean isSameRecipe(Item input, Item installation) {
        return input == inputItem && installation == installationItem;
    }

    public ItemStack getResult() {
        return new ItemStack(outputItem);
    }
}
