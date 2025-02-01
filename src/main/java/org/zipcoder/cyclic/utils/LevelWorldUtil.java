package org.zipcoder.cyclic.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.ArrayList;
import java.util.List;

public class LevelWorldUtil {
    public LevelWorldUtil() {
    }

    public static Direction getRandomDirection(RandomSource rand) {
        int index = Mth.nextInt(rand, 0, Direction.values().length - 1);
        return Direction.values()[index];
    }

    public static boolean removeFlowingLiquid(Level world, BlockPos pos, boolean nukeOption) {
        BlockState blockHere = world.getBlockState(pos);
        if (blockHere.hasProperty(BlockStateProperties.WATERLOGGED)) {
            return world.setBlock(pos, (BlockState) blockHere.setValue(BlockStateProperties.WATERLOGGED, false), 18);
        } else if (blockHere.getBlock() instanceof BucketPickup) {
            BucketPickup block = (BucketPickup) blockHere.getBlock();
            ItemStack res = block.pickupBlock(world, pos, blockHere);
            return !res.isEmpty() ? world.setBlock(pos, Blocks.AIR.defaultBlockState(), 18) : true;
        } else {
            return nukeOption ? world.setBlock(pos, Blocks.AIR.defaultBlockState(), 18) : false;
        }
    }

    public static String dimensionToString(Level world) {
        return world.dimension().location().toString();
    }

    public static ResourceKey<Level> stringToDimension(String key) {
        return ResourceKey.create(Registries.DIMENSION, ResourceLocation.tryParse(key));
    }

    public static double distanceBetweenHorizontal(BlockPos start, BlockPos end) {
        int xDistance = Math.abs(start.getX() - end.getX());
        int zDistance = Math.abs(start.getZ() - end.getZ());
        return Math.sqrt((double) (xDistance * xDistance + zDistance * zDistance));
    }

    public static BlockPos nextReplaceableInDirection(Level world, BlockPos posIn, Direction facing, int max, Block blockMatch) {
        BlockPos posToPlaceAt = new BlockPos(posIn);
        BlockPos posLoop = new BlockPos(posIn);

        for (int i = 0; i < max; ++i) {
            BlockState state = world.getBlockState(posLoop);
            if (state.getBlock() != null && world.getBlockState(posLoop).isAir()) {
                posToPlaceAt = posLoop;
                break;
            }

            posLoop = posLoop.relative(facing);
        }

        return posToPlaceAt;
    }

