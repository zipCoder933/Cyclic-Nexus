package org.zipcoder.cyclic.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Iterator;
import java.util.List;

public class ItemStackUtil {
    public static final String NBT_LORE = "Lore";
    public static final String NBT_DISPLAY = "display";

    public ItemStackUtil() {
    }

    public static void addLoreToStack(ItemStack crafting, String lore, String color) {
        CompoundTag displayTag = new CompoundTag();
        ListTag tagList = new ListTag();
        if (color == null) {
            color = "gold";
        }

        String escaped = "{\"text\":\"" + lore + "\",\"color\":\"" + color + "\"}";
        tagList.add(StringTag.valueOf(escaped));
        displayTag.put("Lore", tagList);
        crafting.getTag().put("display", displayTag);
    }

    public static void applyRandomEnch(RandomSource random, ItemStack crafting) {
        EnchantmentHelper.enchantItem(random, crafting, 1, false);
    }

    public static void applyRandomEnch(RandomSource random, ItemStack crafting, int level, boolean allowTreasure) {
        applyRandomEnch(random, crafting);
    }

    public static int countEmptySlots(IItemHandler handler) {
        if (handler == null) {
            return 0;
        } else {
            int empty = 0;

            for(int i = 0; i < handler.getSlots(); ++i) {
                if (handler.getStackInSlot(i).isEmpty()) {
                    ++empty;
                }
            }

            return empty;
        }
    }

    public static ItemStack findItem(String id) {
        Item head = (Item) ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(id));
        return head != null ? new ItemStack(head) : ItemStack.EMPTY;
    }

    public static void dropAll(IItemHandler items, Level world, BlockPos pos) {
        if (items != null) {
            for(int i = 0; i < items.getSlots(); ++i) {
                Containers.dropItemStack(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), items.getStackInSlot(i));
            }

        }
    }

    public static void repairItem(ItemStack s) {
        repairItem(s, 1);
    }

    public static void repairItem(ItemStack s, int amount) {
        s.setDamageValue(Math.max(0, s.getDamageValue() - amount));
    }

    public static void damageItem(LivingEntity player, ItemStack stack) {
        damageItem(player, stack, InteractionHand.MAIN_HAND);
    }

    public static void damageItem(LivingEntity player, ItemStack stack, InteractionHand hand) {
        if (stack.isDamageableItem()) {
            if (player == null) {
                stack.setDamageValue(stack.getDamageValue() + 1);
            } else {
                stack.hurtAndBreak(1, player, (p) -> {
                    p.broadcastBreakEvent(InteractionHand.MAIN_HAND);
                });
            }

            if (stack.getDamageValue() >= stack.getMaxDamage()) {
                stack.shrink(1);
                stack = ItemStack.EMPTY;
            }

        }
    }

    public static void damageItemRandomly(LivingEntity player, ItemStack stack) {
        if (player.level().random.nextDouble() < 0.001) {
            damageItem(player, stack);
        }

    }

    public static void drop(Level world, BlockPos pos, Block drop) {
        if (!world.isClientSide) {
            world.addFreshEntity(new ItemEntity(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), new ItemStack(drop.asItem())));
        }

    }

    public static void drop(Level world, BlockPos pos, ItemStack drop) {
        if (!world.isClientSide) {
            world.addFreshEntity(new ItemEntity(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), drop));
        }

    }

    public static boolean matches(ItemStack current, ItemStack in) {
        return ItemStack.matches(current, in) && ItemStack.isSameItemSameTags(current, in);
    }

    public static void shrink(Player player, ItemStack stac) {
        if (!player.isCreative()) {
            stac.shrink(1);
        }

    }

    public static void drop(Level world, BlockPos center, List<ItemStack> lootDrops) {
        Iterator var3 = lootDrops.iterator();

        while(var3.hasNext()) {
            ItemStack dropMe = (ItemStack)var3.next();
            drop(world, center, dropMe);
        }

    }

    public static void dropItemStackMotionless(Level world, BlockPos pos, ItemStack stack) {
        if (!stack.isEmpty()) {
            if (!world.isClientSide) {
                ItemEntity entityItem = new ItemEntity(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, stack);
                world.addFreshEntity(entityItem);
                entityItem.setDeltaMovement(0.0, 0.0, 0.0);
            }

        }
    }

    public static void deleteTag(ItemStack itemstack) {
        int dmg = itemstack.getDamageValue();
        itemstack.setTag((CompoundTag)null);
        itemstack.setDamageValue(dmg);
    }

    public static void randomlyRepair(RandomSource rnd, ItemStack stack, int factor) {
        if (stack.isDamaged() && rnd.nextInt(factor) == 0) {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }

    }
}