package com.github.reygrschel.voltaics.power;

public interface IHeat {
    double takeHeat(double heat, boolean simulated);
    double getTemperature();
    double getMeltingPoint();
    double giveHeat(double heat, boolean simulated);
}