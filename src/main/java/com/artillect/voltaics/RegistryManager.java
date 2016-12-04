package com.artillect.voltaics;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

<<<<<<< HEAD
import com.artillect.voltaics.tileentity.TileEntityInductor;
//import com.artillect.voltaics.tileentity.TileEntityInductor;
import com.artillect.voltaics.tileentity.TileEntityLowVoltageConduit;
import com.artillect.voltaics.tileentity.TileEntityVoltaicPile;
import com.artillect.voltaics.block.BlockInductor;
import com.artillect.voltaics.block.BlockLowVoltageConduit;
import com.artillect.voltaics.block.BlockVoltaicPile;
=======
import com.artillect.voltaics.tileentity.*;
import com.artillect.voltaics.block.*;
import com.artillect.voltaics.item.*;
>>>>>>> 24f19b6... fixed copper ore

public class RegistryManager {
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Item> items = new ArrayList<Item>();

	public static Item voltmeter, ingotCopper, hammer;
	public static Block voltaicPile, lowVoltageConduit, inductor, copperOre;
	
	public static void registerAll(){
		//blocks.add(blockCaminiteLargeBrick = (new BlockBase(Material.ROCK,"blockCaminiteLargeBrick",true)).setHarvestProperties("pickaxe", 0).setHardness(1.6f));
		
		/*FluidRegistry.registerFluid(fluidMoltenUmberSteel = new FluidMoltenUmberSteel());
		blocks.add(blockMoltenUmberSteel = (new BlockMoltenUmberSteel("moltenUmberSteel",false)));
		FluidRegistry.addBucketForFluid(fluidMoltenUmberSteel);
		
		FluidRegistry.registerFluid(fluidMoltenAstralite = new FluidMoltenAstralite());
		blocks.add(blockMoltenAstralite = (new BlockMoltenAstralite("moltenAstralite",false)));
		FluidRegistry.addBucketForFluid(fluidMoltenAstralite);*/
		items.add(voltmeter = new ItemVoltmeter());
		items.add(ingotCopper = new ItemBase("ingotCopper",true));
		items.add(hammer = new ItemHammer());
		
		blocks.add(copperOre = new BlockCopperOre("copperOre").setCreativeTab(CreativeTabs.MATERIALS));
		blocks.add(voltaicPile = (new BlockVoltaicPile(Material.ROCK,"voltaicPile",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.4f));
		blocks.add(lowVoltageConduit = (new BlockLowVoltageConduit(Material.ROCK,"lowVoltageConduit",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.0f));
		blocks.add(inductor = (new BlockInductor(Material.ROCK,"inductor",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.4f));
		GameRegistry.registerTileEntity(TileEntityVoltaicPile.class, Voltaics.modId+":tileEntityVoltaicPile");
		GameRegistry.registerTileEntity(TileEntityLowVoltageConduit.class, Voltaics.modId+":tileEntityLowVoltageConduit");
		GameRegistry.registerTileEntity(TileEntityInductor.class, Voltaics.modId+":tileEntityInductor");
	}
	
	public static void registerFluids(){

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
