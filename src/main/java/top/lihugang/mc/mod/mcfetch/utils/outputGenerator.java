package top.lihugang.mc.mod.mcfetch.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class outputGenerator {
    public static String generate(String modLoader, List<List<AbstractMap.SimpleEntry<String, String>>> info) {
        StringBuilder builder = new StringBuilder();

        List<AbstractMap.SimpleEntry<String, String>> flattedInfo = new ArrayList<>();
        for (List<AbstractMap.SimpleEntry<String, String>> sublist: info) {
            flattedInfo.addAll(sublist);
        }


        try {
            String iconFilePath = "./config/mcfetch/" + modLoader;
            Scanner scanner = new Scanner(new FileReader(iconFilePath));
            for (AbstractMap.SimpleEntry<String, String> object: flattedInfo) {
                builder.append("§f");
                if (scanner.hasNextLine()) {
                    String thisLineIcon = scanner.nextLine();
                    builder.append(thisLineIcon);
                    builder.append("   "); // The length of the spaces is as the same as neofetch results
                }
                String key = object.getKey(), value = object.getValue();
                if (!key.startsWith("(hide)")) {
                    builder.append("§c");
                    builder.append(key);
                    builder.append("§f");
                    builder.append(": ");
                }
                builder.append(value);
                builder.append('\n');
            }

            while (scanner.hasNextLine()) {
                String thisLineIcon = scanner.nextLine();
                if (thisLineIcon.indexOf('s') == -1) break; // blank line
                builder.append(thisLineIcon);
                builder.append('\n');
            }

            scanner.close();

        } catch (FileNotFoundException exception) {
            return "[mcfetch] Error: Cannot read icon file";
        }
        builder.deleteCharAt(builder.length() - 1); // remove the last '\n'
        return builder.toString();
    }
}
