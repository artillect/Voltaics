package com.github.artillect.voltaics.capability;

import com.github.artillect.voltaics.power.IEnergyConsumer;
import com.github.artillect.voltaics.power.IEnergyHolder;
import com.github.artillect.voltaics.power.IEnergyProducer;
import com.github.artillect.voltaics.power.IHeat;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class Capabilities {
    
    @CapabilityInject(IEnergyConsumer.class)
    public static Capability<IEnergyConsumer> CAPABILITY_CONSUMER = null;
 
    @CapabilityInject(IEnergyProducer.class)
    public static Capability<IEnergyProducer> CAPABILITY_PRODUCER = null;
    
    @CapabilityInject(IEnergyHolder.class)
    public static Capability<IEnergyHolder> CAPABILITY_HOLDER = null;
    
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