package de.fixclient.gem_fabric.item.season_2;

import de.fixclient.gem_fabric.item.Gem;
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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Luft_Gem extends Gem {
    public Luft_Gem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (user.isInSneakingPose()) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 1, 127));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 600, 1));
        } else {
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
        }

        return ActionResult.SUCCESS;
    }

    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.damage(world, livingEntity.getDamageSources().fall(), 0);
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 1, 3));
        }
    }
}
