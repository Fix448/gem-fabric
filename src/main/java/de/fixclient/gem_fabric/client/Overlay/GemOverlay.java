package de.fixclient.gem_fabric.client.Overlay;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class GemOverlay{
    public void renderHud(Entity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider) {
        MinecraftClient client = MinecraftClient.getInstance();
        double x = entity.getX() - client.getCameraEntity().getX();
        double y = entity.getY() + entity.getHeight() + 0.5 - client.getCameraEntity().getY();
        double z = entity.getZ() - client.getCameraEntity().getZ();

        matrices.push();
        matrices.translate(x, y, z);
        matrices.multiply(client.getEntityRenderDispatcher().getRotation());
        matrices.scale(-0.025f, -0.025f, -0.025f);
    }
}
