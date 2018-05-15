package com.artillect.voltaics.power;

import net.minecraft.tileentity.TileEntity;

public interface IHeat {
    double takeHeat(double heat, boolean simulated);
    double getTemperature();
    double getMeltingPoint();
    double giveHeat(double heat, boolean simulated);
}