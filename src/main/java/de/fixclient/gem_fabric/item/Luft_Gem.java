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


}
