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

        if (capability == EnergyCapabilities.CAPABILITY_CONSUMER || capability == EnergyCapabilities.CAPABILITY_HOLDER || capability == EnergyCapabilities.CAPABILITY_PRODUCER)
            return (T) this.container;
            
        return super.getCapability(capability, facing);
    }
    
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {

        if (capability == EnergyCapabilities.CAPABILITY_CONSUMER || capability == EnergyCapabilities.CAPABILITY_HOLDER || capability == EnergyCapabilities.CAPABILITY_PRODUCER)
            return true;
            
        return super.hasCapability(capability, facing);
    }

	@Override
	public void update() {
		this.container.takePower(JouleUtils.distributePowerToAllFaces(this.getWorld(), pos, 50, false), false); //Replace 20 with JouleUtils.distributePowerToAllFaces(this.getWorld(), pos, 50, false)
	}
}