package top.lihugang.mc.mod.mcfetch.client.utils;

public class memoryFormatter {
    public static String format(long size) {
        if (size < 1024L) return size + "B";
        if (size < 1024L * 1024L) return (long)(size / 1024) + "KiB";
        return (long)(size / 1024L / 1024L) + "MiB";
    }
}
