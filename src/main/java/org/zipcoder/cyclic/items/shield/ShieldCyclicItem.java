package org.zipcoder.cyclic.items.shield;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.List;

public class ShieldCyclicItem extends ShieldItem implements Equipable {
    public static enum ShieldType {
        LEATHER, OBSIDIAN, BONE;
        // STONE? COPPER?
    }

    private ShieldType type;

    public ShieldCyclicItem(Item.Properties properties, ShieldType type) {
        super(properties);
        this.type = type;
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stackShield, ItemStack stackIngredient) {
        if (type == ShieldType.LEATHER)
            return stackIngredient.is(Items.LEATHER);
        if (type == ShieldType.BONE)
            return stackIngredient.is(Items.BONE);
        if (type == ShieldType.OBSIDIAN)
            return stackIngredient.is(Blocks.OBSIDIAN.asItem());
        return false;
    }

    public int getUseDuration(ItemStack p_43107_) {
        return 72000;
    }

//    @Override
//    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
//        consumer.accept(new IClientItemExtensions() {
//
//            @Override
//            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
//                return ShieldBlockEntityWithoutLevelRenderer.instance;
//            }
//        });
//    }
}
