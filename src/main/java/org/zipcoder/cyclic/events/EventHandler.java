package org.zipcoder.cyclic.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.social.PlayerEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.zipcoder.cyclic.Cyclic;
import org.zipcoder.cyclic.blocks.angelScaffolding.ItemScaffolding;
import org.zipcoder.cyclic.items.ItemRegistry;
import org.zipcoder.cyclic.items.glowHelmet.GlowHelmet;
import org.zipcoder.cyclic.utils.GameSettingsFunctions;
import org.zipcoder.cyclic.utils.ItemStackUtil;
import org.zipcoder.cyclic.utils.LevelWorldUtil;


//This tag automatically adds the event without needing to register it manually
@Mod.EventBusSubscriber(modid = Cyclic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {


    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getItemStack().isEmpty()) {
            if (event.getItemStack().getItem() instanceof ItemScaffolding
                    && event.getEntity().isCrouching()) {
                scaffoldHit(event);
            }
        }
    }

    private void scaffoldHit(PlayerInteractEvent.RightClickBlock event) {
        ItemScaffolding item = (ItemScaffolding) event.getItemStack().getItem();
        Direction opp = event.getFace().getOpposite();
        BlockPos dest = LevelWorldUtil.nextReplaceableInDirection(event.getLevel(), event.getPos(), opp, 16, item.getBlock());
        if (event.getLevel().isEmptyBlock(dest)) {
            event.getLevel().setBlockAndUpdate(dest, item.getBlock().defaultBlockState());
            ItemStack stac = event.getEntity().getItemInHand(event.getHand());
            ItemStackUtil.shrink(event.getEntity(), stac);
            event.setCanceled(true);
        }
    }


//    To disable flight inertia
//    public final static Vec3 defaultDelta = new Vec3(1, 1, 1);
//    public final static Vec3 stillDelta = new Vec3(0.01, 0, 0.01);
//    @SubscribeEvent
//    public static void onClientTick(TickEvent.ClientTickEvent event) {
////        ItemTags.TRIMMABLE_ARMOR
//        if (event.phase == TickEvent.Phase.END) {
//            Player player = Minecraft.getInstance().player;
//            if (player != null) {//Important
//                if (player.getAbilities().flying) {
//                    // Force movement values to prevent inertia
//                    player.setDeltaMovement(stillDelta);
//                    player.setSpeed(100000000);
//                    // player.noPhysics = true;
//                }
//            }
//        }
//    }

//    private static double defaultGamma = 1.0D;
//    public static ItemStack getHelmet(Player player) {
//        return player.getItemBySlot(EquipmentSlot.HEAD);
//    }
//    @SubscribeEvent
//    public static void onHelmetChange(LivingEquipmentChangeEvent event) {
//        //This event is only fired on the server side
//        System.out.println("LivingEquipmentChangeEvent fired! ClientSide: " + event.getEntity().level().isClientSide);
//        Player player = Minecraft.getInstance().player;//Get the local player
//        if (event.getEntity().level().isClientSide()) System.out.println("On the client!");
//
//        // Check if the slot is the HEAD (helmet)
//        if (event.getSlot() == EquipmentSlot.HEAD) {
//            System.out.println("Head slot changed!");
//            ItemStack oldHelmet = event.getFrom();
//            ItemStack newHelmet = event.getTo();
//
//            // If the custom helmet is equipped, increase gamma
//            if (newHelmet.getItem() == ItemRegistry.GLOW_HELMET.get()) {
//                System.out.println("Custom helmet equipped! Adjusting gamma...");
//                defaultGamma = GameSettingsFunctions.getGammaClamped();
//                GameSettingsFunctions.setGamma(GlowHelmet.MAX_GAMMA); // Set high gamma
//            } else if (oldHelmet.getItem() == ItemRegistry.GLOW_HELMET.get()
//                    && newHelmet.getItem() != ItemRegistry.GLOW_HELMET.get()) {
//                System.out.println("Custom helmet removed! Resetting gamma...");
//                GameSettingsFunctions.setGamma(defaultGamma); // Default gamma
//            }
//        }
//    }


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
