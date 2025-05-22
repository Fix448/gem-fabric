package me.fixclient.gem_fabric.item;

import me.fixclient.gem_fabric.Main;
import me.fixclient.gem_fabric.item.season_2.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public class ItemManager {

    public static final Item HEALING_GEM = new Healing_Gem(createItemSettings(ItemNames.HEALING_GEM_NAME).useCooldown(6.0f));
    public static final Item TELEPORT_GEM = new Teleportation_Gem(createItemSettings(ItemNames.TELEPORT_GEM_NAME).useCooldown(6.0f));
    public static final Item AIR_GEM = new Air_Gem(createItemSettings(ItemNames.LUFT_GEM_NAME).useCooldown(6.0f));
    public static final Item ORANGE_GEM = new Orange_Gem(createItemSettings(ItemNames.ORANGE_GEM_NAME).useCooldown(6.0f));
    public static final Item GOD_GEM = new god_gem(createItemSettings(ItemNames.GOD_GEM_NAME));

    public static void registerItem(String name, Item item) {
        Registry.register(Registries.ITEM,
                Identifier.of(Main.MOD_ID, name),
                item);
    }

    private static Item.Settings createItemSettings(String name) {
        return new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, name)));
    }
}
