package org.zipcoder.cyclic.utils;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Iterator;

public class EnchantmentUtils {
    public static final EquipmentSlot[] armors = new EquipmentSlot[]{EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HEAD, EquipmentSlot.LEGS};

    public static ItemStack getFirstArmorStackWithEnchant(Enchantment enchantment, LivingEntity player) {
        if (player == null) {
            return ItemStack.EMPTY;
        } else {
            Iterator var2 = player.getArmorSlots().iterator();

            ItemStack main;
            do {
                if (!var2.hasNext()) {
                    return ItemStack.EMPTY;
                }

                main = (ItemStack)var2.next();
            } while(main.isEmpty() || !EnchantmentHelper.getEnchantments(main).containsKey(enchantment));

            return main;
        }
    }

    public static int getCurrentArmorLevelSlot(Enchantment enchantment, LivingEntity player, EquipmentSlot type) {
        ItemStack armor = player.getItemBySlot(type);
        int level = 0;
        if (!armor.isEmpty() && EnchantmentHelper.getEnchantments(armor) != null && EnchantmentHelper.getEnchantments(armor).containsKey(enchantment)) {
            level = (Integer) EnchantmentHelper.getEnchantments(armor).get(enchantment);
        }

        return level;
    }

    public static int getCurrentArmorLevel(Enchantment enchantment, LivingEntity player) {
        int level = 0;
        EquipmentSlot[] var4 = armors;
        int var5 = armors.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            EquipmentSlot slot = var4[var6];
            ItemStack armor = player.getItemBySlot(slot);
            if (!armor.isEmpty() && EnchantmentHelper.getEnchantments(armor) != null && EnchantmentHelper.getEnchantments(armor).containsKey(enchantment)) {
                int newlevel = (Integer) EnchantmentHelper.getEnchantments(armor).get(enchantment);
                if (newlevel > level) {
                    level = newlevel;
                }
            }
        }

        return level;
    }
}
