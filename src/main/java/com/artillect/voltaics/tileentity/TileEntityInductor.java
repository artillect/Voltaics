package com.artillect.voltaics.tileentity;

import com.artillect.voltaics.capability.EnergyCapabilities;
import com.artillect.voltaics.lib.JouleUtils;
import com.artillect.voltaics.power.implementation.BaseEnergyContainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityInductor extends TileEntity implements ITickable {

	private BaseEnergyContainer container;
	
	public TileEntityInductor() {
		this.container = new BaseEnergyContainer(0, 1000, 50, 50);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.container = new BaseEnergyContainer(compound.getCompoundTag("JouleContainer"));
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

        if (capability == EnergyCapabilities.CAPABILITY_CONSUMER || capability == EnergyCapabilities.CAPABILITY_HOLDER)
            return true;
            
        return super.hasCapability(capability, facing);
    }

    @Override
    public void update() {
    	this.container.givePower(JouleUtils.consumePowerFromAllFaces(this.getWorld(), pos, Math.min(this.container.getCapacity()-this.container.getStoredPower(), this.container.getInputRate()), false), false);
    }
}
