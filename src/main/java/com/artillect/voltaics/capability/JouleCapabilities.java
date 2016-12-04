package com.artillect.voltaics.capability;

import com.artillect.voltaics.power.IJouleConsumer;
import com.artillect.voltaics.power.IJouleHolder;
import com.artillect.voltaics.power.IJouleProducer;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class JouleCapabilities {
    
    /**
     * Access to the consumer capability. Can be used for making checks.
     */
    @CapabilityInject(IJouleConsumer.class)
    public static Capability<IJouleConsumer> CAPABILITY_CONSUMER = null;
    
    /**
     * Access to the producer capability. Can be used for making checks.
     */
    @CapabilityInject(IJouleProducer.class)
    public static Capability<IJouleProducer> CAPABILITY_PRODUCER = null;
    
    /**
     * Access to the holder capability. Can be used for making checks.
     */
    @CapabilityInject(IJouleHolder.class)
    public static Capability<IJouleHolder> CAPABILITY_HOLDER = null;
    
    public static class CapabilityJouleConsumer<T extends IJouleConsumer> implements IStorage<IJouleConsumer> {
        
        @Override
        public NBTBase writeNBT (Capability<IJouleConsumer> capability, IJouleConsumer instance, EnumFacing side) {
            
            return null;
        }
        
        @Override
        public void readNBT (Capability<IJouleConsumer> capability, IJouleConsumer instance, EnumFacing side, NBTBase nbt) {
        
        }
    }
    
    public static class CapabilityJouleProducer<T extends IJouleProducer> implements IStorage<IJouleProducer> {
        
        @Override
        public NBTBase writeNBT (Capability<IJouleProducer> capability, IJouleProducer instance, EnumFacing side) {
            
            return null;
        }
        
        @Override
        public void readNBT (Capability<IJouleProducer> capability, IJouleProducer instance, EnumFacing side, NBTBase nbt) {
        
        }
    }
    
    public static class CapabilityJouleHolder<T extends IJouleHolder> implements IStorage<IJouleHolder> {
        
        @Override
        public NBTBase writeNBT (Capability<IJouleHolder> capability, IJouleHolder instance, EnumFacing side) {
            
            return null;
        }
        
        @Override
        public void readNBT (Capability<IJouleHolder> capability, IJouleHolder instance, EnumFacing side, NBTBase nbt) {
        
        }
    }
}