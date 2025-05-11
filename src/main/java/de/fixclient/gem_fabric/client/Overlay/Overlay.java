package de.fixclient.gem_fabric.client.Overlay;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;

public abstract class Overlay {
    private final String name;

    private boolean active = false;
    private double x;
    private double y;


    protected Overlay(String name) {
        this.name = name;
    }

    public abstract void tick();

    public abstract void render(DrawContext context);

    public boolean shouldRenderInGUI() {
        return false;
    }

    public @NotNull String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public int getX() {
        return (int) Math.floor(x);
    }

    public int getY() {
        return (int) Math.floor(y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
