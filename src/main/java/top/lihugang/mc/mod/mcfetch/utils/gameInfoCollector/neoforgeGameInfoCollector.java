package top.lihugang.mc.mod.mcfetch.utils.gameInfoCollector;

import java.util.*;

import net.minecraft.client.Minecraft;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.IModInfo;

import org.slf4j.Logger;

import top.lihugang.mc.mod.mcfetch.utils.timeFormatter;

public class neoforgeGameInfoCollector {
    public static List<AbstractMap.SimpleEntry<String, String>> collect(long startTime) {
        List<AbstractMap.SimpleEntry<String, String>> info = new ArrayList<>();
        List<IModInfo> mods = ModList.get().getMods();
        Minecraft client = Minecraft.getInstance();

        assert client.player != null;
        String playerName = client.player.getName().getString();
        info.add(
                new AbstractMap.SimpleEntry<>("(hide)playerName", "§c" + playerName)
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
                         "§cMinecraft " + minecraftVersion + " (neoforge)"
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