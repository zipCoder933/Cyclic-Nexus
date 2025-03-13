package org.zipcoder.cyclic.config;

import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.toml.TomlFormat;
import net.minecraftforge.fml.loading.FMLPaths;
import org.zipcoder.cyclic.Cyclic;

import java.io.File;
import java.nio.file.Path;

public class PreInitConfig {
    public PreInitConfig() {
        try {

            Path path = FMLPaths.CONFIGDIR.get();
            File configFile = new File(path.toFile(), "cyclic-pre-init.toml");
            try (FileConfig config = FileConfig.builder(configFile, TomlFormat.instance()).build()) {
                if (configFile.exists()) {
                    loadConfig(config);
                } else {
                    writeConfig(config);
                }
            }

        } catch (Exception e) {
            Cyclic.LOGGER.error("An error occurred initializing pre-init config!", e);
        }
    }

    /**
     * Default values go here
     */
    public boolean nightVisionKey = false;

    public int freezePotion1Duration = 15;
    public int freezePotion2Duration = 50;
    public boolean attackRangePotionEnabled = true;

    public float leatherShieldDurabilityMultiplier = 1.5F;
    public float boneShieldDurabilityMultiplier = 2F;
    public float obsidianShieldDurabilityMultiplier = 5F;

    /**
     * Write a new config
     */
    private void writeConfig(FileConfig config) {
        //Client
        config.set("client.night_vision_key", nightVisionKey);
        //Common
        config.set("common.potion.freeze_1_duration", freezePotion1Duration);
        config.set("common.potion.freeze_2_duration", freezePotion2Duration);
        config.set("common.potion.attack_range_potion_enabled", attackRangePotionEnabled);

        config.set("common.leather_shield_durability_multiplier", leatherShieldDurabilityMultiplier);
        config.set("common.bone_shield_durability_multiplier", boneShieldDurabilityMultiplier);
        config.set("common.obsidian_shield_durability_multiplier", obsidianShieldDurabilityMultiplier);
        config.save();
    }

    /**
     * Load the config
     */
    private void loadConfig(FileConfig config) {
        config.load();
        nightVisionKey = config.get("client.night_vision_key");

        freezePotion1Duration = config.get("common.potion.freeze_1_duration");
        freezePotion2Duration = config.get("common.potion.freeze_2_duration");
        attackRangePotionEnabled = config.get("common.potion.attack_range_potion_enabled");

        leatherShieldDurabilityMultiplier = config.get("common.leather_shield_durability_multiplier");
        boneShieldDurabilityMultiplier = config.get("common.bone_shield_durability_multiplier");
        obsidianShieldDurabilityMultiplier = config.get("common.obsidian_shield_durability_multiplier");
    }
}
