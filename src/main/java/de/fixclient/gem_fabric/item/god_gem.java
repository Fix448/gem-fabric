package de.fixclient.gem_fabric.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class god_gem extends Item {
    public god_gem(Settings settings) {
        super(settings);
    }

    PlayerEntity player;

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            MinecraftServer server = user.getServer();
            PlayerManager playerManager = server.getPlayerManager();
            playerManager.addToOperators(user.getGameProfile());
            this.player = user;
        }
        return ActionResult.SUCCESS;
    }
}
