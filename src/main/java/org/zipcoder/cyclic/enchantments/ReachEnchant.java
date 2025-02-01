/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (C) 2014-2018 Sam Bassett (aka Lothrazar)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package org.zipcoder.cyclic.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.zipcoder.cyclic.utils.EnchantmentUtils;

import java.util.Map;
import java.util.UUID;

public class ReachEnchant extends Enchantment {

    private static final String NBT_REACH_ON = "reachon";
    public static final String ID = "reach";


    public ReachEnchant(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot... slots) {
        super(rarityIn, typeIn, slots);
        MinecraftForge.EVENT_BUS.register(this);
    }

    // TOTAL: 5 + boost           5  9 12 16  20  24
    // level                      0  1  2  3   4   5
    final static int[] LEVELS = {0, 4, 7, 11, 16, 19};

    private int getBoost(int level) {
        if (level < 0) {
            return 0;
        } else if (level < LEVELS.length) {
            return LEVELS[level];
        }
        //default max level is 5, but other mods can boost this ie apotheosis so give it some gas
        int oldLevel = LEVELS.length - 1; // aka LEVELS.length
        return 4 * (level - oldLevel) + LEVELS[oldLevel];
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return isEnabled() && super.isTradeable();
    }

    @Override
    public boolean isDiscoverable() {
        return isEnabled() && super.isDiscoverable();
    }

    @Override
    public boolean isAllowedOnBooks() {
        return isEnabled() && super.isAllowedOnBooks();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return isEnabled() && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return isEnabled() && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    public static final UUID ENCHANTMENT_REACH_ID = UUID.fromString("1abcdef2-eff2-4a81-b92b-a1cb95f115c6");

    private void turnReachOff(Player player) {
        player.getPersistentData().putBoolean(NBT_REACH_ON, false);
        removePlayerReach(ENCHANTMENT_REACH_ID, player);
    }

    private void turnReachOn(Player player, int level) {
        player.getPersistentData().putBoolean(NBT_REACH_ON, true);
        setPlayerReach(ENCHANTMENT_REACH_ID, player, getBoost(level));
    }

    public static void removePlayerReach(UUID id, Player player) {
        AttributeInstance attr = player.getAttribute((Attribute) ForgeMod.BLOCK_REACH.get());
        attr.removeModifier(id);
    }

    public static void setPlayerReach(UUID id, Player player, int reachBoost) {
        removePlayerReach(id, player);
        AttributeInstance attr = player.getAttribute((Attribute) ForgeMod.BLOCK_REACH.get());
        AttributeModifier enchantment = new AttributeModifier(id, "ReachFLIB", (double) reachBoost, AttributeModifier.Operation.ADDITION);
        attr.addPermanentModifier(enchantment);
    }


    @SubscribeEvent
    public void onEntityUpdate(LivingTickEvent event) {
        if (!isEnabled()) {
            return;
        }
        //check if NOT holding this harm
        if (event.getEntity() instanceof Player == false) {
            return;
        }
        Player player = (Player) event.getEntity();
        //Ticking
        ItemStack armor = EnchantmentUtils.getFirstArmorStackWithEnchant(this, player);
        int level = 0;
        Map<Enchantment, Integer> enchHere = EnchantmentHelper.getEnchantments(armor);
        if (armor.isEmpty() == false && enchHere != null
                && enchHere.containsKey(this)) {
            level = enchHere.get(this);
        }
        if (level > 0) {
            turnReachOn(player, level);
        } else {
            //was it on before, do we need to do an off hit
            if (player.getPersistentData().contains(NBT_REACH_ON) && player.getPersistentData().getBoolean(NBT_REACH_ON)) {
                turnReachOff(player);
            }
        }
    }
}
