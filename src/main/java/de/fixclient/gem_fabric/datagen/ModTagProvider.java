package de.fixclient.gem_fabric.datagen;

import de.fixclient.gem_fabric.item.ItemManager;
import de.fixclient.gem_fabric.util.Tags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModTagProvider extends FabricTagProvider.ItemTagProvider {
     public ModTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(Tags.FIRST_GEM)
                .add(ItemManager.HEILUNGS_GEM)
                .add(ItemManager.CRASH_GEM);

        getOrCreateTagBuilder(Tags.SECOND_GEM)
                .add(ItemManager.TELEPORT_GEM);

        getOrCreateTagBuilder(Tags.THIRD_GEM)
                .add(ItemManager.LUFT_GEM);

        getOrCreateTagBuilder(Tags.FOURTH_GEM)
                .add(ItemManager.ORANGE_GEM);

        getOrCreateTagBuilder(Tags.GOD_GEM)
                .add(ItemManager.GOD_GEM);
    }
}
