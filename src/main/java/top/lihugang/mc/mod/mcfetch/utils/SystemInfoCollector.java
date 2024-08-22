package top.lihugang.mc.mod.mcfetch.utils;

import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class SystemInfoCollector {
    public static List<AbstractMap.SimpleEntry<String, String>> collect() {
        List<AbstractMap.SimpleEntry<String, String>> info = new ArrayList<>();

        HardwareAbstractionLayer hardwareInfo = new SystemInfo().getHardware();

        info.add(
                new AbstractMap.SimpleEntry<>("OS", System.getProperty("os.name") + " " + System.getProperty("os.version"))
        );
        info.add(
                new AbstractMap.SimpleEntry<>("Arch", System.getProperty("os.arch"))
        );
        info.add(
                new AbstractMap.SimpleEntry<>("Java", System.getProperty("java.version"))
        );

        Runtime runtime = Runtime.getRuntime();

        info.add(
                new AbstractMap.SimpleEntry<>("CPU", runtime.availableProcessors() + "x " + hardwareInfo.getProcessor().getProcessorIdentifier().getName())
        );

        long totalMemory = runtime.totalMemory();
        long usedMemory = totalMemory - runtime.freeMemory();

        info.add(
                new AbstractMap.SimpleEntry<>("MC Memory", memoryFormatter.format(usedMemory) + " / " + memoryFormatter.format(totalMemory))
        );

        info.add(
                new AbstractMap.SimpleEntry<>("GPU", hardwareInfo.getGraphicsCards().getFirst().getName() + " " + hardwareInfo.getGraphicsCards().getFirst().getVersionInfo())
        );

        try {

            GraphicsDevice[] graphicsDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

            if (graphicsDevices.length != 0)
                info.add(
                        new AbstractMap.SimpleEntry<>("Resolution", graphicsDevices[0].getDisplayMode().getWidth() + "x" + graphicsDevices[0].getDisplayMode().getHeight())
                );

        } catch (Exception ignored) {}

        info.add(
                new AbstractMap.SimpleEntry<>("(hide)color-row1", "§0█§4█§2█§6█§1█§5█§3█§7█")
        );
        info.add(
                new AbstractMap.SimpleEntry<>("(hide)color-row2", "§8█§c█§a█§e█§9█§d█§b█§f█")
        );

        return info;
    }
}
