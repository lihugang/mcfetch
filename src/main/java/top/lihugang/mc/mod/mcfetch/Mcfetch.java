package top.lihugang.mc.mod.mcfetch;

import com.mojang.logging.LogUtils;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;

import org.slf4j.Logger;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import top.lihugang.mc.mod.mcfetch.utils.SystemInfoCollector;
import top.lihugang.mc.mod.mcfetch.utils.defaultConfigGenerator;
import top.lihugang.mc.mod.mcfetch.utils.gameInfoCollector.neoforgeGameInfoCollector;
import top.lihugang.mc.mod.mcfetch.utils.outputGenerator;


@Mod(Mcfetch.MODID)
public class Mcfetch {
    public static final String MODID = "mcfetch";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static long minecraftStartTime = new Date().getTime();

    public Mcfetch(IEventBus modEventBus, ModContainer modContainer) {
    }

    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientCommandRegister(RegisterClientCommandsEvent event) {
            defaultConfigGenerator.generate(LOGGER);

            event.getDispatcher().register(
                    Commands.literal(MODID).executes(context -> {
                        List<AbstractMap.SimpleEntry<String, String>> gameInfo = neoforgeGameInfoCollector.collect(minecraftStartTime);
                        List<AbstractMap.SimpleEntry<String, String>> sysInfo = SystemInfoCollector.collect();

                        List<List<AbstractMap.SimpleEntry<String, String>>> list = new ArrayList<>();
                        list.add(gameInfo);
                        list.add(sysInfo);

                        String commandResponse = outputGenerator.generate("neoforge", list);

                        LOGGER.info(commandResponse);
                        context.getSource().sendSystemMessage(Component.literal(commandResponse));
                        return 1;
                    })
            );
        }
    }
}
