/*
 * package com.github.artillect.voltaics.tileentity;
 * 
 * import com.github.artillect.voltaics.power.implementation.BaseHeatMachine;
 * 
 * import net.minecraft.item.ItemStack; import
 * net.minecraft.tileentity.TileEntity; import net.minecraft.util.ITickable;
 * import net.minecraftforge.fluids.FluidRegistry; import
 * net.minecraftforge.fluids.FluidStack;
 * 
 * public class TileEntityDistiller extends TileEntity implements ITickable {
 * 
 * private FluidStack fluidContent; private FluidStack vaporContent; private
 * ItemStack inventory = ItemStack.EMPTY; private BaseHeatMachine container;
 * 
 * public TileEntityDistiller() { this.container = new BaseHeatMachine(20,
 * 1200); }
 * 
 * @Override public void update(){ if(world.isRemote){ return; }
 * 
 * if(fluidContent != null) { if (fluidContent.getFluid() == FluidRegistry.WATER
 * && this.container.getTemperature()>100) { //Do Fluid Processing Here,
 * hardcode for different fluids I guess } } } }
 */