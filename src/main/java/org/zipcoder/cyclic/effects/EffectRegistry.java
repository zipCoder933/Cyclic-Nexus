package org.zipcoder.cyclic.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public class EffectRegistry {
    public final static DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MOD_ID);

    public static final RegistryObject<MobEffect> FREEZE = MOB_EFFECTS.register("freeze",
            () -> new FreezeEffect(MobEffectCategory.HARMFUL, new Color(100,110,255).getRGB()));

    public static final RegistryObject<MobEffect> ATTACK_RANGE = MOB_EFFECTS.register("attack_range",
            () -> new EmptyMobEffect(MobEffectCategory.BENEFICIAL, new Color(14, 165, 130).getRGB())
            .addAttributeModifier(ForgeMod.ENTITY_REACH.get(), "5207DE5E-7CE8-4030-940E-514C1F160890", 1.5f, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> REACH_DISTANCE = MOB_EFFECTS.register("reach_distance",
            () -> new EmptyMobEffect(MobEffectCategory.BENEFICIAL, 0x500980)
            .addAttributeModifier(ForgeMod.BLOCK_REACH.get(), "5207DE5E-7CE8-4030-940E-514C1F160890", 1.5f, AttributeModifier.Operation.MULTIPLY_TOTAL));

    //    public static final RegistryObject<MobEffect> SWIMSPEED = MOB_EFFECTS.register("swimspeed", () -> new CyclicMobEffect(MobEffectCategory.BENEFICIAL, 0x663300)
//            .addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "8207DE5E-7CE8-4030-940E-514C1F160890", 2, AttributeModifier.Operation.MULTIPLY_TOTAL));
//
//    public static final RegistryObject<MobEffect> GRAVITY = MOB_EFFECTS.register("gravity", () -> new CyclicMobEffect(MobEffectCategory.HARMFUL, 0x730043)
//            .addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "5207DE5E-7CE8-4030-940E-514C1F160890", 5, AttributeModifier.Operation.MULTIPLY_TOTAL));
//
//    public static final RegistryObject<MobEffect> ANTIGRAVITY = MOB_EFFECTS.register("antigravity", () -> new CyclicMobEffect(MobEffectCategory.BENEFICIAL, 0x730043)
//            .addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "5207DE5E-7CE8-4030-940E-514C1F160890", -0.015, AttributeModifier.Operation.ADDITION)); //default gravity is +0.08


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
