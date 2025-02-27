package net.justwoofwolf.chromesmp.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.justwoofwolf.chromesmp.item.ModItems;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                createShaped(RecipeCategory.TOOLS, ModItems.HASTE_CARTRIDGE, 1)
                        .pattern("sfp")
                        .pattern("cbc")
                        .pattern("pfs")
                        .input('s', Items.GOLDEN_SWORD)
                        .input('f', Items.RABBIT_FOOT)
                        .input('p', Items.GOLDEN_PICKAXE)
                        .input('c', Items.SUGAR)
                        .input('b', ModItems.BLANK_CARTRIDGE)
                        .group("multi_bench")
                        .criterion(hasItem(ModItems.HASTE_CARTRIDGE), conditionsFromItem(ModItems.HASTE_CARTRIDGE))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "ModRecipeProvider";
    }
}