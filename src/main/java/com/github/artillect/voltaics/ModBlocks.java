package com.github.artillect.voltaics;

import java.util.HashSet;
import java.util.Set;

import com.github.artillect.voltaics.block.BlockBase;
import com.github.artillect.voltaics.block.BlockDistiller;
import com.github.artillect.voltaics.block.BlockHeatingChamber;
import com.github.artillect.voltaics.block.BlockHeatingCoil;
import com.github.artillect.voltaics.block.BlockLowVoltageConduit;
import com.github.artillect.voltaics.block.BlockVoltaicPile;
import com.github.artillect.voltaics.tileentity.TileEntityLowVoltageConduit;
import com.github.artillect.voltaics.tileentity.TileEntityVoltaicPile;
import com.google.common.base.Preconditions;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
	
	public static final BlockVoltaicPile VOLTAIC_PILE = new BlockVoltaicPile(Material.ROCK,"voltaicPile",true);
	
	public static final BlockHeatingCoil HEATING_COIL = new BlockHeatingCoil(Material.ROCK,"heatingCoil",true);
	
	public static final BlockLowVoltageConduit LOW_VOLTAGE_CONDUIT = new BlockLowVoltageConduit(Material.ROCK,"lowVoltageConduit",true);
	
	public static final BlockBase COPPER_ORE = new BlockBase(Material.ROCK, "copperOre", true);
	
	public static final BlockBase ZINC_ORE = new BlockBase(Material.ROCK,"zincOre",true);
	
	public static final BlockHeatingChamber HEATING_CHAMBER = new BlockHeatingChamber(Material.ROCK,"heatingChamber",true);
	
	public static final BlockDistiller DISTILLER = new BlockDistiller(Material.ROCK,"distiller",true);
	
	@Mod.EventBusSubscriber(modid = Voltaics.modId)
	public static class RegistrationHandler {
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();
			
			final Block[] blocks = {
					VOLTAIC_PILE,
					HEATING_COIL,
					LOW_VOLTAGE_CONDUIT,
					COPPER_ORE,
					ZINC_ORE,
					HEATING_CHAMBER,
					DISTILLER
			};
			
			registry.registerAll(blocks);
		}
		
		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
			final ItemBlock[] items = {
					new ItemBlock(VOLTAIC_PILE),
					new ItemBlock(LOW_VOLTAGE_CONDUIT),
					new ItemBlock(HEATING_COIL),
					new ItemBlock(COPPER_ORE),
					new ItemBlock(ZINC_ORE),
					new ItemBlock(HEATING_CHAMBER),
					new ItemBlock(DISTILLER)
			};
			
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			for (final ItemBlock item : items) {
				final Block block = item.getBlock();
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
				registry.register(item.setRegistryName(registryName));
				ITEM_BLOCKS.add(item);
			}

			registerTileEntities();
		}
	}
	
	private static void registerTileEntities() {
		registerTileEntity(TileEntityVoltaicPile.class,"voltaic_pile");
		// registerTileEntity(TileEntityHeatingCoil.class,"heating_coil");
		registerTileEntity(TileEntityLowVoltageConduit.class,"low_voltage_conduit");
		// registerTileEntity(TileEntityHeatingChamber.class,"heating_chamber");
	}
	
	@SuppressWarnings("deprecation")
	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		GameRegistry.registerTileEntity(tileEntityClass, Voltaics.modId + ":" + name);
	}
}
