package com.github.artillect.voltaics.tileentity;

import com.github.artillect.voltaics.capability.Capabilities;
import com.github.artillect.voltaics.power.implementation.BaseEnergyContainer;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityHeatingCoil extends TileEntity implements ITickable {

	private BaseEnergyContainer container;
	
	public TileEntityHeatingCoil() {
		this.container = new BaseEnergyContainer(0, 1000, 50, 50, 20, 1200);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.container = new BaseEnergyContainer(compound.getCompoundTag("HeatContainer"));
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

        if (capability == Capabilities.CAPABILITY_CONSUMER || capability == Capabilities.CAPABILITY_HOLDER)
            return (T) this.container;
            
        return super.getCapability(capability, facing);
    }
    
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {

        if (capability == Capabilities.CAPABILITY_CONSUMER || capability == Capabilities.CAPABILITY_HOLDER || capability == Capabilities.CAPABILITY_HEAT)
            return true;
            
        return super.hasCapability(capability, facing);
    }

    @Override
    public void update() {
    	//Take energy from internal energy buffer and create heat based on temperature
    	//TODO: Balance resistance change based on temperature so it hits a true maximum when heating while fully insulated
    	if (this.container.getStoredPower() >= 50) {
    		this.container.takePower(50, false);
    		this.container.addHeat(1/(1+0.00393*(this.container.getTemperature()-20)), false);
    	}

    	//Lose heat to environment
    	double lostDegrees = 0;
        for (final EnumFacing side : EnumFacing.values()) {
            final Block block = world.getBlockState(pos.offset(side)).getBlock();
            if (block == Blocks.AIR) {
            	lostDegrees = -7.9*(this.getCapability(Capabilities.CAPABILITY_HEAT, side).getTemperature() - 20)/4600;
            	this.container.addHeat(lostDegrees, false);
            }
        }
    }
}
