package com.github.artillect.voltaics.power.implementation;

import java.util.HashMap;
import java.util.Map.Entry;
import com.github.artillect.voltaics.capability.Capabilities;
import com.github.artillect.voltaics.power.IEnergyPath;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.INBTSerializable;

public class BaseEnergyPath implements IEnergyPath, INBTSerializable<NBTTagCompound> {

	private long temperature;

	private long meltingPoint;
    
    HashMap<TileEntity, EnumFacing> voltageSourceList;
	
    public BaseEnergyPath(long temperature, long meltingPoint) {
        
        this.temperature = temperature;
        this.meltingPoint = meltingPoint;
    }

    public BaseEnergyPath(NBTTagCompound dataTag) {
        
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

	@Override
	public long getVoltage() {
		long voltage = 0;
		
		if (this.voltageSourceList == null) return 0;
		
		for (Entry<TileEntity, EnumFacing> entry : this.voltageSourceList.entrySet()) {
			TileEntity tile = entry.getKey();
			EnumFacing facing = entry.getValue();
			voltage += tile.getCapability(Capabilities.CAPABILITY_SOURCE, facing).getVoltage();
		}
		return voltage;
	}

	@Override
	public HashMap<TileEntity, EnumFacing> getVoltageSourceList() {
		return this.voltageSourceList;
	}

	@Override
	public void addToVoltageSourceList(TileEntity tile, EnumFacing facing) {
		this.voltageSourceList.put(tile, facing);
	}

	@Override
	public void removeFromVoltageSourceList(TileEntity tile, EnumFacing facing) {
		if (this.voltageSourceList.get(tile) != null) {
			this.voltageSourceList.remove(tile);
		}
	}


}
