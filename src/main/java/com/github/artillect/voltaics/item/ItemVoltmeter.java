//package com.github.artillect.voltaics.item;
//
//import java.util.HashMap;
//
//import com.github.artillect.voltaics.capability.Capabilities;
//import com.github.artillect.voltaics.power.IEnergyPath;
//import com.github.artillect.voltaics.power.IEnergySource;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.EnumActionResult;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.EnumHand;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.text.TextComponentString;
//import net.minecraft.world.World;
//
//public class ItemVoltmeter extends ItemBase {
//	public ItemVoltmeter() {
//		super("voltmeter", true);
//		this.setMaxStackSize(1);
//	}
//	
//	//Get stored energy and max storage on use, otherwise return fail
//	@Override
//    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//		if (!worldIn.isRemote) return EnumActionResult.FAIL;
//		TileEntity te = worldIn.getTileEntity(pos);
//		if (te == null) return EnumActionResult.FAIL;
//
//		/*if (te.hasCapability(Capabilities.CAPABILITY_SOURCE, facing)) {
//			IEnergySource energyBuffer = te.getCapability(Capabilities.CAPABILITY_SOURCE, facing);
//			
//			double storedEnergy = energyBuffer.getStoredPower();
//			double maxStoredEnergy = energyBuffer.getMaxPower();		
//			double voltage = energyBuffer.getVoltage();
//			player.sendMessage(new TextComponentString("Stored Energy: "+storedEnergy+" Therms"));
//			player.sendMessage(new TextComponentString("Max Energy Storage: "+maxStoredEnergy+" Therms"));
//			player.sendMessage(new TextComponentString("Current Voltage: "+voltage+" V"));
//	
//			return EnumActionResult.SUCCESS;
//		} else*/ if (te.hasCapability(Capabilities.CAPABILITY_PATH, facing)) {
//			IEnergyPath energyBuffer = te.getCapability(Capabilities.CAPABILITY_PATH, facing);
//			double voltage = energyBuffer.getVoltage();
//			HashMap<TileEntity, EnumFacing> voltageSourceList = energyBuffer.getVoltageSourceList();
//			player.sendMessage(new TextComponentString("Current Voltage: "+voltage+" V"));
//			player.sendMessage(new TextComponentString("Voltage Sources: "+voltageSourceList));
//			return EnumActionResult.SUCCESS;
//
//		} else return EnumActionResult.FAIL;
//	}
//}