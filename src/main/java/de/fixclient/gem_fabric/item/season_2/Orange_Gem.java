package de.fixclient.gem_fabric.item.season_2;

import de.fixclient.gem_fabric.item.Gem;
import de.fixclient.gem_fabric.item.ItemOwners;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Orange_Gem extends Gem {
    public Orange_Gem(Settings settings) {
        super(settings);
    }



    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (user.isInSneakingPose()) {
            if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld) world;
                List<LivingEntity> entityList = serverWorld.getNonSpectatingEntities(LivingEntity.class, user.getBoundingBox().expand(4.0, 2.0, 4.0));
                if (!entityList.isEmpty()) {
                    for (LivingEntity livingEntity : entityList) {
                        if (!livingEntity.equals(user)) {
                            double distance = user.squaredDistanceTo(livingEntity);
                            if (distance < 16.0) {
                                Vec3d vec3d = livingEntity.getPos().subtract(user.getPos());
                                Vec3d multiplied = vec3d.normalize().multiply(2);
                                livingEntity.addVelocity(multiplied.x, 0.7, multiplied.z);
                            }
                        }
                    }
                }
            }
        } else {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 2, 127));
            ItemOwners.ON_USING_ORANGE_GEM = user;
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1, 1));
        }
    }
}
