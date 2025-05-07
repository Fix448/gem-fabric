package de.fixclient.gem_fabric;

import de.fixclient.gem_fabric.commands.Gem_Command;
import de.fixclient.gem_fabric.item.ItemManager;
import de.fixclient.gem_fabric.item.ItemNames;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.entity.VaultBlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.session.report.ReporterEnvironment;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

    public final static String MOD_ID = "gem_fabric";
    public final static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("registering Items");
        ItemManager.registerItem(ItemNames.HEILUNGS_GEM_NAME, ItemManager.HEILUNGS_GEM);
        ItemManager.registerItem(ItemNames.TELEPORT_GEM_NAME, ItemManager.TELEPORT_GEM);
        ItemManager.registerItem(ItemNames.LUFT_GEM_NAME, ItemManager.LUFT_GEM);
        ItemManager.registerItem(ItemNames.ORANGE_GEM_NAME, ItemManager.ORANGE_GEM);
        ItemManager.registerItem(ItemNames.GOD_GEM_NAME, ItemManager.GOD_GEM);

        ItemManager.registerItem(ItemNames.CRASH_GEM_NAME, ItemManager.CRASH_GEM);


        ItemTooltipCallback.EVENT.register(((itemStack, tooltipContext, tooltipType, list) -> {
            if (itemStack.isOf(ItemManager.ORANGE_GEM)) {
                if (Screen.hasShiftDown()) {
                    list.add(Text.translatable("tooltip.gem_fabric.orange_gem.shift-down"));
                } else {
                    list.add(Text.translatable("tooltip.gem_fabric.not-shift-down"));
                }
            } else if (itemStack.isOf(ItemManager.LUFT_GEM)) {
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
            } else if (itemStack.isOf(ItemManager.HEILUNGS_GEM)) {
                if (Screen.hasShiftDown()) {
                    list.add(Text.translatable("tooltip.gem_fabric.heilungs_gem.shift-down"));
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

        Gem_Command.register();

    }
}
