package org.zipcoder.cyclic.events;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.zipcoder.cyclic.items.shield.MaterialShieldRegistry;
import org.zipcoder.cyclic.potions.PotionsRegistry;

public class EventRegistry {
    public static void setup(final FMLCommonSetupEvent event) {
        PotionsRegistry.setup();
//        PacketRegistry.setup();
        //Since we already use hints, we don't need this
//        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

}
