package com.github.reygrschel.voltaics.capability;

import com.github.reygrschel.voltaics.power.IHeat;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class HeatCapabilities {
    /**
     * Access to the Heat capability. Can be used for making checks.
     */
    @CapabilityInject(IHeat.class)
    public static Capability<IHeat> CAPABILITY_HEAT = null;
    
    public static class CapabilityHeat<T extends IHeat> implements IStorage<IHeat> {
        
        @Override
        public NBTBase writeNBT (Capability<IHeat> capability, IHeat instance, EnumFacing side) {
            
            return null;
        }
        
        @Override
        public void readNBT (Capability<IHeat> capability, IHeat instance, EnumFacing side, NBTBase nbt) {
        
        }
    }
}