package de.fixclient.gem_fabric.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class Orange_Gem extends Item {
    public Orange_Gem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 5));
            List<LivingEntity> entityList = serverWorld.getNonSpectatingEntities(LivingEntity.class, user.getBoundingBox().expand(4.0, 2.0, 4.0));
            if (!entityList.isEmpty()) {
                for (LivingEntity livingEntity : entityList) {
                    double distance = user.squaredDistanceTo(livingEntity);
                    if (distance < 16.0) {
                        double f = livingEntity.fallDistance;
                        double g = 22.0 + f - 8.0;
                        livingEntity.damage(serverWorld, livingEntity.getDamageSources().maceSmash(user), (float) g);
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }
}
