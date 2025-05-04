package de.fixclient.gem_fabric.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class Luft_Gem extends Item {
    public Luft_Gem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            List<LivingEntity> entityList = serverWorld.getNonSpectatingEntities(LivingEntity.class, user.getBoundingBox().expand(4.0, 2.0, 4.0));
            if (!entityList.isEmpty()) {
                for (LivingEntity livingEntity : entityList) {
                    if (!livingEntity.equals(user)) {
                        double distance = user.squaredDistanceTo(livingEntity);
                        if (distance < 16.0) {
                            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 5, 127));
                        }

                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onItemEntityDestroyed(ItemEntity entity) {
        if (!entity.getWorld().isClient) {
            ServerWorld serverWorld = (ServerWorld) entity.getWorld();
            BlockPos spawn = serverWorld.getSpawnPos();
            serverWorld.spawnEntity(new ItemEntity(entity.getWorld(), spawn.getX(), spawn.getY(), spawn.getZ(), new ItemStack(ItemManager.LUFT_GEM)));
        }
    }
}
