package org.zipcoder.cyclic.materials;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.zipcoder.cyclic.Cyclic;
import org.zipcoder.cyclic.sound.SoundRegistry;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {

    GLOW_ARMOR("glowing",
            30, // durability
            new int[]{ // protection
                    3, // head
                    7, // body
                    5, // legs
                    4, // feet
            },
            10, // enchantability
            SoundEvents.ARMOR_EQUIP_DIAMOND, // equip sound
            1F, // toughness
            0F, // knockback resistance
            () -> Ingredient.of(Items.GOLD_BLOCK)), // repair material

    //ArmorMaterials.GOLD.getDurabilityForType(ArmorItem.Type.HELMET)
    //Emerald is gold but with a higher durability, toughness and enchantment value
    EMERALD_ARMOR("emerald",
            25, // durability
            new int[]{ // protection
                    2, // helmtet
                    5, // chestplate
                    3, // leggings
                    1, // boots
            },
            30, // enchantability
            SoundRegistry.EQUIP_EMERALD.get(), // equip sound
            0.25F, // toughness
            0F, // knockback resistance
            () -> Ingredient.of(Items.EMERALD_BLOCK)); // repair material

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = new int[]{11, 16, 16, 13};

    ModArmorMaterials(String name, int durability, int[] protectionAmounts,
                      int enchantability, SoundEvent equipSound,
                      float toughness, float knockbackResistance,
                      Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durability;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return Cyclic.MOD_ID + ":" + name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
