package com.artillect.voltaics.client;

import com.artillect.voltaics.Voltaics;
import com.artillect.voltaics.block.BlockModel;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class BakedModelLoader implements ICustomModelLoader {

    public static final BlockModel BlockModel = new BlockModel();


    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        return modelLocation.getResourceDomain().equals(Voltaics.modId) && "BlockLowVoltageConduit".equals(modelLocation.getResourcePath());
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception {
        return BlockModel;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }
}
