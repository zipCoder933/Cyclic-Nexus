package org.zipcoder.cyclic.utils;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public final class FlightHelper {
    private FlightHelper() {
    }

    public static boolean flyOnGroundInCreative = true;

    private static final KeyMapping KEY_SPRINT = Minecraft.getInstance().options.keySprint;

    private static boolean isSprinting() {
        return KEY_SPRINT.isDown();
    }


    static boolean isFlyingCreativeOrSpectator(final LocalPlayer player) {
        return player.getAbilities().flying && (player.isCreative() || player.isSpectator());
    }

    static boolean shouldFlyOnGround(final LocalPlayer player) {
        return flyOnGroundInCreative && player.isCreative() && player.getAbilities().flying;
    }

}