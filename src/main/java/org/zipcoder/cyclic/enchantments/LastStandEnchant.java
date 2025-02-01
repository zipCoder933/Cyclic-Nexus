package org.zipcoder.cyclic.enchantments;///*******************************************************************************
// * The MIT License (MIT)
// *
// * Copyright (C) 2014-2018 Sam Bassett (aka Lothrazar)
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// ******************************************************************************/
//package com.lothrazar.cyclic.enchant;
//
//import com.lothrazar.cyclic.ModCyclic;
//import com.lothrazar.cyclic.registry.EnchantRegistry;
//import com.lothrazar.cyclic.registry.SoundRegistry;
//import com.lothrazar.library.enchant.EnchantmentFlib;
//import com.lothrazar.library.util.ChatUtil;
//import com.lothrazar.library.util.PlayerUtil;
//import com.lothrazar.library.util.SoundUtil;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.effect.MobEffects;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.EnchantmentCategory;
//import net.minecraft.world.item.enchantment.Enchantments;
//import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
//import net.minecraftforge.common.ForgeConfigSpec.IntValue;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.entity.living.LivingDamageEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//
//public class LastStandEnchant extends EnchantmentFlib {
//
//  public static final String ID = "laststand";
//  public static BooleanValue CFG;
//  public static IntValue COST;
//  public static IntValue ABS;
//  public static IntValue COOLDOWN;
//
//  public LastStandEnchant(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot... slots) {
//    super(rarityIn, typeIn, slots);
//    MinecraftForge.EVENT_BUS.register(this);
//  }
//
//  @Override
//  public boolean checkCompatibility(Enchantment ench) {
//    return super.checkCompatibility(ench) && ench != EnchantRegistry.LAUNCH.get()
//        && ench != EnchantRegistry.EXPERIENCE_BOOST.get()
//        && ench != Enchantments.MENDING
//        && ench != Enchantments.THORNS;
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
//  public boolean canEnchant(ItemStack stack) {
//    return isEnabled() && super.canEnchant(stack);
//  }
//
//  @Override
//  public boolean canApplyAtEnchantingTable(ItemStack stack) {
//    return isEnabled() && super.canApplyAtEnchantingTable(stack);
//  }
//
//  @Override
//  public boolean isEnabled() {
//    return CFG.get();
//  }
//
//  @Override
//  public int getMaxLevel() {
//    return 2;
//  }
//
//  @SubscribeEvent
//  public void onEntityUpdate(LivingDamageEvent event) {
//    if (!isEnabled()) {
//      return;
//    }
//    final int level = getCurrentArmorLevelSlot(event.getEntity(), EquipmentSlot.LEGS);
//    if (level > 0 && event.getEntity().getHealth() - event.getAmount() <= 0 && event.getEntity() instanceof ServerPlayer player) {
//      //if enchanted and it would cause death, then we go on
//      if (COOLDOWN.get() > 0 &&
//          player.getCooldowns().isOnCooldown(player.getItemBySlot(EquipmentSlot.LEGS).getItem())) {
//        return; //if equippped enchanted item is on cooldown for any reason, done
//      }
//      final int xpCost = Math.max(1, COST.get() / level); // min 1.  higher level gives a lower cost. level 1 is 30xp, lvl 3 is 10xp etc
//      if (PlayerUtil.getExpTotal(player) < xpCost) {
//        return; // POOR
//      }
//      //survive
//      float toSurvive = event.getEntity().getHealth() - 1;
//      event.setAmount(toSurvive);
//      player.giveExperiencePoints(-1 * xpCost);
//      //now the fluff
//      SoundUtil.playSoundFromServer(player, SoundRegistry.CHAOS_REAPER.get(), 1F, 0.4F);
//      ChatUtil.sendStatusMessage(player, "enchantment." + ModCyclic.MODID + "." + ID + ".activated");
//      if (ABS.get() > 0) {
//        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, ABS.get(), level - 1));
//      }
//      if (COOLDOWN.get() > 0) {
//        player.getCooldowns().addCooldown(player.getItemBySlot(EquipmentSlot.LEGS).getItem(), COOLDOWN.get());
//      }
//    }
//  }
//}
