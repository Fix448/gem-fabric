package me.fixclient.gem_fabric.item;

import me.fixclient.gem_fabric.util.GemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
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

    /**
     * This affects, that a destroyed ItemEntity instantly spawns at the WorldSpawn*/

    @Override
    public void onItemEntityDestroyed(@NotNull ItemEntity entity) {
        if (!entity.getWorld().isClient) {
            ServerWorld serverWorld = (ServerWorld) entity.getWorld();
            BlockPos spawn = serverWorld.getSpawnPos();
            serverWorld.spawnEntity(new ItemEntity(entity.getWorld(), spawn.getX(), spawn.getY() + GemSettings.SETTINGS.get("gem_spawn_height"), spawn.getZ(), new ItemStack(this)));
        }
    }

    /**
     * This Method is needed for the {@link me.fixclient.gem_fabric.commands.StatusCommand}*/
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof PlayerEntity playerEntity) {
            if (stack.isOf(ItemManager.HEALING_GEM)) {
                ItemOwners.HEALING_GEM_OWNER = playerEntity;
            } else if (stack.isOf(ItemManager.AIR_GEM)) {
                ItemOwners.AIR_GEM_OWNER = playerEntity;
            } else if (stack.isOf(ItemManager.TELEPORT_GEM)) {
                ItemOwners.TELEPORT_GEM_OWNER = playerEntity;
            } else if (stack.isOf(ItemManager.ORANGE_GEM)) {
                ItemOwners.ORANGE_GEM_OWNER = playerEntity;
            }
        }
    }
}
