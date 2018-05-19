package com.github.artillect.voltaics.tileentity;

import javax.annotation.Nullable;

import com.github.artillect.voltaics.capability.Capabilities;
import com.github.artillect.voltaics.power.implementation.BaseEnergyContainer;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityLowVoltageConduit extends TileEntity implements ITickable {
	private BaseEnergyContainer container;
	
	public TileEntityLowVoltageConduit() {
		this.container = new BaseEnergyContainer(0, 250, 50, 50, 20, 1200);
	}
	
	//Connections
	public static enum EnumConduitConnection{
		NONE, CONDUIT, BLOCK, LEVER
	}
	
	public static EnumConduitConnection connectionFromInt(int value){
		switch (value){
		case 0:
			return EnumConduitConnection.NONE;
		case 1:
			return EnumConduitConnection.CONDUIT;
		case 2:
			return EnumConduitConnection.BLOCK;
		case 3:
			return EnumConduitConnection.LEVER;
		}
		return EnumConduitConnection.NONE;
	}
	
	public EnumConduitConnection getConnection(IBlockAccess world, BlockPos pos, EnumFacing side){
		if (world.getTileEntity(pos) instanceof TileEntityLowVoltageConduit){
			return EnumConduitConnection.CONDUIT;
		}
		else if (world.getTileEntity(pos) != null){
			if (world.getTileEntity(pos).hasCapability(Capabilities.CAPABILITY_HOLDER, side)){
				return EnumConduitConnection.BLOCK;
			}
		}
		return EnumConduitConnection.NONE;
	}

	public EnumConduitConnection up = EnumConduitConnection.NONE, down = EnumConduitConnection.NONE, north = EnumConduitConnection.NONE, south = EnumConduitConnection.NONE, east = EnumConduitConnection.NONE, west = EnumConduitConnection.NONE;
	
	//Get and set NBT
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setInteger("up", up.ordinal());
		compound.setInteger("down", down.ordinal());
		compound.setInteger("north", north.ordinal());
		compound.setInteger("south", south.ordinal());
		compound.setInteger("west", west.ordinal());
		compound.setInteger("east", east.ordinal());
        compound.setTag("JouleContainer", this.container.serializeNBT());
        return super.writeToNBT(compound);
	}
	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		up = connectionFromInt(compound.getInteger("up"));
		down = connectionFromInt(compound.getInteger("down"));
		north = connectionFromInt(compound.getInteger("north"));
		south = connectionFromInt(compound.getInteger("south"));
		west = connectionFromInt(compound.getInteger("west"));
		east = connectionFromInt(compound.getInteger("east"));
        this.container = new BaseEnergyContainer(compound.getCompoundTag("JouleContainer"));
	}
	public void updateNeighbors(IBlockAccess world){
		up = getConnection(world,getPos().up(),EnumFacing.DOWN);
		down = getConnection(world,getPos().down(),EnumFacing.UP);
		north = getConnection(world,getPos().north(),EnumFacing.NORTH);
		south = getConnection(world,getPos().south(),EnumFacing.SOUTH);
		west = getConnection(world,getPos().west(),EnumFacing.WEST);
		east = getConnection(world,getPos().east(),EnumFacing.EAST);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}
	
	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}
	
	//Get and check capabilities
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {
 
        if (capability == Capabilities.CAPABILITY_CONSUMER || capability == Capabilities.CAPABILITY_PRODUCER || capability == Capabilities.CAPABILITY_HOLDER)
            return (T) this.container;
            
        return super.getCapability(capability, facing);
    }
	
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {
        
        if (capability == Capabilities.CAPABILITY_CONSUMER || capability == Capabilities.CAPABILITY_PRODUCER || capability == Capabilities.CAPABILITY_HOLDER)
            return true;
            
        return super.hasCapability(capability, facing);
    }
    
	@Override
	public void update() {
		for (EnumFacing side : EnumFacing.values()) {
			final TileEntity tile = this.getWorld().getTileEntity(pos.offset(side));
			//Use this algorithm to balance across the power network
			if (tile != null && tile instanceof TileEntityLowVoltageConduit) {
				long takerStored = tile.getCapability(Capabilities.CAPABILITY_HOLDER, side).getStoredPower();
				if (this.container.getStoredPower() > takerStored) {
					this.container.takePower(tile.getCapability(Capabilities.CAPABILITY_CONSUMER, side).givePower(Math.min(50, (this.container.getStoredPower() + takerStored)/2 - takerStored), false), false);
				}
			} else 
			//Use this algorithm to give power to adjacent blocks
			if (tile != null && tile.hasCapability(Capabilities.CAPABILITY_CONSUMER, side)) {
				long takerStored = tile.getCapability(Capabilities.CAPABILITY_HOLDER, side).getStoredPower();
				long takerCapacity = tile.getCapability(Capabilities.CAPABILITY_HOLDER, side).getCapacity();
				if (this.container.getStoredPower() > takerStored) {
					this.container.takePower(tile.getCapability(Capabilities.CAPABILITY_CONSUMER, side).givePower(Math.min(50, takerCapacity-takerStored), false), false);
				}
			}
		}
	}
}
