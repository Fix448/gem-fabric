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

public class GemSettings {

    public static final Map<String, Integer> SETTINGS = new HashMap<>();

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
            SETTINGS.putAll(settings);
        }
        catch (IOException e) {
            Main.LOGGER.error("Error by GemSettings#loadSettings", e);
        }
    }

    public static void createSettings() {
        SETTINGS.put("gem_spawn_height", 5);
    }
}