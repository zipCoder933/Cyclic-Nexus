package org.zipcoder.cyclic.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public class NetworkHandler {

    public static SimpleChannel CHANNEL_INSTANCE;
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        CHANNEL_INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> "1.0", s -> true, s -> true);

        CHANNEL_INSTANCE.messageBuilder(GlowHelmetPacket.class, nextID())
                .encoder(GlowHelmetPacket::encode)
                .decoder(GlowHelmetPacket::decode)
                .consumerNetworkThread(GlowHelmetPacket::handle)
                .add();
    }

    public static void sendToClient(Object packet, ServerPlayer player) {
        CHANNEL_INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object packet) {
        CHANNEL_INSTANCE.sendToServer(packet);
    }
}
