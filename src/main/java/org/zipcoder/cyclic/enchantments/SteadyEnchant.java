///*******************************************************************************
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
//package org.zipcoder.cyclic.enchantments;
//
//import com.lothrazar.library.enchant.EnchantmentFlib;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.EnchantmentCategory;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//
//public class SteadyEnchant extends EnchantmentFlib {
//
//  public static final String ID = "steady";
//
//
//  public SteadyEnchant(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot... slots) {
//    super(rarityIn, typeIn, slots);
//    MinecraftForge.EVENT_BUS.register(this);
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
//    return true;
//  }
//
//  @Override
//  public int getMaxLevel() {
//    return 1;
//  }
//
//  @SubscribeEvent
//  public void onLivingKnockBackEvent(LivingKnockBackEvent event) {
//    if (this.getCurrentArmorLevel(event.getEntity()) > 0) {
//      event.setCanceled(true);
//    }
//  }
//}
