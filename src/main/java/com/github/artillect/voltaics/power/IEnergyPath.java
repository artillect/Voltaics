package com.github.artillect.voltaics.power;

import java.util.HashMap;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public interface IEnergyPath {
    
    long getVoltage();
    HashMap<TileEntity, EnumFacing> getVoltageSourceList();
    void addToVoltageSourceList(TileEntity tile, EnumFacing facing);
    void removeFromVoltageSourceList(TileEntity tile, EnumFacing facing);
}