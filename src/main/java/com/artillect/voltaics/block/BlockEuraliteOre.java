package com.artillect.voltaics.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockEuraliteOre extends BlockBase{
	public BlockEuraliteOre(String unlocalizedName) {
        super(Material.ROCK, unlocalizedName, true);
        setHardness(3f);
        setResistance(5f);
    }

    public BlockEuraliteOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
