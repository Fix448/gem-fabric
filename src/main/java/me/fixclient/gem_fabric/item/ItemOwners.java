package me.fixclient.gem_fabric.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * This class saves some information about the gems */
public class ItemOwners {
    public static PlayerEntity ON_USING_ORANGE_GEM;

    public static Map<String, PlayerEntity> itemOwners = new HashMap<>();
}
