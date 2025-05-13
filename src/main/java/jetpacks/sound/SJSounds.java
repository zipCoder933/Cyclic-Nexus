package jetpacks.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import jetpacks.SimplyJetpacks;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public class SJSounds {

    public static final SoundEvent JETPACK = SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "jetpack"));
    public static final SoundEvent JETPACK_OTHER = SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "jetpack_other"));
    public static final SoundEvent ROCKET = SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "rocket"));

    /*// TODO: check this (cringe)
    @SubscribeEvent
//    public void onRegisterSounds(FMLClientSetupEvent<SoundEvent> event) {
    public void onRegisterSounds(RegisterEvent.<SoundEvent> event) {
//        IForgeRegistry<SoundEvent> registry = event.get();
        IForgeRegistry<SoundEvent> registry = RegistryManager.ACTIVE.getRegistry(ForgeRegistries.Keys.SOUND_EVENTS);
        registry.register("jetpack", JETPACK);
        registry.register("jetpack_other", JETPACK_OTHER);
        registry.register("rocket", ROCKET);
    }*/
}
