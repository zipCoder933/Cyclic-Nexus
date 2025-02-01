package org.zipcoder.cyclic.events;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModKeybinds {
    public static final String DEFAULT_CATEGORY = "key."+MOD_ID+".default";

    public static final KeyMapping KEY_TOGGLE_NIGHT_VISION = new KeyMapping(
            "key."+MOD_ID+".toggle_night_vision",
            KeyConflictContext.IN_GAME, // Only works in-game
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_N, // Default to "G"
            DEFAULT_CATEGORY
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KEY_TOGGLE_NIGHT_VISION);
    }
}
