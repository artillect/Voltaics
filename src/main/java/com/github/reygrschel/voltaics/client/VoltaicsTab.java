package com.github.reygrschel.voltaics.client;

import com.github.reygrschel.voltaics.RegistryManager;
import com.github.reygrschel.voltaics.Voltaics;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VoltaicsTab extends CreativeTabs{
	public VoltaicsTab() {
		super(Voltaics.modId);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(RegistryManager.voltmeter);
	}
}
