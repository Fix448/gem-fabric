package de.fixclient.gem_fabric.client.Overlay;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

import java.util.List;
import java.util.function.BiConsumer;

public class GemOverlay extends Overlay{

    public  void render(DrawContext context, RenderTickCounter counter) {

        for (Identifier identifier : HUD) {
            aviable.put(identifier, false);
        }

        List<Identifier> aviableElements = new ArrayList<>();

        BiConsumer<Identifier, Boolean> action = (key, value) -> {
            if (value) {
                aviableElements.add(key);
            }
        };

        aviable.forEach(action);

        for (Identifier element : aviableElements) {
            draw(aviableElements.toArray().length, element, context);
        }
    }

    private DrawContext draw(int iteration, Identifier element, DrawContext context) {
        int distance = iteration * 20;
        for (int i = 0; i<=distance; i = i +20) {
            context.drawTexture(RenderLayer::getGuiTextured, element, distance, 10, 0,0,16, 16, 16, 16);
        }
        return context;
    }
}
