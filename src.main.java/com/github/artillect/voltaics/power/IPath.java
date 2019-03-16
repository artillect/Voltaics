package com.github.artillect.voltaics.power;

import java.util.HashMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public interface IPath {
    HashMap<TileEntity, EnumFacing> getVoltageSourceList();
    long getVoltage();
}