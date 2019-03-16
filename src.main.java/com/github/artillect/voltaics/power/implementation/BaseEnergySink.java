package com.github.artillect.voltaics.power.implementation;

import com.github.artillect.voltaics.power.IHeat;
import com.github.artillect.voltaics.power.ISink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class BaseEnergySink implements ISink, IHeat, INBTSerializable<NBTTagCompound> {
    
	private double temperature;

	private double meltingTemperature;
	
	public double voltage;
	
    /**
     * Constructor for setting all of the base values, including the stored power.
     * 
     * @param power The amount of stored power to initialize the Machine with.
     * @param capacity The maximum amount of Joule power that the Machine should hold.
     * @param input The maximum rate of power that can be accepted at a time.
     * @param output The maximum rate of power that can be extracted at a time.
     * @param j 
     * @param i 
     */
    public BaseEnergySink(double meltingTemperature) {
        
        this.meltingTemperature = meltingTemperature;
    }
    
    /**
     * Constructor for creating an instance directly from a compound tag. This expects that the
     * compound tag has some of the required data. @See {@link #deserializeNBT(NBTTagCompound)}
     * for precise info on what is expected. This constructor will only set the stored power if
     * it has been written on the compound tag.
     * 
     * @param dataTag The NBTCompoundTag to read the important data from.
     */
    public BaseEnergySink(NBTTagCompound dataTag) {
        
        this.deserializeNBT(dataTag);
    }
    
    @Override
    public NBTTagCompound serializeNBT () {
        
        final NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setDouble("HeatTemperature", this.temperature);
        dataTag.setDouble("HeatMeltingPoint", this.meltingTemperature);
        
        return dataTag;
    }
    
    @Override
    public void deserializeNBT (NBTTagCompound nbt) {
        if (nbt.hasKey("HeatTemperature"))
            this.temperature = nbt.getDouble("HeatTemperature");
        
        if (nbt.hasKey("HeatMeltingPoint"))
            this.meltingTemperature = nbt.getDouble("HeatMeltingPoint");
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
		this.meltingTemperature += heat;
	}

}
