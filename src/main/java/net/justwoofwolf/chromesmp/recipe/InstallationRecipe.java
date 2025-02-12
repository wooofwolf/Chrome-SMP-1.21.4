package net.justwoofwolf.chromesmp.recipe;

import net.justwoofwolf.chromesmp.ChromeSMP;
import net.justwoofwolf.chromesmp.component.ModComponents;
import net.justwoofwolf.chromesmp.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;

public class InstallationRecipe {
    private TagKey<Item> inputTag;
    private Item installationItem;
    private ComponentType<Integer> output;

    public InstallationRecipe(String inputTag, String installation, String output, List<InstallationRecipe> masterList) {
        this.inputTag = TagKey.of(RegistryKeys.ITEM, Identifier.ofVanilla(inputTag));

        switch (installation) {
            case "test_cartridge" -> this.installationItem = ModItems.TEST_CARTRIDGE;
        }

        switch (output) {
            case "tree_feller" -> this.output = ModComponents.TREE_FELLER_COMPONENT;
        }

        masterList.add(this);
    }

    public boolean isSameRecipe(ItemStack input, ItemStack installation) {
        return input.isIn(inputTag) && installation.getItem().equals(installationItem);
    }

    public ItemStack getResult(ItemStack input) {
        input.set(output, 1);

        return input;
    }

    public ComponentType<Integer> getTag() {
        return output;
    }
}
