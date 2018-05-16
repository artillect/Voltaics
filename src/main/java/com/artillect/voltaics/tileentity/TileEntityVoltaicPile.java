package com.artillect.voltaics.tileentity;

import java.util.ArrayList;

import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

import com.artillect.voltaics.capability.EnergyCapabilities;
import com.artillect.voltaics.lib.JouleUtils;
import com.artillect.voltaics.power.implementation.BaseEnergyContainer;
import com.artillect.voltaics.tileentity.TileEntityLowVoltageConduit.EnumConduitConnection;

public class TileEntityVoltaicPile extends TileEntity implements ITickable {
	
	private BaseEnergyContainer container;
	
	public TileEntityVoltaicPile() {
		this.container = new BaseEnergyContainer(20000, 20000, 50, 50);
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

    	if ((capability == EnergyCapabilities.CAPABILITY_HOLDER || capability == EnergyCapabilities.CAPABILITY_PRODUCER) && facing == EnumFacing.UP) {
			return (T) this.container;
    	}
    	else if ((capability == EnergyCapabilities.CAPABILITY_HOLDER || capability == EnergyCapabilities.CAPABILITY_CONSUMER) && facing == EnumFacing.DOWN) {
    		return (T) this.container;
    	}
            
        return super.getCapability(capability, facing);
    }
    
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {
    	
    	if ((capability == EnergyCapabilities.CAPABILITY_HOLDER || capability == EnergyCapabilities.CAPABILITY_PRODUCER) && facing == EnumFacing.UP) {
			return true;
    	}
    	else if ((capability == EnergyCapabilities.CAPABILITY_HOLDER || capability == EnergyCapabilities.CAPABILITY_CONSUMER) && facing == EnumFacing.DOWN) {
    		return true;
    	}
    	else return false;    
    }

	@Override
	public void update() {
		TileEntity tile = this.getWorld().getTileEntity(pos.offset(EnumFacing.UP));
		if (tile != null && tile.hasCapability(EnergyCapabilities.CAPABILITY_CONSUMER, EnumFacing.DOWN) && tile.hasCapability(EnergyCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN)) {
			long takenPower = tile.getCapability(EnergyCapabilities.CAPABILITY_CONSUMER, EnumFacing.DOWN).givePower(Math.min(50, tile.getCapability(EnergyCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN).getCapacity()-tile.getCapability(EnergyCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN).getStoredPower()), false);
			this.container.takePower(takenPower, false); //Replace 20 with JouleUtils.distributePowerToAllFaces(this.getWorld(), pos, 50, false)
		}
		tile = this.getWorld().getTileEntity(pos.offset(EnumFacing.DOWN));
		if (tile != null && tile.hasCapability(EnergyCapabilities.CAPABILITY_PRODUCER, EnumFacing.UP) && tile.hasCapability(EnergyCapabilities.CAPABILITY_HOLDER, EnumFacing.UP)) {
			long givenPower = tile.getCapability(EnergyCapabilities.CAPABILITY_PRODUCER, EnumFacing.UP).takePower(Math.min(50, Math.min(this.container.getCapacity()-this.container.getStoredPower(), tile.getCapability(EnergyCapabilities.CAPABILITY_HOLDER, EnumFacing.UP).getStoredPower())), false);
			this.container.givePower(givenPower, false);
		}
	}
}