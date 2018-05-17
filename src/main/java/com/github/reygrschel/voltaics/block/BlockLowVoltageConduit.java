package com.github.reygrschel.voltaics.block;

import com.github.reygrschel.voltaics.capability.EnergyCapabilities;
import com.github.reygrschel.voltaics.tileentity.TileEntityLowVoltageConduit;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLowVoltageConduit extends BlockTEBase implements IModeledBlock{
	public BlockLowVoltageConduit(Material material, String name, boolean addToTab) {
		super(material, name, addToTab);
	}
    
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");

    //Set bounding box based on connection to other blocks and conduits
    //TODO: Make bounding box appropriate for tile entities with compatible capabilities
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		double x1 = 0.375;
		double y1 = 0.375;
		double z1 = 0.375;
		double x2 = 0.625;
		double y2 = 0.625;
		double z2 = 0.625;
		
		if (source.getTileEntity(pos) instanceof TileEntityLowVoltageConduit) {
			TileEntityLowVoltageConduit conduit = ((TileEntityLowVoltageConduit)source.getTileEntity(pos));
			if (conduit.up != TileEntityLowVoltageConduit.EnumConduitConnection.NONE) {
				y2 = 1;
			}
			if (conduit.down != TileEntityLowVoltageConduit.EnumConduitConnection.NONE) {
				y1 = 0;
			}
			if (conduit.north != TileEntityLowVoltageConduit.EnumConduitConnection.NONE) {
				z1 = 0;
			}
			if (conduit.south != TileEntityLowVoltageConduit.EnumConduitConnection.NONE) {
				z2 = 1;
			}
			if (conduit.west != TileEntityLowVoltageConduit.EnumConduitConnection.NONE) {
				x1 = 0;
			}
			if (conduit.east != TileEntityLowVoltageConduit.EnumConduitConnection.NONE) {
				x2 = 1;
			}
		}
		
		return new AxisAlignedBB(x1,y1,z1,x2,y2,z2);
	}
    
	//Update neighbors
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		if (world.getTileEntity(pos) != null) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos)).updateNeighbors(world);
		}
	}
	
	/*@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block) {
		if (world.getTileEntity(pos) != null) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos)).updateNeighbors(world);
			world.notifyBlockUpdate(pos, state, world.getBlockState(pos), 3);
		}
	}*/
	
	/*@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (world.getTileEntity(pos) != null) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos)).updateNeighbors(world);
		}
	}*/
   

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (world.getTileEntity(pos) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos)).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.up()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.up())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.down()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.down())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.north()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.north())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.south()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.south())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.west()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.west())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.east()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.east())).updateNeighbors(world);
		}
	}
	
	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (world.getTileEntity(pos.up()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.up())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.down()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.down())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.north()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.north())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.south()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.south())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.west()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.west())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.east()) instanceof TileEntityLowVoltageConduit) {
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.east())).updateNeighbors(world);
		}
	}
	
	//Get and set blockstate and connections
	@Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

	@Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state
        		.withProperty(NORTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.north(), EnumFacing.NORTH)))
        		.withProperty(SOUTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.south(), EnumFacing.SOUTH)))
                .withProperty(WEST,  Boolean.valueOf(this.canConnectTo(worldIn, pos.west(), EnumFacing.WEST)))
                .withProperty(EAST,  Boolean.valueOf(this.canConnectTo(worldIn, pos.east(), EnumFacing.EAST)))
                .withProperty(UP,  Boolean.valueOf(this.canConnectTo(worldIn, pos.up(), EnumFacing.UP)))
                .withProperty(DOWN,  Boolean.valueOf(this.canConnectTo(worldIn, pos.down(), EnumFacing.DOWN)));
    }
    
    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing direction) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te == null) {
        	return false;
        }
        return (te instanceof TileEntityLowVoltageConduit) || (te.hasCapability(EnergyCapabilities.CAPABILITY_HOLDER, direction.getOpposite()));
    }
    
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {UP, DOWN, NORTH, EAST, WEST, SOUTH});
    }
    
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
    	return new TileEntityLowVoltageConduit();
    }
}