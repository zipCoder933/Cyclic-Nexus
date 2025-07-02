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

//net.minecraft.world.item.ArmorMaterials
public enum ModArmorMaterials implements ArmorMaterial {


    /**
     *  LEATHER("leather", 5, (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (p_266652_) -> {
     *         p_266652_.put(Type.BOOTS, 1);
     *         p_266652_.put(Type.LEGGINGS, 2);
     *         p_266652_.put(Type.CHESTPLATE, 3);
     *         p_266652_.put(Type.HELMET, 1);
     *     }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
     *         return Ingredient.of(new ItemLike[]{Items.LEATHER});
     *     }),
     *     CHAIN("chainmail", 15, (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (p_266651_) -> {
     *         p_266651_.put(Type.BOOTS, 1);
     *         p_266651_.put(Type.LEGGINGS, 4);
     *         p_266651_.put(Type.CHESTPLATE, 5);
     *         p_266651_.put(Type.HELMET, 2);
     *     }), 12, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
     *         return Ingredient.of(new ItemLike[]{Items.IRON_INGOT});
     *     }),
     *     IRON("iron", 15, (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (p_266654_) -> {
     *         p_266654_.put(Type.BOOTS, 2);
     *         p_266654_.put(Type.LEGGINGS, 5);
     *         p_266654_.put(Type.CHESTPLATE, 6);
     *         p_266654_.put(Type.HELMET, 2);
     *     }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
     *         return Ingredient.of(new ItemLike[]{Items.IRON_INGOT});
     *     }),
     *     GOLD("gold", 7, (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (p_266650_) -> {
     *         p_266650_.put(Type.BOOTS, 1);
     *         p_266650_.put(Type.LEGGINGS, 3);
     *         p_266650_.put(Type.CHESTPLATE, 5);
     *         p_266650_.put(Type.HELMET, 2);
     *     }), 25, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
     *         return Ingredient.of(new ItemLike[]{Items.GOLD_INGOT});
     *     }),
     */

    //ArmorMaterials.GOLD.getDurabilityForType(ArmorItem.Type.HELMET)


    COPPER_ARMOR("copper",
            15, // durability
            new int[]{
                    1,// helmtet
                    3,// chestplate
                    2,// leggings
                    1,},// boots
            10, // enchantability
            SoundEvents.ARMOR_EQUIP_GENERIC, // equip sound
            0F, // toughness
            0F, // knockback resistance
            () -> Ingredient.of(Items.COPPER_INGOT)), // repair material

    //Emerald is gold but with a higher durability, toughness and enchantment value
    EMERALD_ARMOR("emerald",
            25, // durability
            new int[]{ // protection
                    2, // helmet
                    6, // chestplate
                    4, // leggings (iron-1)
                    1, // boots (iron-1)
            },
            35, // enchantability
            SoundRegistry.EQUIP_EMERALD.get(), // equip sound
            0.25F, // toughness
            0.2F, // knockback resistance
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
