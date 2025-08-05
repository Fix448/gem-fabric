package me.fixclient.gem_fabric.commands;

import com.mojang.brigadier.context.CommandContext;
import me.fixclient.gem_fabric.Main;
import me.fixclient.gem_fabric.item.ItemOwners;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.function.BiConsumer;

public class StatusCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> commandDispatcher.register(CommandManager.literal("status").executes(StatusCommand::status)));
    }

    private static int status(CommandContext<ServerCommandSource> context) {
        var player = context.getSource().getPlayer();
        BiConsumer<String, PlayerEntity> sendMessages = ((itemName, GemOwner) -> {
            if (!(GemOwner == null)) {
                player.sendMessage(Text.translatable(itemName)
                        .append(Text.translatable("command.gem_fabric.status_filling"))
                        .append(GemOwner.getName()));
            }
        });
        ItemOwners.itemOwners.forEach(sendMessages);
        return 1;
    }
}
