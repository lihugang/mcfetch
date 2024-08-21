package top.lihugang.mc.mod.mcfetch.client;

import net.minecraft.text.LiteralText;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import top.lihugang.mc.mod.mcfetch.client.utils.SystemInfoCollector;
import top.lihugang.mc.mod.mcfetch.client.utils.defaultConfigGenerator;
import top.lihugang.mc.mod.mcfetch.client.utils.gameInfoCollector.fabricGameInfoCollector;
import top.lihugang.mc.mod.mcfetch.client.utils.outputGenerator;

public class McfetchClient implements ClientModInitializer {

    public static final String MOD_ID = "mcfetch";
    // Minecraft 1.16.5 does not support LoggerFactory
    @Override
    public void onInitializeClient() {
        long minecraftStartTime = new Date().getTime();

        defaultConfigGenerator.generate();

        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal(MOD_ID).executes(context -> {
                    List<AbstractMap.SimpleEntry<String, String>> gameInfo = fabricGameInfoCollector.collect(minecraftStartTime);
                    List<AbstractMap.SimpleEntry<String, String>> sysInfo = SystemInfoCollector.collect();

                    List<List<AbstractMap.SimpleEntry<String, String>>> list = new ArrayList<>();
                    list.add(gameInfo);
                    list.add(sysInfo);

                    String commandResponse = outputGenerator.generate("fabric", list);

                    context.getSource().sendFeedback(new LiteralText(commandResponse));
                    return 1;
                })
        );}
}
