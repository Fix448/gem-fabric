package de.fixclient.gem_fabric.client.Overlay;

import de.fixclient.gem_fabric.Main;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class GemOverlay extends Overlay{
    protected GemOverlay() {
        super("gems");
        setX(10);
        setY(10);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(DrawContext context, TextRenderer font) {
        Identifier healing_gem_texture = Identifier.of(Main.MOD_ID, "textures/item/heilungs_gem");
        Identifier teleport_gem_texture = Identifier.of(Main.MOD_ID, "textures/item/heilungs_gem");
        Identifier orange_gem_texture = Identifier.of(Main.MOD_ID, "textures/item/heilungs_gem");
        Identifier luft_gem_texture = Identifier.of(Main.MOD_ID, "textures/item/heilungs_gem");

        context.drawTexture(RenderLayer::getGuiTextured, healing_gem_texture, getX(), getY(), 0, 0, 16, 16, 16, 16);
        context.drawTexture(RenderLayer::getGuiTextured, teleport_gem_texture, getX(), getY(), 0, 0, 16, 16, 16, 16);
        context.drawTexture(RenderLayer::getGuiTextured, orange_gem_texture, getX(), getY(), 0, 0, 16, 16, 16, 16);
        context.drawTexture(RenderLayer::getGuiTextured, luft_gem_texture, getX(), getY(), 0, 0, 16, 16, 16, 16);
    }
}
