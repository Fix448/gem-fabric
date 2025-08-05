package me.fixclient.gem_fabric.util;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GemSets {


    private static final Map<String, Set<Item>> allGemSets = new HashMap<>();

    public static void registerGemSet(String name, Set<Item> set) {
        allGemSets.put(name, set);
    }

    public static void registerGemSet(String name) {registerGemSet(name, new HashSet<>());}

    public static Map<String, Set<Item>> getAllGemSets() {
        return allGemSets;
    }

    public static void addGemToSet(String name, Item item) {
        allGemSets.get(name).add(item);
    }
}
