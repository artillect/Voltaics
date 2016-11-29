package com.artillect.voltaics.item;

import com.artillect.voltaics.power.EnergyCapabilityProvider;
import com.artillect.voltaics.power.IEnergyCapability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemVoltmeter extends ItemBase {
	public ItemVoltmeter() {
		super("voltmeter", true);
		this.setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) return EnumActionResult.FAIL;
		TileEntity te = worldIn.getTileEntity(pos);
		if (te == null) return EnumActionResult.FAIL;

		if (!te.hasCapability(EnergyCapabilityProvider.energyCapability, facing)) return EnumActionResult.FAIL;

		IEnergyCapability energyBuffer = te.getCapability(EnergyCapabilityProvider.energyCapability, facing);
		
		double storedEnergy = energyBuffer.getEnergy();
		
		playerIn.addChatMessage(new TextComponentString("Stored Energy: "+storedEnergy+" J"));
		  
		return EnumActionResult.SUCCESS;
	}
}
