package org.zipcoder.cyclic.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityUtil {
    private static final double ENTITY_PULL_DIST = 0.4;
    private static final double ENTITY_PULL_SPEED_CUTOFF = 3.0;
    private static final float ITEMSPEEDFAR = 0.9F;
    private static final float ITEMSPEEDCLOSE = 0.2F;
    private static final int TICKS_FALLDIST_SYNC = 22;

    public EntityUtil() {
    }

//    public static boolean haveSameDimension(Entity tamed, Entity owner) {
//        return LevelWorldUtil.dimensionToString(tamed.level()).equalsIgnoreCase(LevelWorldUtil.dimensionToString(owner.level()));
//    }

    public static boolean isTamedByPlayer(Entity entity, Player player) {
        if (entity instanceof OwnableEntity tamed) {
            return tamed.getOwnerUUID() != null && tamed.getOwnerUUID().equals(player.getUUID());
        } else if (!(entity instanceof AbstractHorse horse)) {
            return false;
        } else {
            return horse.getOwnerUUID() != null && horse.getOwnerUUID().equals(player.getUUID());
        }
    }

    private static boolean enderTeleportEvent(LivingEntity player, Level world, double x, double y, double z) {
        if (player.getRootVehicle() != player) {
            return false;
        } else {
            teleportWallSafe(player, world, x, y, z);
            return true;
        }
    }

    public static boolean enderTeleportEvent(LivingEntity player, Level world, BlockPos target) {
        return enderTeleportEvent(player, world, (double)((float)target.getX() + 0.5F), (double)((float)target.getY() + 0.5F), (double)((float)target.getZ() + 0.5F));
    }

    private static void teleportWallSafe(LivingEntity player, Level world, double x, double y, double z) {
        BlockPos coords = BlockPos.containing(x, y, z);
        world.getChunk(coords).setUnsaved(true);
        player.teleportTo(x, y, z);
        moveEntityWallSafe(player, world);
    }

    public static void moveEntityWallSafe(Entity entity, Level world) {
        while(!world.noCollision(entity)) {
            entity.teleportTo(entity.xo, entity.yo + 1.0, entity.zo);
        }

    }

    public static Direction getFacing(LivingEntity entity) {
        int yaw = (int)entity.getYRot();
        if (yaw < 0) {
            yaw += 360;
        }

        yaw += 22;
        yaw %= 360;
        int facing = yaw / 45;
        return Direction.from2DDataValue(facing / 2);
    }

    public static double getSpeedTranslated(double speed) {
        return speed * 100.0;
    }

    public static void launchDirection(Entity entity, float power, Direction facing) {
        double velX = 0.0;
        double velZ = 0.0;
        double velY = 0.0;
        switch (facing) {
            case EAST:
                velX = (double)Math.abs(power);
                velZ = 0.0;
                break;
            case WEST:
                velX = (double)(-1.0F * Math.abs(power));
                velZ = 0.0;
                break;
            case NORTH:
                velX = 0.0;
                velZ = (double)(-1.0F * Math.abs(power));
                break;
            case SOUTH:
                velX = 0.0;
                velZ = (double)Math.abs(power);
            case UP:
            case DOWN:
        }

        Entity ridingEntity = entity.getVehicle();
        if (ridingEntity != null) {
            entity.setDeltaMovement(entity.getDeltaMovement().x, 0.0, entity.getDeltaMovement().z);
            ridingEntity.fallDistance = 0.0F;
            ridingEntity.push(velX, velY, velZ);
        } else {
            entity.setDeltaMovement(entity.getDeltaMovement().x, 0.0, entity.getDeltaMovement().z);
            entity.fallDistance = 0.0F;
            entity.push(velX, velY, velZ);
        }

    }

    public static void launch(Entity entity, float rotationPitch, float power) {
        float rotationYaw = entity.getYRot();
        launch(entity, rotationPitch, rotationYaw, power);
    }

    public static void launch(Entity entity, float rotationPitch, float rotationYaw, float power) {
        float mountPower = (float)((double)power + 0.5);
        double velX = (double)(-Mth.sin(rotationYaw / 180.0F * 3.1415927F) * Mth.cos(rotationPitch / 180.0F * 3.1415927F) * power);
        double velZ = (double)(Mth.cos(rotationYaw / 180.0F * 3.1415927F) * Mth.cos(rotationPitch / 180.0F * 3.1415927F) * power);
        double velY = (double)(-Mth.sin(rotationPitch / 180.0F * 3.1415927F) * power);
        if (velY < 0.0) {
            velY *= -1.0;
        }

        Entity ridingEntity = entity.getVehicle();
        if (ridingEntity != null) {
            entity.setDeltaMovement(entity.getDeltaMovement().x, 0.0, entity.getDeltaMovement().z);
            ridingEntity.fallDistance = 0.0F;
            ridingEntity.push(velX * (double)mountPower, velY * (double)mountPower, velZ * (double)mountPower);
        } else {
            entity.setDeltaMovement(entity.getDeltaMovement().x, 0.0, entity.getDeltaMovement().z);
            entity.fallDistance = 0.0F;
            entity.push(velX, velY, velZ);
        }

    }

    public static AABB makeBoundingBox(BlockPos center, int hRadius, int vRadius) {
        return makeBoundingBox((double)center.getX(), (double)center.getY(), (double)center.getZ(), hRadius, vRadius);
    }

    public static AABB makeBoundingBox(double x, double y, double z, int hRadius, int vRadius) {
        return new AABB(x - (double)hRadius, y - (double)vRadius, z - (double)hRadius, x + (double)hRadius, y + (double)vRadius, z + (double)hRadius);
    }

//    public static int moveEntityItemsInRegion(Level world, BlockPos pos, int hRadius, int vRadius) {
//        return moveEntityItemsInRegion(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), hRadius, vRadius, true);
//    }

//    public static int moveEntityItemsInRegion(Level world, double x, double y, double z, int hRadius, int vRadius, boolean towardsPos) {
//        AABB range = makeBoundingBox(x, y, z, hRadius, vRadius);
//        List<Entity> all = getItemExp(world, range);
//        return pullEntityList(x, y, z, towardsPos, all);
//    }

    public static List<Entity> getItemExp(Level world, AABB range) {
        List<Entity> all = new ArrayList();
        all.addAll(world.getEntitiesOfClass(ItemEntity.class, range));
        all.addAll(world.getEntitiesOfClass(ExperienceOrb.class, range));
        return all;
    }

    public static boolean speedupEntityIfMoving(LivingEntity entity, float factor) {
        if (entity.zza > 0.0F) {
            if (entity.getVehicle() != null && entity.getVehicle() instanceof LivingEntity) {
                speedupEntity((LivingEntity)entity.getVehicle(), factor);
                return true;
            } else {
                speedupEntity(entity, factor);
                return true;
            }
        } else {
            return false;
        }
    }

    public static void speedupEntity(LivingEntity entity, float factor) {
        float x = Mth.sin(-entity.getYRot() * 0.017453292F) * factor;
        float z = Mth.cos(entity.getYRot() * 0.017453292F) * factor;
        entity.setDeltaMovement((double)x, entity.getDeltaMovement().y, (double)z);
    }

//    public static int pullEntityList(double x, double y, double z, boolean towardsPos, List<? extends Entity> all) {
//        return pullEntityList(x, y, z, towardsPos, all, 0.2F, 0.9F);
//    }

//    public static int pullEntityList(double x, double y, double z, boolean towardsPos, List<? extends Entity> all, float speedClose, float speedFar) {
//        int moved = 0;
//        int direction = towardsPos ? 1 : -1;
//        Iterator var19 = all.iterator();
//
//        while(true) {
//            Entity entity;
//            do {
//                do {
//                    if (!var19.hasNext()) {
//                        return moved;
//                    }
//
//                    entity = (Entity)var19.next();
//                } while(entity == null);
//            } while(entity instanceof Player && ((Player)entity).isCrouching());
//
//            BlockPos p = entity.blockPosition();
//            double xDist = Math.abs(x - (double)p.getX());
//            double zDist = Math.abs(z - (double)p.getZ());
//            double hdist = Math.sqrt(xDist * xDist + zDist * zDist);
//            if (hdist > 0.4) {
//                float speed = hdist > 3.0 ? speedFar : speedClose;
//                setEntityMotionFromVector(entity, x, y, z, (float)direction * speed);
//                ++moved;
//            }
//        }
//    }

//    public static void setEntityMotionFromVector(Entity entity, double x, double y, double z, float modifier) {
//        Vector3d originalPosVector = new Vector3d(x, y, z);
//        Vector3d entityVector = new Vector3d(entity);
//        Vector3d finalVector = originalPosVector.copy().subtract(entityVector);
//        if (finalVector.mag() > 1.0) {
//            finalVector.normalize();
//        }
//
//        double motionX = finalVector.x * (double)modifier;
//        double motionY = finalVector.y * (double)modifier;
//        double motionZ = finalVector.z * (double)modifier;
//        entity.setDeltaMovement(motionX, motionY, motionZ);
//    }

    public static void addOrMergePotionEffect(LivingEntity player, MobEffectInstance newp) {
        if (player.hasEffect(newp.getEffect())) {
            MobEffectInstance p = player.getEffect(newp.getEffect());
            int ampMax = Math.max(p.getAmplifier(), newp.getAmplifier());
            int dur = newp.getDuration() + p.getDuration();
            player.addEffect(new MobEffectInstance(newp.getEffect(), dur, ampMax));
        } else {
            player.addEffect(newp);
        }

    }

    public static void centerEntityHoriz(Entity entity, BlockPos pos) {
        float fixedX = (float)pos.getX() + 0.5F;
        float fixedZ = (float)pos.getZ() + 0.5F;
        entity.setPos((double)fixedX, (double)entity.blockPosition().getY(), (double)fixedZ);
    }

    public static List<Villager> getVillagers(Level world, BlockPos p, int r) {
        BlockPos start = p.offset(-r, -r, -r);
        BlockPos end = p.offset(r, r, r);
        return world.getEntitiesOfClass(Villager.class, new AABB(start, end));
    }

    public static LivingEntity getClosestEntity(Level world, Player player, List<? extends LivingEntity> list) {
        LivingEntity closest = null;
        double minDist = 999999.0;
        Iterator var12 = list.iterator();

        while(var12.hasNext()) {
            LivingEntity ent = (LivingEntity)var12.next();
            double xDistance = Math.abs(player.xo - ent.xo);
            double zDistance = Math.abs(player.zo - ent.zo);
            double dist = Math.sqrt(xDistance * xDistance + zDistance * zDistance);
            if (dist < minDist) {
                minDist = dist;
                closest = ent;
            }
        }

        return closest;
    }

    public static Villager getVillager(Level world, int x, int y, int z) {
        List<Villager> all = world.getEntitiesOfClass(Villager.class, new AABB(new BlockPos(x, y, z)));
        return all.size() == 0 ? null : (Villager)all.get(0);
    }

    public static float yawDegreesBetweenPoints(double posX, double posY, double posZ, double posX2, double posY2, double posZ2) {
        float f = (float)(180.0 * Math.atan2(posX2 - posX, posZ2 - posZ) / 3.1415927410125732);
        return f;
    }

    public static float pitchDegreesBetweenPoints(double posX, double posY, double posZ, double posX2, double posY2, double posZ2) {
        return (float)Math.toDegrees(Math.atan2(posY2 - posY, Math.sqrt((posX2 - posX) * (posX2 - posX) + (posZ2 - posZ) * (posZ2 - posZ))));
    }

    public static Vec3 lookVector(float rotYaw, float rotPitch) {
        return new Vec3(Math.sin((double)rotYaw) * Math.cos((double)rotPitch), Math.sin((double)rotPitch), Math.cos((double)rotYaw) * Math.cos((double)rotPitch));
    }

    public static float getYawFromFacing(Direction currentFacing) {
        switch (currentFacing) {
            case EAST:
                return 270.0F;
            case WEST:
                return 90.0F;
            case NORTH:
                return 180.0F;
            case SOUTH:
            case UP:
            case DOWN:
            default:
                return 0.0F;
        }
    }

    public static void setEntityFacing(LivingEntity entity, Direction currentFacing) {
        float yaw = 0.0F;
        switch (currentFacing) {
            case EAST:
                yaw = 270.0F;
                break;
            case WEST:
                yaw = 90.0F;
                break;
            case NORTH:
                yaw = 180.0F;
                break;
            case SOUTH:
            case UP:
            case DOWN:
            default:
                yaw = 0.0F;
        }

        entity.setYRot(yaw);
    }

    public static void dragEntityMomentum(LivingEntity entity, double verticalMomentumFactor) {
        double x = entity.getDeltaMovement().x / verticalMomentumFactor;
        double z = entity.getDeltaMovement().z / verticalMomentumFactor;
        entity.setDeltaMovement(x, entity.getDeltaMovement().y, z);
    }

    public static void setCooldownItem(Player player, Item item, int cooldown) {
        player.getCooldowns().addCooldown(item, cooldown);
    }

    public static Attribute getAttributeJump(Horse ahorse) {
        return Attributes.JUMP_STRENGTH;
    }

//    public static void eatingHorse(Horse ahorse) {
//        ahorse.eating();
//    }

    public static void tryMakeEntityClimb(Level worldIn, LivingEntity entity, double climbSpeed) {
        if (entity.isCrouching()) {
            entity.setDeltaMovement(entity.getDeltaMovement().x, 0.0, entity.getDeltaMovement().z);
        } else if (entity.zza > 0.0F && entity.getDeltaMovement().y < climbSpeed) {
            entity.setDeltaMovement(entity.getDeltaMovement().x, climbSpeed, entity.getDeltaMovement().z);
            entity.fallDistance = 0.0F;
        }
//        if (worldIn.isClientSide && entity.tickCount % 22 == 0) {
//            PacketRegistry.INSTANCE.sendToServer(new PacketPlayerFalldamage());
//        }
    }

//    public static void dimensionTeleport(ServerPlayer player, ServerLevel world, BlockPosDim loc) {
//        if (!(player instanceof FakePlayer)) {
//            if (player.canChangeDimensions()) {
//                if (!world.isClientSide) {
//                    DimensionTransit transit = new DimensionTransit(world, loc);
//                    transit.teleport(player);
//                    player.changeDimension(transit.getTargetLevel(), transit);
//                }
//
//            }
//        }
//    }
}