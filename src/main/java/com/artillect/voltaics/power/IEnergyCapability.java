package com.artillect.voltaics.power;

import net.minecraft.nbt.NBTTagCompound;

public interface IEnergyCapability {
	public int getEnergy();
	public int getEnergyCapacity();
	public void setEnergy(int value);
	public void setEnergyCapacity(int value);
	public int addAmount(int value, boolean doAdd);
	public int removeAmount(int value, boolean doRemove);
	public void writeToNBT(NBTTagCompound tag);
	public void readFromNBT(NBTTagCompound tag);
}
