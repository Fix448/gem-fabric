package de.fixclient.gem_fabric.item.season_2;

import de.fixclient.gem_fabric.item.Gem;
import de.fixclient.gem_fabric.item.ItemManager;
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

public class Orange_Gem extends Gem {
    public Orange_Gem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            List<LivingEntity> entityList = serverWorld.getNonSpectatingEntities(LivingEntity.class, user.getBoundingBox().expand(4.0, 2.0, 4.0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 10));
            if (!entityList.isEmpty()) {
                if (user.isOnGround()) {
                    for (LivingEntity livingEntity : entityList) {
                        double distance = user.squaredDistanceTo(livingEntity);
                        if (!livingEntity.equals(user)) {
                            if (distance < 16.0) {
                                double f = livingEntity.fallDistance;
                                double g = 22.0 + f - 8.0;
                                livingEntity.damage(serverWorld, livingEntity.getDamageSources().maceSmash(user), (float) g);
                            }
                        }

                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }
}
