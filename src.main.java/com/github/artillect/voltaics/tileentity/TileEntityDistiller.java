package com.github.artillect.voltaics.tileentity;

import com.github.artillect.voltaics.power.implementation.BaseEnergySink;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityDistiller extends TileEntity implements ITickable {
	
	private FluidStack fluidContent;
	private FluidStack vaporContent;
	private ItemStack inventory = ItemStack.EMPTY;
	private BaseEnergySink container;
	
	public TileEntityDistiller() {
		this.container = new BaseEnergySink(1200);
	}
	
	@Override
	public void update(){

	}
}
