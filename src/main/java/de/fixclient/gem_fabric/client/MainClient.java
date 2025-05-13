package de.fixclient.gem_fabric.client;

import de.fixclient.gem_fabric.Main;
import de.fixclient.gem_fabric.client.Overlay.GemOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.util.Identifier;

public class MainClient implements ClientModInitializer {
    public static final GemOverlay overlay = new GemOverlay();
    @Override
    public void onInitializeClient() {


        Identifier layer = Identifier.of(Main.MOD_ID, "gem_overlay");
        HudLayerRegistrationCallback.EVENT.register(layeredDrawerWrapper -> layeredDrawerWrapper.attachLayerBefore(IdentifiedLayer.CHAT, layer, overlay::render));
    }
}
