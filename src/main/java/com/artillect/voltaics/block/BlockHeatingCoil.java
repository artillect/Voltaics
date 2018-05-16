package com.artillect.voltaics.block;

import com.artillect.voltaics.tileentity.TileEntityHeatingCoil;

//import com.artillect.voltaics.tileentity.TileEntityInductor;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHeatingCoil extends BlockTEBase{

	public BlockHeatingCoil(Material material, String name, boolean addToTab) {
		super(material, name, addToTab);
		
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityHeatingCoil();
	}

}
