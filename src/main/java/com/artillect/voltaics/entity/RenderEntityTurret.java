package com.artillect.voltaics.entity;

import javax.annotation.Nonnull;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderEntityTurret extends RenderLiving<EntityTurret> {
	private ResourceLocation mobTexture = new ResourceLocation("modtut:textures/entity/weirdzombie.png");

    public static final Factory FACTORY = new Factory();

    public RenderEntityTurret(RenderManager rendermanagerIn) {
        // We use the vanilla zombie model here and we simply
        // retexture it. Of course you can make your own model
        super(rendermanagerIn, new ModelZombie(), 0.5F);
    }
    
    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityTurret entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityTurret> {

        @Override
        public Render<? super EntityTurret> createRenderFor(RenderManager manager) {
            return new RenderEntityTurret(manager);
        }

    }
}
