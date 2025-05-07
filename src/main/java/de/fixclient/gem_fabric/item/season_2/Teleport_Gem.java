package de.fixclient.gem_fabric.item.season_2;

import de.fixclient.gem_fabric.item.ItemManager;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
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

    @Override
    public void onItemEntityDestroyed(ItemEntity entity) {
        if (!entity.getWorld().isClient) {
            ServerWorld serverWorld = (ServerWorld) entity.getWorld();
            BlockPos spawn = serverWorld.getSpawnPos();
            serverWorld.spawnEntity(new ItemEntity(entity.getWorld(), spawn.getX() , spawn.getY() +1, spawn.getZ(), new ItemStack(ItemManager.TELEPORT_GEM)));
        }
    }
}
