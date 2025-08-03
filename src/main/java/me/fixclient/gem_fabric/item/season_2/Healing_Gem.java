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
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class Healing_Gem extends Gem {
    public Healing_Gem(Settings settings) {
        super(settings);
    }

    /**
     * If the user is sneaking, Entities around it can't move, because they have slowness 127.
     * If not, the Player can't die for a half minute*/

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
                                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, GemSettings.SETTINGS.get("healing_gem_slowness_duration"), 127));
                            }
                        }
                    }
                }
            }
        } else {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, GemSettings.SETTINGS.get("healing_gem_invulnerability_duration"), 127));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, GemSettings.SETTINGS.get("healing_gem_invulnerability_duration")));

        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1, GemSettings.SETTINGS.get("healing_gem_regeneration_amplifier")));
        }
    }
}
