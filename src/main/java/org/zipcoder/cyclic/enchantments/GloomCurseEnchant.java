//package org.zipcoder.cyclic.enchantments;
//
//import com.lothrazar.cyclic.ModCyclic;
//import com.lothrazar.cyclic.config.ConfigRegistry;
//import com.lothrazar.library.enchant.EnchantmentFlib;
//import com.lothrazar.library.util.EnchantUtil;
//import com.lothrazar.library.util.FakePlayerUtil;
//import com.lothrazar.library.util.StringParseUtil;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.effect.MobEffect;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.item.ArmorItem;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.EnchantmentCategory;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import java.util.Collections;
//import java.util.List;
//
//public class GloomCurseEnchant extends EnchantmentFlib {
//
//  public static final double BASE_ACTIVATION_CHANCE = 0.1;
//  public static final double BASE_APPLY_CHANCE = 0.3;
//  public static final double MIN_EFFECTS = 1;
//  public static final double MAX_EFFECTS = 3;
//  public static final int EFFECT_DURATION = 20 * 5;
//
//  public GloomCurseEnchant(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot... slots) {
//    super(rarityIn, typeIn, slots);
//    MinecraftForge.EVENT_BUS.register(this);
//  }
//
//
//  public static final String ID = "curse";
//
//  @Override
//  public boolean isEnabled() {
//    return true;
//  }
//
//  @Override
//  public int getMaxLevel() {
//    return 1;
//  }
//
//  @Override
//  public boolean isTradeable() {
//    return isEnabled() && super.isTradeable();
//  }
//
//  @Override
//  public boolean isDiscoverable() {
//    return isEnabled() && super.isDiscoverable();
//  }
//
//  @Override
//  public boolean isAllowedOnBooks() {
//    return isEnabled() && super.isAllowedOnBooks();
//  }
//
//  @Override
//  public boolean canApplyAtEnchantingTable(ItemStack stack) {
//    return isEnabled() && super.canApplyAtEnchantingTable(stack);
//  }
//
//  @Override
//  public boolean canEnchant(ItemStack stack) {
//    return isEnabled() && stack.getItem() instanceof ArmorItem;
//  }
//
//  @Override
//  public void doPostHurt(LivingEntity user, Entity attacker, int level) {
//    if (user.level().isClientSide || !(attacker instanceof LivingEntity)
//        || FakePlayerUtil.isFakePlayer(attacker)) {
//      //do nothing on clientside, server only
//      //only trigger if attacker is alive and not a fakeplayer
//      return;
//    }
//    LivingEntity livingAttacker = (LivingEntity) attacker;
//    //only allow activation once
//    int totalLevels = getCurrentArmorLevelSlot(user, EquipmentSlot.HEAD)
//        + getCurrentArmorLevelSlot(user, EquipmentSlot.CHEST)
//        + getCurrentArmorLevelSlot(user, EquipmentSlot.LEGS)
//        + getCurrentArmorLevelSlot(user, EquipmentSlot.FEET);
//    double adjustedActivationChance = BASE_ACTIVATION_CHANCE / totalLevels;
//    //does it pass the chance
//    if (adjustedActivationChance > user.level().random.nextDouble()) {
//      List<MobEffect> negativeEffects = EnchantUtil.getNegativeEffects();
//      Collections.shuffle(negativeEffects);
//      int appliedEffects = 0;
//      for (MobEffect effect : negativeEffects) {
//        if (effect == null) {
//          continue;
//          //should be impossible, but i had a random NPE crash log
//        }
//        ResourceLocation effectKey = ForgeRegistries.MOB_EFFECTS.getKey(effect);
//        if (StringParseUtil.isInList(ConfigRegistry.getGloomIgnoreList(), effectKey)) {
//          ModCyclic.LOGGER.info("Gloom(curse) effect cannot apply " + effectKey);
//          continue;
//        }
//        if (appliedEffects < MIN_EFFECTS
//            || BASE_APPLY_CHANCE > user.level().random.nextDouble()) {
//          //the OR means, if we are under minimum, always go thru
//          //if we are beyond minimum, check the random chance
//          livingAttacker.addEffect(new MobEffectInstance(effect, EFFECT_DURATION));
//          appliedEffects++;
//          if (appliedEffects >= MAX_EFFECTS) {
//            break;
//          }
//        }
//      }
//    }
//    super.doPostHurt(user, attacker, level);
//  }
//}
