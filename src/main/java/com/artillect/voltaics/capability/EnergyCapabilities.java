package com.artillect.voltaics.capability;

import com.artillect.voltaics.power.IEnergyConsumer;
import com.artillect.voltaics.power.IEnergyHolder;
import com.artillect.voltaics.power.IEnergyProducer;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class EnergyCapabilities {
    
    /**
     * Access to the consumer capability. Can be used for making checks.
     */
    @CapabilityInject(IEnergyConsumer.class)
    public static Capability<IEnergyConsumer> CAPABILITY_CONSUMER = null;
    
    /**
     * Access to the producer capability. Can be used for making checks.
     */
    @CapabilityInject(IEnergyProducer.class)
    public static Capability<IEnergyProducer> CAPABILITY_PRODUCER = null;
    
    /**
     * Access to the holder capability. Can be used for making checks.
     */
    @CapabilityInject(IEnergyHolder.class)
    public static Capability<IEnergyHolder> CAPABILITY_HOLDER = null;
    
    //Capability Magic
    public static class CapabilityJouleConsumer<T extends IEnergyConsumer> implements IStorage<IEnergyConsumer> {
        
        @Override
        public NBTBase writeNBT (Capability<IEnergyConsumer> capability, IEnergyConsumer instance, EnumFacing side) {
            
            return null;
        }
        
        @Override
        public void readNBT (Capability<IEnergyConsumer> capability, IEnergyConsumer instance, EnumFacing side, NBTBase nbt) {
        
        }
    }
    
    public static class CapabilityJouleProducer<T extends IEnergyProducer> implements IStorage<IEnergyProducer> {
        
        @Override
        public NBTBase writeNBT (Capability<IEnergyProducer> capability, IEnergyProducer instance, EnumFacing side) {
            
            return null;
        }
        
        @Override
        public void readNBT (Capability<IEnergyProducer> capability, IEnergyProducer instance, EnumFacing side, NBTBase nbt) {
        
        }
    }
    
    public static class CapabilityJouleHolder<T extends IEnergyHolder> implements IStorage<IEnergyHolder> {
        
        @Override
        public NBTBase writeNBT (Capability<IEnergyHolder> capability, IEnergyHolder instance, EnumFacing side) {
            
            return null;
        }
        
        @Override
        public void readNBT (Capability<IEnergyHolder> capability, IEnergyHolder instance, EnumFacing side, NBTBase nbt) {
        
        }
    }
}