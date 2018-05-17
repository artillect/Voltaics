package com.artillect.voltaics.tileentity;

import com.artillect.voltaics.capability.EnergyCapabilities;
import com.artillect.voltaics.capability.HeatCapabilities;
import com.artillect.voltaics.power.implementation.BaseHeatMachine;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityCoalGenerator extends TileEntity implements ITickable {
	private BaseHeatMachine container;
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.container = new BaseHeatMachine(compound.getCompoundTag("HeatContainer"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("JouleContainer", this.container.serializeNBT());
		return super.writeToNBT(compound);
	}
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {

        if (capability == EnergyCapabilities.CAPABILITY_CONSUMER || capability == EnergyCapabilities.CAPABILITY_HOLDER)
            return (T) this.container;
            
        return super.getCapability(capability, facing);
    }
    
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {

        if (capability == EnergyCapabilities.CAPABILITY_CONSUMER || capability == EnergyCapabilities.CAPABILITY_HOLDER || capability == HeatCapabilities.CAPABILITY_HEAT)
            return true;
            
        return super.hasCapability(capability, facing);
    }
}
