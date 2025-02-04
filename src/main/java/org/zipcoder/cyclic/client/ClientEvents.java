package org.zipcoder.cyclic.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.zipcoder.cyclic.items.shield.MaterialShieldRegistry;

public class ClientEvents {
    public static void setupClient(final FMLClientSetupEvent event) {
        MaterialShieldRegistry.initShields();
    }
}
