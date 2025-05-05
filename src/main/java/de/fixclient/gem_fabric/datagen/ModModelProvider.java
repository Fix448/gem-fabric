package de.fixclient.gem_fabric.datagen;

import de.fixclient.gem_fabric.item.ItemManager;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemManager.HEILUNGS_GEM, Models.GENERATED);
        itemModelGenerator.register(ItemManager.TELEPORT_GEM, Models.GENERATED);
        itemModelGenerator.register(ItemManager.LUFT_GEM, Models.GENERATED);
        itemModelGenerator.register(ItemManager.ORANGE_GEM, Models.GENERATED);
        itemModelGenerator.register(ItemManager.GOD_GEM, Models.GENERATED);
    }
}
