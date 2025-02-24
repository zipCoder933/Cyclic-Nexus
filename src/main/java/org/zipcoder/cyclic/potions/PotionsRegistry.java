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
import org.zipcoder.cyclic.items.ItemRegistry;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public class PotionsRegistry {
    public final static DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);

    static final int dur3Min = 180 * 20;
    static final int dur1Min = 60 * 20;

    public static final RegistryObject<Potion> FREEZE_POTION = POTIONS.register("freeze_potion", //10 seconds
            () -> new Potion(MOD_ID + "_freeze", new MobEffectInstance(EffectRegistry.FREEZE.get(), 20 * 10, 0)));

    public static final RegistryObject<Potion> FREEZE_POTION_2 = POTIONS.register("freeze_potion_2", //30 seconds
            () -> new Potion(MOD_ID + "_freeze", new MobEffectInstance(EffectRegistry.FREEZE.get(), 20 * 40, 0)));

    public static final RegistryObject<Potion> FREEZE_POTION_3 = POTIONS.register("freeze_potion_3", //3 minutes
            () -> new Potion(MOD_ID + "_freeze", new MobEffectInstance(EffectRegistry.FREEZE.get(), dur3Min, 0)));

    public static final RegistryObject<Potion> REACH_POTION = POTIONS.register("reach_distance",
            () -> new Potion(MOD_ID + "_reach_distance", new MobEffectInstance(EffectRegistry.REACH_DISTANCE.get(), dur3Min, 0)));

    public static final RegistryObject<Potion> ATTACK_RANGE_POTION = POTIONS.register("attach_range",
            () -> new Potion(MOD_ID + "_attack_range", new MobEffectInstance(EffectRegistry.ATTACK_RANGE.get(), dur3Min, 0)));

    //Uses existing effects
    public static final RegistryObject<Potion> BLIND_POTION = POTIONS.register("blind",
            () -> new Potion(MOD_ID + "_blind", new MobEffectInstance(MobEffects.BLINDNESS, dur3Min)));


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
        final ItemStack slowPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS);

        //freeze
        basicBrewing(FREEZE_POTION.get(), slowPotion.copy(), ItemRegistry.SNOWFLAKE.get());

        //freeze 2
        final ItemStack freeze_potion = PotionUtils.setPotion(new ItemStack(Items.POTION), FREEZE_POTION.get());
        basicBrewing(FREEZE_POTION_2.get(), freeze_potion.copy(), ItemRegistry.GOLD_SNOWFLAKE.get());

        //freeze 3
//        final ItemStack freeze_potion_2 = PotionUtils.setPotion(new ItemStack(Items.POTION), FREEZE_POTION_2.get());
//        basicBrewing(FREEZE_POTION_3.get(), freeze_potion_2.copy(), ItemRegistry.GOLD_SNOWFLAKE.get());


        //reach
        basicBrewing(REACH_POTION.get(), awkwardPotion.copy(), Items.AMETHYST_SHARD);

//        basicBrewing(ATTACK_RANGE_POTION.get(), awkwardPotion.copy(), Items.GOLDEN_APPLE);

        //blind
        basicBrewing(BLIND_POTION.get(), nightVisionPotion.copy(), Items.GOLDEN_APPLE);
    }

    private static void basicBrewing(Potion outputPotion, ItemStack inputPotion, Item ingredient) {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(inputPotion, Ingredient.of(ingredient), PotionUtils.setPotion(new ItemStack(Items.POTION), outputPotion)));
    }
}
