package com.github.artillect.voltaics.capability;

import com.github.artillect.voltaics.power.IHeat;
import com.github.artillect.voltaics.power.IPath;
import com.github.artillect.voltaics.power.ISink;
import com.github.artillect.voltaics.power.ISource;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class Capabilities {
	
    @CapabilityInject(ISource.class)
    public static Capability<ISource> CAPABILITY_SOURCE = null;
    
    @CapabilityInject(IPath.class)
    public static Capability<IPath> CAPABILITY_PATH = null;
    
    @CapabilityInject(ISink.class)
    public static Capability<ISink> CAPABILITY_SINK = null;

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
    
    public static class CapabilitySource<T extends ISource> implements IStorage<ISource> {
        
        @Override
        public NBTBase writeNBT (Capability<ISource> capability, ISource instance, EnumFacing side) {
            
            return null;
        }
        
        @Override
        public void readNBT (Capability<ISource> capability, ISource instance, EnumFacing side, NBTBase nbt) {
        
        }
    }
    
    public static class CapabilityPath<T extends IPath> implements IStorage<IPath> {
        
        @Override
        public NBTBase writeNBT (Capability<IPath> capability, IPath instance, EnumFacing side) {
            
            return null;
        }
        
        @Override
        public void readNBT (Capability<IPath> capability, IPath instance, EnumFacing side, NBTBase nbt) {
        
        }
    }
    
    public static class CapabilitySink<T extends ISink> implements IStorage<ISink> {
        
        @Override
        public NBTBase writeNBT (Capability<ISink> capability, ISink instance, EnumFacing side) {
            
            return null;
        }
        
        @Override
        public void readNBT (Capability<ISink> capability, ISink instance, EnumFacing side, NBTBase nbt) {
        
        }
    }
    
    /*

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
    } */
}