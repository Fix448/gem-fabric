package me.fixclient.gem_fabric.item;

import me.fixclient.gem_fabric.Main;
import me.fixclient.gem_fabric.item.season_2.*;
import me.fixclient.gem_fabric.util.GemSets;
import me.fixclient.gem_fabric.util.GemSettings;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.Map;


public class ItemManager {

    public static final Item HEALING_GEM = registerGem("healing_gem",
            new Healing_Gem(createItemSettings("healing_gem").useCooldown(6.0f), "item.gem_fabric.healing_gem"),
            "season_2",
            Map.of("healing_gem_slowness_duration", 200,
                    "healing_gem_invulnerability_duration", 600,
                    "healing_gem_regeneration_amplifier", 1),
            "item.gem_fabric.healing_gem");

    public static final Item TELEPORT_GEM = registerGem("teleport_gem",
            new Teleportation_Gem(createItemSettings("teleport_gem").useCooldown(6.0f), "item.gem_fabric.teleport_gem"),
            "season_2",
            Map.of("teleportation_gem_haste_amplifier", 2),
            "item.gem_fabric.teleport_gem");

    public static final Item AIR_GEM = registerGem("air_gem",
            new Air_Gem(createItemSettings("air_gem").useCooldown(6.0f), "item.gem_fabric.air_gem"),
            "season_2",
            Map.of("air_gem_levitation_duration", 1,
                    "air_gem_levitation_amplifier", 127,
                    "air_gem_slow_falling_duration", 150,
                    "air_gem_dolphins_grace_amplifier", 3),
            "item.gem_fabric.air_gem");

    public static final Item ORANGE_GEM = registerGem("orange_gem",
            new Orange_Gem(createItemSettings("orange_gem").useCooldown(6.0f), "item.gem_fabric.orange_gem"),
            "season_2",
            Map.of("orange_gem_resistance_amplifier", 1,
                    "orange_gem_levitation_duration", 1),
            "item.gem_fabric.orange_gem");

    public static final Item GOD_GEM = registerGem("god_gem",
            new god_gem(createItemSettings("god_gem"), "item.gem_fabric.god_gem"),
            "season_2",
            Map.of(),
            "item.gem_fabric.god_gem");

    public static Item registerGem(String name, Item item, String setName, Map<String, Integer> settings, String translatableKey) {

        GemSets.addGemToSet(setName, item);

        GemSettings.SETTINGS.putAll(settings);

        ItemOwners.itemOwners.put(translatableKey, null);

        return Registry.register(Registries.ITEM,
                Identifier.of(Main.MOD_ID, name),
                item);
    }

    private static Item.Settings createItemSettings(String name) {
        return new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, name)));
    }

    @SuppressWarnings("unused")
    public static void initialize() {}
}
