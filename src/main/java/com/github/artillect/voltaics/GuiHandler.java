package com.github.artillect.voltaics;

import com.github.artillect.voltaics.container.ContainerHeatingChamber;
import com.github.artillect.voltaics.gui.GuiHeatingChamber;
import com.github.artillect.voltaics.tileentity.TileEntityHeatingChamber;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    public static final int HEATINGCHAMBER = 0;
	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case HEATINGCHAMBER:
			return new ContainerHeatingChamber(player.inventory, (TileEntityHeatingChamber)world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case HEATINGCHAMBER:
				return new GuiHeatingChamber(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
			default:
				return null;
		}
	}
}
