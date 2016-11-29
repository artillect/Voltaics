package com.artillect.voltaics.power;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class EnergyCapabilityProvider implements ICapabilityProvider {
	private IEnergyCapability capability = null;
	
	public EnergyCapabilityProvider(){
		capability = new DefaultEnergyCapability();
	}
	
	public EnergyCapabilityProvider(IEnergyCapability capability){
		this.capability = capability;
	}
	
	@CapabilityInject(IEnergyCapability.class)
	public static final Capability<IEnergyCapability> energyCapability = null;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == energyCapability;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
    	if (energyCapability != null && capability == energyCapability) return (T)capability;
    	return null;
	}
}
