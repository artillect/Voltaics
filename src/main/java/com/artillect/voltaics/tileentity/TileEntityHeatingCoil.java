package com.artillect.voltaics.tileentity;

import com.artillect.voltaics.capability.EnergyCapabilities;
import com.artillect.voltaics.capability.HeatCapabilities;
import com.artillect.voltaics.lib.JouleUtils;
import com.artillect.voltaics.power.IEnergyConsumer;
import com.artillect.voltaics.power.IHeat;
import com.artillect.voltaics.power.implementation.BaseEnergyContainer;
import com.artillect.voltaics.power.implementation.BaseHeatMachine;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityHeatingCoil extends TileEntity implements ITickable {

	private BaseHeatMachine container;
	
	public TileEntityHeatingCoil() {
		this.container = new BaseHeatMachine(0, 1000, 50, 50, 20, 1200);
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

    @Override
    public void update() {
    	//Take energy from internal energy buffer and create heat based on temperature
    	//TODO: Balance resistance change based on temperature so it hits a true maximum when heating while fully insulated
    	if (this.container.getStoredPower() >= 50) {
    		this.container.takePower(50, false);
    		this.container.giveHeat(1/(1+0.00393*(this.container.getTemperature()-20)), false);
    	}

    	//Lose heat to environment
    	double lostHeat = 0;
    	double lostDegrees = 0;
        for (final EnumFacing side : EnumFacing.values()) {
            final Block block = world.getBlockState(pos.offset(side)).getBlock();
			final TileEntity tile = this.getWorld().getTileEntity(pos.offset(side));
            if (block == Blocks.AIR) {
            	lostHeat = 7.9*(this.getCapability(HeatCapabilities.CAPABILITY_HEAT, side).getTemperature() - 20);
                lostDegrees = lostHeat/4600;
            	this.container.takeHeat(lostDegrees, false);
            }
        }
    }
}
