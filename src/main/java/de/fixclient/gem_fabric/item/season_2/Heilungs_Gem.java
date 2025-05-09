package de.fixclient.gem_fabric.item.season_2;

import de.fixclient.gem_fabric.item.Gem;
import de.fixclient.gem_fabric.item.ItemManager;
import net.minecraft.entity.ItemEntity;
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



public class Heilungs_Gem extends Gem {
    public Heilungs_Gem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1200, 127));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1200, 127));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200));
        return ActionResult.SUCCESS;
    }
}
