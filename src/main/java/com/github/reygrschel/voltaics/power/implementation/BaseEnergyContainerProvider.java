package com.github.reygrschel.voltaics.power.implementation;

import com.github.reygrschel.voltaics.capability.Capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * A capability provider for the base Joule container implementation.
 */
public class BaseEnergyContainerProvider implements INBTSerializable<NBTTagCompound>, ICapabilityProvider {
    
    /**
     * The capability being provided.
     */
    private final BaseEnergyContainer container;
    
    /**
     * Constructor for setting the BaseJouleContainer for the provider to provide.
     * 
     * @param container The BaseJouleContainer to provide.
     */
    public BaseEnergyContainerProvider(BaseEnergyContainer container) {
        
        this.container = container;
    }
    
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {
        
        return capability == Capabilities.CAPABILITY_CONSUMER || capability == Capabilities.CAPABILITY_PRODUCER || capability == Capabilities.CAPABILITY_HOLDER;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {
        
        if (capability == Capabilities.CAPABILITY_CONSUMER || capability == Capabilities.CAPABILITY_PRODUCER || capability == Capabilities.CAPABILITY_HOLDER)
            return (T) this.container;
            
        return null;
    }
    
    @Override
    public NBTTagCompound serializeNBT () {
        
        return this.container.serializeNBT();
    }
    
    @Override
    public void deserializeNBT (NBTTagCompound nbt) {
        
        this.container.deserializeNBT(nbt);
    }
}