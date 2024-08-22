package top.lihugang.mc.mod.mcfetch.utils.gameInfoCollector;

import java.util.*;

import net.minecraft.client.Minecraft;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.IModInfo;

import org.slf4j.Logger;

import top.lihugang.mc.mod.mcfetch.utils.timeFormatter;

public class neoforgeGameInfoCollector {
    public static char colorSymbol = 0xa7; // It seems that NeoForge does not support utf-8 characters in the source code
    public static List<AbstractMap.SimpleEntry<String, String>> collect(long startTime, Logger logger) {
        List<AbstractMap.SimpleEntry<String, String>> info = new ArrayList<>();
        List<IModInfo> mods = ModList.get().getMods();
        Minecraft client = Minecraft.getInstance();

        assert client.player != null;
        String playerName = client.player.getName().getString();
        info.add(
                new AbstractMap.SimpleEntry<>("(hide)playerName", colorSymbol + "c" + playerName)
        );

        info.add(
                new AbstractMap.SimpleEntry<>("(hide)bar", "-".repeat(playerName.length()))
        );

        String minecraftVersion = "<error>", neoforgeVersion = "<error>";
        for (IModInfo currentMod : mods) {
            String modName = currentMod.getDisplayName();
            if (modName.equals("Minecraft")) {
                minecraftVersion = currentMod.getVersion().toString();
            } else if (modName.equals("NeoForge")) {
                neoforgeVersion= currentMod.getVersion().toString();
            }
        }

        info.add(
                new AbstractMap.SimpleEntry<>("(hide)MinecraftInfo",
                         colorSymbol + "cMinecraft " + minecraftVersion + " (neoforge)"
                )
        );

        info.add(
                new AbstractMap.SimpleEntry<>("NeoForge Version", neoforgeVersion)
        );


        info.add(
                new AbstractMap.SimpleEntry<>("MC Uptime", timeFormatter.format(new Date().getTime() - startTime))
        );

        info.add(
                new AbstractMap.SimpleEntry<>("MC Display", client.getWindow().getWidth() + "x" + client.getWindow().getHeight())
        );

        info.add(
                new AbstractMap.SimpleEntry<>("Mods", mods.size() + " (neoforge)")
        );

        return info;
    }
}