package io.github.artillect.voltaics;

import io.github.artillect.voltaics.init.Registration;
import io.github.artillect.voltaics.init.ModInit;
import io.github.artillect.voltaics.init.ClientInit;


import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Voltaics.MODID)
public class Voltaics {

	public static final String MODID = "voltaics";
	
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	public Voltaics() {
		LOGGER.debug("Change the world with the amazing technology of Voltaics!");
		
		
		Registration.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModInit::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientInit::init);
	}
}
