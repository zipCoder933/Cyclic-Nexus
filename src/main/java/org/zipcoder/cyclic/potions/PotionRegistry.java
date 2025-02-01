//package org.zipcoder.cyclic.potions;
//
//import net.minecraft.world.effect.MobEffect;
//import net.minecraft.world.effect.MobEffectCategory;
//import net.minecraft.world.effect.MobEffects;
//import net.minecraft.world.entity.ai.attributes.AttributeModifier;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraftforge.common.ForgeMod;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.zipcoder.cyclic.Cyclic.MOD_ID;
//
//public class PotionRegistry {
//    public final static DeferredRegister<Enchantment> POTIOINS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
//
//
//
//    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MOD_ID);
//
//    public static final RegistryObject<StunEffect> STUN = MOB_EFFECTS.register("stun", () -> new StunEffect(MobEffectCategory.HARMFUL, 0xcccc00));
//
//    public static final RegistryObject<MobEffect> SWIMSPEED = MOB_EFFECTS.register("swimspeed", () -> new CyclicMobEffect(MobEffectCategory.BENEFICIAL, 0x663300)
//            .addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "8207DE5E-7CE8-4030-940E-514C1F160890", 2, AttributeModifier.Operation.MULTIPLY_TOTAL));
//
//    public static final RegistryObject<MobEffect> GRAVITY = MOB_EFFECTS.register("gravity", () -> new CyclicMobEffect(MobEffectCategory.HARMFUL, 0x730043)
//            .addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "5207DE5E-7CE8-4030-940E-514C1F160890", 5, AttributeModifier.Operation.MULTIPLY_TOTAL));
//
//    public static final RegistryObject<MobEffect> ANTIGRAVITY = MOB_EFFECTS.register("antigravity", () -> new CyclicMobEffect(MobEffectCategory.BENEFICIAL, 0x730043)
//            .addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "5207DE5E-7CE8-4030-940E-514C1F160890", -0.015, AttributeModifier.Operation.ADDITION)); //default gravity is +0.08
//
//    public static final RegistryObject<MobEffect> ATTACK_RANGE = MOB_EFFECTS.register("attack_range", () -> new CyclicMobEffect(MobEffectCategory.BENEFICIAL, 0x35db77)
//            .addAttributeModifier(ForgeMod.ENTITY_REACH.get(), "5207DE5E-7CE8-4030-940E-514C1F160890", 2, AttributeModifier.Operation.MULTIPLY_TOTAL));
//
//    public static final RegistryObject<MobEffect> REACH_DISTANCE = MOB_EFFECTS.register("reach_distance", () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x500980)
//            .addAttributeModifier(ForgeMod.BLOCK_REACH.get(), "5207DE5E-7CE8-4030-940E-514C1F160890", 2, AttributeModifier.Operation.MULTIPLY_TOTAL));
//
//
//
//
//    public static void register(IEventBus eventBus) {
//        POTIOINS.register(eventBus);
//    }
//}
