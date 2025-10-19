package com.github.theredbrain.finiteplants.registry;

import com.github.theredbrain.finiteplants.FinitePlants;
import com.github.theredbrain.finiteplants.block.CactusRootBlock;
import com.github.theredbrain.finiteplants.block.SugarCaneRootBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

import java.util.List;

public class BlockRegistry {

	public static RegistryKey<Block> CACTUS_ROOT_BLOCK_KEY = RegistryKey.of(RegistryKeys.BLOCK, FinitePlants.identifier("cactus_root"));
	public static RegistryKey<Item> CACTUS_ROOT_ITEN_KEY = RegistryKey.of(RegistryKeys.ITEM, FinitePlants.identifier("cactus_root"));
	public static final Block CACTUS_ROOT = registerBlock(CACTUS_ROOT_BLOCK_KEY, CACTUS_ROOT_ITEN_KEY, new CactusRootBlock(Block.Settings.create().registryKey(CACTUS_ROOT_BLOCK_KEY).mapColor(MapColor.DARK_GREEN).ticksRandomly().strength(0.4F).sounds(BlockSoundGroup.WOOL).pistonBehavior(PistonBehavior.DESTROY)), List.of(ItemGroups.NATURAL));
	public static RegistryKey<Block> SUGAR_CANE_ROOT_BLOCK_KEY = RegistryKey.of(RegistryKeys.BLOCK, FinitePlants.identifier("sugar_cane_root"));
	public static RegistryKey<Item> SUGAR_CANE_ROOT_ITEN_KEY = RegistryKey.of(RegistryKeys.ITEM, FinitePlants.identifier("sugar_cane_root"));
	public static final Block SUGAR_CANE_ROOT = registerBlock(SUGAR_CANE_ROOT_BLOCK_KEY, SUGAR_CANE_ROOT_ITEN_KEY, new SugarCaneRootBlock(Block.Settings.create().registryKey(SUGAR_CANE_ROOT_BLOCK_KEY).mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().strength(0.4F).sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY)), List.of(ItemGroups.NATURAL));

	private static Block registerBlock(RegistryKey<Block> block_key, RegistryKey<Item> item_key, Block block, List<RegistryKey<ItemGroup>> itemGroupList) {
		Registry.register(Registries.ITEM, item_key, new BlockItem(block, new Item.Settings().registryKey(item_key)));
		for (RegistryKey<ItemGroup> itemGroup : itemGroupList) {
			ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> content.add(block));
		}
		return Registry.register(Registries.BLOCK, block_key, block);
	}

	public static void init() {
	}
}
