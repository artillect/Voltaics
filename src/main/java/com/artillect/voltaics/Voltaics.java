package com.artillect.voltaics;

import com.artillect.voltaics.client.VoltaicsTab;
import com.artillect.voltaics.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Voltaics.modId, name = Voltaics.name, version = Voltaics.version, acceptedMinecraftVersions = "[1.12]")
public class Voltaics {

	public static final String modId = "voltaics";
	public static final String name = "Voltaics";
	public static final String version = "1.0.0";

	@Mod.Instance(modId)
	public static Voltaics instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(name + " has begun loading.");
		proxy.preInit(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		RegistryManager.initR(); //initialize recipes
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
	
	@SidedProxy(serverSide = "com.artillect.voltaics.proxy.CommonProxy", clientSide = "com.artillect.voltaics.proxy.ClientProxy")
	public static CommonProxy proxy;
	public static final VoltaicsTab creativeTab = new VoltaicsTab();
}