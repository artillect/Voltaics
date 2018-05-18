package com.github.reygrschel.voltaics.item;

import com.github.reygrschel.voltaics.capability.Capabilities;
import com.github.reygrschel.voltaics.power.IHeat;

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
	
	//Get temperature of block through CAPABILITY_HEAT on facing, otherwise return failure
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) return EnumActionResult.PASS;
		TileEntity te = worldIn.getTileEntity(pos);
		if (te == null) return EnumActionResult.PASS;

		if (!te.hasCapability(Capabilities.CAPABILITY_HEAT, facing)) return EnumActionResult.FAIL;

		IHeat heatBuffer = te.getCapability(Capabilities.CAPABILITY_HEAT, facing);
		
		int temperature = (int) heatBuffer.getTemperature();		
		player.sendMessage(new TextComponentString("Temperature: "+temperature+" C"));
		return EnumActionResult.SUCCESS;
	}
}
