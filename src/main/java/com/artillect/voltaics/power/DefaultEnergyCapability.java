package com.artillect.voltaics.power;

import com.artillect.voltaics.Voltaics;

import net.minecraft.nbt.NBTTagCompound;

public class DefaultEnergyCapability implements IEnergyCapability {
	private double energy = 0;
	private double capacity = 0;
	@Override
	public double getEnergy() {
		return energy;
	}

	@Override
	public double getEnergyCapacity() {
		return capacity;
	}

	@Override
	public void setEnergy(double value) {
		energy = value;
	}

	@Override
	public void setEnergyCapacity(double value) {
		capacity = value;
	}

	@Override
	public double addAmount(double value, boolean doAdd) {
		if (energy+value > capacity){
			double added = capacity-energy;
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
	public double removeAmount(double value, boolean doRemove) {
		if (energy-value < 0){
			double removed = energy;
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
			energy = tag.getDouble(Voltaics.modId+":energy");
		}
		if (tag.hasKey(Voltaics.modId+":energyCapacity")){
			capacity = tag.getDouble(Voltaics.modId+":energyCapacity");
		}
	}
}
