package com.github.artillect.voltaics.power;

public interface IHeat {
    long getTemperature();
    long getMeltingTemperature();
	void addHeat(long heat);
}