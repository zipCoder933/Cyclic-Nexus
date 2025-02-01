package org.zipcoder.cyclic.items.shield;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public class MaterialShieldRegistry {
    public static final Material SHIELD_BASE_WOOD = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/wood_base"));
    public static final Material SHIELD_BASE_WOOD_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/wood_base_nopattern"));
    public static final Material SHIELD_BASE_LEATHER = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/leather_base"));
    public static final Material SHIELD_BASE_LEATHER_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/leather_base_nopattern"));
    public static final Material SHIELD_BASE_FLINT = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/flint_base"));
    public static final Material SHIELD_BASE_FLINT_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/flint_base_nopattern"));
    public static final Material SHIELD_BASE_BONE = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/bone_base"));
    public static final Material SHIELD_BASE_BONE_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/bone_base_nopattern"));
    public static final Material SHIELD_BASE_OBSIDIAN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/obsidian_base"));
    public static final Material SHIELD_BASE_OBSIDIAN_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/obsidian_base_nopattern"));
}
