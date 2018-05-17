package com.artillect.voltaics.block;

import com.artillect.voltaics.GuiHandler;
import com.artillect.voltaics.Voltaics;
import com.artillect.voltaics.tileentity.TileEntityHeatingChamber;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHeatingChamber extends BlockTEBase {

	public BlockHeatingChamber(Material material, String name, boolean addToTab) {
		super(material, name, addToTab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityHeatingChamber();
	}
	
	//Open Heating Chamber gui when right-clicked
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.isSneaking()) {
			if (!world.isRemote) {
					player.openGui(Voltaics.instance, GuiHandler.HEATINGCHAMBER, world, pos.getX(), pos.getY(), pos.getZ());
				}
			return true;
		}
		return true;
	}
}
