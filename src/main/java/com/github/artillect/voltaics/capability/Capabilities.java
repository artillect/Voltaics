//package com.github.artillect.voltaics.capability;
//
//import com.github.artillect.voltaics.power.IEnergySink;
//import com.github.artillect.voltaics.power.IEnergyPath;
//import com.github.artillect.voltaics.power.IEnergySource;
//import com.github.artillect.voltaics.power.IHeat;
//
//import net.minecraft.nbt.NBTBase;
//import net.minecraft.util.EnumFacing;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.capabilities.Capability.IStorage;
//import net.minecraftforge.common.capabilities.CapabilityInject;
//
//public class Capabilities {
//    
//    @CapabilityInject(IEnergySink.class)
//    public static Capability<IEnergySink> CAPABILITY_SINK = null;
// 
//    @CapabilityInject(IEnergySource.class)
//    public static Capability<IEnergySource> CAPABILITY_SOURCE = null;
//    
//    @CapabilityInject(IEnergyPath.class)
//    public static Capability<IEnergyPath> CAPABILITY_PATH = null;
//    
//    @CapabilityInject(IHeat.class)
//    public static Capability<IHeat> CAPABILITY_HEAT = null;
//    
//    public static class CapabilityHeat<T extends IHeat> implements IStorage<IHeat> {
//        
//        @Override
//        public NBTBase writeNBT (Capability<IHeat> capability, IHeat instance, EnumFacing side) {
//            
//            return null;
//        }
//        
//        @Override
//        public void readNBT (Capability<IHeat> capability, IHeat instance, EnumFacing side, NBTBase nbt) {
//        
//        }
//    }
//    
//    //Capability Magic
//    public static class CapabilityJouleSink<T extends IEnergySink> implements IStorage<IEnergySink> {
//        
//        @Override
//        public NBTBase writeNBT (Capability<IEnergySink> capability, IEnergySink instance, EnumFacing side) {
//            
//            return null;
//        }
//        
//        @Override
//        public void readNBT (Capability<IEnergySink> capability, IEnergySink instance, EnumFacing side, NBTBase nbt) {
//        
//        }
//    }
//    
//    public static class CapabilityJouleSOURCE<T extends IEnergySource> implements IStorage<IEnergySource> {
//        
//        @Override
//        public NBTBase writeNBT (Capability<IEnergySource> capability, IEnergySource instance, EnumFacing side) {
//            
//            return null;
//        }
//        
//        @Override
//        public void readNBT (Capability<IEnergySource> capability, IEnergySource instance, EnumFacing side, NBTBase nbt) {
//        
//        }
//    }
//    
//    public static class CapabilityJoulePath<T extends IEnergyPath> implements IStorage<IEnergyPath> {
//        
//        @Override
//        public NBTBase writeNBT (Capability<IEnergyPath> capability, IEnergyPath instance, EnumFacing side) {
//            
//            return null;
//        }
//        
//        @Override
//        public void readNBT (Capability<IEnergyPath> capability, IEnergyPath instance, EnumFacing side, NBTBase nbt) {
//        
//        }
//    }
//}