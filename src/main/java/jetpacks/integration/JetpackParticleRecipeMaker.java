package jetpacks.integration;

import jetpacks.SimplyJetpacks;
import jetpacks.item.JetpackItem;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraftforge.registries.ForgeRegistries;
import jetpacks.datagen.SJTags;

import java.util.ArrayList;
import java.util.List;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public final class JetpackParticleRecipeMaker {

    public static List<CraftingRecipe> createJetpackParticleRecipes() {
        List<CraftingRecipe> recipes = new ArrayList<>();
        String group = ""+MOD_ID+".particle_customization";
        // TODO: test these
        List<Item> jetpackList = ForgeRegistries.ITEMS.tags().getTag(SJTags.JETPACK).stream().toList();
        List<Item> particleList = ForgeRegistries.ITEMS.tags().getTag(SJTags.PARTICLES).stream().toList();
//        List<Item> jetpackList = SJTags.JETPACK.getValues();
//        List<Item> particleList = SJTags.PARTICLES.getValues();
        ItemStack jetpackStack;
        ItemStack particleStack;
        for (Item jetpack : jetpackList) {
            jetpackStack = new ItemStack(jetpack);
            for (Item particle : particleList) {
                particleStack = new ItemStack(particle);
                NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY, Ingredient.of(particleStack), Ingredient.of(jetpackStack));
                ResourceLocation id = new ResourceLocation(MOD_ID, particleStack.getItem() + "_" + jetpackStack.getItem());
                ItemStack output = JetpackItem.setParticleId(jetpackStack.copy(), particleStack);
                ShapelessRecipe recipe = new ShapelessRecipe(id, group, CraftingBookCategory.MISC, output, inputs);
                recipes.add(recipe);
            }
        }
        return recipes;
    }
}
