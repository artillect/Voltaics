package com.github.artillect.voltaics.proxy;

import com.github.artillect.voltaics.RegistryManager;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event){
		super.preInit(event);
		RegistryManager.registerRendering();
	}
}
