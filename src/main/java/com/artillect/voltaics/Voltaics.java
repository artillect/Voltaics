package com.artillect.voltaics;

import com.artillect.voltaics.client.VoltaicsTab;
import com.artillect.voltaics.entity.EntityTurret;
import com.artillect.voltaics.entity.RenderEntityTurret;
import com.artillect.voltaics.proxy.CommonProxy;

import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

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
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		RegistryManager.initR(); //initalize recepies
		int id = 1;
        EntityRegistry.registerModEntity(EntityTurret.class, "Turret", id++, Voltaics.instance, 64, 3, true, 0x996600, 0x00ff00);

        // We want our mob to spawn in Plains and ice plains biomes. If you don't add this then it will not spawn automatically
        // but you can of course still make it spawn manually
        //EntityRegistry.addSpawn(EntityTurret.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.ICE_PLAINS);

        // This is the loot table for our mob
        LootTableList.register(EntityTurret.LOOT);
        RenderingRegistry.registerEntityRenderingHandler(EntityTurret.class, RenderEntityTurret.FACTORY);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
	
	@SidedProxy(serverSide = "com.artillect.voltaics.proxy.CommonProxy", clientSide = "com.artillect.voltaics.proxy.ClientProxy")
	public static CommonProxy proxy;
	public static final VoltaicsTab creativeTab = new VoltaicsTab();
}