package org.zipcoder.cyclic.items;

import net.minecraft.world.item.*;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.zipcoder.cyclic.Cyclic;
import org.zipcoder.cyclic.blocks.BlockRegistry;
import org.zipcoder.cyclic.blocks.angelScaffolding.ItemScaffolding;
import org.zipcoder.cyclic.items.shield.ShieldCyclicItem;
import org.zipcoder.cyclic.materials.ModArmorMaterials;
import org.zipcoder.cyclic.materials.ModToolMaterials;

import static org.zipcoder.cyclic.Cyclic.preInit;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, Cyclic.MOD_ID);

//    public static final RegistryObject<Item> GLOW_HELMET = ITEMS.register("glowing_helmet", () -> new GlowHelmet());

    //Misc
    public static final RegistryObject<Item> SNOWFLAKE = ITEMS.register("snowflake", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_SNOWFLAKE = ITEMS.register("blue_snowflake", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_SNOWFLAKE = ITEMS.register("gold_snowflake", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PRISMARINE_GEMSTONE = ITEMS.register("prismarine_gemstone", () -> new Item(new Item.Properties()));


    //Scaffolding
    public static final RegistryObject<Item> SCAFFOLD_FRAGILE = ITEMS.register("scaffold_fragile", () -> new ItemScaffolding(BlockRegistry.SCAFFOLD_FRAGILE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCAFFOLD_RESPONSIVE = ITEMS.register("scaffold_responsive", () -> new ItemScaffolding(BlockRegistry.SCAFFOLD_RESPONSIVE.get(), new Item.Properties()));

    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", () -> new ShovelItem(ModToolMaterials.COPPER, 1, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe", () -> new HoeItem(ModToolMaterials.COPPER, -1, -2f, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword", () -> new SwordItem(ModToolMaterials.COPPER, 3, -2.4f, (new Item.Properties())));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new PickaxeItem(ModToolMaterials.COPPER, 1, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe", () -> new AxeItem(ModToolMaterials.COPPER, 7, -3.4f, new Item.Properties()));

    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel", () -> new ShovelItem(ModToolMaterials.AMETHYST, 0, -2.9f, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe", () -> new HoeItem(ModToolMaterials.AMETHYST, -1, -2.9f, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword", () -> new SwordItem(ModToolMaterials.AMETHYST, 3, -2.9f, (new Item.Properties())));
    public static final RegistryObject<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe", () -> new PickaxeItem(ModToolMaterials.AMETHYST, 1, -2.9f, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe", () -> new AxeItem(ModToolMaterials.AMETHYST, 5, -2.9f, new Item.Properties()));

    public static final RegistryObject<Item> EMERALD_BOOTS = ITEMS.register("emerald_boots", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_HELMET = ITEMS.register("emerald_helmet", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings", () -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> EMERALD_SHOVEL = ITEMS.register("emerald_shovel", () -> new ShovelItem(ModToolMaterials.EMERALD, 1, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_HOE = ITEMS.register("emerald_hoe", () -> new HoeItem(ModToolMaterials.EMERALD, -1, -3, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_SWORD = ITEMS.register("emerald_sword", () -> new SwordItem(ModToolMaterials.EMERALD, 5, -2.1f, (new Item.Properties())));
    public static final RegistryObject<Item> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe", () -> new PickaxeItem(ModToolMaterials.EMERALD, 1, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_AXE = ITEMS.register("emerald_axe", () -> new AxeItem(ModToolMaterials.EMERALD, 6, -3, new Item.Properties()));


    //    public static final RegistryObject<Item> SPIKES_DIAMOND = ITEMS.register("spikes_diamond", () -> new BlockItem(BlockRegistry.SPIKES_DIAMOND.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPIKES_IRON = ITEMS.register("spikes_iron", () -> new BlockItem(BlockRegistry.SPIKES_IRON.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPIKES_CURSE = ITEMS.register("spikes_curse", () -> new BlockItem(BlockRegistry.SPIKES_CURSE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPIKES_FIRE = ITEMS.register("spikes_fire", () -> new BlockItem(BlockRegistry.SPIKES_FIRE.get(), new Item.Properties()));


//    public static final RegistryObject<Item> CRYSTAL_BOOTS = ITEMS.register("crystal_boots", () -> new ArmorItem(MaterialRegistry.ArmorMats.GEMOBSIDIAN, ArmorItem.Type.BOOTS, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_HELMET = ITEMS.register("crystal_helmet", () -> new ArmorItem(MaterialRegistry.ArmorMats.GEMOBSIDIAN, ArmorItem.Type.HELMET, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_CHESTPLATE = ITEMS.register("crystal_chestplate", () -> new ArmorItem(MaterialRegistry.ArmorMats.GEMOBSIDIAN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_LEGGINGS = ITEMS.register("crystal_leggings", () -> new ArmorItem(MaterialRegistry.ArmorMats.GEMOBSIDIAN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_SHOVEL = ITEMS.register("crystal_shovel", () -> new ShovelItem(ModToolMaterials.GEMOBSIDIAN, 1.5F, -3.0F, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_HOE = ITEMS.register("crystal_hoe", () -> new HoeItem(ToolMats.GEMOBSIDIAN, -4, 0F, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_SWORD = ITEMS.register("crystal_sword", () -> new SwordItem(ModToolMaterials.GEMOBSIDIAN, 3, -2.4F, (new Item.Properties())));
//    public static final RegistryObject<Item> CRYSTAL_PICKAXE = ITEMS.register("crystal_pickaxe", () -> new PickaxeItem(ModToolMaterials.GEMOBSIDIAN, 1, -2.8F, new Item.Properties()));
//    public static final RegistryObject<Item> CRYSTAL_AXE = ITEMS.register("crystal_axe", () -> new AxeItem(ModToolMaterials.GEMOBSIDIAN, 5.0F, -3.0F, new Item.Properties()));

    //Shields (Basically just regular shields with more durability)
    //Shield tier is as follows: Flint, Leather, Bone, Obsidian
    //A vanilla shield has 336 durability
    private static final int VANILLA_SHIELD_DURABILITY = 336;

    //More durable than vanilla shield
    public static final RegistryObject<Item> SHIELD_LEATHER = ITEMS.register("shield_leather", () -> new ShieldCyclicItem(
            new Item.Properties().durability((int) (VANILLA_SHIELD_DURABILITY * preInit.leatherShieldDurabilityMultiplier)), ShieldCyclicItem.ShieldType.LEATHER));

    //More durable than leather shield
    public static final RegistryObject<Item> SHIELD_BONE = ITEMS.register("shield_bone", () -> new ShieldCyclicItem(
            new Item.Properties().durability((int) (VANILLA_SHIELD_DURABILITY * preInit.boneShieldDurabilityMultiplier)), ShieldCyclicItem.ShieldType.BONE));

    //Most durable
    public static final RegistryObject<Item> SHIELD_OBSIDIAN = ITEMS.register("shield_obsidian", () -> new ShieldCyclicItem(
            new Item.Properties().durability((int) (VANILLA_SHIELD_DURABILITY * preInit.obsidianShieldDurabilityMultiplier)), ShieldCyclicItem.ShieldType.OBSIDIAN));


    //Creative gear
    public static final RegistryObject<Item> CREATIVE_SWORD = ITEMS.register("creative_sword", () -> new SwordItem(ModToolMaterials.CREATIVE, 3, -2.4F, (new Item.Properties())));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static void addToCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {

            event.accept(ItemRegistry.COPPER_SWORD);
            event.accept(ItemRegistry.AMETHYST_SWORD);
            event.accept(ItemRegistry.EMERALD_SWORD);

            event.accept(ItemRegistry.COPPER_AXE);
            event.accept(ItemRegistry.AMETHYST_AXE);
            event.accept(ItemRegistry.EMERALD_AXE);


            event.accept(ItemRegistry.SHIELD_LEATHER);
            event.accept(ItemRegistry.SHIELD_BONE);
            event.accept(ItemRegistry.SHIELD_OBSIDIAN);


            event.accept(ItemRegistry.EMERALD_BOOTS);
            event.accept(ItemRegistry.EMERALD_HELMET);
            event.accept(ItemRegistry.EMERALD_CHESTPLATE);
            event.accept(ItemRegistry.EMERALD_LEGGINGS);

        } else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {

            event.accept(ItemRegistry.COPPER_SHOVEL);
            event.accept(ItemRegistry.COPPER_HOE);
            event.accept(ItemRegistry.COPPER_PICKAXE);
            event.accept(ItemRegistry.COPPER_AXE);

            event.accept(ItemRegistry.AMETHYST_SHOVEL);
            event.accept(ItemRegistry.AMETHYST_HOE);
            event.accept(ItemRegistry.AMETHYST_PICKAXE);
            event.accept(ItemRegistry.AMETHYST_AXE);

            event.accept(ItemRegistry.EMERALD_SHOVEL);
            event.accept(ItemRegistry.EMERALD_HOE);
            event.accept(ItemRegistry.EMERALD_PICKAXE);
            event.accept(ItemRegistry.EMERALD_AXE);

//            event.accept(ItemRegistry.GLOW_HELMET);

        } else if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ItemRegistry.SCAFFOLD_FRAGILE);
            event.accept(ItemRegistry.SCAFFOLD_RESPONSIVE);

            event.accept(ItemRegistry.SPIKES_IRON);
            event.accept(ItemRegistry.SPIKES_CURSE);
            event.accept(ItemRegistry.SPIKES_FIRE);

        } else if (event.getTabKey() == CreativeModeTabs.OP_BLOCKS) {
            event.accept(ItemRegistry.CREATIVE_SWORD);

        } else if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ItemRegistry.PRISMARINE_GEMSTONE);
            event.accept(ItemRegistry.SNOWFLAKE);
            event.accept(ItemRegistry.BLUE_SNOWFLAKE);
            event.accept(ItemRegistry.GOLD_SNOWFLAKE);
        }
    }
}
