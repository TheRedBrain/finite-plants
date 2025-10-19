package com.github.theredbrain.finiteplants.mixin.block;

import com.github.theredbrain.finiteplants.registry.BlockRegistry;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {

	@WrapOperation(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
	protected boolean finiteplants$randomTick(BlockState instance, Block block, Operation<Boolean> original) {
		return original.call(instance, block) || instance.isOf(BlockRegistry.SUGAR_CANE_ROOT);
	}

	@WrapMethod(method = "canPlaceAt")
	protected boolean finiteplants$canPlaceAt(BlockState state, WorldView world, BlockPos pos, Operation<Boolean> original) {
		BlockState blockState = world.getBlockState(pos.down());
		return blockState.isOf(Blocks.SUGAR_CANE) || blockState.isOf(BlockRegistry.SUGAR_CANE_ROOT);
	}
}
