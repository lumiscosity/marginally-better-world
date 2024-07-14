package com.lumiscosity.mbworld.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FallenLogFeature extends Feature<FallenLogFeatureConfig> {
    public FallenLogFeature(Codec<FallenLogFeatureConfig> codec) {
        super(codec);
    }

    public void push_to_surface(BlockPos.Mutable mutable, StructureWorldAccess world) {
        while (!world.getBlockState(mutable).isReplaceable() || mutable.getY() >= world.getTopY()) {
            mutable.move(Direction.UP, 1);
        }
    }

    @Override
    public boolean generate(FeatureContext<FallenLogFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        FallenLogFeatureConfig config = context.getConfig();
        int i = random.nextBetween(config.min_size, config.max_size);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        if (!this.canGenerate(world, blockPos, i, mutable, config)) {
            return false;
        } else {
            // stump
            mutable.set(blockPos);
            if (!world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) {
                this.setBlockState(world, mutable, config.stump.get(random, mutable));
            }
            // log
            Direction direction = Direction.byId(random.nextBetween(2,5));
            mutable.move(direction, random.nextBetween(1, 2));

            for (int j = 0; j <= i; j++) {
                mutable.move(direction, 1);
                push_to_surface(mutable, world);
                setBlockState(world, mutable, config.log.get(random, mutable));
                if (random.nextFloat() <= config.mushroomChance) {
                    mutable.move(Direction.UP, 1);
                    setBlockState(world, mutable, config.mushroomBlock.get(random, mutable));
                    mutable.move(Direction.DOWN, 1);
                }
            }
            return true;
        }
    }

    protected boolean canGenerate(StructureWorldAccess world, BlockPos pos, int size, BlockPos.Mutable mutablePos, FallenLogFeatureConfig config) {
        int i = pos.getY();
        if (i >= world.getBottomY() + 1) {
            return config.allowedPlacement.test(world, pos.down());
        } else {
            return false;
        }
    }
}
