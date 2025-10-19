package com.github.theredbrain.finiteplants;

import com.github.theredbrain.finiteplants.registry.BlockRegistry;
import com.github.theredbrain.finiteplants.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class FinitePlants implements ModInitializer {
	public static final String MOD_ID = "finiteplants";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		BlockRegistry.init();
		ItemRegistry.init();

		Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(MOD_ID);
		if (modContainer.isPresent()) {
			ResourceManagerHelper.registerBuiltinResourcePack(identifier("finiteplants_data_pack"), modContainer.get(), Text.translatable("resourcepack.finiteplants.finiteplants_data_pack.name"), ResourcePackActivationType.ALWAYS_ENABLED);
			ResourceManagerHelper.registerBuiltinResourcePack(identifier("finiteplants_resource_pack"), modContainer.get(), Text.translatable("resourcepack.finiteplants.finiteplants_resource_pack.name"), ResourcePackActivationType.DEFAULT_ENABLED);
		}
	}

	public static Identifier identifier(String path) {
		return Identifier.of(MOD_ID, path);
	}

}
