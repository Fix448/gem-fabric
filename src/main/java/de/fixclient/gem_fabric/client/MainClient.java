package de.fixclient.gem_fabric.client;

import de.fixclient.gem_fabric.client.Overlay.GemOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;

public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //HudLayerRegistrationCallback.EVENT.register(layeredDrawerWrapper -> layeredDrawerWrapper.attachLayerBefore(IdentifiedLayer.CHAT, GemOverlay::render));
    }
}
