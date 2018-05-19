package com.github.artillect.voltaics.gui;

import com.github.artillect.voltaics.Voltaics;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

//Inventory magic that I understand slightly more
public class GuiHeatingChamber extends GuiContainer {

	private InventoryPlayer playerInv;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(Voltaics.modId, "textures/gui/heatingchamber.png");

	public GuiHeatingChamber(Container object, InventoryPlayer playerInv) {
		super(object);
		this.playerInv = playerInv;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = I18n.format("tile.voltaics.heatingChamber.name");
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, 0x404040);
		fontRendererObj.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
	}
}
