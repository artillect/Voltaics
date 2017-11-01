package com.artillect.voltaics.power;

public interface IHeat {
    long takeHeat(long heat, boolean simulated);
    long getTemperature();
    long getMeltingPoint();
    long giveHeat(long heat, boolean simulated);
}