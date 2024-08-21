package top.lihugang.mc.mod.mcfetch.client.utils;

public class timeFormatter {
    public static String format(long time) {
        long sec = time / 1000;
        if (sec < 60) return sec + " second" + (sec == 1 ? "" : "s");

        long min = sec / 60;
        if (min < 60) return min + " min" + (min == 1 ? "" : "s");

        long hour = min / 60;
        if (hour < 24) return hour +
                " hour" +
                (hour == 1 ? "" : "s") +
                ", " +
                min % 60 +
                " min" +
                ((min % 60) < 2 ? "" : "s");

        long day = hour / 24;
        return day +
                "day" +
                (day == 1 ? "" : "s") +
                ", " +
                hour % 24 +
                " hour" +
                ((hour % 24) < 2 ? "" : "s") +
                ", " +
                min % 60 +
                " min" +
                ((min % 60) < 2 ? "" : "s");
    }
}
