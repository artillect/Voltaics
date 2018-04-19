package com.artillect.voltaics.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHammer extends ItemBase{
	public ItemHammer() {
		super("hammer", true);
		this.setMaxStackSize(1);
	}
	
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            EnumFacing targetFacing = facing != null ? facing : EnumFacing.UP;

            IBlockState state = worldIn.getBlockState(pos);
            if (state != null) {
                return state.getBlock().rotateBlock(worldIn, pos, targetFacing) ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
            }
        }
        return EnumActionResult.SUCCESS;
    }
}