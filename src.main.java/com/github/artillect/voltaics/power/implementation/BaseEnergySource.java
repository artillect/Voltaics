package com.github.artillect.voltaics.power.implementation;

import com.github.artillect.voltaics.power.IHeat;
import com.github.artillect.voltaics.power.ISource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * A basic Joule container that serves as a consumer, producer and holder. Custom
 * implementations do not need to use all three. The INBTSerializable interface is also
 * optional.
 */
public class BaseEnergySource implements ISource, IHeat, INBTSerializable<NBTTagCompound> {
    
    /**
     * The amount of stored Joule power.
     */
    private long storedPower;
    
    private long voltage;
    /**
     * The maximum amount of Joule power that can be stored.
     */
    private long maxPower;
    
    private double temperature;
    
    private double meltingTemperature;
    
    /**
     * Constructor for setting all of the base values, including the stored power.
     * 
     * @param power The amount of stored power to initialize the container with.
     * @param capacity The maximum amount of Joule power that the container should hold.
     * @param input The maximum rate of power that can be accepted at a time.
     * @param output The maximum rate of power that can be extracted at a time.
     * @param j 
     * @param i 
     * @param j 
     * @param i 
     */
    public BaseEnergySource(long voltage, long power, long capacity, double temperature, double meltingPoint) {
        
    	this.voltage = voltage;
        this.storedPower = power;
        this.maxPower = capacity;
        this.temperature = temperature;
        this.meltingTemperature = meltingPoint;
    }
    
    /**
     * Constructor for creating an instance directly from a compound tag. This expects that the
     * compound tag has some of the required data. @See {@link #deserializeNBT(NBTTagCompound)}
     * for precise info on what is expected. This constructor will only set the stored power if
     * it has been written on the compound tag.
     * 
     * @param dataTag The NBTCompoundTag to read the important data from.
     */
    public BaseEnergySource(NBTTagCompound dataTag) {
        
        this.deserializeNBT(dataTag);
    }
    
    @Override
    public NBTTagCompound serializeNBT () {
        
        final NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setLong("JoulePower", this.storedPower);
        dataTag.setLong("JouleCapacity", this.maxPower);
        
        return dataTag;
    }
    
    @Override
    public void deserializeNBT (NBTTagCompound nbt) {
        
        this.storedPower = nbt.getLong("JoulePower");
        
        if (nbt.hasKey("JouleCapacity"))
            this.maxPower = nbt.getLong("JouleCapacity");
            
        if (this.storedPower > this.getMaxPower())
            this.storedPower = this.getMaxPower();
    }

	@Override
	public double getTemperature() {
		return temperature;
	}

	@Override
	public double getMeltingTemperature() {
		return meltingTemperature;
	}

	@Override
	public void addTemperature(double heat) {
        this.temperature += heat;
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}

	@Override
	public long getStoredPower() {
		return storedPower;
	}

    @Override
    public long addStoredPower (long Joule, boolean simulated) {
        
        final long acceptedJoule = Math.min(this.getMaxPower() - this.getStoredPower(), Joule);
        
        if (!simulated)
            this.storedPower += acceptedJoule;
            
        return acceptedJoule;
    }

	@Override
	public long getVoltage() {
		return voltage;
	}

	@Override
	public void setVoltage(long voltage) {
		this.voltage = voltage;
	}
 
}