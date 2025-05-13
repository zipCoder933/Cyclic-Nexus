package org.zipcoder.cyclic.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.zipcoder.cyclic.utils.mixin.I_ServerGamePacketListenerImpl;

@Mixin(net.minecraft.server.network.ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin implements I_ServerGamePacketListenerImpl {
    @Shadow
    private int aboveGroundTickCount;

    @Override
    public int getAboveGroundTickCount() {
        return aboveGroundTickCount;
    }

    @Override
    public void setAboveGroundTickCount(int val) {
        aboveGroundTickCount = val;
    }
}
