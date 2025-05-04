package de.fixclient.gem_fabric;

import de.fixclient.gem_fabric.commands.Gem_Command;
import de.fixclient.gem_fabric.item.ItemManager;
import de.fixclient.gem_fabric.item.ItemNames;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.text.Text;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.ServerWorldAccess;
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
            }
        }));

        Gem_Command.register();

        ServerWorldEvents.LOAD.register(((server, world) -> {
            ServerWorld serverWorld = (ServerWorld) world;
            StructureTemplateManager manager = serverWorld.getStructureTemplateManager();
            Identifier templateId = Identifier.of(MOD_ID, "altar");
            StructureTemplate template = manager.getTemplate(templateId).orElseThrow(() -> new RuntimeException("Struktur konnte nicht gefunden werden"));
            Vec3i size = template.getSize();
            BlockPos pivot = new BlockPos(size.getX() / 2, 0, size.getZ());
            StructurePlacementData placementData = new StructurePlacementData()
                    .setRotation(BlockRotation.NONE)
                    .setMirror(BlockMirror.NONE)
                    .setIgnoreEntities(false);
            BlockPos spawn = serverWorld.getSpawnPos();
            template.place(
                    (ServerWorldAccess) serverWorld,
                    spawn,
                    pivot,
                    placementData,
                    server.getOverworld().getRandom(),
                    2
            );
            serverWorld.setSpawnPos(new BlockPos(spawn.getX(), spawn.getY() + 4, spawn.getZ()), serverWorld.getSpawnAngle());
        }));
    }
}
