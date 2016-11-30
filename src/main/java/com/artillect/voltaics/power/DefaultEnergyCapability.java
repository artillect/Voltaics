package com.artillect.voltaics.power;

import com.artillect.voltaics.Voltaics;

import net.minecraft.nbt.NBTTagCompound;

public class DefaultEnergyCapability implements IEnergyCapability {
	private int energy = 0;
	private int capacity = 0;
	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public int getEnergyCapacity() {
		return capacity;
	}

	@Override
	public void setEnergy(int value) {
		energy = value;
	}

	@Override
	public void setEnergyCapacity(int value) {
		capacity = value;
	}

	@Override
	public int addAmount(int value, boolean doAdd) {
		if (energy+value > capacity){
			int added = capacity-energy;
			if (doAdd){
				energy = capacity;
			}
			return added;
		}
		if (doAdd){
			energy += value;
		}
		return value;
	}

	@Override
	public int removeAmount(int value, boolean doRemove) {
		if (energy-value < 0){
			int removed = energy;
			if (doRemove){
				energy = 0;
			}
			return removed;
		}
		if (doRemove){
			energy -= value;
		}
		return value;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setDouble(Voltaics.modId+":energy", energy);
		tag.setDouble(Voltaics.modId+":energyCapacity", capacity);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		if (tag.hasKey(Voltaics.modId+":energy")){
			energy = (int) tag.getDouble(Voltaics.modId+":energy");
		}
		if (tag.hasKey(Voltaics.modId+":energyCapacity")){
			capacity = (int) tag.getDouble(Voltaics.modId+":energyCapacity");
		}
	}
}
