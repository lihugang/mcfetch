package top.lihugang.mc.mod;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.minecraft.text.Text;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.lihugang.mc.mod.utils.SystemInfoCollector;
import top.lihugang.mc.mod.utils.defaultConfigGenerator;
import top.lihugang.mc.mod.utils.gameInfoCollector.quiltGameInfoCollector;
import top.lihugang.mc.mod.utils.outputGenerator;

public class mcfetch implements ModInitializer {

	public static final String MOD_ID = "mcfetch";
	public static final Logger logger = LoggerFactory.getLogger(MOD_ID);

	@ClientOnly
	@Override
	public void onInitialize(ModContainer mod) {
		long minecraftStartTime = new Date().getTime();

		defaultConfigGenerator.generate(logger);

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal(MOD_ID).executes(context -> {
				List<AbstractMap.SimpleEntry<String, String>> gameInfo = quiltGameInfoCollector.collect(minecraftStartTime);
				List<AbstractMap.SimpleEntry<String, String>> sysInfo = SystemInfoCollector.collect();

				List<List<AbstractMap.SimpleEntry<String, String>>> list = new ArrayList<>();
				list.add(gameInfo);
				list.add(sysInfo);

				String commandResponse = outputGenerator.generate("quilt", list);

				logger.info(commandResponse);

				context.getSource().sendFeedback(Text.literal(commandResponse));
				return 1;
			}));
		});
	}
}
