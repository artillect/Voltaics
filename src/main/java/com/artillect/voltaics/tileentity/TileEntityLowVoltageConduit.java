package com.artillect.voltaics.tileentity;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.artillect.voltaics.block.BlockLowVoltageConduit;
import com.artillect.voltaics.power.DefaultEnergyCapability;
import com.artillect.voltaics.power.EnergyCapabilityProvider;
import com.artillect.voltaics.power.IEnergyCapability;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

import com.artillect.voltaics.network.PacketHandler;
import com.artillect.voltaics.network.message.MessageTEUpdate;

public class TileEntityLowVoltageConduit extends TileEntity implements ITileEntityBase, ITickable {
	public IEnergyCapability capability = new DefaultEnergyCapability();
	public long ticksExisted = 0;

	public TileEntityLowVoltageConduit() {
		super();
		capability.setEnergyCapacity(200);
		capability.setEnergy(200); //Testing feature only, remove later
	}
	
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
			if (world.getTileEntity(pos).hasCapability(EnergyCapabilityProvider.energyCapability, side)){
				return EnumConduitConnection.BLOCK;
			}
		}
		return EnumConduitConnection.NONE;
	}

	public EnumConduitConnection up = EnumConduitConnection.NONE, down = EnumConduitConnection.NONE, north = EnumConduitConnection.NONE, south = EnumConduitConnection.NONE, east = EnumConduitConnection.NONE, west = EnumConduitConnection.NONE;
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger("up", up.ordinal());
		tag.setInteger("down", down.ordinal());
		tag.setInteger("north", north.ordinal());
		tag.setInteger("south", south.ordinal());
		tag.setInteger("west", west.ordinal());
		tag.setInteger("east", east.ordinal());
		capability.writeToNBT(tag);
		return tag;
	}
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		up = connectionFromInt(tag.getInteger("up"));
		down = connectionFromInt(tag.getInteger("down"));
		north = connectionFromInt(tag.getInteger("north"));
		south = connectionFromInt(tag.getInteger("south"));
		west = connectionFromInt(tag.getInteger("west"));
		east = connectionFromInt(tag.getInteger("east"));
		capability.readFromNBT(tag);
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
	
	@Override
	public boolean activate(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == EnergyCapabilityProvider.energyCapability) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		super.getCapability(capability, facing);
		if (capability == EnergyCapabilityProvider.energyCapability) {
			return (T) this.capability;
		}
		return (T) this.capability;
	}
	
	@Override
	public void update() {
		ArrayList<EnumFacing> connectedFaces = new ArrayList<EnumFacing>();
		if (up == EnumConduitConnection.CONDUIT || up == EnumConduitConnection.BLOCK){
			connectedFaces.add(EnumFacing.UP);
		}
		if (down == EnumConduitConnection.CONDUIT || down == EnumConduitConnection.BLOCK){
			connectedFaces.add(EnumFacing.DOWN);
		}
		if (north == EnumConduitConnection.CONDUIT || north == EnumConduitConnection.BLOCK){
			connectedFaces.add(EnumFacing.NORTH);
		}
		if (south == EnumConduitConnection.CONDUIT || south == EnumConduitConnection.BLOCK){
			connectedFaces.add(EnumFacing.SOUTH);
		}
		if (west == EnumConduitConnection.CONDUIT || west == EnumConduitConnection.BLOCK){
			connectedFaces.add(EnumFacing.WEST);
		}
		if (east == EnumConduitConnection.CONDUIT || east == EnumConduitConnection.BLOCK){
			connectedFaces.add(EnumFacing.EAST);
		}
		if (connectedFaces.size() > 0)
			for (int i = 0; i < connectedFaces.size(); i++) {
				TileEntity te = (getWorld().getTileEntity(getPos().offset(connectedFaces.get(i))));
				if (capability.getEnergy() <= 100) {
					if ((te != null) && te.hasCapability(EnergyCapabilityProvider.energyCapability, null)) {
						IEnergyCapability cap = te.getCapability(EnergyCapabilityProvider.energyCapability, null);
						if ((cap.getEnergy() < capability.getEnergyCapacity()-capability.getEnergy()) && cap.getEnergy() > 0) {
							int removed = cap.removeAmount(10, true);
							int added = capability.addAmount(removed, true);
						}
					}
				}
				if (capability.getEnergy() > 100) {
					if ((te != null) && te.hasCapability(EnergyCapabilityProvider.energyCapability, null)) {
						IEnergyCapability cap = te.getCapability(EnergyCapabilityProvider.energyCapability, null);
						if (cap.getEnergy() < cap.getEnergyCapacity() && capability.getEnergy() > 0) {
							int added = cap.addAmount(Math.min(10,capability.getEnergy()), true);
							int removed = capability.removeAmount(added, true);
							//if (!getWorld().isRemote){
							//	PacketHandler.INSTANCE.sendToAll(new MessageTEUpdate(this));
							//}
						}
					}
				}
			}
		connectedFaces.clear();
	}
}
