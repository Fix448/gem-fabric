package me.fixclient.gem_fabric.item.season_2;

import me.fixclient.gem_fabric.item.Gem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

public class god_gem extends Gem {
    public god_gem(Settings settings, String translatableKey) {
        super(settings, false, translatableKey);
    }

    /** This item gives you OP and you get game mode creative */
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            MinecraftServer server = user.getServer();
            assert server != null;
            PlayerManager playerManager = server.getPlayerManager();
            playerManager.addToOperators(user.getGameProfile());
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
            serverPlayer.changeGameMode(GameMode.CREATIVE);
        }
        return ActionResult.SUCCESS;
    }
}
