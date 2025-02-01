package org.zipcoder.cyclic.blocks.angelScaffolding;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.zipcoder.cyclic.utils.EntityUtil;

public class BlockScaffolding extends Block {

    private static final double CHANCE_CRUMBLE = 0.6;
    private static final double CLIMB_SPEED = 0.31D; // compare to climbing glove
    private static final double OFFSET = 0.125D;
    public static final VoxelShape AABB = Block.box(OFFSET, OFFSET, OFFSET,
            16 - OFFSET, 16 - OFFSET, 16 - OFFSET); //required to make entity collied happen for ladder climbing
    private boolean doesAutobreak = true;

    public BlockScaffolding(Properties properties, boolean autobreak) {
        super(properties.strength(0.1F).randomTicks().noOcclusion().sound(SoundType.SCAFFOLDING));
        this.doesAutobreak = autobreak;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidState) {
        return true;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return AABB;
    }


    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        //If a block next to it was broken, destroy it
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        if (blockIn == this) {
            worldIn.destroyBlock(pos, true);
        }
    }

//    @Override
//    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {}

    //Random tick is more efficient
    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        if (doesAutobreak && random.nextDouble() < CHANCE_CRUMBLE) {
            worldIn.destroyBlock(pos, true);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        //If we are crouching, place instead of replacing
        if (player.isCrouching()) return super.use(state, world, pos, player, hand, hit);

        //Replace a block that is placed in it
        ItemStack heldItem = player.getItemInHand(hand);
        if (heldItem.isEmpty()) {
            return InteractionResult.SUCCESS;
        }
        Block b = Block.byItem(heldItem.getItem());
        if (b != null && b != Blocks.AIR && !(b instanceof BlockScaffolding)
                && world.getBlockState(pos).getBlock() == this) {
            world.destroyBlock(pos, true);
            UseOnContext context = new UseOnContext(player, hand, hit);
            heldItem.useOn(context);
            return InteractionResult.SUCCESS;
            //to cancel event chains
        }
        return InteractionResult.PASS;
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if (!(entityIn instanceof LivingEntity)) {
            return;
        }
        LivingEntity entity = (LivingEntity) entityIn;
        if (!entityIn.horizontalCollision) {
            return;
        }
        EntityUtil.tryMakeEntityClimb(worldIn, entity, CLIMB_SPEED);
    }
}
