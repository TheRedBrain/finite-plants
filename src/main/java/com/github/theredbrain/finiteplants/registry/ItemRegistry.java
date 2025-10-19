package com.github.theredbrain.finiteplants.registry;

import com.github.theredbrain.finiteplants.FinitePlants;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemRegistry {

	public static RegistryKey<Item> CUSTOM_SWEET_BERRIES_KEY = RegistryKey.of(RegistryKeys.ITEM, FinitePlants.identifier("custom_sweet_berries"));
	public static final Item CUSTOM_SWEET_BERRIES = registerItem(CUSTOM_SWEET_BERRIES_KEY, new Item(new Item.Settings()
			.registryKey(CUSTOM_SWEET_BERRIES_KEY)
			.food(FoodComponents.SWEET_BERRIES)
	), List.of(ItemGroups.NATURAL));

	private static Item registerItem(RegistryKey<Item> key, Item item, List<RegistryKey<ItemGroup>> itemGroupList) {
		for (RegistryKey<ItemGroup> itemGroup : itemGroupList) {
			ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> content.add(item));
		}
		return Registry.register(Registries.ITEM, key, item);
	}

	public static void init() {
	}
}
