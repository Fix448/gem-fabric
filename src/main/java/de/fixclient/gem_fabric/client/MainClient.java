package de.fixclient.gem_fabric.client;

import de.fixclient.gem_fabric.client.Overlay.GemOverlay;
import de.fixclient.gem_fabric.util.Tags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class MainClient implements ClientModInitializer {
    public static final GemOverlay overlay = new GemOverlay();
    @Override
    public void onInitializeClient() {

        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            WorldRenderer worldRenderer = context.worldRenderer();
            MatrixStack matrices = context.matrixStack();
            VertexConsumerProvider.Immediate vertexConsumerProvider = client.getBufferBuilders().getEntityVertexConsumers();

            for (Entity entity : client.world.getEntities()) {
                if ((entity instanceof LivingEntity livingEntity)) {
                    for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
                        ItemStack stack = livingEntity.getEquippedStack(equipmentSlot);
                        if (stack.isIn(Tags.FIRST_GEM)) {

                        } else if (stack.isIn(Tags.SECOND_GEM)) {

                        } else if (stack.isIn(Tags.THIRD_GEM)) {

                        } else if (stack.isIn(Tags.FOURTH_GEM)) {

                        }
                    }
                }
            }
        });

    }
}
