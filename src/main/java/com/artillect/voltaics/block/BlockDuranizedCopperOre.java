package com.artillect.voltaics.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockDuranizedCopperOre extends BlockBase {
	public BlockDuranizedCopperOre(String unlocalizedName) {
        super(Material.ROCK, unlocalizedName, true);
        setHardness(3f);
        setResistance(5f);
    }

    public BlockDuranizedCopperOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
