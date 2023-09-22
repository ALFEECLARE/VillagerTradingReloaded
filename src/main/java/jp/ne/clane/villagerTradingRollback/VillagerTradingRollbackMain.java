package jp.ne.clane.villagerTradingRollback;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VillagerTradingRollbackMain.MOD_ID)
public class VillagerTradingRollbackMain {
	public static final String MOD_ID = "villagertradingrollback";
	public static final String MOD_NAME = "VillagerTradingRollback";
	public static final String[] MOD_AUTHORS = {"ALFEECLARE@CLANE SOFTWARE"};
	private static final Logger log = LogManager.getLogger(MOD_ID);

	private static VillagerTradingRollbackMain instance;

	//private VillagerTradingRollbackConfig config;

	private static void log(String message) {
		log.info("[{}] {}", log.getName(), message);
	}

	/**
	 * Reload modules
	 */
	public VillagerTradingRollbackMain modules() {
		try {
			Minecraft mc = Minecraft.getInstance();
			if (mc.levelRenderer != null)
				mc.levelRenderer.allChanged();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return this;
	}

	public VillagerTradingRollbackMain() {
		instance = this;
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);

		MinecraftForge.EVENT_BUS.register(this);
	}

	/**
	 * get this mod
	 */
	public static VillagerTradingRollbackMain getMod() {
		return instance;
	}

	/**
	 * Mod config file
	 */
	public static File getSaveFile() {
		Minecraft mc = Minecraft.getInstance();
		return new File(mc.gameDirectory, "config/VillagerTradingRollback.json");
	}

	private void setup(final FMLCommonSetupEvent event) {
		log("Initialization");
		GossipType.MAJOR_POSITIVE.max = 100;
		GossipType.MAJOR_POSITIVE.decayPerTransfer = 100;
		GossipType.MINOR_POSITIVE.max = 200;
	}
}
