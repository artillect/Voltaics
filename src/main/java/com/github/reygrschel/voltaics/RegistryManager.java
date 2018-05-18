package com.github.reygrschel.voltaics;

import java.util.ArrayList;

import com.github.reygrschel.voltaics.block.BlockCopperOre;
import com.github.reygrschel.voltaics.block.BlockHeatingChamber;
import com.github.reygrschel.voltaics.block.BlockHeatingCoil;
import com.github.reygrschel.voltaics.block.BlockLowVoltageConduit;
import com.github.reygrschel.voltaics.block.BlockVoltaicPile;
import com.github.reygrschel.voltaics.block.BlockZincOre;
import com.github.reygrschel.voltaics.block.IModeledBlock;
import com.github.reygrschel.voltaics.item.IModeledItem;
import com.github.reygrschel.voltaics.item.ItemBase;
import com.github.reygrschel.voltaics.item.ItemHammer;
import com.github.reygrschel.voltaics.item.ItemThermometer;
import com.github.reygrschel.voltaics.item.ItemVoltmeter;
import com.github.reygrschel.voltaics.tileentity.TileEntityHeatingCoil;
import com.github.reygrschel.voltaics.tileentity.TileEntityLowVoltageConduit;
import com.github.reygrschel.voltaics.tileentity.TileEntityVoltaicPile;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;


public class RegistryManager {
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static Item voltmeter, hammer, plateCopper, ingotCopper, plateZinc, electrolyticCell, dustSalt, thermometer;
	public static Block voltaicPile, lowVoltageConduit, heatingCoil, copperOre, zincOre, heatingChamber;
	
	public static void registerAll(){
		GameRegistry.registerWorldGenerator(new DefaultWorldGen(), 1);
		
		//register items
		items.add(voltmeter = new ItemVoltmeter());
		items.add(plateCopper = new ItemBase("plateCopper", true));
		items.add(ingotCopper = new ItemBase("ingotCopper", true));
		items.add(plateZinc = new ItemBase("plateZinc", true));
		items.add(electrolyticCell = new ItemBase("electrolyticCell", true));
		items.add(dustSalt = new ItemBase("dustSalt", true));
		items.add(hammer = new ItemHammer());
		items.add(thermometer = new ItemThermometer());
		
		//register blocks
		blocks.add(voltaicPile = (new BlockVoltaicPile(Material.ROCK,"voltaicPile",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.4f));
		blocks.add(lowVoltageConduit = (new BlockLowVoltageConduit(Material.ROCK,"lowVoltageConduit",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.0f));
		blocks.add(heatingCoil = (new BlockHeatingCoil(Material.ROCK,"heatingCoil",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.4f));
		blocks.add(copperOre = (new BlockCopperOre(Material.ROCK,"copperOre",true)));
		blocks.add(zincOre = (new BlockZincOre(Material.ROCK,"zincOre",true)));
		blocks.add(heatingChamber = (new BlockHeatingChamber(Material.ROCK,"heatingChamber",true)));
		
		//register tile entities from blocks
		GameRegistry.registerTileEntity(TileEntityVoltaicPile.class, Voltaics.modId+":tileEntityVoltaicPile");
		GameRegistry.registerTileEntity(TileEntityLowVoltageConduit.class, Voltaics.modId+":tileEntityLowVoltageConduit");
		GameRegistry.registerTileEntity(TileEntityHeatingCoil.class, Voltaics.modId+":tileEntityInductor");
		
		//register ores from blocks
		OreDictionary.registerOre("oreCopper", copperOre);
		OreDictionary.registerOre("ingotCopper", ingotCopper);
	}
	
	//register fluids
	public static void registerFluids(){

	}
	
	//register recipes
	public static void initR(){
		//register shapeless
		
		//register shaped
		
		//register smelting
		GameRegistry.addSmelting(copperOre, new ItemStack(ingotCopper), 0f);
	}
	
	@SideOnly(Side.CLIENT)
    public static void registerRendering(){
		for (int i = 0; i < blocks.size(); i ++){
			if (blocks.get(i) instanceof IModeledBlock){
				((IModeledBlock)blocks.get(i)).initModel();
			}
		}
		for (int i = 0; i < items.size(); i ++){
			if (items.get(i) instanceof IModeledItem){
				((IModeledItem)items.get(i)).initModel();
			}
		}
	}
}