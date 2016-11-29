package com.artillect.voltaics.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.artillect.voltaics.Voltaics;

public class ItemBase extends Item implements IModeledItem {
	public ItemBase(String name, boolean addToTab){
		super();
		setUnlocalizedName(Voltaics.modId + "." + name);
		setRegistryName(Voltaics.modId + ":" + name);
		if (addToTab){
			setCreativeTab(Voltaics.creativeTab);
		}
		GameRegistry.register(this);
	}
		
	@Override
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
	}

}
