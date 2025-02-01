package org.zipcoder.cyclic.items.shield;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.zipcoder.cyclic.items.ItemRegistry;
import org.zipcoder.cyclic.utils.mixin.I_BlockEntityWithoutLevelRenderer;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShieldBlockEntityWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer {

    public static ShieldBlockEntityWithoutLevelRenderer instance;

    public ShieldBlockEntityWithoutLevelRenderer(BlockEntityRenderDispatcher rd, EntityModelSet ems) {
        super(rd, ems);
    }

    @SubscribeEvent
    public static void onRegisterReloadListener(RegisterClientReloadListenersEvent event) {
        instance = new ShieldBlockEntityWithoutLevelRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        event.registerReloadListener(instance);
    }

    @Override
    public void renderByItem(ItemStack stackIn, ItemDisplayContext type, PoseStack ps, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        /**
         * Shield model is private, so we have to use a mixin
         */
        I_BlockEntityWithoutLevelRenderer mixinThis = (I_BlockEntityWithoutLevelRenderer) this;
        ShieldModel shieldModel = mixinThis.getShieldModel();

        //copied from superclass
        ps.pushPose();
        ps.scale(1, -1, -1);
        boolean isBanner = (stackIn.getTagElement("BlockEntityTag") != null);
        Material rendermaterial = isBanner ? ModelBakery.SHIELD_BASE : ModelBakery.NO_PATTERN_SHIELD;

        if (stackIn.is(ItemRegistry.SHIELD_LEATHER.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_LEATHER : MaterialShieldRegistry.SHIELD_BASE_LEATHER_NOPATTERN;
        } else if (stackIn.is(ItemRegistry.SHIELD_BONE.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_BONE : MaterialShieldRegistry.SHIELD_BASE_BONE_NOPATTERN;
        } else if (stackIn.is(ItemRegistry.SHIELD_OBSIDIAN.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_OBSIDIAN : MaterialShieldRegistry.SHIELD_BASE_OBSIDIAN_NOPATTERN;
        }
        VertexConsumer vertex = rendermaterial.sprite().wrap(ItemRenderer.getFoilBufferDirect(
                buffer,
                shieldModel.renderType(rendermaterial.atlasLocation()),
                true, stackIn.hasFoil()));

        shieldModel.handle().render(ps, vertex, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        //    if (isBanner) {
        //      List<Pair<BannerPattern, DyeColor>> pattern = BannerBlockEntity.createPatterns(ShieldItem.getColor(stackIn), BannerBlockEntity.getItemPatterns(stackIn));
        //      BannerRenderer.renderPatterns(ps, buffer, combinedLight, combinedOverlay, shieldModel.plate(), rendermaterial, false, pattern, stackIn.hasFoil());
        //    }
        //    else {
        shieldModel.plate().render(ps, vertex, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        //    }

        ps.popPose();
    }
}