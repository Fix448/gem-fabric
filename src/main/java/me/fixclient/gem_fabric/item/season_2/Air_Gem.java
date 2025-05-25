package me.fixclient.gem_fabric.item.season_2;

import me.fixclient.gem_fabric.item.Gem;
import me.fixclient.gem_fabric.util.GemSettings;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Air_Gem extends Gem {
    public Air_Gem(Settings settings) {
        super(settings);
    }

    /**
     * If the user is sneaking, then he gets Levitation 127 and flies slowly down.
     * If not, the LivingEntities around it gets Levitation 127 and fall down, <strong>but without any Slow Falling</strong>*/

    @Override
    public ActionResult use(World world, @NotNull PlayerEntity user, Hand hand) {
        if (user.isInSneakingPose()) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, GemSettings.AIR_GEM_LEVITATION_DURATION, GemSettings.AIR_GEM_LEVITATION_AMPLIFIER));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, GemSettings.AIR_GEM_SLOW_FALLING_DURATION, 2));
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

    /** This gives Dolphins Grace and the livingEntity gets no fall damage({@code livingEntity.damage(world, livingEntity.getDamageSources().fall(), 0);})*/

    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.damage(world, livingEntity.getDamageSources().fall(), 0);
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 1, GemSettings.AIR_GEM_DOLPHINS_GRACE_AMPLIFIER));
        }
    }
}
