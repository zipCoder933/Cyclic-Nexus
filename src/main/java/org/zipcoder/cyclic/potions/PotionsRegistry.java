package org.zipcoder.cyclic.potions;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.zipcoder.cyclic.effects.EffectRegistry;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public class PotionsRegistry {
    public final static DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);

    public static final RegistryObject<Potion> FREEZE_POTION = POTIONS.register("freeze_potion",
            () -> new Potion(MOD_ID + "_blind", new MobEffectInstance(EffectRegistry.FREEZE.get(), 100, 0)));

    public static final RegistryObject<Potion> REACH_POTION = POTIONS.register("reach_distance",
            () -> new Potion(MOD_ID + "_reach_distance", new MobEffectInstance(EffectRegistry.REACH_DISTANCE.get(), 1000, 0)));

    public static final RegistryObject<Potion> ATTACK_RANGE_POTION = POTIONS.register("reach_potion",
            () -> new Potion(MOD_ID + "_attack_range", new MobEffectInstance(EffectRegistry.ATTACK_RANGE.get(), 1000, 0)));

    //Uses existing effects
    public static final RegistryObject<Potion> BLIND_POTION = POTIONS.register("blind",
            () -> new Potion(MOD_ID + "_blind", new MobEffectInstance(MobEffects.BLINDNESS, 3600)));


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

    /**
     * Add potion recipes
     */
    public static void setup() {
        final ItemStack awkwardPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD);
        final ItemStack thickPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.THICK);
        final ItemStack nightVisionPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.NIGHT_VISION);
        // Potion recipes
        basicBrewing(FREEZE_POTION.get(), nightVisionPotion.copy(), Items.BEETROOT);
        basicBrewing(REACH_POTION.get(), thickPotion.copy(), Items.AMETHYST_SHARD);
//        basicBrewing(ATTACK_RANGE_POTION.get(), awkwardPotion.copy(), Items.GOLDEN_APPLE);
        basicBrewing(BLIND_POTION.get(), nightVisionPotion.copy(), Items.GOLDEN_APPLE);
    }

    private static void basicBrewing(Potion outputPotion, ItemStack inputPotion, Item ingredient) {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(inputPotion, Ingredient.of(ingredient), PotionUtils.setPotion(new ItemStack(Items.POTION), outputPotion)));
    }
}
