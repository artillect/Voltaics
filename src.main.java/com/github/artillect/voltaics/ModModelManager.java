package com.github.artillect.voltaics;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Voltaics.modId)
public class ModModelManager {
	public static final ModModelManager INSTANCE = new ModModelManager();
	
	private ModModelManager() {}
	
	@SubscribeEvent
	public static void registerAllModels(final ModelRegistryEvent event) {
		INSTANCE.registerFluidModels();
		INSTANCE.registerBlockModels();
		INSTANCE.registerItemModels();
	}
	
	private void registerFluidModels() {
	}
	
	private void registerBlockModels() {
		registerBlockItemModel(ModBlocks.COPPER_ORE.getDefaultState());
		registerBlockItemModel(ModBlocks.HEATING_CHAMBER.getDefaultState());
		registerBlockItemModel(ModBlocks.HEATING_COIL.getDefaultState());
		registerBlockItemModel(ModBlocks.LOW_VOLTAGE_CONDUIT.getDefaultState());
		registerBlockItemModel(ModBlocks.VOLTAIC_PILE.getDefaultState());
		registerBlockItemModel(ModBlocks.ZINC_ORE.getDefaultState());
	}
	
	private void registerBlockItemModel(final IBlockState state) {
		final Block block = state.getBlock();
		final Item item = Item.getItemFromBlock(block);

		if (item != Items.AIR) {
			final ResourceLocation registryName = Objects.requireNonNull(block.getRegistryName());
			registerItemModel(item, new ModelResourceLocation(registryName, propertyStringMapper.getPropertyString(state.getProperties())));
		}
	}

	private final Set<Item> itemsRegistered = new HashSet<>();

	private void registerItemModels() {
		// Register items with custom model names first
		registerItemModel(ModItems.DUST_SALT, Voltaics.modId + ":dustSalt");
		registerItemModel(ModItems.ELECTROLYTIC_CELL, Voltaics.modId + ":electrolyticCell");
		registerItemModel(ModItems.HAMMER, Voltaics.modId + ":hammer");
		registerItemModel(ModItems.INGOT_COPPER, Voltaics.modId + ":ingotCopper");
		registerItemModel(ModItems.PLATE_COPPER, Voltaics.modId + ":plateCopper");
		registerItemModel(ModItems.PLATE_ZINC, Voltaics.modId + ":plateZinc");
		registerItemModel(ModItems.THERMOMETER, Voltaics.modId + ":thermometer");
		registerItemModel(ModItems.VOLTMETER, Voltaics.modId + ":voltmeter");

		// Then register items with default model names
		ModItems.RegistrationHandler.ITEMS.stream().filter(item -> !itemsRegistered.contains(item)).forEach(this::registerItemModel);
	}
	
	private void registerItemModel(final Item item) {
		final ResourceLocation registryName = Objects.requireNonNull(item.getRegistryName());
		registerItemModel(item, registryName.toString());
	}
	private void registerItemModel(final Item item, final String modelLocation) {
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation, "inventory");
		registerItemModel(item, fullModelLocation);
	}
	
	private void registerItemModel(final Item item, final ModelResourceLocation fullModelLocation) {
		ModelBakery.registerItemVariants(item, fullModelLocation); // Ensure the custom model is loaded and prevent the default model from being loaded
		registerItemModel(item, stack -> fullModelLocation);
	}
	
	private void registerItemModel(final Item item, final ItemMeshDefinition meshDefinition) {
		itemsRegistered.add(item);
		ModelLoader.setCustomMeshDefinition(item, meshDefinition);
	}
	private final StateMapperBase propertyStringMapper = new StateMapperBase() {
		@Override
		protected ModelResourceLocation getModelResourceLocation(final IBlockState state) {
			return new ModelResourceLocation("minecraft:air");
		}
	};
}
