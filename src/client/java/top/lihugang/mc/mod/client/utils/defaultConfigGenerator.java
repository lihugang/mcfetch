package top.lihugang.mc.mod.client.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;

public class defaultConfigGenerator {
    static public void generate(Logger logger) {
        Map<String, String> icons = new TreeMap<>();

        File
                configRoot = new File("./config"),
                configDirectory = new File("./config/mcfetch");
        if (!configDirectory.exists()) {
            if (!configRoot.exists()) configRoot.mkdir();
            configDirectory.mkdir();

            logger.warn("Default config directory does not exist.");

            // In the font of Minecraft, # occupies 6 units, <space> occupies 4 units
            icons.put("fabric", ""
                            .concat("                              \n")
                            .concat("                  §8MM§f         \n")
                            .concat("                  §8M§fss§8M§f      \n")
                            .concat("               §8M§fs§8MM§fsss§8M§f   \n")
                            .concat("            §8M§fsssss§8MM§fsss§8M§f\n")
                            .concat("         §8M§fsssssssss§8MM§fs§8M§f\n")
                            .concat("      §8M§fssssssssssss§8M§f   \n")
                            .concat("   §8M§fssssssssssssss§8M§f   \n")
                            .concat("§8M§fssssssssssssss§8M§f      \n")
                            .concat("§8M§fssssssssssss§8M§f         \n")
                            .concat("   §8M§fssssssss§8M§f            \n")
                            .concat("      §8M§fssss§8M§f               \n")
                            .concat("         §8MM§f                  \n")
                            .concat("                              \n")
                            .concat("                              \n")
                            .concat("                              \n")
                            .concat("                              \n")
                            .concat("                              \n")
                            .concat("                              \n")
                            .concat("                              \n")
            );

            for (String key: icons.keySet()) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("./config/mcfetch/" + key));
                    writer.write(icons.get(key));
                    writer.close();
                } catch (Exception exception) {
                    logger.error("Failed to create config file: {}", key);
                    logger.error(exception.getMessage());
                }
            }
            logger.info("Generating default config file successfully.");
        } else logger.info("Config directory exists, default config file generating skipped.");
    }
}
