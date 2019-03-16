package com.github.artillect.voltaics.power;

public interface ISource {
	long getMaxPower();
	long getStoredPower();
	long getVoltage();
	long addStoredPower(long Joule, boolean simulated);
	void setVoltage(long voltage);
}