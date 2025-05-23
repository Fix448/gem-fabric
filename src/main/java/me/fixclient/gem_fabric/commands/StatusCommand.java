package me.fixclient.gem_fabric.commands;

import com.mojang.brigadier.context.CommandContext;
import me.fixclient.gem_fabric.item.ItemOwners;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class StatusCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> commandDispatcher.register(CommandManager.literal("status").executes(StatusCommand::status)));
    }

    private static int status(CommandContext<ServerCommandSource> context) {
        var player = context.getSource().getPlayer();
        if (!(player == null)) {
            if (!(ItemOwners.HEALING_GEM_OWNER == null)) {
                player.sendMessage(Text.translatable("item.gem_fabric.healing_gem")
                        .append(Text.translatable("command.gem_fabric.status_filling"))
                        .append(ItemOwners.HEALING_GEM_OWNER.getName()));
            }
            if (!(ItemOwners.TELEPORT_GEM_OWNER == null)) {
                player.sendMessage(Text.translatable("item.gem_fabric.luft_gem")
                        .append(Text.translatable("command.gem_fabric.status_filling"))
                        .append(ItemOwners.TELEPORT_GEM_OWNER.getName()));
            }

            if (!(ItemOwners.AIR_GEM_OWNER == null)) {
                player.sendMessage(Text.translatable("item.gem_fabric.teleport_gem")
                        .append(Text.translatable("command.gem_fabric.status_filling"))
                        .append(ItemOwners.AIR_GEM_OWNER.getName()));
            }

            if (!(ItemOwners.ORANGE_GEM_OWNER == null)) {
                player.sendMessage(Text.translatable("item.gem_fabric.orange_gem")
                        .append(Text.translatable("command.gem_fabric.status_filling"))
                        .append(ItemOwners.ORANGE_GEM_OWNER.getName()));
            }
        }
        return 1;
    }
}
