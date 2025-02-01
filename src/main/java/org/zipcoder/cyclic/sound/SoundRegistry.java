package org.zipcoder.cyclic.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public class SoundRegistry {

  //thank you https://gist.github.com/ChampionAsh5357/c21724bafbc630da2ed8899fe0c1d226#soundevent
  public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);


//  public static final RegistryObject<SoundEvent> BASEY = SOUND_EVENTS.register("basey", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "basey")));
//  public static final RegistryObject<SoundEvent> BASS_ECHO = SOUND_EVENTS.register("bass_echo", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "bass_echo")));
//  public static final RegistryObject<SoundEvent> BIP = SOUND_EVENTS.register("bip", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "bip")));
//  public static final RegistryObject<SoundEvent> BUZZP = SOUND_EVENTS.register("buzzp", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "buzzp")));
//  public static final RegistryObject<SoundEvent> BWEWE = SOUND_EVENTS.register("bwewe", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "bwewe")));
//  public static final RegistryObject<SoundEvent> BWOAAAP = SOUND_EVENTS.register("bwoaaap", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "bwoaaap")));
//  public static final RegistryObject<SoundEvent> CHAOS_REAPER = SOUND_EVENTS.register("chaos_reaper", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "chaos_reaper")));
//  public static final RegistryObject<SoundEvent> COIN = SOUND_EVENTS.register("coin", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "coin")));
//  public static final RegistryObject<SoundEvent> CRACK = SOUND_EVENTS.register("crack", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "crack")));
//  public static final RegistryObject<SoundEvent> CRACKLE = SOUND_EVENTS.register("crackle", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "crackle")));
//  public static final RegistryObject<SoundEvent> DCOIN = SOUND_EVENTS.register("dcoin", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "dcoin")));
//  public static final RegistryObject<SoundEvent> DICE_MIKE_KOENIG_SHORT = SOUND_EVENTS.register("dice_mike_koenig_short", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "dice_mike_koenig_short")));
//  public static final RegistryObject<SoundEvent> EXPLOSM = SOUND_EVENTS.register("explosm", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "explosm")));
//  public static final RegistryObject<SoundEvent> FIREBALL_EXPLODE = SOUND_EVENTS.register("fireball_explode", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "fireball_explode")));
//  public static final RegistryObject<SoundEvent> DOORBELL_MIKEKOENIG = SOUND_EVENTS.register("doorbell_mikekoenig", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "doorbell_mikekoenig")));
//  public static final RegistryObject<SoundEvent> FIRELAUNCH = SOUND_EVENTS.register("firelaunch", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "firelaunch")));
//  public static final RegistryObject<SoundEvent> GOODLAUNCH = SOUND_EVENTS.register("goodlaunch", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "goodlaunch")));
//  public static final RegistryObject<SoundEvent> GUITAR = SOUND_EVENTS.register("guitar", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "guitar")));
//  public static final RegistryObject<SoundEvent> HOVERING = SOUND_EVENTS.register("hovering", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "hovering")));
//  public static final RegistryObject<SoundEvent> LASERBEANPEW = SOUND_EVENTS.register("laserbeanpew", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "laserbeanpew")));
//  public static final RegistryObject<SoundEvent> MACHINE_LAUNCH = SOUND_EVENTS.register("machine_launch", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "machine_launch")));
//  public static final RegistryObject<SoundEvent> MAGIC_MISSILE = SOUND_EVENTS.register("magic_missile", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "magic_missile")));
//  public static final RegistryObject<SoundEvent> MONSTER_BALL_CAPTURE = SOUND_EVENTS.register("monster_ball_capture", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "monster_ball_capture")));
//  public static final RegistryObject<SoundEvent> MONSTER_BALL_RELEASE = SOUND_EVENTS.register("monster_ball_release", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "monster_ball_release")));
//  public static final RegistryObject<SoundEvent> PEW = SOUND_EVENTS.register("pew", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "pew")));
//  public static final RegistryObject<SoundEvent> PEW_LONG = SOUND_EVENTS.register("pew_long", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "pew_long")));
//  public static final RegistryObject<SoundEvent> POW = SOUND_EVENTS.register("pow", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "pow")));
//  public static final RegistryObject<SoundEvent> POWERUPSCALES = SOUND_EVENTS.register("powerupscales", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "powerupscales")));
//  public static final RegistryObject<SoundEvent> SPIKEMAYBE = SOUND_EVENTS.register("spikemaybe", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "spikemaybe")));
//  public static final RegistryObject<SoundEvent> SPIKES_IN = SOUND_EVENTS.register("spikes_in", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "spikes_in")));
//  public static final RegistryObject<SoundEvent> SPIRIT_SEEKER = SOUND_EVENTS.register("spirit_seeker", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "spirit_seeker")));
//  public static final RegistryObject<SoundEvent> STEP_HEIGHT_DOWN = SOUND_EVENTS.register("step_height_down", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "step_height_down")));
//  public static final RegistryObject<SoundEvent> STEP_HEIGHT_UP = SOUND_EVENTS.register("step_height_up", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "step_height_up")));
//  public static final RegistryObject<SoundEvent> PSCHEW_FIRE = SOUND_EVENTS.register("pschew_fire", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "pschew_fire")));
//  public static final RegistryObject<SoundEvent> FROST_STAFF_LAUNCH = SOUND_EVENTS.register("frost_staff_launch", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "frost_staff_launch")));
//  public static final RegistryObject<SoundEvent> LIGHTNING_STAFF_LAUNCH = SOUND_EVENTS.register("lightning_staff_launch", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "lightning_staff_launch")));
//  public static final RegistryObject<SoundEvent> FIREBALL_STAFF_LAUNCH = SOUND_EVENTS.register("fireball_staff_launch", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "fireball_staff_launch")));
//  public static final RegistryObject<SoundEvent> BLOCK_SCAFFOLDING_0 = SOUND_EVENTS.register("block_scaffolding_0", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "block_scaffolding_0")));
//  public static final RegistryObject<SoundEvent> BLOCK_SCAFFOLDING_1 = SOUND_EVENTS.register("block_scaffolding_1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "block_scaffolding_1")));
//  public static final RegistryObject<SoundEvent> DUNGEONFINDER = SOUND_EVENTS.register("dungeonfinder", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "dungeonfinder")));
//  public static final RegistryObject<SoundEvent> WARP_ECHO = SOUND_EVENTS.register("warp_echo", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "warp_echo")));
//  public static final RegistryObject<SoundEvent> TOOL_MODE = SOUND_EVENTS.register("tool_mode", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "tool_mode")));
//  public static final RegistryObject<SoundEvent> DICE_MIKE_KOENIG = SOUND_EVENTS.register("dice_mike_koenig", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "dice_mike_koenig")));
//  public static final RegistryObject<SoundEvent> FILL = SOUND_EVENTS.register("fill", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "fill")));
//  public static final RegistryObject<SoundEvent> THUNK = SOUND_EVENTS.register("thunk", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "thunk")));
//  public static final RegistryObject<SoundEvent> FAN_LOOP = SOUND_EVENTS.register("fan_loop", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "fan_loop")));
//  public static final RegistryObject<SoundEvent> FAN_OFF = SOUND_EVENTS.register("fan_off", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "fan_off")));
//  public static final RegistryObject<SoundEvent> FAN_ON = SOUND_EVENTS.register("fan_on", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "fan_on")));
//  public static final RegistryObject<SoundEvent> METAL_PITCH = SOUND_EVENTS.register("metal_pitch", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "metal_pitch")));


  public static final RegistryObject<SoundEvent> SPIKES_ON = SOUND_EVENTS.register("spikes_on", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "spikes_on")));
  public static final RegistryObject<SoundEvent> SPIKES_OFF = SOUND_EVENTS.register("spikes_off", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "spikes_off")));
  public static final RegistryObject<SoundEvent> EQUIP_EMERALD = SOUND_EVENTS.register("equip_emerald", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "equip_emerald")));

  public static void register(IEventBus modEventBus) {
    SOUND_EVENTS.register(modEventBus);
  }
}
