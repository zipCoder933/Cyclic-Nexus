//package org.zipcoder.cyclic.potions;
//
//import net.minecraft.world.effect.MobEffect;
//import net.minecraft.world.effect.MobEffectCategory;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
//
//public class MagneticEffect extends MobEffect {
//
//  public MagneticEffect(MobEffectCategory typeIn, int liquidColorIn) {
//    super(typeIn, liquidColorIn);
//  }
//
//  @Override
//  public void tick(LivingTickEvent event) {
//    LivingEntity living = event.getEntity();
//    int amp = living.getEffect(this).getAmplifier();
//    EntityUtil.moveEntityItemsInRegion(living.level(), living.blockPosition(), 8 * amp, 1 + amp);
//  }
//}
