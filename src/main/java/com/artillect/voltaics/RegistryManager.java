package com.artillect.voltaics;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

<<<<<<< HEAD
=======

import com.artillect.voltaics.block.IModeledBlock;
import com.artillect.voltaics.item.IModeledItem;
import com.artillect.voltaics.item.ItemBase;
import com.artillect.voltaics.item.ItemHammer;
import com.artillect.voltaics.item.ItemVoltmeter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

>>>>>>> 557d1ce... Re-added registrymanager
import com.artillect.voltaics.tileentity.TileEntityInductor;
//import com.artillect.voltaics.tileentity.TileEntityInductor;
import com.artillect.voltaics.tileentity.TileEntityLowVoltageConduit;
import com.artillect.voltaics.tileentity.TileEntityVoltaicPile;
import com.artillect.voltaics.block.BlockInductor;
import com.artillect.voltaics.block.BlockLowVoltageConduit;
import com.artillect.voltaics.block.BlockVoltaicPile;
<<<<<<< HEAD
=======
import com.artillect.voltaics.tileentity.*;
import com.artillect.voltaics.block.*;
import com.artillect.voltaics.item.*;
>>>>>>> 24f19b6... fixed copper ore
=======
>>>>>>> 557d1ce... Re-added registrymanager

public class RegistryManager {
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Item> items = new ArrayList<Item>();

<<<<<<< HEAD
	public static Item voltmeter, ingotCopper, hammer, ingotdcopper;
	public static Block voltaicPile, lowVoltageConduit, inductor, copperOre,
		duranizedcopperore;
	
	public static void registerAll(){
		VoltaicsWorldGen wgen;	//world generator
		
		//add items
		items.add(voltmeter = new ItemVoltmeter());
		items.add(ingotCopper = new ItemBase("ingotCopper",true));
		items.add(ingotdcopper = new ItemBase("ingotDuranizedCopper",true));
		items.add(hammer = new ItemHammer());
		
		//add blocks
		blocks.add(duranizedcopperore = (new BlockDuranizedCopperOre("dCopperOre")).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		blocks.add(copperOre = (new BlockCopperOre("copperOre")).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
=======
	public static Item voltmeter, ingotCopper, hammer;
	public static Block voltaicPile, lowVoltageConduit, inductor;
	
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
		
>>>>>>> 557d1ce... Re-added registrymanager
		blocks.add(voltaicPile = (new BlockVoltaicPile(Material.ROCK,"voltaicPile",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.4f));
		blocks.add(lowVoltageConduit = (new BlockLowVoltageConduit(Material.ROCK,"lowVoltageConduit",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.0f));
		blocks.add(inductor = (new BlockInductor(Material.ROCK,"inductor",true)).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.4f));
		GameRegistry.registerTileEntity(TileEntityVoltaicPile.class, Voltaics.modId+":tileEntityVoltaicPile");
		GameRegistry.registerTileEntity(TileEntityLowVoltageConduit.class, Voltaics.modId+":tileEntityLowVoltageConduit");
		GameRegistry.registerTileEntity(TileEntityInductor.class, Voltaics.modId+":tileEntityInductor");
<<<<<<< HEAD
		
		//register ores
		OreDictionary.registerOre("oreCopper", copperOre);
		OreDictionary.registerOre("ingotCopper", ingotCopper);
		OreDictionary.registerOre("oreDuranium", duranizedcopperore);
		
		//register worldgen
		RegistryManager rm = new RegistryManager();
		wgen = rm.new VoltaicsWorldGen();
		GameRegistry.registerWorldGenerator(wgen, 0);
=======
>>>>>>> 557d1ce... Re-added registrymanager
	}
	
	public static void registerFluids(){

	}
	
<<<<<<< HEAD
	public static void initR(){
		GameRegistry.addSmelting(copperOre, new ItemStack(ingotCopper), 0f);
		GameRegistry.addSmelting(duranizedcopperore, new ItemStack(ingotdcopper), 2f);
	}
	
	@SideOnly(Side.CLIENT)
    public static void registerRendering(){
=======
	@SideOnly(Side.CLIENT)
    public static void registerRendering(){

>>>>>>> 557d1ce... Re-added registrymanager
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
<<<<<<< HEAD
	
	class VoltaicsWorldGen implements IWorldGenerator {
		@Override
		public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
				IChunkProvider chunkProvider) {
			switch (world.provider.getDimension()) {
			case 0: 
				generateOverworld(world, random, chunkX, chunkZ);
				break;
			}
			
		}

		public void generateOverworld(World world, Random rand, int x, int z) {
			generateOre(copperOre, world, rand, x, z, 2, 6, 4, 5, 100, Blocks.STONE);
			generateOre(duranizedcopperore, world, rand, x, z, 2, 6, 4, 5, 10, Blocks.STONE);
		}
		
		public void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int minVienSize, int maxVienSize, int chance, int minY, int maxY, Block generateIn) {
			int vienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
			int heightRange = maxY - minY;
			WorldGenMinable gen = new WorldGenMinable(block.getDefaultState(), vienSize);
			for(int i = 0; i < chance; i++) {
				int xRand = chunkX * 16 + random.nextInt(16);
				int yRand = random.nextInt(heightRange) + minY;
				int zRand = chunkZ * 16 + random.nextInt(16);
				gen.generate(world, random, new BlockPos(xRand, yRand, zRand));
			}
		}
	}
}
=======
}
>>>>>>> 557d1ce... Re-added registrymanager
