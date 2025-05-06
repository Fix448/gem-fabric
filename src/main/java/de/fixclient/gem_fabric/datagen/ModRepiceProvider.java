package de.fixclient.gem_fabric.datagen;

import de.fixclient.gem_fabric.item.ItemManager;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRepiceProvider extends FabricRecipeProvider {
    public ModRepiceProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.MISC, ItemManager.GOD_GEM)
                        .pattern(" B ")
                        .pattern("LSG")
                        .pattern(" O ")
                        .input('B', Ingredient.ofItem(ItemManager.LUFT_GEM))
                        .input('L', Ingredient.ofItem(ItemManager.HEILUNGS_GEM))
                        .input('G', Ingredient.ofItem(ItemManager.TELEPORT_GEM))
                        .input('O', Ingredient.ofItem(ItemManager.ORANGE_GEM))
                        .input('S', Ingredient.ofItem(Items.EMERALD))
                        .criterion(hasItem(ItemManager.ORANGE_GEM), conditionsFromItem(ItemManager.ORANGE_GEM))
                        .criterion(hasItem(ItemManager.TELEPORT_GEM), conditionsFromItem(ItemManager.TELEPORT_GEM))
                        .criterion(hasItem(ItemManager.LUFT_GEM), conditionsFromItem(ItemManager.LUFT_GEM))
                        .criterion(hasItem(ItemManager.HEILUNGS_GEM), conditionsFromItem(ItemManager.HEILUNGS_GEM))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "";
    }
}
