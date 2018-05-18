package com.github.reygrschel.voltaics.power.implementation;

import com.github.reygrschel.voltaics.power.IEnergyConsumer;
import com.github.reygrschel.voltaics.power.IEnergyHolder;
import com.github.reygrschel.voltaics.power.IEnergyProducer;
import com.github.reygrschel.voltaics.power.IHeat;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * A basic Joule container that serves as a consumer, producer and holder. Custom
 * implementations do not need to use all three. The INBTSerializable interface is also
 * optional.
 */
public class BaseEnergyContainer implements IEnergyConsumer, IEnergyProducer, IEnergyHolder, IHeat, INBTSerializable<NBTTagCompound> {
    
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
    
    private double temperature;
    
    private double meltingPoint;
    
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
    public BaseEnergyContainer(long power, long capacity, long input, long output, double temperature, double meltingPoint) {
        
        this.stored = power;
        this.capacity = capacity;
        this.inputRate = input;
        this.outputRate = output;
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
    public BaseEnergyContainer(NBTTagCompound dataTag) {
        
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
    public long takePower (long Joule, boolean simulated) {
        
        final long removedPower = Math.min(this.stored, Math.min(this.getOutputRate(), Joule));
        
        if (!simulated)
            this.stored -= removedPower;
            
        return removedPower;
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
     * Sets the capacity of the the container. If the existing stored power is more than the
     * new capacity, the stored power will be decreased to match the new capacity.
     * 
     * @param capacity The new capacity for the container.
     * @return The instance of the container being updated.
     */
    public BaseEnergyContainer setCapacity (long capacity) {
        
        this.capacity = capacity;
        
        if (this.stored > capacity)
            this.stored = capacity;
            
        return this;
    }
    
    /**
     * Gets the maximum amount of Joule power that can be accepted by the container.
     * 
     * @return The amount of Joule power that can be accepted at any time.
     */
    public long getInputRate () {
        
        return this.inputRate;
    }
    
    /**
     * Sets the maximum amount of Joule power that can be accepted by the container.
     * 
     * @param rate The amount of Joule power to accept at a time.
     * @return The instance of the container being updated.
     */
    public BaseEnergyContainer setInputRate (long rate) {
        
        this.inputRate = rate;
        return this;
    }
    
    /**
     * Gets the maximum amount of Joule power that can be pulled from the container.
     * 
     * @return The amount of Joule power that can be extracted at any time.
     */
    public long getOutputRate () {
        
        return this.outputRate;
    }
    
    /**
     * Sets the maximum amount of Joule power that can be pulled from the container.
     * 
     * @param rate The amount of Joule power that can be extracted.
     * @return The instance of the container being updated.
     */
    public BaseEnergyContainer setOutputRate (long rate) {
        
        this.outputRate = rate;
        return this;
    }
    
    /**
     * Sets both the input and output rates of the container at the same time. Both rates will
     * be the same.
     * 
     * @param rate The input/output rate for the Joule container.
     * @return The instance of the container being updated.
     */
    public BaseEnergyContainer setTransferRate (long rate) {
        
        this.setInputRate(rate);
        this.setOutputRate(rate);
        return this;
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
    public double giveHeat (double heat, boolean simulated) {
        final double acceptedHeat = heat;
        
        if (!simulated)
            this.temperature += acceptedHeat;
            
        return acceptedHeat;
    }
    
	@Override
	public double takeHeat(double lostDegrees, boolean simulated) {
		final double takenHeat = lostDegrees;
		
		if (!simulated)
			this.temperature -= takenHeat;
		return takenHeat;
	}
}