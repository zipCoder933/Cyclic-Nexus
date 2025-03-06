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

    /**
     * Write a new config
     */
    private void writeConfig(FileConfig config) {
        config.set("client.night_vision_key", nightVisionKey);
        config.save();
    }

    /**
     * Load the config
     */
    private void loadConfig(FileConfig config) {
        config.load();
        nightVisionKey = config.get("client.night_vision_key");
    }
}
