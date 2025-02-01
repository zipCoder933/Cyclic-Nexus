package org.zipcoder.cyclic.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.zipcoder.cyclic.utils.mixin.I_OptionInstance;

public class GameSettingsFunctions {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final OptionInstance<Double> gamma = mc.options.gamma();

    public static void setGamma(double g) {
        I_OptionInstance<Double> mixIn = (I_OptionInstance<Double>) (Object) gamma;
        mixIn.setUnchecked(g);
    }

    public static double getGamma() {
        Options options = mc.options;
        return options.gamma().get();
    }

    public static double getGammaClamped() {
        Options options = mc.options;
        double value = options.gamma().get();
        if (value < 0.0D) return 0.0D;
        else if (value > 1.0D) return 1.0D;
        else return value;
    }
}