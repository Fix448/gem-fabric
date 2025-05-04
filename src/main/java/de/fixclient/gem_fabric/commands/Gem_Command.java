package de.fixclient.gem_fabric.commands;


import com.mojang.brigadier.context.CommandContext;
import de.fixclient.gem_fabric.item.ItemManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Gem_Command {

    public static void register() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("gem")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.literal("start").executes(Gem_Command::start)));
        }));
    }

    private static int start (CommandContext<ServerCommandSource> context) {
        World world = context.getSource().getWorld();
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            BlockPos spawn = serverWorld.getSpawnPos();
            serverWorld.spawnEntity(new ItemEntity(world, spawn.getX(), spawn.getY() + 1, spawn.getZ(), new ItemStack(ItemManager.TELEPORT_GEM)));
            serverWorld.spawnEntity(new ItemEntity(world, spawn.getX(), spawn.getY() + 1, spawn.getZ(), new ItemStack(ItemManager.LUFT_GEM)));
            serverWorld.spawnEntity(new ItemEntity(world, spawn.getX(), spawn.getY() + 1, spawn.getZ(), new ItemStack(ItemManager.HEILUNGS_GEM)));
            serverWorld.spawnEntity(new ItemEntity(world, spawn.getX(), spawn.getY() + 1, spawn.getZ(), new ItemStack(ItemManager.ORANGE_GEM)));
        }
        return 1;
    }
}
