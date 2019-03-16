package com.github.artillect.voltaics.power.implementation;

import com.github.artillect.voltaics.power.IEnergySink;
import com.github.artillect.voltaics.power.IEnergyPath;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class BaseEnergySink implements IEnergySink, INBTSerializable<NBTTagCompound> {

	private long temperature;

	private long meltingPoint;
    
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
    public BaseEnergySink(long temperature, long meltingPoint) {
        
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
    public BaseEnergySink(NBTTagCompound dataTag) {
        
        this.deserializeNBT(dataTag);
    }
    
    @Override
    public NBTTagCompound serializeNBT () {
        
        final NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setLong("HeatTemperature", this.temperature);
        dataTag.setLong("HeatMeltingPoint", this.meltingPoint);
        
        return dataTag;
    }
    
    @Override
    public void deserializeNBT (NBTTagCompound nbt) {

        if (nbt.hasKey("HeatTemperature"))
            this.temperature = nbt.getLong("HeatTemperature");
        
        if (nbt.hasKey("HeatMeltingPoint"))
            this.meltingPoint = nbt.getLong("HeatMeltingPoint");

    }

}
