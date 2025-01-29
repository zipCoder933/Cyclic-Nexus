package org.zipcoder.cyclic.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;

public class GameSettingsFunctions {

    public static final double DEFAULT_GAMMA = 1.0D;
    public static final double MIN_GAMMA = 0.0D;
    public static final double MAX_GAMMA = 5.0D;

    private static final Minecraft mc = Minecraft.getInstance();

    public static void setGamma(double gamma) {
        Options options = mc.options;
        options.gamma().set(gamma);
    }

    public static double getGamma() {
        Options options = mc.options;
        return options.gamma().get();
    }

}