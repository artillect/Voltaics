package com.github.artillect.voltaics.client;

import com.github.artillect.voltaics.ModBlocks;
import com.github.artillect.voltaics.Voltaics;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class VoltaicsTab extends CreativeTabs{
	public VoltaicsTab() {
		super(Voltaics.modId);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.VOLTAIC_PILE);
	}
}
