package com.github.artillect.voltaics;

import com.github.artillect.voltaics.debug.ForgeLoggerTweaker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;


@Mod(Voltaics.modId)
public class Voltaics {

	// initialize some variables containing information about the mod
	public static final String modId = "voltaics";
	public static final String name = "Voltaics";
	public static final String version = "0.0.1";
	
	// get event bus for registration events
	public static IEventBus MOD_EVENT_BUS;
	
	
	// do registry things
	public Voltaics() {
		final boolean HIDE_CONSOLE_NOISE = false;
		if (HIDE_CONSOLE_NOISE) {
			ForgeLoggerTweaker.setMinimumLevel(Level.WARN);
			ForgeLoggerTweaker.applyLoggerFilter();
		}
		
		MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
		
		registerCommonEvents();
		DistExecutor.runWhenOn(Dist.CLIENT, () -> Voltaics::registerClientOnlyEvents);
	}
	
	
	// register things that are server-side
	public static void registerCommonEvents() {
		
	}
	
	// register client-side things
	public static void registerClientOnlyEvents() {
		
	}
}