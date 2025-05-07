package de.fixclient.gem_fabric.util;

import de.fixclient.gem_fabric.Main;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class Tags {
    public static final TagKey<Item> FIRST_GEM = createTag("first_gem_tag");
    public static final TagKey<Item> SECOND_GEM = createTag("second_gem_tag");
    public static final TagKey<Item> THIRD_GEM = createTag("third_gem_tag");
    public static final TagKey<Item> FOURTH_GEM = createTag("fourth_gem_tag");
    public static final TagKey<Item> GOD_GEM = createTag("god_gem_tag");

    public static TagKey<Item> createTag(String path) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, path));
    }
}
