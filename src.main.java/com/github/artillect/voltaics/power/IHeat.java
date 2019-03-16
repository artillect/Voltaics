package com.github.artillect.voltaics.power;

public interface IHeat {
    double getTemperature();
    double getMeltingTemperature();
    void addTemperature(double heat);
}