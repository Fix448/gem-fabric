package me.fixclient.gem_fabric.item.season_2;

import me.fixclient.gem_fabric.item.Gem;
import me.fixclient.gem_fabric.util.GemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Teleportation_Gem extends Gem {

    public Teleportation_Gem(Settings settings) {
        super(settings);
    }
    /**
     * If the player is sneaking, then it works like a chorus fruit.
     * If not, it spawns an EnderPearlEntity*/
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            if (user.isInSneakingPose()) {
                for (int i = 0; i < 16; i++) {
                    double d = user.getX() + (user.getRandom().nextDouble() - 0.5) * 16.0D;
                    double e = Math.clamp(user.getY() + (user.getRandom().nextDouble() - 0.5) * 16.0D,
                            world.getBottomY(),
                            world.getBottomY() + serverWorld.getLogicalHeight() -1);
                    double f = user.getZ() + (user.getRandom().nextDouble() -0.5) * 16.0D;
                    if (user.hasVehicle()) user.stopRiding();
                    user.teleport(d, e, f, true);
                    SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    SoundCategory soundCategory = SoundCategory.PLAYERS;
                    world.playSound(null, user.getX(), user.getZ(), user.getY(), soundEvent, soundCategory);
                    user.onLanding();
                }
            } else {
                ProjectileEntity.spawnWithVelocity(EnderPearlEntity::new, serverWorld, user.getStackInHand(hand), user, 0.0f, 1.5f, 0);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1, GemSettings.TELEPORTATION_GEM_HASTE_AMPLIFIER));
        }
    }
}
