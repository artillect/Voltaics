package io.github.artillect.voltaics.init;

//import io.github.artillect.voltaics.block.*;
//import io.github.artillect.voltaics.item.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static io.github.artillect.voltaics.Voltaics.MODID;

public class Registration {

	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	
	public static void init() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static final RegistryObject<Block> COPPEROREBLOCK = BLOCKS.register("copperore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f)));
	public static final RegistryObject<Item> COPPEROREBLOCK_ITEM = ITEMS.register("copperoreblock", () -> new BlockItem(COPPEROREBLOCK.get(), new Item.Properties().group(ModInit.ITEM_GROUP)));
}
