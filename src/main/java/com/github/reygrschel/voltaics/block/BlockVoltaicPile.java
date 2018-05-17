package com.github.reygrschel.voltaics.block;

import com.github.reygrschel.voltaics.tileentity.TileEntityLowVoltageConduit;
import com.github.reygrschel.voltaics.tileentity.TileEntityVoltaicPile;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVoltaicPile extends BlockTEBase {
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockVoltaicPile(Material material, String name, boolean addToTab) {
		super(material, name, addToTab);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	//Update connected neighbors on harvest
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player){
		if (world.getTileEntity(pos.up()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.up())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.down()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.down())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.north()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.north())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.south()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.south())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.west()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.west())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.east()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.east())).updateNeighbors(world);
		}
	}
    
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVoltaicPile();
	}

}
