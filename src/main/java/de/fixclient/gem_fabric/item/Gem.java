package de.fixclient.gem_fabric.item;

import de.fixclient.gem_fabric.util.Tags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class Gem extends Item {
    public Gem(Settings settings) {
        super(settings);
    }

    @Override
    public void onItemEntityDestroyed(@NotNull ItemEntity entity) {
        if (!entity.getWorld().isClient) {
            ServerWorld serverWorld = (ServerWorld) entity.getWorld();
            BlockPos spawn = serverWorld.getSpawnPos();
            serverWorld.spawnEntity(new ItemEntity(entity.getWorld(), spawn.getX(), spawn.getY() +1, spawn.getZ(), new ItemStack(this)));
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (stack.isIn(Tags.FIRST_GEM)) {
            ItemOwners.FIRST_GEM_OWNER = entity;
        } else if (stack.isIn(Tags.SECOND_GEM)) {
            ItemOwners.SECOND_GEM_OWNER = entity;
        } else if (stack.isIn(Tags.THIRD_GEM)) {
            ItemOwners.THIRD_GEM_OWNER = entity;
        } else if (stack.isIn(Tags.FOURTH_GEM)) {
            ItemOwners.FOURTH_GEM_OWNER = entity;
        }
    }

}
