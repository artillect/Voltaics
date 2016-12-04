package com.artillect.voltaics.power.implementation;

import com.artillect.voltaics.capability.EnergyCapabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class BaseEnergyMachineProvider implements INBTSerializable<NBTTagCompound>, ICapabilityProvider{

    
    /**
     * The capability being provided.
     */
    private final BaseEnergyMachine Machine;
    
    /**
     * Constructor for setting the BaseJouleMachine for the provider to provide.
     * 
     * @param Machine The BaseJouleMachine to provide.
     */
    public BaseEnergyMachineProvider(BaseEnergyMachine Machine) {
        
        this.Machine = Machine;
    }
    
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {
        
        return capability == EnergyCapabilities.CAPABILITY_CONSUMER || capability == EnergyCapabilities.CAPABILITY_PRODUCER || capability == EnergyCapabilities.CAPABILITY_HOLDER;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {
        
        if (capability == EnergyCapabilities.CAPABILITY_CONSUMER || capability == EnergyCapabilities.CAPABILITY_PRODUCER || capability == EnergyCapabilities.CAPABILITY_HOLDER)
            return (T) this.Machine;
            
        return null;
    }
    
    @Override
    public NBTTagCompound serializeNBT () {
        
        return this.Machine.serializeNBT();
    }
    
    @Override
    public void deserializeNBT (NBTTagCompound nbt) {
        
        this.Machine.deserializeNBT(nbt);
    }

}
