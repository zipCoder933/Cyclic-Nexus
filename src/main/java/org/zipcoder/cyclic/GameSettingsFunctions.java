package org.zipcoder.cyclic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;

public class GameSettingsFunctions {

    private static final Minecraft mc = Minecraft.getInstance();

    public static void setGamma(Options options, double gamma) {
        options.gamma().set(gamma);
    }

    public static double getGamma(Options options) {
        return options.gamma().get();
    }

    public static void setFullbright() {
        Options options = mc.options;
        GameSettingsFunctions.setGamma(options, 1.0D);
    }
}