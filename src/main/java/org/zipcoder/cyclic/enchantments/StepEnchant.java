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
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public class StepEnchant extends Enchantment {

    private static final String NBT_ON = MOD_ID + "_stepenchant";
    public static final String ID = "step";


    public StepEnchant(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot... slots) {
        super(rarityIn, typeIn, slots);
        MinecraftForge.EVENT_BUS.register(this);
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
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return isEnabled() && super.canApplyAtEnchantingTable(stack);
    }


    public boolean isEnabled() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        //anything that goes on your feet
        boolean yes = isEnabled()
                && (stack.getItem() instanceof ArmorItem)
                && ((ArmorItem) stack.getItem()).getType() == ArmorItem.Type.BOOTS;
        return yes;
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
        ItemStack armor = player.getItemBySlot(EquipmentSlot.FEET);
        int level = 0;
        if (!armor.isEmpty() && EnchantmentHelper.getEnchantments(armor) != null
                && EnchantmentHelper.getEnchantments(armor).containsKey(this)) {
            level = EnchantmentHelper.getEnchantments(armor).get(this);
        }
    if (level > 0) {
      turnOn(player, armor);
    }
    else {
      turnOff(player, armor);
    }
    }

    public static final UUID ID_STEP_HEIGHT = UUID.fromString("66d30aa2-eaa2-4a81-b92b-a1cb95f115ca");
    static final float VANILLA = 0.6F;

    public static void disableStepHeight(Player player) {
        AttributeInstance attr = player.getAttribute((Attribute) ForgeMod.STEP_HEIGHT_ADDITION.get());
        attr.removeModifier(ID_STEP_HEIGHT);
    }

    public static void enableStepHeight(Player player) {
        float newVal;
        if (player.isCrouching()) {
            newVal = 0.29999995F;
        } else {
            newVal = 0.46249998F;
        }

        AttributeInstance attr = player.getAttribute((Attribute) ForgeMod.STEP_HEIGHT_ADDITION.get());
        AttributeModifier oldModifier = attr.getModifier(ID_STEP_HEIGHT);
        double old = oldModifier == null ? 0.0 : oldModifier.getAmount();
        if ((double) newVal != old) {
            setStepHeightInternal(player, (double) newVal);
        }

    }

    private static void setStepHeightInternal(Player player, double newVal) {
        AttributeInstance attr = player.getAttribute((Attribute) ForgeMod.STEP_HEIGHT_ADDITION.get());
        attr.removeModifier(ID_STEP_HEIGHT);
        if (newVal != 0.0) {
            AttributeModifier healthModifier = new AttributeModifier(ID_STEP_HEIGHT, "flib", newVal, AttributeModifier.Operation.ADDITION);
            attr.addPermanentModifier(healthModifier);
        }
    }

    private void turnOn(Player player, ItemStack armor) {
        player.getPersistentData().putBoolean(NBT_ON, true);
        enableStepHeight(player);
    }

    private void turnOff(Player player, ItemStack armor) {
        //skip if lofty stature has override
        //was it on before, do we need to do an off hit
        if (player.getPersistentData().contains(NBT_ON) && player.getPersistentData().getBoolean(NBT_ON)) {
            disableStepHeight(player);
            player.getPersistentData().putBoolean(NBT_ON, false);
        }
    }
}
