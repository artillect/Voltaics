package com.github.artillect.voltaics.power;

public interface IHeat {
    double getTemperature();
    double getMeltingPoint();
    double addHeat(double heat, boolean simulated);
}