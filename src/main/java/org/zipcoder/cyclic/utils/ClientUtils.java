package org.zipcoder.cyclic.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;

public class ClientUtils {
    static Minecraft mc = Minecraft.getInstance();

    public static void showToast(String title, String message) {
        if (mc.player != null) {
            SystemToast toast = new SystemToast(
                    SystemToast.SystemToastIds.TUTORIAL_HINT, // Toast Type
                    Component.literal(title),
                    Component.literal(message)
            );
            mc.getToasts().addToast(toast);
        }
    }

    public static void displayMessge(String message) {
        Minecraft.getInstance().player.displayClientMessage(Component.literal(message), true);
    }
}
