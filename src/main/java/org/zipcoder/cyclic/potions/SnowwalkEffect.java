//package org.zipcoder.cyclic.potions;
//
//import com.lothrazar.cyclic.potion.CyclicMobEffect;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.effect.MobEffect;
//import net.minecraft.world.effect.MobEffectCategory;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
//
//public class SnowwalkEffect extends MobEffect {
//
//  public SnowwalkEffect(MobEffectCategory typeIn, int liquidColorIn) {
//    super(typeIn, liquidColorIn);
//  }
//
//  @Override
//  public void tick(LivingTickEvent event) {
//    // delete me i guess
//    LivingEntity living = event.getEntity();
//    Level level = living.level();
//    BlockPos blockpos = living.blockPosition();
//    BlockState blockstate = Blocks.SNOW.defaultBlockState();
//    living.getEffect(this).getAmplifier(); // TODO: radius?
//    if (level.isEmptyBlock(blockpos) && blockstate.canSurvive(level, blockpos)) {
//      //world.getBlockState(blockpos).is(Blocks.AIR)) {
//      //is air
//      level.setBlockAndUpdate(blockpos, blockstate);
//    }
//  }
//}
