package de.fixclient.gem_fabric.item;

import de.fixclient.gem_fabric.Main;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public class ItemManager {

    public static final Item HEILUNGS_GEM = new Heilungs_Gem(createItemSettings(ItemNames.HEILUNGS_GEM_NAME).useCooldown(6.0f));
    public static final Item TELEPORT_GEM = new Teleport_Gem(createItemSettings(ItemNames.TELEPORT_GEM_NAME).useCooldown(6.0f));
    public static final Item LUFT_GEM = new Luft_Gem(createItemSettings(ItemNames.LUFT_GEM_NAME).useCooldown(6.0f));
    public static final Item ORANGE_GEM = new Orange_Gem(createItemSettings(ItemNames.ORANGE_GEM_NAME).useCooldown(6.0f));
    public static final Item GOD_GEM = new god_gem(createItemSettings(ItemNames.GOD_GEM_NAME));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM,
                Identifier.of(Main.MOD_ID, name),
                item);
    }

    private static Item.Settings createItemSettings(String name) {
        return new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, name)));
    }
    
}
