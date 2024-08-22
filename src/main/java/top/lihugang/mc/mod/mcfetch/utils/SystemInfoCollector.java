package top.lihugang.mc.mod.mcfetch.utils;

import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class SystemInfoCollector {
    public static char colorSymbol = 0xa7; // It seems that NeoForge does not support utf-8 characters in the source code
    public static char blockSymbol = 0x2588;
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
                new AbstractMap.SimpleEntry<>("(hide)color-row1",
                        "" +
                                colorSymbol + '0' + blockSymbol +
                                colorSymbol + '4' + blockSymbol +
                                colorSymbol + '2' + blockSymbol +
                                colorSymbol + '6' + blockSymbol +
                                colorSymbol + '1' + blockSymbol +
                                colorSymbol + '5' + blockSymbol +
                                colorSymbol + '3' + blockSymbol +
                                colorSymbol + '7' + blockSymbol
                )
        );
        info.add(
                new AbstractMap.SimpleEntry<>("(hide)color-row1",
                        "" +
                                colorSymbol + '8' + blockSymbol +
                                colorSymbol + 'c' + blockSymbol +
                                colorSymbol + 'a' + blockSymbol +
                                colorSymbol + 'e' + blockSymbol +
                                colorSymbol + '9' + blockSymbol +
                                colorSymbol + 'd' + blockSymbol +
                                colorSymbol + 'b' + blockSymbol +
                                colorSymbol + 'f' + blockSymbol
                )
        );

        return info;
    }
}
