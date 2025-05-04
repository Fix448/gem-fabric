package de.fixclient.gem_fabric.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Teleport_Gem extends Item {

    public Teleport_Gem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            ProjectileEntity.spawnWithVelocity(EnderPearlEntity::new, serverWorld, user.getStackInHand(hand), user, 0.0f, 1.5f, 1.0F);
        }
        return ActionResult.SUCCESS;
    }
}
