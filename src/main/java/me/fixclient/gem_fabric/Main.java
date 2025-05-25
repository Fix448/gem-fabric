package me.fixclient.gem_fabric;

import me.fixclient.gem_fabric.commands.GemCommand;
import me.fixclient.gem_fabric.commands.StatusCommand;
import me.fixclient.gem_fabric.item.ItemManager;
import me.fixclient.gem_fabric.item.ItemNames;
import me.fixclient.gem_fabric.item.ItemOwners;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main implements ModInitializer {

    public final static String MOD_ID = "gem_fabric";
    public final static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("registering Items");
        ItemManager.registerItem(ItemNames.HEALING_GEM_NAME, ItemManager.HEALING_GEM);
        ItemManager.registerItem(ItemNames.TELEPORT_GEM_NAME, ItemManager.TELEPORT_GEM);
        ItemManager.registerItem(ItemNames.LUFT_GEM_NAME, ItemManager.AIR_GEM);
        ItemManager.registerItem(ItemNames.ORANGE_GEM_NAME, ItemManager.ORANGE_GEM);
        ItemManager.registerItem(ItemNames.GOD_GEM_NAME, ItemManager.GOD_GEM);


        ItemTooltipCallback.EVENT.register(((itemStack, tooltipContext, tooltipType, list) -> {
            if (itemStack.isOf(ItemManager.ORANGE_GEM)) {
                if (Screen.hasShiftDown()) {
                    list.add(Text.translatable("tooltip.gem_fabric.orange_gem.shift-down"));
                } else {
                    list.add(Text.translatable("tooltip.gem_fabric.not-shift-down"));
                }
            } else if (itemStack.isOf(ItemManager.AIR_GEM)) {
                if (Screen.hasShiftDown()) {
                    list.add(Text.translatable("tooltip.gem_fabric.luft_gem.shift-down"));
                }else {
                    list.add(Text.translatable("tooltip.gem_fabric.not-shift-down"));
                }
            } else if (itemStack.isOf(ItemManager.TELEPORT_GEM)) {
                if (Screen.hasShiftDown()) {
                    list.add(Text.translatable("tooltip.gem_fabric.teleport_gem.shift-down"));
                }else {
                    list.add(Text.translatable("tooltip.gem_fabric.not-shift-down"));
                }
            } else if (itemStack.isOf(ItemManager.HEALING_GEM)) {
                if (Screen.hasShiftDown()) {
                    list.add(Text.translatable("tooltip.gem_fabric.healing_gem.shift-down"));
                }else {
                    list.add(Text.translatable("tooltip.gem_fabric.not-shift-down"));
                }
            } else if (itemStack.isOf(ItemManager.GOD_GEM)) {
                if (Screen.hasShiftDown()) {
                    list.add(Text.translatable("tooltip.gem_fabric.god_gem.shift-down"));
                }else {
                    list.add(Text.translatable("tooltip.gem_fabric.not-shift-down"));
                }
            }
        }));

        GemCommand.register();
        StatusCommand.register();

        ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
            for (ServerPlayerEntity serverPlayer : minecraftServer.getPlayerManager().getPlayerList()) {
                if (serverPlayer.equals(ItemOwners.ON_USING_ORANGE_GEM)) {
                    ServerWorld serverWorld = serverPlayer.getServerWorld();
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

    }
}
