package com.artillect.voltaics.power;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class EnergyCapabilityStorage implements IStorage<IEnergyCapability> {

	@Override
	public NBTBase writeNBT(Capability<IEnergyCapability> capability, IEnergyCapability instance, EnumFacing side) {
		return null;
	}

	@Override
	public void readNBT(Capability<IEnergyCapability> capability, IEnergyCapability instance, EnumFacing side,
			NBTBase nbt) {
		
	}

}
