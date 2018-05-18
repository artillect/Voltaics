package com.github.reygrschel.voltaics.proxy;

import com.github.reygrschel.voltaics.RegistryManager;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event){
		super.preInit(event);
		RegistryManager.registerRendering();
	}
}
