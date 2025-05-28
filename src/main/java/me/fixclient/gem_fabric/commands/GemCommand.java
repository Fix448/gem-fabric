package me.fixclient.gem_fabric.commands;


import com.mojang.brigadier.arguments.IntegerArgumentType;
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
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class GemCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("gem")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("start").executes(GemCommand::startStructure))
                .then(CommandManager.literal("drop").executes(GemCommand::dropGem))
                .then(CommandManager.literal("settings")
                        .then(CommandManager.literal("general")
                                .then(CommandManager.literal("gem_spawn_height")
                                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                                .executes(commandContext -> {
                                                    GemSettings.GEM_SPAWN_HEIGHT = IntegerArgumentType.getInteger(commandContext, "value");
                                                    return 1;
                                                })
                                        )
                                )
                        )
                        .then(CommandManager.literal("healing_gem")
                                .then(CommandManager.literal("invulnerability_duration")
                                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                                .executes(commandContext -> {
                                                    GemSettings.HEALING_GEM_INVULNERABILITY_DURATION = IntegerArgumentType.getInteger(commandContext, "value");
                                                    return 1;
                                                })
                                        )
                                )
                                .then(CommandManager.literal("slowness_duration")
                                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                                .executes(commandContext -> {
                                                    GemSettings.HEALING_GEM_SLOWNESS_DURATION = IntegerArgumentType.getInteger(commandContext, "value");
                                                    return 1;
                                                })
                                        )
                                )
                                .then(CommandManager.literal("regeneration_amplifier")
                                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                                .executes(commandContext -> {
                                                    GemSettings.HEALING_GEM_REGENERATION_AMPLIFIER = IntegerArgumentType.getInteger(commandContext, "value");
                                                    return 1;
                                                })
                                        )
                                )
                        )
                        .then(CommandManager.literal("air_gem")
                                .then(CommandManager.literal("levitation_duration")
                                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                                .executes(commandContext -> {
                                                    GemSettings.AIR_GEM_LEVITATION_DURATION = IntegerArgumentType.getInteger(commandContext, "value");
                                                    return 1;
                                                })
                                        )
                                )
                                .then(CommandManager.literal("levitation_amplifier")
                                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                                .executes(commandContext -> {
                                                    GemSettings.AIR_GEM_LEVITATION_AMPLIFIER = IntegerArgumentType.getInteger(commandContext, "value");
                                                    return 1;
                                                })
                                        )
                                )
                                .then(CommandManager.literal("slow_falling_duration")
                                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                                .executes(commandContext -> {
                                                    GemSettings.AIR_GEM_SLOW_FALLING_DURATION = IntegerArgumentType.getInteger(commandContext, "value");
                                                    return 1;
                                                })
                                        )
                                )
                                .then(CommandManager.literal("dolphins_grace_amplifier")
                                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                                .executes(commandContext -> {
                                                    GemSettings.AIR_GEM_DOLPHINS_GRACE_AMPLIFIER = IntegerArgumentType.getInteger(commandContext, "value");
                                                    return 1;
                                                })
                                        )
                                )
                        )
                        .then(CommandManager.literal("orange_gem")
                                .then(CommandManager.literal("levitation_duration")
                                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                                .executes(commandContext -> {
                                                    GemSettings.ORANGE_GEM_LEVITATION_DURATION = IntegerArgumentType.getInteger(commandContext, "value");
                                                    return 1;
                                                })
                                        )
                                )
                                .then(CommandManager.literal("resistance_amplifier")
                                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                                .executes(commandContext -> {
                                                    GemSettings.ORANGE_GEM_RESISTANCE_AMPLIFIER = IntegerArgumentType.getInteger(commandContext, "value");
                                                    return 1;
                                                })
                                        )
                                )
                        )
                        .then(CommandManager.literal("teleportation_gem")
                                .then(CommandManager.literal("haste_amplifier")
                                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                                                .executes(commandContext -> {
                                                    GemSettings.TELEPORTATION_GEM_HASTE_AMPLIFIER = IntegerArgumentType.getInteger(commandContext, "value");
                                                    return 1;
                                                })
                                        )
                                )
                        )
                )
                .then(CommandManager.literal("save")
                        .executes(GemCommand::save)
                )
        )));
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

    private static int save(CommandContext<ServerCommandSource> context) {
        Path path = Path.of(context.getSource().getServer().getSavePath(WorldSavePath.ROOT) + "/gem_settings.json");
        Main.LOGGER.info(path.toString());
        Map<String, Integer> settings = new java.util.HashMap<>(Map.of(
                "gem_spawn_height", GemSettings.GEM_SPAWN_HEIGHT,
                "healing_gem_slowness_duration", GemSettings.HEALING_GEM_SLOWNESS_DURATION,
                "healing_gem_invulnerability_duration", GemSettings.HEALING_GEM_INVULNERABILITY_DURATION,
                "healing_gem_regeneration_amplifier", GemSettings.HEALING_GEM_REGENERATION_AMPLIFIER,
                "air_gem_levitation_duration", GemSettings.AIR_GEM_LEVITATION_DURATION,
                "air_gem_levitation_amplifier", GemSettings.AIR_GEM_LEVITATION_AMPLIFIER,
                "air_gem_slow_falling_duration", GemSettings.AIR_GEM_SLOW_FALLING_DURATION,
                "air_gem_dolphins_grace_amplifier", GemSettings.AIR_GEM_DOLPHINS_GRACE_AMPLIFIER,
                "orange_gem_levitation_duration", GemSettings.ORANGE_GEM_LEVITATION_DURATION,
                "orange_gem_resistance_amplifier", GemSettings.ORANGE_GEM_RESISTANCE_AMPLIFIER
        ));
        settings.put("teleportation_gem_haste_amplifier", GemSettings.TELEPORTATION_GEM_HASTE_AMPLIFIER);
        GemSettings.saveSettings(path, settings);
        return 1;
    }
}
