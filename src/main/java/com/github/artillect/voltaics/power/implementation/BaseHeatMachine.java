package com.github.artillect.voltaics.power.implementation;

import com.github.artillect.voltaics.power.IHeat;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * A basic Joule container that serves as a consumer, producer and holder. Custom
 * implementations do not need to use all three. The INBTSerializable interface is also
 * optional.
 */
public class BaseHeatMachine implements IHeat, INBTSerializable<NBTTagCompound> {
    
    private double temperature;
    
    private double meltingPoint;
    
    /**
     * Constructor for setting all of the base values, including the stored power.
     * 
     * @param power The amount of stored power to initialize the container with.
     * @param capacity The maximum amount of Joule power that the container should hold.
     * @param input The maximum rate of power that can be accepted at a time.
     * @param output The maximum rate of power that can be extracted at a time.
     */
    public BaseHeatMachine(double temperature, double meltingPoint) {
        this.temperature = temperature;
        this.meltingPoint = meltingPoint;
    }
    
    /**
     * Constructor for creating an instance directly from a compound tag. This expects that the
     * compound tag has some of the required data. @See {@link #deserializeNBT(NBTTagCompound)}
     * for precise info on what is expected. This constructor will only set the stored power if
     * it has been written on the compound tag.
     * 
     * @param dataTag The NBTCompoundTag to read the important data from.
     */
    public BaseHeatMachine(NBTTagCompound dataTag) {
        
        this.deserializeNBT(dataTag);
    }
    
    @Override
    public double getTemperature () {
    	return this.temperature;
    }
    
    @Override
    public double getMeltingPoint () {
    	return this.meltingPoint;
    }
    
    @Override
    public double addHeat (double heat, boolean simulated) {
        final double acceptedHeat = heat;
        
        if (!simulated)
            this.temperature += acceptedHeat;
            
        return acceptedHeat;
    }
    
    @Override
    public NBTTagCompound serializeNBT () {
        
        final NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setDouble("HeatTemperature", this.temperature);
        dataTag.setDouble("HeatMeltingPoint", this.meltingPoint);
        
        return dataTag;
    }
    
    @Override
    public void deserializeNBT (NBTTagCompound nbt) {
        
        if (nbt.hasKey("HeatTemperature"))
            this.temperature = nbt.getDouble("HeatTemperature");
        
        if (nbt.hasKey("HeatMeltingPoint"))
            this.meltingPoint = nbt.getDouble("HeatMeltingPoint");
    }
}