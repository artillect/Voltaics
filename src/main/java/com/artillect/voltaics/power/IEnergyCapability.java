package com.artillect.voltaics.power;

import net.minecraft.nbt.NBTTagCompound;

public interface IEnergyCapability {
	public double getEnergy();
	public double getEnergyCapacity();
	public void setEnergy(double value);
	public void setEnergyCapacity(double value);
	public double addAmount(double value, boolean doAdd);
	public double removeAmount(double value, boolean doRemove);
	public void writeToNBT(NBTTagCompound tag);
	public void readFromNBT(NBTTagCompound tag);
}
