package org.zipcoder.cyclic.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FreezeEffect extends MobEffect {
    protected FreezeEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        //This is the important part
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                "91AEAA56-376B-4498-935B-2F7F68070636", -100, AttributeModifier.Operation.ADDITION);
    }

    //This is not required but it is nice to have
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {// server
            Double x = pLivingEntity.getX();
            Double y = pLivingEntity.getY();
            Double z = pLivingEntity.getZ();
//
            pLivingEntity.teleportTo(x, y, z);
            pLivingEntity.setDeltaMovement(0, 0, 0);
//            pLivingEntity.getBrain().
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
