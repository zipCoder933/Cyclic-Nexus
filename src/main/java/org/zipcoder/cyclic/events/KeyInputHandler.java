package org.zipcoder.cyclic.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.zipcoder.cyclic.items.glowHelmet.GlowHelmet;
import org.zipcoder.cyclic.utils.GameSettingsFunctions;

/**
 * Key events only work in the client
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KeyInputHandler {

    private static double defaultGamma = 1.0D;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (ModKeybinds.KEY_TOGGLE_NIGHT_VISION.isDown()) {
            System.out.println("Custom key pressed!");
            if (GameSettingsFunctions.getGamma() > 1.0D) {
                GameSettingsFunctions.setGamma(defaultGamma);
                showToast("Cyclic", "Night Vision Disabled");
            } else {
                defaultGamma = GameSettingsFunctions.getGammaClamped();
                GameSettingsFunctions.setGamma(GlowHelmet.MAX_GAMMA);
                showToast("Cyclic", "Night Vision Enabled");
            }
        }
    }

    public static void showToast(String title, String message) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            SystemToast toast = new SystemToast(
                    SystemToast.SystemToastIds.TUTORIAL_HINT, // Toast Type
                    Component.literal(title),
                    Component.literal(message)
            );
            mc.getToasts().addToast(toast);
        }
    }
}
