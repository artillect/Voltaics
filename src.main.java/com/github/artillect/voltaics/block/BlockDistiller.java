package com.github.artillect.voltaics.block;

import com.github.artillect.voltaics.tileentity.TileEntityDistiller;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDistiller extends BlockTEBase {

	public BlockDistiller(Material material, String name, boolean addToTab) {
		super(Material.ROCK, "distiller", true);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityDistiller();
	}
}
