package com.github.artillect.voltaics.block;

import com.github.artillect.voltaics.Voltaics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBase extends Block implements IModeledBlock {
	public Item itemBlock = null;
	public boolean isOpaqueCube = true, isFullCube = true;
	public BlockRenderLayer layer = BlockRenderLayer.SOLID;
	
	//Constructor that sets name-related properties
	public BlockBase(Material material, String name, boolean addToTab) {
		super(material);
		setUnlocalizedName(Voltaics.modId+"."+name);
		setRegistryName(Voltaics.modId+":"+name);
		if (addToTab) {
			setCreativeTab(Voltaics.creativeTab);
		}
		GameRegistry.register(this);
        GameRegistry.register(itemBlock = (new ItemBlock(this).setRegistryName(this.getRegistryName() + "inventory")));
    }
	
	//Setting and checking opacity and solidness
	public BlockBase setIsOpaqueCube(boolean b) {
		isOpaqueCube = b;
		return this;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return isOpaqueCube;
	}
	
	public BlockBase setIsFullCube(boolean b) {
		isFullCube = b;
		return this;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return isFullCube;
	}
	
	public BlockBase setHarvestProperties(String toolType, int level) {
		super.setHarvestLevel(toolType, level);
		return this;
	}
	
	//Set block model
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString(),"inventory"));
	}

}
