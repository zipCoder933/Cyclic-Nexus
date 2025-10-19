package org.zipcoder.cyclic.events;

import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ArmorWatcher {
    
    @SubscribeEvent
    public static void onArmorChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        EquipmentSlot slot = event.getSlot();
        // Detect only armor slots
        if (slot.getType() == EquipmentSlot.Type.ARMOR) {
            var from = event.getFrom();
            var to = event.getTo();

            if (from.isEmpty() && !to.isEmpty()) {
                player.sendSystemMessage(
                    net.minecraft.network.chat.Component.literal("You equipped: " + to.getDisplayName().getString() + " 🛡️")
                );
            } else if (!from.isEmpty() && to.isEmpty()) {
                player.sendSystemMessage(
                    net.minecraft.network.chat.Component.literal("You unequipped: " + from.getDisplayName().getString() + " 👕")
                );
            }
        }
    }
}
