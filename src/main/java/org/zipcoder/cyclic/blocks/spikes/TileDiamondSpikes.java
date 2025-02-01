//package org.zipcoder.cyclic.blocks.spikes;
//
//import com.google.common.collect.Maps;
//import com.lothrazar.cyclic.block.TileBlockEntityCyclic;
//import com.lothrazar.cyclic.registry.TileRegistry;
//import net.minecraft.core.BlockPos;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//import net.minecraft.world.item.enchantment.Enchantments;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraftforge.common.util.FakePlayer;
//
//import java.lang.ref.WeakReference;
//import java.util.Map;
//
//public class TileDiamondSpikes extends TileBlockEntityCyclic {
//
//  WeakReference<FakePlayer> fakePlayer;
//  final static boolean dropItemsOnGround = true;
//
//  public TileDiamondSpikes(BlockPos pos, BlockState state) {
//    super(TileRegistry.SPIKES_DIAMOND.get(), pos, state);
//  }
//
//  @Override
//  public void saveAdditional(CompoundTag tag) {
//    super.saveAdditional(tag);
//  }
//
//  @Override
//  public void load(CompoundTag tag) {
//    super.load(tag);
//  }
//
//  public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, TileDiamondSpikes e) {
//    e.tick();
//  }
//
//  public static <E extends BlockEntity> void clientTick(Level level, BlockPos blockPos, BlockState blockState, TileDiamondSpikes e) {
//    e.tick();
//  }
//
//  public void tick() {
//    if (timer > 0) {
//      timer--;
//      return;
//    }
//    timer = level.random.nextInt(24) + 12;
//    if (fakePlayer == null && level instanceof ServerLevel) {
//      fakePlayer = setupBeforeTrigger((ServerLevel) level, "spikes_diamond");
//      if (fakePlayer.get().getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
//        Map<Enchantment, Integer> map = Maps.newHashMap();
//        map.put(Enchantments.BANE_OF_ARTHROPODS, 2);
//        map.put(Enchantments.SWEEPING_EDGE, 3);
//        map.put(Enchantments.SHARPNESS, 1);
//        ItemStack sword = new ItemStack(Items.DIAMOND_SWORD);
//        EnchantmentHelper.setEnchantments(map, sword);
//        fakePlayer.get().setItemInHand(InteractionHand.MAIN_HAND, sword);
//      }
//      if (level.random.nextDouble() < 0.001F) {
//        tryDumpFakePlayerInvo(fakePlayer, null, dropItemsOnGround);
//      }
//    }
//  }
//
//  @Override
//  public void setField(int field, int value) {}
//
//  @Override
//  public int getField(int field) {
//    return 0;
//  }
//}
