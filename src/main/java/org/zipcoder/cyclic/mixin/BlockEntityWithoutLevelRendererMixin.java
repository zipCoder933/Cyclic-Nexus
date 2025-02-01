package org.zipcoder.cyclic.mixin;

import net.minecraft.client.model.ShieldModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.zipcoder.cyclic.utils.mixin.I_BlockEntityWithoutLevelRenderer;

@Mixin(net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer.class)
public class BlockEntityWithoutLevelRendererMixin implements I_BlockEntityWithoutLevelRenderer {

    @Shadow
    private ShieldModel shieldModel;

    @Override
    public ShieldModel getShieldModel() {
        return shieldModel;
    }
}
