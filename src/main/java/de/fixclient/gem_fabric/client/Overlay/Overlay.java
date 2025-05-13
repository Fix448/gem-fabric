package de.fixclient.gem_fabric.client.Overlay;

import de.fixclient.gem_fabric.Main;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;

public abstract class Overlay{
    public static final Identifier healing_gem_texture = Identifier.of(Main.MOD_ID, "textures/item/heilungs_gem.png");
    public static final Identifier teleport_gem_texture = Identifier.of(Main.MOD_ID, "textures/item/teleport_gem.png");
    public static final Identifier orange_gem_texture = Identifier.of(Main.MOD_ID, "textures/item/luft_gem.png");
    public static final Identifier luft_gem_texture = Identifier.of(Main.MOD_ID, "textures/item/orange_gem.png");

    public List<Identifier> HUD = List.of(healing_gem_texture, teleport_gem_texture, orange_gem_texture, luft_gem_texture);

    public static HashMap<Identifier, Boolean> aviable = new HashMap<>();

}
