package org.zipcoder.cyclic.sound;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Iterator;

public class SoundUtil {
    public SoundUtil() {
    }

    public static void playSoundAtBlock(Level world, Entity player, BlockPos pos, SoundEvent soundIn) {
        world.playSound(player, pos, soundIn, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public static void playSound(Level world, BlockPos pos, SoundEvent soundIn) {
        world.playLocalSound((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), soundIn, SoundSource.BLOCKS, 0.5F, 0.5F, false);
    }

    public static void playSound(Level world, BlockPos pos, SoundEvent soundIn, float volume) {
        world.playLocalSound((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), soundIn, SoundSource.BLOCKS, volume, 0.5F, false);
    }

    public static void playSound(Entity entityIn, SoundEvent soundIn) {
        playSound(entityIn, soundIn, 1.0F, 1.0F);
    }

    public static void playSound(Entity entityIn, SoundEvent soundIn, float volume) {
        playSound(entityIn, soundIn, volume, 1.0F);
    }

    public static void playSound(Entity entityIn, SoundEvent soundIn, float volume, float pitch) {
        if (entityIn != null && entityIn.level().isClientSide) {
            entityIn.playSound(soundIn, volume, pitch);
        }

    }

    public static void playSoundFromServer(ServerPlayer entityIn, BlockPos pos, SoundEvent soundIn, float vol, float pitch) {
        if (soundIn != null && entityIn != null) {
            entityIn.connection.send(new ClientboundSoundPacket(Holder.direct(soundIn), SoundSource.BLOCKS, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), vol, pitch, entityIn.level().getRandom().nextLong()));
        }
    }

    public static void playSoundFromServer(ServerPlayer entityIn, SoundEvent soundIn, float vol, float pitch) {
        if (soundIn != null && entityIn != null) {
            entityIn.connection.send(new ClientboundSoundPacket(Holder.direct(soundIn), SoundSource.BLOCKS, entityIn.xOld, entityIn.yOld, entityIn.zOld, vol, pitch, entityIn.level().getRandom().nextLong()));
        }
    }

    public static void playSoundFromServer(ServerLevel world, BlockPos pos, SoundEvent soundIn) {
        Iterator var3 = world.players().iterator();

        while(var3.hasNext()) {
            ServerPlayer sp = (ServerPlayer)var3.next();
            playSoundFromServer(sp, pos, soundIn, 1.0F, 1.0F);
        }

    }

    public static void playSoundFromServerById(ServerLevel world, BlockPos pos, String sid) {
        SoundEvent sound = (SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(sid));
        if (sound != null) {
            Iterator var4 = world.players().iterator();

            while(var4.hasNext()) {
                ServerPlayer sp = (ServerPlayer)var4.next();
                playSoundFromServer(sp, pos, sound, 1.0F, 1.0F);
            }
        }

    }

    public static void playSoundById(Player player, String sid) {
        SoundEvent sound = (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(sid));
        if (sound != null && player.level().isClientSide) {
            playSound(player, sound);
        }

    }
}
