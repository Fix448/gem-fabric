package me.fixclient.gem_fabric.commands;


import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import me.fixclient.gem_fabric.Main;
import me.fixclient.gem_fabric.util.GemSetSuggester;
import me.fixclient.gem_fabric.util.GemSets;
import me.fixclient.gem_fabric.util.GemSettings;
import me.fixclient.gem_fabric.util.GemSettingsSuggester;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
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
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GemCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("gem")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("start").executes(GemCommand::startStructure))
                .then(CommandManager.literal("drop")
                        .then(CommandManager.argument("set", StringArgumentType.string())
                                .suggests(new GemSetSuggester())
                                .executes(GemCommand::dropGem))
                        )
                .then(CommandManager.literal("settings")
                        .then(CommandManager.argument("setting", StringArgumentType.string())
                                .suggests(new GemSettingsSuggester())
                                .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                        .executes(GemCommand::settings)
                                )
                        )
                )
                .then(CommandManager.literal("save")
                        .executes(GemCommand::save)
                )
        )

        ));
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
            String set = StringArgumentType.getString(context, "set");
            List<ItemEntity> entityList = getItemEntities(serverWorld, spawn, set);
            for (ItemEntity itemEntity : entityList) {
                itemEntity.setVelocity(Vec3d.ZERO);
                serverWorld.spawnEntity(itemEntity);
            }
        }
        return 1;
    }

    private static @NotNull List<ItemEntity> getItemEntities(ServerWorld serverWorld, BlockPos spawn, String set) {
        List<ItemEntity> entityList = new ArrayList<>();
        for (Item item : GemSets.getAllGemSets().get(set)) {
            ItemEntity itemEntity = new ItemEntity(serverWorld, spawn.getX() + 0.5, spawn.getY() + GemSettings.SETTINGS.get("gem_spawn_height"), spawn.getZ() + 0.5, new ItemStack(item));
            entityList.add(itemEntity);
        }
        return entityList;
    }

    private static int save(CommandContext<ServerCommandSource> context) {
        Path path = Path.of(context.getSource().getServer().getSavePath(WorldSavePath.ROOT) + "/gem_settings.json");
        Main.LOGGER.info(path.toString());
        GemSettings.saveSettings(path, GemSettings.SETTINGS);
        return 1;
    }

    private static int settings(CommandContext<ServerCommandSource> context) {
        String setting = StringArgumentType.getString(context, "setting");
        int value = IntegerArgumentType.getInteger(context, "value");
        if (GemSettings.SETTINGS.containsKey(setting)) {
            GemSettings.SETTINGS.put(setting, value);
        }
        return 1;
    }
}
