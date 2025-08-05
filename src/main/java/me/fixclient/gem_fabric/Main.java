package me.fixclient.gem_fabric;

import me.fixclient.gem_fabric.commands.GemCommand;
import me.fixclient.gem_fabric.commands.StatusCommand;
import me.fixclient.gem_fabric.item.ItemManager;
import me.fixclient.gem_fabric.item.ItemOwners;
import me.fixclient.gem_fabric.util.GemSets;
import me.fixclient.gem_fabric.util.GemSettings;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.WorldSavePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

public class Main implements ModInitializer {

    public final static String MOD_ID = "gem_fabric";
    public final static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        GemSets.registerGemSet("season_2");
        LOGGER.info("Registering Items");
        ItemManager.initialize();
        LOGGER.info("Registering Items done");

        GemCommand.register();
        StatusCommand.register();

        ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
            for (ServerPlayerEntity serverPlayer : minecraftServer.getPlayerManager().getPlayerList()) {
                if (serverPlayer.equals(ItemOwners.ON_USING_ORANGE_GEM)) {
                    ServerWorld serverWorld = serverPlayer.getWorld();
                    List<LivingEntity> entityList = serverWorld.getNonSpectatingEntities(LivingEntity.class, serverPlayer.getBoundingBox().expand(4.0, 2.0, 4.0));
                    if (!entityList.isEmpty()) {
                        for (LivingEntity livingEntity : entityList) {
                            if (!livingEntity.equals(ItemOwners.ON_USING_ORANGE_GEM)) {
                                double distance = serverPlayer.squaredDistanceTo(livingEntity);
                                if (distance < 16.0d) {
                                    double f = serverPlayer.fallDistance;
                                    if (serverPlayer.isOnGround() && f > 1.5) {
                                        double g = 22.0 + f - 8.0;
                                        livingEntity.damage(serverWorld, livingEntity.getDamageSources().maceSmash(ItemOwners.ON_USING_ORANGE_GEM), (float) g);
                                        ItemOwners.ON_USING_ORANGE_GEM = null;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        ServerLifecycleEvents.SERVER_STARTED.register(server -> GemSettings.loadSettings(Path.of(server.getSavePath(WorldSavePath.ROOT) + "/gem_settings.json")));


    }
}
