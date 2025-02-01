//package org.zipcoder.cyclic.potions;
//
//import com.lothrazar.cyclic.potion.CyclicMobEffect;
//import net.minecraft.world.effect.MobEffect;
//import net.minecraft.world.effect.MobEffectCategory;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
//import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
//
//public class FrostEffect extends MobEffect {
//
//  public FrostEffect(MobEffectCategory typeIn, int liquidColorIn) {
//    super(typeIn, liquidColorIn);
//  }
//
//  @Override
//  public void tick(LivingTickEvent event) {
//    // delete me i guess
//    LivingEntity living = event.getEntity();
//    int amp = living.getEffect(this).getAmplifier();
//    FrostWalkerEnchantment.onEntityMoved(living, living.level(), living.blockPosition(), amp);
//  }
//}
