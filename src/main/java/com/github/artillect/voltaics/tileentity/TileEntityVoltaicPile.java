package com.github.artillect.voltaics.tileentity;

import com.github.artillect.voltaics.capability.Capabilities;
import com.github.artillect.voltaics.power.implementation.BaseEnergyContainer;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityVoltaicPile extends TileEntity implements ITickable {
	
	private BaseEnergyContainer container;
	
	public TileEntityVoltaicPile() {
		this.container = new BaseEnergyContainer(20000, 20000, 50, 50, 20, 1200);
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
	
	//Sided capabilities
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {
    	
    	if (capability == Capabilities.CAPABILITY_HOLDER) {
    		return (T) this.container;
    	}
    	else if (capability == Capabilities.CAPABILITY_PRODUCER && facing == EnumFacing.UP) {
			return (T) this.container;
    	}
    	else if (capability == Capabilities.CAPABILITY_CONSUMER && facing == EnumFacing.DOWN) {
    		return (T) this.container;
    	}
            
        return super.getCapability(capability, facing);
    }
    
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {
    	
    	if (capability == Capabilities.CAPABILITY_HOLDER) {
    		return true;
    	}
    	if (capability == Capabilities.CAPABILITY_PRODUCER && facing == EnumFacing.UP) {
			return true;
    	}
    	else if (capability == Capabilities.CAPABILITY_CONSUMER && facing == EnumFacing.DOWN) {
    		return true;
    	}
    	else return false;    
    }

    //TODO Many of these lines are very long, shorten
	@Override
	public void update() {
		//Give power to tile entity above
		TileEntity tile = this.getWorld().getTileEntity(pos.offset(EnumFacing.UP));
		if (tile != null && tile.hasCapability(Capabilities.CAPABILITY_CONSUMER, EnumFacing.DOWN) && tile.hasCapability(Capabilities.CAPABILITY_HOLDER, EnumFacing.DOWN)) {
			long takenPower = tile.getCapability(Capabilities.CAPABILITY_CONSUMER, EnumFacing.DOWN).givePower(Math.min(50, tile.getCapability(Capabilities.CAPABILITY_HOLDER, EnumFacing.DOWN).getCapacity()-tile.getCapability(Capabilities.CAPABILITY_HOLDER, EnumFacing.DOWN).getStoredPower()), false);
			this.container.takePower(takenPower, false);
		}
		
		//Take power from tile entity below
		tile = this.getWorld().getTileEntity(pos.offset(EnumFacing.DOWN));
		if (tile != null && tile.hasCapability(Capabilities.CAPABILITY_PRODUCER, EnumFacing.UP) && tile.hasCapability(Capabilities.CAPABILITY_HOLDER, EnumFacing.UP)) {
			long givenPower = tile.getCapability(Capabilities.CAPABILITY_PRODUCER, EnumFacing.UP).takePower(Math.min(50, Math.min(this.container.getCapacity()-this.container.getStoredPower(), tile.getCapability(Capabilities.CAPABILITY_HOLDER, EnumFacing.UP).getStoredPower())), false);
			this.container.givePower(givenPower, false);
		}
	}
}