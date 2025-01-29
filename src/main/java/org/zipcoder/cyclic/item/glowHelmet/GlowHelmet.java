package org.zipcoder.cyclic.item.glowHelmet;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import org.zipcoder.cyclic.ModArmorMaterials;

public class GlowHelmet extends ArmorItem {
    public GlowHelmet() {
        super(
                ModArmorMaterials.GLOW_ARMOR,//Armor material
                Type.HELMET, //Type
                new Item.Properties().stacksTo(1)); //Properties
    }
}