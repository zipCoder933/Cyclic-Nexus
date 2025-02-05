package org.zipcoder.cyclic.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.zipcoder.cyclic.client.utils.ClientGameSettings;
import org.zipcoder.cyclic.items.glowHelmet.GlowHelmet;
import static org.zipcoder.cyclic.client.ClientModEvents.KEY_TOGGLE_NIGHT_VISION;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyInputHandlerClient {


    private static double defaultGamma = 1.0D;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (KEY_TOGGLE_NIGHT_VISION.isDown()) {
            System.out.println("Custom key pressed!");
            if (ClientGameSettings.getGamma() > 1.0D) {
                ClientGameSettings.setGamma(defaultGamma);
                org.zipcoder.cyclic.client.utils.ClientUtils.showToast("Night Vision", "Night Vision Disabled");
            } else {
                defaultGamma = ClientGameSettings.getGammaClamped();
                ClientGameSettings.setGamma(GlowHelmet.MAX_GAMMA);
                org.zipcoder.cyclic.client.utils.ClientUtils.showToast("Night Vision", "Night Vision Enabled");
            }
        }
    }
}