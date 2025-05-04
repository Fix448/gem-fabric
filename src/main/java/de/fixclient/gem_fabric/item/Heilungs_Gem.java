package de.fixclient.gem_fabric.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;



public class Heilungs_Gem extends Item {
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