    public static ItemEntity dropItemStackInWorld(Level world, BlockPos pos, ItemStack stack) {
        if (pos != null && world != null && !stack.isEmpty()) {
            ItemEntity entityItem = new ItemEntity(world, (double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, stack);
            if (!world.isClientSide) {
                world.addFreshEntity(entityItem);
            }

            return entityItem;
        } else {
            return null;
        }
    }

    public static void dropItemStackRandomMotion(Level world, BlockPos pos, ItemStack itemStack, float motion) {
        ItemEntity entityitem = new ItemEntity(world, (double) ((float) pos.getX() + world.random.nextFloat() * 0.8F + 0.1F), (double) ((float) pos.getY() + world.random.nextFloat() * 0.8F + 0.1F), (double) ((float) pos.getZ() + world.random.nextFloat() * 0.8F + 0.1F), itemStack);
        float motionX = (float) world.random.nextGaussian() * motion;
        float motionY = (float) world.random.nextGaussian() * motion + 0.2F;
        float motionZ = (float) world.random.nextGaussian() * motion;
        entityitem.setDeltaMovement((double) motionX, (double) motionY, (double) motionZ);
        world.addFreshEntity(entityitem);
    }

    public static boolean doesBlockExist(Level world, BlockPos start, BlockState blockHunt, int radius, int height) {
        int xMin = start.getX() - radius;
        int xMax = start.getX() + radius;
        int yMin = start.getY();
        int yMax = start.getY() + height;
        int zMin = start.getZ() - radius;
        int zMax = start.getZ() + radius;
        BlockPos posCurrent = null;

        for (int xLoop = xMin; xLoop <= xMax; ++xLoop) {
            for (int yLoop = yMin; yLoop <= yMax; ++yLoop) {
                for (int zLoop = zMin; zLoop <= zMax; ++zLoop) {
                    posCurrent = new BlockPos(xLoop, yLoop, zLoop);
                    if (world.getBlockState(posCurrent) == blockHunt) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static ArrayList<BlockPos> findBlocksByTag(Level world, BlockPos start, TagKey<Block> blockHunt, int radius) {
        ArrayList<BlockPos> found = new ArrayList();
        int xMin = start.getX() - radius;
        int xMax = start.getX() + radius;
        int yMin = start.getY() - radius;
        int yMax = start.getY() + radius;
        int zMin = start.getZ() - radius;
        int zMax = start.getZ() + radius;
        BlockPos posCurrent = null;

        for (int xLoop = xMin; xLoop <= xMax; ++xLoop) {
            for (int yLoop = yMin; yLoop <= yMax; ++yLoop) {
                for (int zLoop = zMin; zLoop <= zMax; ++zLoop) {
                    posCurrent = new BlockPos(xLoop, yLoop, zLoop);
                    if (world.getBlockState(posCurrent).is(blockHunt)) {
                        found.add(posCurrent);
                    }
                }
            }
        }

        return found;
    }

    public static ArrayList<BlockPos> findBlocks(Level world, BlockPos start, Block blockHunt, int radius) {
        ArrayList<BlockPos> found = new ArrayList();
        int xMin = start.getX() - radius;
        int xMax = start.getX() + radius;
        int yMin = start.getY() - radius;
        int yMax = start.getY() + radius;
        int zMin = start.getZ() - radius;
        int zMax = start.getZ() + radius;
        BlockPos posCurrent = null;

        for (int xLoop = xMin; xLoop <= xMax; ++xLoop) {
            for (int yLoop = yMin; yLoop <= yMax; ++yLoop) {
                for (int zLoop = zMin; zLoop <= zMax; ++zLoop) {
                    posCurrent = new BlockPos(xLoop, yLoop, zLoop);
                    if (world.getBlockState(posCurrent).getBlock().equals(blockHunt)) {
                        found.add(posCurrent);
                    }
                }
            }
        }

        return found;
    }

    public static void toggleLeverPowerState(Level worldIn, BlockPos blockPos, BlockState blockState) {
        boolean hasPowerHere = (Boolean) blockState.getValue(LeverBlock.POWERED);
        BlockState stateNew = (BlockState) blockState.setValue(LeverBlock.POWERED, !hasPowerHere);
        boolean success = worldIn.setBlockAndUpdate(blockPos, stateNew);
        if (success) {
            flagUpdate(worldIn, blockPos, blockState, stateNew);
            flagUpdate(worldIn, blockPos.below(), blockState, stateNew);
            flagUpdate(worldIn, blockPos.above(), blockState, stateNew);
            flagUpdate(worldIn, blockPos.west(), blockState, stateNew);
            flagUpdate(worldIn, blockPos.east(), blockState, stateNew);
            flagUpdate(worldIn, blockPos.north(), blockState, stateNew);
            flagUpdate(worldIn, blockPos.south(), blockState, stateNew);
        }

    }

    public static void flagUpdate(Level worldIn, BlockPos blockPos, BlockState blockState, BlockState stateNew) {
        worldIn.sendBlockUpdated(blockPos, blockState, stateNew, 3);
        worldIn.updateNeighborsAt(blockPos, stateNew.getBlock());
        worldIn.updateNeighborsAt(blockPos, blockState.getBlock());
    }


    public static List<BlockPos> getPositionsInRange(BlockPos pos, int xMin, int xMax, int yMin, int yMax, int zMin, int zMax) {
        List<BlockPos> found = new ArrayList();

        for (int x = xMin; x <= xMax; ++x) {
            for (int y = yMin; y <= yMax; ++y) {
                for (int z = zMin; z <= zMax; ++z) {
                    found.add(new BlockPos(x, y, z));
                }
            }
        }

        return found;
    }

    public static boolean doBlockStatesMatch(BlockState replacedBlockState, BlockState newToPlace) {
        return replacedBlockState.equals(newToPlace);
    }

    public static BlockPos getFirstBlockAbove(Level world, BlockPos pos) {
        BlockPos posCurrent = null;

        for (int y = pos.getY() + 1; y < world.getMaxBuildHeight(); ++y) {
            posCurrent = new BlockPos(pos.getX(), y, pos.getZ());
            if (world.getBlockState(posCurrent).isAir() && world.getBlockState(posCurrent.above()).isAir() && !world.getBlockState(posCurrent.below()).isAir()) {
                return posCurrent;
            }
        }

        return null;
    }

    public static BlockPos getLastAirBlockAbove(Level world, BlockPos pos) {
        return getLastAirBlock(world, pos, Direction.UP);
    }

    public static BlockPos getLastAirBlockBelow(Level world, BlockPos pos) {
        return getLastAirBlock(world, pos, Direction.DOWN);
    }

    public static BlockPos getLastAirBlock(Level world, BlockPos pos, Direction direction) {
        byte increment;
        if (direction == Direction.DOWN) {
            increment = -1;
        } else {
            increment = 1;
        }

        BlockPos posPrevious = pos;

        for (int y = pos.getY(); y < world.getMaxBuildHeight() && y > 0; y += increment) {
            BlockPos posCurrent = new BlockPos(pos.getX(), y, pos.getZ());
            if (!world.isEmptyBlock(posCurrent)) {
                return posPrevious;
            }

            posPrevious = posCurrent;
        }

        return pos;
    }

    public static BlockPos returnClosest(BlockPos playerPos, BlockPos pos1, BlockPos pos2) {
        if (pos1 == null && pos2 == null) {
            return null;
        } else if (pos1 == null) {
            return pos2;
        } else if (pos2 == null) {
            return pos1;
        } else {
            return distanceBetweenHorizontal(playerPos, pos1) <= distanceBetweenHorizontal(playerPos, pos2) ? pos1 : pos2;
        }
    }

    public static boolean withinArea(BlockPos center, int radius, int height, BlockPos candle) {
        return center.getX() - radius < candle.getX() && candle.getX() < center.getX() + radius && center.getY() - height < candle.getY() && candle.getY() < center.getY() + height && center.getY() - radius < candle.getY() && candle.getY() < center.getY() + radius;
    }
}