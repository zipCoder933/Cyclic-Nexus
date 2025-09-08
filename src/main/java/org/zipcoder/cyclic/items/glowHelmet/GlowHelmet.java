package org.zipcoder.cyclic.items.glowHelmet;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import org.zipcoder.cyclic.client.utils.ClientGameSettings;
import org.zipcoder.cyclic.materials.ModArmorMaterials;

public class GlowHelmet extends ArmorItem {
    private static double DEFAULT_GAMMA = 1.0D;
    private static final double MAX_GAMMA = 8.5;

    public static void enableNightVision() {
        ClientGameSettings.setGamma(GlowHelmet.DEFAULT_GAMMA);
    }

    public static void disableNightVision() {
        GlowHelmet.DEFAULT_GAMMA = ClientGameSettings.getGammaClamped();
        ClientGameSettings.setGamma(GlowHelmet.MAX_GAMMA);
    }

    public GlowHelmet() {
        super(
                ModArmorMaterials.EMERALD_ARMOR,//Armor material
                Type.HELMET, //Type
                new Item.Properties().stacksTo(1)); //Properties
    }

    public void onEquipped_client(net.minecraft.world.entity.player.Player player) {
        enableNightVision();
    }

    public void onUnequipped_client(net.minecraft.world.entity.player.Player player) {
        disableNightVision();
    }
}