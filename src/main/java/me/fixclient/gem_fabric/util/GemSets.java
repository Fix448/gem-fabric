package me.fixclient.gem_fabric.util;

import me.fixclient.gem_fabric.Main;
import net.minecraft.item.Item;

import java.util.*;

public class GemSets {


    private static final Map<String, List<Item>> allGemSets = new HashMap<>();

    public static void registerGemSet(String name, List<Item> list) {
        allGemSets.put(name, list);
    }

    public static void registerGemSet(String name) {registerGemSet(name, new ArrayList<>());}

    public static Map<String, List<Item>> getAllGemSets() {
        return allGemSets;
    }

    public static void addGemToSet(String name, Item item) {
        allGemSets.get(name).add(item);
    }
}
