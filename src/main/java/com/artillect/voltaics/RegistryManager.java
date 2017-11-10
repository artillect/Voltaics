package com.artillect.voltaics;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import com.artillect.voltaics.block.IModeledBlock;
import com.artillect.voltaics.item.IModeledItem;
import com.artillect.voltaics.item.ItemBase;
import com.artillect.voltaics.item.ItemHammer;
import com.artillect.voltaics.item.ItemVoltmeter;


import com.artillect.voltaics.tileentity.TileEntityInductor;
//import com.artillect.voltaics.tileentity.TileEntityInductor;
import com.artillect.voltaics.tileentity.TileEntityLowVoltageConduit;
import com.artillect.voltaics.tileentity.TileEntityVoltaicPile;
import com.artillect.voltaics.block.BlockCopperOre;
import com.artillect.voltaics.block.BlockInductor;
import com.artillect.voltaics.block.BlockLowVoltageConduit;
import com.artillect.voltaics.block.BlockVoltaicPile;


public class RegistryManager {
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static Item voltmeter, ingotCopper, hammer;
	public static Block voltaicPile, lowVoltageConduit, inductor, copperOre;
	
	public static void registerAll(){

		items.add(voltmeter = new ItemVoltmeter());
		items.add(ingotCopper = new ItemBase("ingotCopper",true));
		items.add(hammer = new ItemHammer());
		blocks.add(voltaicPile = (new BlockVoltaicPile(Material.ROCK,"voltaicPile",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.4f));
		blocks.add(lowVoltageConduit = (new BlockLowVoltageConduit(Material.ROCK,"lowVoltageConduit",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.0f));
		blocks.add(inductor = (new BlockInductor(Material.ROCK,"inductor",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.4f));
		GameRegistry.registerTileEntity(TileEntityVoltaicPile.class, Voltaics.modId+":tileEntityVoltaicPile");
		GameRegistry.registerTileEntity(TileEntityLowVoltageConduit.class, Voltaics.modId+":tileEntityLowVoltageConduit");
		GameRegistry.registerTileEntity(TileEntityInductor.class, Voltaics.modId+":tileEntityInductor");
		
		//register ores
		OreDictionary.registerOre("oreCopper", copperOre);
		OreDictionary.registerOre("ingotCopper", ingotCopper);
	}
	
	public static void registerFluids(){

	}
	
	public static void initR(){
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