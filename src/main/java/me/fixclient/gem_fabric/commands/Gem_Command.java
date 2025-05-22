package me.fixclient.gem_fabric.commands;


import com.mojang.brigadier.context.CommandContext;
import me.fixclient.gem_fabric.Main;
import me.fixclient.gem_fabric.item.ItemManager;
import me.fixclient.gem_fabric.util.GemSettings;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Gem_Command {

    public static void register() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("gem")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("start").executes(Gem_Command::startStructure))
                .then(CommandManager.literal("drop").executes(Gem_Command::dropGem)))));
    }

    private static int startStructure(CommandContext<ServerCommandSource> context) {
        ServerWorld world = context.getSource().getWorld();
        if (!world.isClient) {
            BlockPos spawn = world.getSpawnPos();
            BlockPos modSpawn = new BlockPos(spawn.getX() -4, spawn.getY(), spawn.getZ() -4);
            StructureTemplateManager manager = world.getStructureTemplateManager();
            Identifier templateId = Identifier.of(Main.MOD_ID, "altar");
            StructureTemplate template = manager.getTemplate(templateId).orElseThrow(() -> new RuntimeException("Structure file couldn't found"));
            BlockPos pivot = new BlockPos(0, 0, 0);
            StructurePlacementData placementData = new StructurePlacementData()
                    .setRotation(BlockRotation.NONE)
                    .setMirror(BlockMirror.NONE)
                    .setIgnoreEntities(false);
            template.place(
                    world,
                    modSpawn,
                    pivot,
                    placementData,
                    context.getSource().getServer().getOverworld().getRandom(),
                    2
            );
        }
        return 1;
    }

    private static int dropGem(CommandContext<ServerCommandSource> context) {
        if (!context.getSource().getWorld().isClient) {
            ServerWorld serverWorld = context.getSource().getWorld();
            BlockPos spawn = serverWorld.getSpawnPos();
            List<ItemEntity> entityList = getItemEntities(serverWorld, spawn);
            for (ItemEntity itemEntity : entityList) {
                itemEntity.setVelocity(Vec3d.ZERO);
                serverWorld.spawnEntity(itemEntity);
            }
        }
        return 1;
    }

    private static @NotNull List<ItemEntity> getItemEntities(ServerWorld serverWorld, BlockPos spawn) {
        ItemEntity healing = new ItemEntity(serverWorld, spawn.getX() +0.5, spawn.getY()+ GemSettings.GEM_SPAWN_HEIGHT, spawn.getZ() +0.5, new ItemStack(ItemManager.HEALING_GEM));
        ItemEntity luft = new ItemEntity(serverWorld, spawn.getX()+0.5, spawn.getY()+ GemSettings.GEM_SPAWN_HEIGHT, spawn.getZ()+0.5, new ItemStack(ItemManager.AIR_GEM));
        ItemEntity orange = new ItemEntity(serverWorld, spawn.getX()+0.5, spawn.getY()+ GemSettings.GEM_SPAWN_HEIGHT, spawn.getZ()+0.5, new ItemStack(ItemManager.ORANGE_GEM));
        ItemEntity teleport = new ItemEntity(serverWorld, spawn.getX()+0.5, spawn.getY()+ GemSettings.GEM_SPAWN_HEIGHT, spawn.getZ()+0.5, new ItemStack(ItemManager.TELEPORT_GEM));
        return List.of(teleport, healing, luft, orange);
    }
}
