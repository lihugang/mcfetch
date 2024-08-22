package top.lihugang.mc.mod.utils.gameInfoCollector;

import net.minecraft.client.MinecraftClient;
import net.fabricmc.loader.api.FabricLoader;
import org.quiltmc.loader.api.QuiltLoader;

import java.util.*;

import top.lihugang.mc.mod.utils.timeFormatter;

public class quiltGameInfoCollector {
    public static List<AbstractMap.SimpleEntry<String, String>> collect(long startTime) {
        List<AbstractMap.SimpleEntry<String, String>> info = new ArrayList<>();

        MinecraftClient client = MinecraftClient.getInstance();
        List<org.quiltmc.loader.api.ModContainer> mods = QuiltLoader.getAllMods().stream().toList();
		List<net.fabricmc.loader.api.ModContainer> fabricMods = FabricLoader.getInstance().getAllMods().stream().toList();

        assert client.player != null;
        String playerName = client.player.getName().getString();
        info.add(
                new AbstractMap.SimpleEntry<>("(hide)playerName", "§c" + playerName)
        );

        info.add(
                new AbstractMap.SimpleEntry<>("(hide)bar", "-".repeat(playerName.length()))
        );

        String minecraftVersion = "<error>", quiltVersion = "<error>", quiltBaseAPIVersion = "<error>", quiltedFabricAPIVersion = "<error>";
        for (org.quiltmc.loader.api.ModContainer currentMod : mods) {
            org.quiltmc.loader.api.ModMetadata modMetadata = currentMod.metadata();
            String modName = modMetadata.name();
            if (modName.equals("Minecraft")) {
                minecraftVersion = modMetadata.version().toString();
            } else if (modName.equals("Quilt Loader")) {
                quiltVersion = modMetadata.version().toString();
            } else if (modName.equals("Quilt Base API")) {
				quiltBaseAPIVersion = modMetadata.version().toString();
            } else if (modName.equals("Quilted Fabric API")) {
				quiltedFabricAPIVersion = modMetadata.version().toString();
			}
        }

        info.add(
                new AbstractMap.SimpleEntry<>("(hide)MinecraftInfo",
                            "§cMinecraft " + minecraftVersion + " (quilt)"
                        )
        );

        info.add(
                new AbstractMap.SimpleEntry<>("Quilt Version", quiltVersion)
        );

		info.add(
			new AbstractMap.SimpleEntry<>("Quilt Base API Version", quiltBaseAPIVersion)
		);

        info.add(
                new AbstractMap.SimpleEntry<>("Quilted Fabric API Version", quiltedFabricAPIVersion)
        );

        info.add(
                new AbstractMap.SimpleEntry<>("MC Uptime", timeFormatter.format(new Date().getTime() - startTime))
        );

        info.add(
                new AbstractMap.SimpleEntry<>("MC Display", client.getWindow().getWidth() + "x" + client.getWindow().getHeight())
        );

        info.add(
                new AbstractMap.SimpleEntry<>("Mods", mods.size() + " (quilt) " + fabricMods.size() + " (fabric)")
        );

        return info;
    }
}
