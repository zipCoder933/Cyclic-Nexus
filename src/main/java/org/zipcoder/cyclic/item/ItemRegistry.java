package org.zipcoder.cyclic.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.zipcoder.cyclic.Cyclic;
import org.zipcoder.cyclic.item.glowHelmet.GlowHelmet;
import org.zipcoder.cyclic.materials.ModArmorMaterials;
import org.zipcoder.cyclic.materials.ModToolMaterials;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, Cyclic.MOD_ID);

    public static final RegistryObject<Item> GLOW_HELMET = ITEMS.register("glowing_helmet", () -> new GlowHelmet());

    //Scaffolding
    public static final RegistryObject<Item> SCAFFOLD_FRAGILE = ITEMS.register("scaffold_fragile", () -> new ItemScaffolding(BlockRegistry.SCAFFOLD_FRAGILE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCAFFOLD_RESPONSIVE = ITEMS.register("scaffold_responsive", () -> new ItemScaffolding(BlockRegistry.SCAFFOLD_RESPONSIVE.get(), new Item.Properties()));

    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", () -> new ShovelItem(ModToolMaterials.COPPER, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe", () -> new HoeItem(ModToolMaterials.COPPER, -4, 0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword", () -> new SwordItem(ModToolMaterials.COPPER, 3, -2.4F, (new Item.Properties())));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new PickaxeItem(ModToolMaterials.COPPER, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe", () -> new AxeItem(ModToolMaterials.COPPER, 6.0F, -3.1F, new Item.Properties()));

    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel", () -> new ShovelItem(ModToolMaterials.AMETHYST, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe", () -> new HoeItem(ModToolMaterials.AMETHYST, -4, 0F, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword", () -> new SwordItem(ModToolMaterials.AMETHYST, 3, -2.4F, (new Item.Properties())));
    public static final RegistryObject<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe", () -> new PickaxeItem(ModToolMaterials.AMETHYST, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe", () -> new AxeItem(ModToolMaterials.AMETHYST, 6.0F, -3.1F, new Item.Properties()));

    public static final RegistryObject<Item> EMERALD_BOOTS = ITEMS.register("emerald_boots", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_HELMET = ITEMS.register("emerald_helmet", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    
    public static final RegistryObject<Item> EMERALD_SHOVEL = ITEMS.register("emerald_shovel", () -> new ShovelItem(ModToolMaterials.EMERALD, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_HOE = ITEMS.register("emerald_hoe", () -> new HoeItem(ModToolMaterials.EMERALD, -4, 0F, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_SWORD = ITEMS.register("emerald_sword", () -> new SwordItem(ModToolMaterials.EMERALD, 3, -2.4F, (new Item.Properties())));
    public static final RegistryObject<Item> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe", () -> new PickaxeItem(ModToolMaterials.EMERALD, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_AXE = ITEMS.register("emerald_axe", () -> new AxeItem(ModToolMaterials.EMERALD, 5.0F, -3.0F, new Item.Properties()));


//    public static final RegistryObject<Item> CRYSTAL_BOOTS = ITEMS.register("crystal_boots", () -> new ArmorItem(MaterialRegistry.ArmorMats.GEMOBSIDIAN, ArmorItem.Type.BOOTS, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_HELMET = ITEMS.register("crystal_helmet", () -> new ArmorItem(MaterialRegistry.ArmorMats.GEMOBSIDIAN, ArmorItem.Type.HELMET, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_CHESTPLATE = ITEMS.register("crystal_chestplate", () -> new ArmorItem(MaterialRegistry.ArmorMats.GEMOBSIDIAN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_LEGGINGS = ITEMS.register("crystal_leggings", () -> new ArmorItem(MaterialRegistry.ArmorMats.GEMOBSIDIAN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_SHOVEL = ITEMS.register("crystal_shovel", () -> new ShovelItem(ModToolMaterials.GEMOBSIDIAN, 1.5F, -3.0F, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_HOE = ITEMS.register("crystal_hoe", () -> new HoeItem(ToolMats.GEMOBSIDIAN, -4, 0F, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_SWORD = ITEMS.register("crystal_sword", () -> new SwordItem(ModToolMaterials.GEMOBSIDIAN, 3, -2.4F, (new Item.Properties())));
//    public static final RegistryObject<Item> CRYSTAL_PICKAXE = ITEMS.register("crystal_pickaxe", () -> new PickaxeItem(ModToolMaterials.GEMOBSIDIAN, 1, -2.8F, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_AXE = ITEMS.register("crystal_axe", () -> new AxeItem(ModToolMaterials.GEMOBSIDIAN, 5.0F, -3.0F, new Item.Properties()));

    //Creative gear
    public static final RegistryObject<Item> CREATIVE_SWORD = ITEMS.register("creative_sword", () -> new SwordItem(ModToolMaterials.CREATIVE, 3, -2.4F, (new Item.Properties())));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
