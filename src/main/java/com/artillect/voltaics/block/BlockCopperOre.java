package com.artillect.voltaics.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCopperOre extends BlockBase{
    public BlockCopperOre(String unlocalizedName) {
        super(Material.ROCK, unlocalizedName, true);
        setHardness(3f);
        setResistance(5f);
    }

    public BlockCopperOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
