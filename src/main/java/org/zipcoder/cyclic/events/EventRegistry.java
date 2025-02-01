package org.zipcoder.cyclic.events;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.zipcoder.cyclic.potions.PotionsRegistry;

public class EventRegistry {
    public static void setup(final FMLCommonSetupEvent event) {
        PotionsRegistry.setup();
//        PacketRegistry.setup();
//        MinecraftForge.EVENT_BUS.register(new PotionEvents());
//        MinecraftForge.EVENT_BUS.register(new ItemEvents());
//        MinecraftForge.EVENT_BUS.register(new PlayerDataEvents());
//        MinecraftForge.EVENT_BUS.register(new PlayerAbilityEvents());
//        MinecraftForge.EVENT_BUS.register(new CapabilityEvents());
//        event.enqueueWork(() -> {
//            CompostRegistry.setup();
//        });
    }
}
