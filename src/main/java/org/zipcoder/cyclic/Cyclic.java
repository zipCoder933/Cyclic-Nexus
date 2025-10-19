package org.zipcoder.cyclic;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.zipcoder.cyclic.effects.EffectRegistry;
import org.zipcoder.cyclic.enchantments.EnchantmentRegistry;
import org.zipcoder.cyclic.events.EventRegistry;
import org.zipcoder.cyclic.network.NetworkHandler;
import org.zipcoder.cyclic.sound.SoundRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Cyclic.MOD_ID)
public class Cyclic {
    public static final String MOD_ID = "cyclic";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final PreInitConfig preInit = new PreInitConfig();

    public Cyclic() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(EventRegistry::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(EventRegistry::setupClient);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);
        EnchantmentRegistry.register(modEventBus);
        EffectRegistry.register(modEventBus);
        SoundRegistry.register(modEventBus);
        PotionsRegistry.register(modEventBus);


        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        NetworkHandler.registerMessages();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        ItemRegistry.addToCreative(event);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }


}
