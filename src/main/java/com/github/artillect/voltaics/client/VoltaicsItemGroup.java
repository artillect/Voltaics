package com.github.artillect.voltaics.client;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class VoltaicsItemGroup extends ItemGroup {
	public VoltaicsItemGroup(String label) {
		super(label);
	}
	
	@Override
	public ItemStack createIcon() {
		return new ItemStack(Items.BOOK);
	}

}
