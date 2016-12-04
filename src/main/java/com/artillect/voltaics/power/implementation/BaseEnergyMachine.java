package com.artillect.voltaics.power.implementation;

import com.artillect.voltaics.power.IEnergyConsumer;
import com.artillect.voltaics.power.IEnergyHolder;
import com.artillect.voltaics.power.IEnergyProducer;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class BaseEnergyMachine implements IEnergyConsumer, IEnergyHolder, INBTSerializable<NBTTagCompound> {
    
    /**
     * The amount of stored Joule power.
     */
    private long stored;
    
    /**
     * The maximum amount of Joule power that can be stored.
     */
    private long capacity;
    
    /**
     * The maximum amount of Joule power that can be accepted.
     */
    private long inputRate;
    
    /**
     * The maximum amount of Joule power that can be extracted
     */
    private long outputRate;
    
    /**
     * Default constructor. Sets capacity to 5000 and transfer rate to 50. This constructor
     * will not set the amount of stored power. These values are arbitrary and should not be
     * taken as a base line for balancing.
     */
    public BaseEnergyMachine() {
        
        this(5000, 50, 50);
    }
    
    /**
     * Constructor for setting the basic values. Will not construct with any stored power.
     * 
     * @param capacity The maximum amount of Joule power that the Machine should hold.
     * @param input The maximum rate of power that can be accepted at a time.
     * @param output The maximum rate of power that can be extracted at a time.
     */
    public BaseEnergyMachine(long capacity, long input, long output) {
        
        this(0, capacity, input, output);
    }
    
    /**
     * Constructor for setting all of the base values, including the stored power.
     * 
     * @param power The amount of stored power to initialize the Machine with.
     * @param capacity The maximum amount of Joule power that the Machine should hold.
     * @param input The maximum rate of power that can be accepted at a time.
     * @param output The maximum rate of power that can be extracted at a time.
     */
    public BaseEnergyMachine(long power, long capacity, long input, long output) {
        
        this.stored = power;
        this.capacity = capacity;
        this.inputRate = input;
        this.outputRate = output;
    }
    
    /**
     * Constructor for creating an instance directly from a compound tag. This expects that the
     * compound tag has some of the required data. @See {@link #deserializeNBT(NBTTagCompound)}
     * for precise info on what is expected. This constructor will only set the stored power if
     * it has been written on the compound tag.
     * 
     * @param dataTag The NBTCompoundTag to read the important data from.
     */
    public BaseEnergyMachine(NBTTagCompound dataTag) {
        
        this.deserializeNBT(dataTag);
    }
    
    @Override
    public long getStoredPower () {
        
        return this.stored;
    }
    
    @Override
    public long givePower (long Joule, boolean simulated) {
        
        final long acceptedJoule = Math.min(this.getCapacity() - this.stored, Math.min(this.getInputRate(), Joule));
        
        if (!simulated)
            this.stored += acceptedJoule;
            
        return acceptedJoule;
    }
   

    @Override
    public long getCapacity () {
        
        return this.capacity;
    }
    
    @Override
    public NBTTagCompound serializeNBT () {
        
        final NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setLong("JoulePower", this.stored);
        dataTag.setLong("JouleCapacity", this.capacity);
        dataTag.setLong("JouleInput", this.inputRate);
        dataTag.setLong("JouleOutput", this.outputRate);
        
        return dataTag;
    }
    
    @Override
    public void deserializeNBT (NBTTagCompound nbt) {
        
        this.stored = nbt.getLong("JoulePower");
        
        if (nbt.hasKey("JouleCapacity"))
            this.capacity = nbt.getLong("JouleCapacity");
            
        if (nbt.hasKey("JouleInput"))
            this.inputRate = nbt.getLong("JouleInput");
            
        if (nbt.hasKey("JouleOutput"))
            this.outputRate = nbt.getLong("JouleOutput");
            
        if (this.stored > this.getCapacity())
            this.stored = this.getCapacity();
    }
    
    /**
     * Sets the capacity of the the Machine. If the existing stored power is more than the
     * new capacity, the stored power will be decreased to match the new capacity.
     * 
     * @param capacity The new capacity for the Machine.
     * @return The instance of the Machine being updated.
     */
    public BaseEnergyMachine setCapacity (long capacity) {
        
        this.capacity = capacity;
        
        if (this.stored > capacity)
            this.stored = capacity;
            
        return this;
    }
    
    /**
     * Gets the maximum amount of Joule power that can be accepted by the Machine.
     * 
     * @return The amount of Joule power that can be accepted at any time.
     */
    public long getInputRate () {
        
        return this.inputRate;
    }
    
    /**
     * Sets the maximum amount of Joule power that can be accepted by the Machine.
     * 
     * @param rate The amount of Joule power to accept at a time.
     * @return The instance of the Machine being updated.
     */
    public BaseEnergyMachine setInputRate (long rate) {
        
        this.inputRate = rate;
        return this;
    }
    
    /**
     * Gets the maximum amount of Joule power that can be pulled from the Machine.
     * 
     * @return The amount of Joule power that can be extracted at any time.
     */
    public long getOutputRate () {
        
        return this.outputRate;
    }
    
    /**
     * Sets the maximum amount of Joule power that can be pulled from the Machine.
     * 
     * @param rate The amount of Joule power that can be extracted.
     * @return The instance of the Machine being updated.
     */
    public BaseEnergyMachine setOutputRate (long rate) {
        
        this.outputRate = rate;
        return this;
    }
    
    /**
     * Sets both the input and output rates of the Machine at the same time. Both rates will
     * be the same.
     * 
     * @param rate The input/output rate for the Joule Machine.
     * @return The instance of the Machine being updated.
     */
    public BaseEnergyMachine setTransferRate (long rate) {
        
        this.setInputRate(rate);
        this.setOutputRate(rate);
        return this;
    }

}
