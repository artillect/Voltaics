package com.artillect.voltaics.item;

import com.artillect.voltaics.capability.HeatCapabilities;
import com.artillect.voltaics.power.IHeat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemThermometer extends ItemBase {
	public ItemThermometer() {
		super("thermometer", true);
		this.setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) return EnumActionResult.PASS;
		TileEntity te = worldIn.getTileEntity(pos);
		if (te == null) return EnumActionResult.PASS;

		if (!te.hasCapability(HeatCapabilities.CAPABILITY_HEAT, facing)) return EnumActionResult.FAIL;

		IHeat heatBuffer = te.getCapability(HeatCapabilities.CAPABILITY_HEAT, facing);
		
		int temperature = (int) heatBuffer.getTemperature();		
		player.sendMessage(new TextComponentString("Temperature: "+temperature+" C"));
		return EnumActionResult.SUCCESS;
	}
}
