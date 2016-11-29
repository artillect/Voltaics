package com.artillect.voltaics.client;

import com.artillect.voltaics.RegistryManager;
import com.artillect.voltaics.Voltaics;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class VoltaicsTab extends CreativeTabs{
	public VoltaicsTab() {
		super(Voltaics.modId);
	}
	
	@Override
	public Item getTabIconItem() {
		return RegistryManager.voltmeter;
	}
}
