package org.zipcoder.cyclic.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import org.zipcoder.cyclic.utils.mixin.I_OptionInstance;

public class ClientGameSettings {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final OptionInstance<Double> gamma = mc.options.gamma();
    private static final I_OptionInstance<Double> mixInGamma = (I_OptionInstance<Double>) (Object) gamma;

    public static void setGamma(double g) {
        mixInGamma.setUnchecked(g);
    }

    public static double getGamma() {
        return gamma.get();
    }

    public static double getGammaClamped() {
        double value = gamma.get();
        if (value < 0.0D) return 0.0D;
        else if (value > 1.0D) return 1.0D;
        else return value;
    }
}