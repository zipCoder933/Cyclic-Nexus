package org.zipcoder.cyclic.events;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.zipcoder.cyclic.Cyclic;
import org.zipcoder.cyclic.item.ItemRegistry;
import org.zipcoder.cyclic.item.glowHelmet.GlowHelmet;
import org.zipcoder.cyclic.utils.GameSettingsFunctions;


//This tag automatically adds the event without needing to register it manually
@Mod.EventBusSubscriber(modid = Cyclic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EventHandler {

    private static double defaultGamma = 1.0D;

//    @SubscribeEvent
//    public static void onClientTick(TickEvent.ClientTickEvent event) {
//        if (event.phase == TickEvent.Phase.START) { // Runs at the start of each client tick
//
//        } else if (event.phase == TickEvent.Phase.END) {// Runs at the end of each client tick
//
//        }
//    }


    public static ItemStack getHelmet(Player player) {
        return player.getItemBySlot(EquipmentSlot.HEAD);
    }



    @SubscribeEvent
    public static void onHelmetChange(LivingEquipmentChangeEvent event) {
        //This event is only fired on the server side
        System.out.println("LivingEquipmentChangeEvent fired! ClientSide: " + event.getEntity().level().isClientSide);
        Player player = Minecraft.getInstance().player;//Get the local player
        if (event.getEntity().level().isClientSide()) System.out.println("On the client!");

        // Check if the slot is the HEAD (helmet)
        if (event.getSlot() == EquipmentSlot.HEAD) {
            System.out.println("Head slot changed!");
            ItemStack oldHelmet = event.getFrom();
            ItemStack newHelmet = event.getTo();

            // If the custom helmet is equipped, increase gamma
            if (newHelmet.getItem() == ItemRegistry.GLOW_HELMET.get()) {
                System.out.println("Custom helmet equipped! Adjusting gamma...");
                defaultGamma = GameSettingsFunctions.getGammaClamped();
                GameSettingsFunctions.setGamma(GlowHelmet.MAX_GAMMA); // Set high gamma
            } else if (oldHelmet.getItem() == ItemRegistry.GLOW_HELMET.get()
                    && newHelmet.getItem() != ItemRegistry.GLOW_HELMET.get()) {
                System.out.println("Custom helmet removed! Resetting gamma...");
                GameSettingsFunctions.setGamma(defaultGamma); // Default gamma
            }
        }
    }


//    @SubscribeEvent
//    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
//        System.out.println("Player logged in!");
//        // Ensure this is the local player
//        if (!(event.getEntity() instanceof LocalPlayer player))return;
//
//        // Check if the helmet is already equipped
//        ItemStack currentHelmet = player.getItemBySlot(EquipmentSlot.HEAD);
//
//        if (currentHelmet.getItem() == ModItems.GLOW_HELMET.get()) {
//            System.out.println("Custom helmet detected on login! Adjusting gamma...");
//            Minecraft.getInstance().options.gamma().set(0.0); // Set high gamma
//        }
//    }
}
