package com.artillect.voltaics.proxy;

import com.artillect.voltaics.RegistryManager;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
		RegistryManager.registerAll();
    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}