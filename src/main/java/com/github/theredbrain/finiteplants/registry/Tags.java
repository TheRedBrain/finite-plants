package com.github.theredbrain.finiteplants.registry;

import com.github.theredbrain.finiteplants.FinitePlants;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class Tags {
	public static final TagKey<Block> CACTUS_ROOTS_CAN_PLACE_ON = TagKey.of(RegistryKeys.BLOCK, FinitePlants.identifier("cactus_roots_can_place_on"));
	public static final TagKey<Block> SUGAR_CANE_ROOTS_CAN_PLACE_ON = TagKey.of(RegistryKeys.BLOCK, FinitePlants.identifier("sugar_cane_roots_can_place_on"));
}
