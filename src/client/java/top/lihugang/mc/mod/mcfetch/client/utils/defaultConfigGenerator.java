package top.lihugang.mc.mod.mcfetch.client.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class defaultConfigGenerator {
    static public void generate() {
        Map<String, String> icons = new TreeMap<>();

        File
                configRoot = new File("./config"),
                configDirectory = new File("./config/mcfetch");
        if (!configDirectory.exists()) {
            if (!configRoot.exists()) configRoot.mkdir();
            configDirectory.mkdir();

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

            icons.put("neoforge", ""
                    .concat("                           \n")
                    .concat("         §8ss   ss§f         \n")
                    .concat("      §8s§7ss§8s   §8s§7ss§8s      \n")
                    .concat("   §6ss§8mm§6ssssss§8mm§6ss   \n")
                    .concat("   §6ssssmmmmmmssss   \n")
                    .concat("   §6smmmmmmmmmmmms   \n")
                    .concat("   §6ss§fhh§6hhhhhh§fhh§6ss   \n")
                    .concat("§6ssss§fh§0O§6ssssss§0O§fh§6ssss\n")
                    .concat("   §6syyyyyyyyyyyys   \n")
                    .concat("   §6ssss§fhh§0yy§fhh§6ssss   \n")
                    .concat("         §6ssssss         \n")
                    .concat("                           \n")
                    .concat("                           \n")
                    .concat("                           \n")
                    .concat("                           \n")
                    .concat("                           \n")
            );

            for (String key: icons.keySet()) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("./config/mcfetch/" + key));
                    writer.write(icons.get(key));
                    writer.close();
                } catch (Exception ignored) {}
            }
        }
    }
}
