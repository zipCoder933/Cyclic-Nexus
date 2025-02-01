//package org.zipcoder.cyclic.effects;
//
//import net.minecraft.world.effect.MobEffect;
//import net.minecraft.world.effect.MobEffectCategory;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
//
//public class WaterwalkEffect extends MobEffect {
//
//    public WaterwalkEffect(MobEffectCategory typeIn, int liquidColorIn) {
//        super(typeIn, liquidColorIn);
//    }
//
//    @Override
//    public void applyEffectTick(LivingEntity living, int pAmplifier) {
//        //    living.getEffect(this).getAmplifier()
//        if (living.isInWater() || living.level().getBlockState(living.blockPosition()).is(Blocks.WATER)) {
//            if (living instanceof Player p) {
//                if (p.isCrouching()) {
//                    return;// let them slip down into it
//                }
//            }
//            living.setOnGround(true); // act as if on solid ground
//            living.setDeltaMovement(living.getDeltaMovement().x, 0, living.getDeltaMovement().z);
//        }
//    }
//}
