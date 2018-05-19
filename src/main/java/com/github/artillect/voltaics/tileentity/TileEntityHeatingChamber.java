package com.github.artillect.voltaics.tileentity;

import com.github.artillect.voltaics.capability.Capabilities;
import com.github.artillect.voltaics.power.implementation.BaseHeatMachine;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityHeatingChamber extends TileEntity implements ITickable {

	private ItemStackHandler inventory = new ItemStackHandler(2);
	private BaseHeatMachine container;
	
	public TileEntityHeatingChamber() {
		this.container = new BaseHeatMachine(0, 1000, 50, 50, 20, 1200);
	}
	
	//Get and set NBT
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
		this.container = new BaseHeatMachine(compound.getCompoundTag("HeatContainer"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("JouleContainer", this.container.serializeNBT());
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}
	
	//Get and check capabilities based on sidedness
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {

        if (capability == Capabilities.CAPABILITY_CONSUMER || capability == Capabilities.CAPABILITY_HOLDER)
            return (T) this.container;
        else if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        	return (T) this.inventory;
            
        return super.getCapability(capability, facing);
    }
    
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {

        if (capability == Capabilities.CAPABILITY_CONSUMER || capability == Capabilities.CAPABILITY_HOLDER || capability == Capabilities.CAPABILITY_HEAT)
            return true;
        else if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        	return true;
            
        return super.hasCapability(capability, facing);
    }
	
	@Override
	public void update() {
		//For each side take heat from blocks with CAPABILITY_HEAT
		for (EnumFacing side : EnumFacing.values()) {
			final TileEntity tile = this.getWorld().getTileEntity(pos.offset(side));
			if (tile != null && tile.hasCapability(Capabilities.CAPABILITY_HEAT,side)) {
				double takenHeat = 7.9*(tile.getCapability(Capabilities.CAPABILITY_HEAT, side.getOpposite()).getTemperature()-this.container.getTemperature());
				double takenDegrees = takenHeat/4600;
				tile.getCapability(Capabilities.CAPABILITY_HEAT, side).addHeat(-takenDegrees, false);
				this.container.addHeat(takenDegrees, false);
			}
		}
	}

}
