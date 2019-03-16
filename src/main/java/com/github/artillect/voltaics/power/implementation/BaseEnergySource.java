package com.github.artillect.voltaics.power.implementation;

import com.github.artillect.voltaics.power.IEnergySink;
import com.github.artillect.voltaics.power.IEnergyPath;
import com.github.artillect.voltaics.power.IEnergySource;
import com.github.artillect.voltaics.power.IHeat;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * A basic Joule container that serves as a consumer, producer and holder. Custom
 * implementations do not need to use all three. The INBTSerializable interface is also
 * optional.
 */
public class BaseEnergySource implements IEnergySource, IHeat, INBTSerializable<NBTTagCompound> {
    
	private long voltage;
	
    private long storedPower;
    
    private long maxPower;
    
    private long temperature;
    
    private long meltingTemperature;

    public BaseEnergySource(long voltage, long stored, long maxPower, long temperature, long meltingPoint) {
        this.voltage = voltage;
    	this.storedPower = stored;
        this.maxPower = maxPower;
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
        dataTag.setLong("JouleVoltage", this.voltage);
        dataTag.setLong("HeatTemperature", this.temperature);
        dataTag.setLong("HeatMeltingTemperature", this.meltingTemperature);
        
        return dataTag;
    }
    
    @Override
    public void deserializeNBT (NBTTagCompound nbt) {
        
        this.storedPower = nbt.getLong("JoulePower");
        
        if (nbt.hasKey("JouleCapacity"))
            this.maxPower = nbt.getLong("JouleCapacity");
            
        if (nbt.hasKey("JouleVoltage"))
            this.maxPower = nbt.getLong("JouleVoltage");
        if (nbt.hasKey("JoulePower"))
            this.maxPower = nbt.getLong("JoulePower");
        
        if (this.storedPower > this.getMaxPower())
            this.storedPower = this.getMaxPower();
    }

	@Override
	public long getTemperature() {
		return this.temperature;
	}

	@Override
	public void addHeat(long heat) {
		this.temperature += heat;
	}

	@Override
	public void addPower(long power) {
		if (power > 0) {
			this.storedPower += Math.min(power, this.maxPower-this.storedPower);
		} else {
			this.storedPower += power;
		}
	}

	@Override
	public long getMaxPower() {
		return this.maxPower;
	}

	@Override
	public long getVoltage() {
		return this.voltage;
	}

	@Override
	public void setVoltage(long voltage) {
		this.voltage = voltage;
	}

	@Override
	public long getMeltingTemperature() {
		return this.meltingTemperature;
	}

	@Override
	public long getStoredPower() {
		return this.storedPower;
	}
}