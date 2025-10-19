package com.github.theredbrain.finiteplants;

import com.github.theredbrain.finiteplants.registry.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;

public class FinitePlantsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		registerBlockColors();
		registerTransparency();
	}

	private void registerBlockColors() {
		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getGrassColor(view, pos) : -1, BlockRegistry.SUGAR_CANE_ROOT);
	}


	private void registerTransparency() {
		BlockRenderLayerMap.putBlock(BlockRegistry.CACTUS_ROOT, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BlockRegistry.SUGAR_CANE_ROOT, BlockRenderLayer.CUTOUT);
	}

}
