package com.github.artillect.voltaics.block;

import net.minecraft.block.material.Material;

public class BlockDistiller extends BlockTEBase {

	public BlockDistiller(Material material, String name, boolean addToTab) {
		super(Material.ROCK, "distiller", true);
	}
	
	/*
	 * @Override public TileEntity createNewTileEntity(World worldIn, int meta) {
	 * return new TileEntityDistiller(); }
	 */
}
