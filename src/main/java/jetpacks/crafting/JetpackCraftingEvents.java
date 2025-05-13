package jetpacks.crafting;

import jetpacks.SimplyJetpacks;
import jetpacks.handlers.RegistryHandler;
import jetpacks.item.JetpackItem;
import jetpacks.item.JetpackType;
import jetpacks.util.NBTUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import jetpacks.datagen.SJTags;

public class JetpackCraftingEvents {

    // TODO: improve this
    @SubscribeEvent
    public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        ItemStack craftedStack = event.getCrafting();
        Item craftedItem = event.getCrafting().getItem();
        int storedEnergy;
        CompoundTag tags;
        boolean particleRecipe = false; // if particle as ingredient, then true

        if (craftedItem instanceof JetpackItem) {
            for (int i = 0; i < event.getInventory().getContainerSize(); i++) {
                ItemStack input = event.getInventory().getItem(i);
                if (ForgeRegistries.ITEMS.tags().getTag(SJTags.PARTICLES).contains(input.getItem())) {
                    SimplyJetpacks.LOGGER.info("TAG CHECK HERE");
                    particleRecipe = true;
                    break;
                }
            }
            if (!particleRecipe) {
                for (int i = 0; i < event.getInventory().getContainerSize(); i++) {
                    ItemStack input = event.getInventory().getItem(i);
                    if (!(input.getItem() instanceof JetpackItem)) {
                        continue;
                    }
                    if (input.getItem() instanceof JetpackItem) {
                        JetpackType inputJetpack = ((JetpackItem) input.getItem()).getJetpackType();
                        // ENERGY/PARTICLES:
                        tags = NBTUtil.getTagCompound(input).copy();
                        storedEnergy = NBTUtil.getInt(input, "Energy");
                        craftedStack.setTag(tags);
                        int energyToTransfer = Math.min(storedEnergy, ((JetpackItem) craftedItem).getCapacity(input));
                        //JetpackParticleType particleType = inputJetpack.getParticleType(input);
                        int particleId = JetpackItem.getParticleId(input);
                        //((JetpackItem) craftedItem).setParticleType(craftedStack, particleType);
                        JetpackItem.setParticleId(craftedStack, particleId);
                        // ARMORPLATING:
                        if (inputJetpack.isArmored()) {
                            Item itemToReturn = getPlating(inputJetpack.getPlatingId());
                            if (itemToReturn != null) {
                                ItemEntity item = event.getEntity().drop(new ItemStack(itemToReturn, 1), false);
                                if (item != null) {
                                    item.setNoPickUpDelay();
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public Item getPlating(int id) {
        return switch (id) {
            case 0 -> Items.IRON_CHESTPLATE;
            case 1 -> Items.GOLDEN_CHESTPLATE;
            case 2 -> Items.DIAMOND_CHESTPLATE;
            case 3 -> Items.NETHERITE_CHESTPLATE;
            case 4 -> RegistryHandler.ARMORPLATING_IE1.get();
            case 5 -> RegistryHandler.ARMORPLATING_IE2.get();
            case 6 -> RegistryHandler.ARMORPLATING_IE3.get();
            case 7 -> RegistryHandler.ARMORPLATING_MEK1.get();
            case 8 -> RegistryHandler.ARMORPLATING_MEK2.get();
            case 9 -> RegistryHandler.ARMORPLATING_MEK3.get();
            case 10 -> RegistryHandler.ARMORPLATING_MEK4.get();
            case 11 -> RegistryHandler.ARMORPLATING_TE1.get();
            case 12 -> RegistryHandler.ARMORPLATING_TE2.get();
            case 13 -> RegistryHandler.ARMORPLATING_TE3.get();
            case 14 -> RegistryHandler.ARMORPLATING_TE4.get();
            default -> null;
        };
    }
}
