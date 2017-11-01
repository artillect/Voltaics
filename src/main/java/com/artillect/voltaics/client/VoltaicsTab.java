<<<<<<< HEAD
package com.artillect.voltaics.client;

import com.artillect.voltaics.RegistryManager;
import com.artillect.voltaics.Voltaics;
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
=======
package com.artillect.voltaics.client;

import com.artillect.voltaics.RegistryManager;
import com.artillect.voltaics.Voltaics;
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
>>>>>>> 9022cc8... Resyncing changes
