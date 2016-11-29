package com.artillect.voltaics.tileentity;

import java.util.ArrayList;
import com.artillect.voltaics.power.DefaultEnergyCapability;
import com.artillect.voltaics.power.EnergyCapabilityProvider;
import com.artillect.voltaics.power.IEnergyCapability;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityLowVoltageConduit extends TileEntity implements ITileEntityBase, ITickable {
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
	public IEnergyCapability capability = new DefaultEnergyCapability();
	
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
	
	public TileEntityLowVoltageConduit(){
		super();
		capability.setEnergyCapacity(1000);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger("up", up.ordinal());
		tag.setInteger("down", down.ordinal());
		tag.setInteger("north", north.ordinal());
		tag.setInteger("south", south.ordinal());
		tag.setInteger("west", west.ordinal());
		tag.setInteger("east", east.ordinal());
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
	public boolean activate(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		
	}
	
	@Override
	public void update() {
		if (capability.getEnergy() != 0){
			//ArrayList<BlockPos> toUpdate = new ArrayList<BlockPos>();
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

			connectedFaces.clear();
			if (up == EnumConduitConnection.BLOCK){
				connectedFaces.add(EnumFacing.UP);
			}
			if (down == EnumConduitConnection.BLOCK){
				connectedFaces.add(EnumFacing.DOWN);
			}
			if (north == EnumConduitConnection.BLOCK){
				connectedFaces.add(EnumFacing.NORTH);
			}
			if (south == EnumConduitConnection.BLOCK){
				connectedFaces.add(EnumFacing.SOUTH);
			}
			if (west == EnumConduitConnection.BLOCK){
				connectedFaces.add(EnumFacing.WEST);
			}
			if (east == EnumConduitConnection.BLOCK){
				connectedFaces.add(EnumFacing.EAST);
			}
		}
	}
}
