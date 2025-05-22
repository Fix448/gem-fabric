package me.fixclient.gem_fabric.util;

import me.fixclient.gem_fabric.Main;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
/** This is for tags. It is in an alpha status and it is <strong>not</strong> finished*/
public class Tags {
    public static final TagKey<Item> FIRST_GEM = createTag("tag.item.gem_fabric.first_gem_tag");
    public static final TagKey<Item> SECOND_GEM = createTag("tag.item.gem_fabric.second_gem_tag");
    public static final TagKey<Item> THIRD_GEM = createTag("tag.item.gem_fabric.third_gem_tag");
    public static final TagKey<Item> FOURTH_GEM = createTag("tag.item.gem_fabric.fourth_gem_tag");
    public static final TagKey<Item> GOD_GEM = createTag("tag.item.gem_fabric.god_gem_tag");

    public static TagKey<Item> createTag(String path) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, path));
    }
}
