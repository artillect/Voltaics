package com.github.artillect.voltaics.power.implementation;

import java.util.HashMap;
import java.util.Map.Entry;
import com.github.artillect.voltaics.capability.Capabilities;
import com.github.artillect.voltaics.power.IHeat;
import com.github.artillect.voltaics.power.IPath;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * A basic Joule container that serves as a consumer, producer and holder. Custom
 * implementations do not need to use all three. The INBTSerializable interface is also
 * optional.
 */
public class BaseEnergyPath implements IPath, IHeat, INBTSerializable<NBTTagCompound> {
        
    private double temperature;
    
    private double meltingTemperature;

	public HashMap<TileEntity, EnumFacing> voltageSourceList;

    public BaseEnergyPath(double meltingPoint) {
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
    public BaseEnergyPath(NBTTagCompound dataTag) {
        
        this.deserializeNBT(dataTag);
    }
    
    @Override
    public NBTTagCompound serializeNBT () {
        
        final NBTTagCompound dataTag = new NBTTagCompound();
        
        return dataTag;
    }
    
    @Override
    public void deserializeNBT (NBTTagCompound nbt) {
               
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
	public HashMap<TileEntity, EnumFacing> getVoltageSourceList() {
		return voltageSourceList;
	}

	@Override
	public long getVoltage() {
		long voltage = 0;
		for (Entry<TileEntity, EnumFacing> entry : voltageSourceList.entrySet()) {
			TileEntity tile = entry.getKey();
			EnumFacing facing = entry.getValue();
			voltage += tile.getCapability(Capabilities.CAPABILITY_SOURCE, facing).getVoltage();
		}
		return voltage;
	}

}