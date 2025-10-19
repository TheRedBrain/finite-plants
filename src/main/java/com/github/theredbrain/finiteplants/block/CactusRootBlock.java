package com.github.theredbrain.finiteplants.block;

import com.github.theredbrain.finiteplants.registry.Tags;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.block.WireOrientation;
import net.minecraft.world.tick.ScheduledTickView;

import java.util.Iterator;

public class CactusRootBlock extends Block {
	public static final MapCodec<CactusRootBlock> CODEC = createCodec(CactusRootBlock::new);
	public static final IntProperty AGE;
	public static final int MAX_AGE = 15;
	protected static final VoxelShape COLLISION_SHAPE;
	protected static final VoxelShape OUTLINE_SHAPE;

	@Override
	public MapCodec<CactusRootBlock> getCodec() {
		return CODEC;
	}

	public CactusRootBlock(Settings settings) {
		super(settings);
		this.setDefaultState((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(AGE, 0));
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!state.canPlaceAt(world, pos)) {
			world.breakBlock(pos, true);
		}

	}

	@Override
	protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		BlockPos blockPos = pos.up();
		if (world.isAir(blockPos)) {
			int i = 1;
			int j = (Integer) state.get(AGE);

//			while(world.getBlockState(pos.down(i)).isOf(this)) {
//				++i;
//				if (i == 3 && j == 15) {
//					return;
//				}
//			}

			if (j == 8 && this.canPlaceAt(this.getDefaultState(), world, pos.up())) {
				double d = i >= 3 ? 0.25 : 0.1;
				if (random.nextDouble() <= d) {
					world.setBlockState(blockPos, Blocks.CACTUS_FLOWER.getDefaultState());
				}
			} else if (j == 15 && i < 3) {
				world.setBlockState(blockPos, Blocks.CACTUS.getDefaultState());
				BlockState blockState = (BlockState) state.with(AGE, 0);
				world.setBlockState(pos, blockState, 260);
				world.updateNeighbor(blockState, blockPos, this, (WireOrientation) null, false);
			}

			if (j < 15) {
				world.setBlockState(pos, (BlockState) state.with(AGE, j + 1), 260);
			}

		}
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return COLLISION_SHAPE;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return OUTLINE_SHAPE;
	}

	@Override
	protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
		if (!state.canPlaceAt(world, pos)) {
			tickView.scheduleBlockTick(pos, this, 1);
		}

		return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		Iterator var4 = Direction.Type.HORIZONTAL.iterator();

		Direction direction;
		BlockState blockState;
		do {
			if (!var4.hasNext()) {
				BlockState blockState2 = world.getBlockState(pos.down());
				return blockState2.isIn(Tags.CACTUS_ROOTS_CAN_PLACE_ON) && !world.getBlockState(pos.up()).isLiquid();
			}

			direction = (Direction) var4.next();
			blockState = world.getBlockState(pos.offset(direction));
		} while (!blockState.isSolid() && !world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA));

		return false;
	}

	@Override
	protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean bl) {
		entity.serverDamage(world.getDamageSources().cactus(), 1.0F);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(new Property[]{AGE});
	}

	@Override
	protected boolean canPathfindThrough(BlockState state, NavigationType type) {
		return false;
	}

	static {
		AGE = Properties.AGE_15;
		COLLISION_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
		OUTLINE_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	}
}
