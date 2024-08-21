package top.lihugang.mc.mod.mcfetch.client.utils.gameInfoCollector;

import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.util.*;

import top.lihugang.mc.mod.mcfetch.client.utils.timeFormatter;

public class fabricGameInfoCollector {
    public static List<AbstractMap.SimpleEntry<String, String>> collect(long startTime) {
        List<AbstractMap.SimpleEntry<String, String>> info = new ArrayList<>();

        MinecraftClient client = MinecraftClient.getInstance();
        List<ModContainer> mods = FabricLoader.getInstance().getAllMods().stream().toList();

        assert client.player != null;
        String playerName = client.player.getName().getString();
        info.add(
                new AbstractMap.SimpleEntry<>("(hide)playerName", "§c" + playerName)
        );

        info.add(
                new AbstractMap.SimpleEntry<>("(hide)bar", "-".repeat(playerName.length()))
        );

        String minecraftVersion = "<error>", fabricVersion = "<error>", fabricAPIVersion = "<error>";
        for (ModContainer currentMod : mods) {
            ModMetadata modMetadata = currentMod.getMetadata();
            String modName = modMetadata.getName();
            if (modName.equals("Minecraft")) {
                minecraftVersion = modMetadata.getVersion().getFriendlyString();
            } else if (modName.equals("Fabric Loader")) {
                fabricVersion = modMetadata.getVersion().getFriendlyString();
            } else if (modName.equals("Fabric API")) {
                fabricAPIVersion = modMetadata.getVersion().getFriendlyString();
            }
        }

        info.add(
                new AbstractMap.SimpleEntry<>("(hide)MinecraftInfo",
                            "§cMinecraft " + minecraftVersion + " (fabric)"
                        )
        );

        info.add(
                new AbstractMap.SimpleEntry<>("Fabric Version", fabricVersion)
        );

        info.add(
                new AbstractMap.SimpleEntry<>("Fabric API Version", fabricAPIVersion)
        );

        info.add(
                new AbstractMap.SimpleEntry<>("MC Uptime", timeFormatter.format(new Date().getTime() - startTime))
        );

        info.add(
                new AbstractMap.SimpleEntry<>("MC Display", client.getWindow().getWidth() + "x" + client.getWindow().getHeight())
        );

        info.add(
                new AbstractMap.SimpleEntry<>("Mods", mods.size() + " (fabric)")
        );

        return info;
    }
}