package org.zipcoder.cyclic.potions;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;

public class BrewingRecipe extends net.minecraftforge.common.brewing.BrewingRecipe {
    private ItemStack inputStack;

    public BrewingRecipe(ItemStack inputStack, Ingredient ingredient, ItemStack output) {
        super(Ingredient.of(new ItemStack[]{inputStack}), ingredient, output);
        this.inputStack = inputStack;
    }

    public boolean isInput(ItemStack stack) {
        return super.isInput(stack) && PotionUtils.getPotion(stack) == PotionUtils.getPotion(this.inputStack);
    }

}
