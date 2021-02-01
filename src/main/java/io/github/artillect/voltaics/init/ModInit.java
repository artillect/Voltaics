package io.github.artillect.voltaics.init;

import io.github.artillect.voltaics.Voltaics;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Voltaics.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModInit {
	
    public static final ItemGroup ITEM_GROUP = new ItemGroup("voltaics") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.COPPEROREBLOCK.get());
        }
    };
	
	public static void init(final FMLCommonSetupEvent event) {
		
	}
}
