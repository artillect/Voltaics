package com.github.artillect.voltaics;

import java.util.HashSet;
import java.util.Set;

import com.github.artillect.voltaics.item.ItemBase;
import com.github.artillect.voltaics.item.ItemHammer;
import com.github.artillect.voltaics.item.ItemThermometer;
import com.github.artillect.voltaics.item.ItemVoltmeter;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

public class ModItems {
	
	public static final ItemBase PLATE_COPPER = new ItemBase("plateCopper", true);
		
	public static final ItemBase INGOT_COPPER = new ItemBase("ingotCopper", true);
	
	public static final ItemBase PLATE_ZINC = new ItemBase("plateZinc", true);
	
	public static final ItemBase ELECTROLYTIC_CELL = new ItemBase("electrolyticCell", true);
	
	public static final ItemBase DUST_SALT = new ItemBase("dustSalt", true);
	
	public static final ItemHammer HAMMER = new ItemHammer();
	
	public static final ItemVoltmeter VOLTMETER = new ItemVoltmeter();

	public static final ItemThermometer THERMOMETER = new ItemThermometer();
	
	@Mod.EventBusSubscriber(modid = Voltaics.modId)
	public static class RegistrationHandler {
		public static final Set<Item> ITEMS = new HashSet<>();
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final Item[] items = {
					PLATE_COPPER,
					INGOT_COPPER,
					PLATE_ZINC,
					ELECTROLYTIC_CELL,
					DUST_SALT,
					HAMMER,
					VOLTMETER,
					THERMOMETER
			};
			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final Item item : items) {
				registry.register(item);
				ITEMS.add(item);
			}
		}
	}
}
