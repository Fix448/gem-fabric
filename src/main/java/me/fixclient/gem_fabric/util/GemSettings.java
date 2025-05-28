package me.fixclient.gem_fabric.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import me.fixclient.gem_fabric.Main;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class GemSettings {
    /**This declares the heigh where the Gem spawns over the World Spawn.
     *This is related to altar.nbt */
    public static int GEM_SPAWN_HEIGHT = 5;

    public static int HEALING_GEM_SLOWNESS_DURATION = 200;
    public static int HEALING_GEM_INVULNERABILITY_DURATION = 600;
    public static int HEALING_GEM_REGENERATION_AMPLIFIER = 1;

    public static int AIR_GEM_LEVITATION_DURATION = 1;
    public static int AIR_GEM_LEVITATION_AMPLIFIER = 127;
    public static int AIR_GEM_SLOW_FALLING_DURATION = 150;
    public static int AIR_GEM_DOLPHINS_GRACE_AMPLIFIER = 3;

    public static int ORANGE_GEM_LEVITATION_DURATION = 2;
    public static int ORANGE_GEM_RESISTANCE_AMPLIFIER = 1;

    public static int TELEPORTATION_GEM_HASTE_AMPLIFIER = 2;

    public static void saveSettings(Path path, Map<String, Integer> settings) {
        Gson gson = new Gson();
        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            gson.toJson(settings, new TypeToken<HashMap<String, Integer>>(){}.getType(), writer);
            Main.LOGGER.info(path.toString());
        }
        catch (IOException e) {
            Main.LOGGER.error("IOException in GemCommand", e);
        }
    }

    public static void loadSettings(Path path) {
        Gson gson = new Gson();
        try (Reader reader = Files.newBufferedReader(path)) {
            Map<String, Integer> settings = gson.fromJson(reader,  new TypeToken<Map<String, Integer>>(){}.getType());
            BiConsumer<String, Integer> restoreSettings = (s, integer) -> {
                switch (s) {
                    case "gem_spawn_height" -> GemSettings.GEM_SPAWN_HEIGHT = integer;
                    case "healing_gem_slowness_duration" -> GemSettings.HEALING_GEM_SLOWNESS_DURATION = integer;
                    case "healing_gem_invulnerability_duration" -> GemSettings.HEALING_GEM_INVULNERABILITY_DURATION = integer;
                    case "healing_gem_regeneration_amplifier" -> GemSettings.HEALING_GEM_REGENERATION_AMPLIFIER = integer;
                    case "air_gem_levitation_duration" -> GemSettings.AIR_GEM_LEVITATION_DURATION = integer;
                    case "air_gem_levitation_amplifier" -> GemSettings.AIR_GEM_LEVITATION_AMPLIFIER = integer;
                    case "air_gem_slow_falling_duration" -> GemSettings.AIR_GEM_SLOW_FALLING_DURATION = integer;
                    case "air_gem_dolphins_grace_amplifier" -> GemSettings.AIR_GEM_DOLPHINS_GRACE_AMPLIFIER = integer;
                    case "orange_gem_levitation_duration" -> GemSettings.ORANGE_GEM_LEVITATION_DURATION = integer;
                    case "orange_gem_resistance_amplifier" -> GemSettings.ORANGE_GEM_RESISTANCE_AMPLIFIER = integer;
                    case "teleportation_gem_haste_amplifier" -> GemSettings.TELEPORTATION_GEM_HASTE_AMPLIFIER = integer;
                }
            };
            settings.forEach(restoreSettings);
        }
        catch (IOException e) {
            Main.LOGGER.error("Error by GemSettings#loadSettings", e);
        }
    }
}