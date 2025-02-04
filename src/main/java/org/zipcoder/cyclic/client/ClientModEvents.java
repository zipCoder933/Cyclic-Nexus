package org.zipcoder.cyclic.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;
import org.zipcoder.cyclic.client.utils.ClientGameSettings;
import org.zipcoder.cyclic.client.utils.ClientUtils;
import org.zipcoder.cyclic.items.glowHelmet.GlowHelmet;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    
    public static final String DEFAULT_CATEGORY = "key." + MOD_ID + ".default";
    public static final KeyMapping KEY_TOGGLE_NIGHT_VISION = new KeyMapping(
            "key." + MOD_ID + ".toggle_night_vision",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            DEFAULT_CATEGORY
    );

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KEY_TOGGLE_NIGHT_VISION);
    }

    private static double defaultGamma = 1.0D;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (KEY_TOGGLE_NIGHT_VISION.isDown()) {
            System.out.println("Custom key pressed!");
            if (ClientGameSettings.getGamma() > 1.0D) {
                ClientGameSettings.setGamma(defaultGamma);
                ClientUtils.showToast("Night Vision", "Night Vision Disabled");
            } else {
                defaultGamma = ClientGameSettings.getGammaClamped();
                ClientGameSettings.setGamma(GlowHelmet.MAX_GAMMA);
                ClientUtils.showToast("Night Vision", "Night Vision Enabled");
            }
        }
    }
}