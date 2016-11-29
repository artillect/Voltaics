package com.artillect.voltaics.block;

import com.artillect.voltaics.Voltaics;
import com.artillect.voltaics.power.EnergyCapabilityProvider;
import com.artillect.voltaics.tileentity.TileEntityLowVoltageConduit;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLowVoltageConduit extends BlockTEBase{
	public BlockLowVoltageConduit(Material material, String name, boolean addToTab) {
		super(material, name, addToTab);
	}
    
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    }
	
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {UP, DOWN, NORTH, EAST, WEST, SOUTH});
    }

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		double x1 = 0.375;
		double y1 = 0.375;
		double z1 = 0.375;
		double x2 = 0.625;
		double y2 = 0.625;
		double z2 = 0.625;
		
		if (source.getTileEntity(pos) instanceof TileEntityLowVoltageConduit){
			TileEntityLowVoltageConduit conduit = ((TileEntityLowVoltageConduit)source.getTileEntity(pos));
			if (conduit.up != TileEntityLowVoltageConduit.EnumConduitConnection.NONE){
				y2 = 1;
			}
			if (conduit.down != TileEntityLowVoltageConduit.EnumConduitConnection.NONE){
				y1 = 0;
			}
			if (conduit.north != TileEntityLowVoltageConduit.EnumConduitConnection.NONE){
				z1 = 0;
			}
			if (conduit.south != TileEntityLowVoltageConduit.EnumConduitConnection.NONE){
				z2 = 1;
			}
			if (conduit.west != TileEntityLowVoltageConduit.EnumConduitConnection.NONE){
				x1 = 0;
			}
			if (conduit.east != TileEntityLowVoltageConduit.EnumConduitConnection.NONE){
				x2 = 1;
			}
		}
		
		return new AxisAlignedBB(x1,y1,z1,x2,y2,z2);
	}
    
   
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor){
		if (world.getTileEntity(pos) != null){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos)).updateNeighbors(world);
		}
	}
	
	/*@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block){
		if (world.getTileEntity(pos) != null){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos)).updateNeighbors(world);
			world.notifyBlockUpdate(pos, state, world.getBlockState(pos), 3);
		}
	}*/
	
	/*@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		if (world.getTileEntity(pos) != null){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos)).updateNeighbors(world);
		}
	}*/
   
	
	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player){
		if (world.getTileEntity(pos.up()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.up())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.down()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.down())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.north()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.north())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.south()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.south())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.west()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.west())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.east()) instanceof TileEntityLowVoltageConduit){
			((TileEntityLowVoltageConduit)world.getTileEntity(pos.east())).updateNeighbors(world);
		}
	}
	
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(UP, Boolean.valueOf(this.canConnectTo(worldIn, pos.up()))).withProperty(DOWN, Boolean.valueOf(this.canConnectTo(worldIn, pos.down()))).withProperty(NORTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.north()))).withProperty(EAST, Boolean.valueOf(this.canConnectTo(worldIn, pos.east()))).withProperty(SOUTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.south()))).withProperty(WEST, Boolean.valueOf(this.canConnectTo(worldIn, pos.west())));
    }
    
    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        return ((te instanceof TileEntity) && te.hasCapability(EnergyCapabilityProvider.energyCapability, null));
    }
    
    public TileEntity createNewTileEntity(World worldIn, int meta) {
    	return new TileEntityLowVoltageConduit();
    }
}