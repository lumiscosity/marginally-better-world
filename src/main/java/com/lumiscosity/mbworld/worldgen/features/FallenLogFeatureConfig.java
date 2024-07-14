package com.lumiscosity.mbworld.worldgen.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class FallenLogFeatureConfig implements FeatureConfig {

    public static final Codec<FallenLogFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            BlockPredicate.BASE_CODEC.fieldOf("allowed_placement").forGetter(fallenLogFeatureConfig -> fallenLogFeatureConfig.allowedPlacement),
                            BlockStateProvider.TYPE_CODEC.fieldOf("log").forGetter(fallenLogFeatureConfig -> fallenLogFeatureConfig.log),
                            BlockStateProvider.TYPE_CODEC.fieldOf("stump").forGetter(fallenLogFeatureConfig -> fallenLogFeatureConfig.stump),
                            Codec.INT.fieldOf("min_size").forGetter(fallenLogFeatureConfig -> fallenLogFeatureConfig.min_size),
                            Codec.INT.fieldOf("max_size").forGetter(fallenLogFeatureConfig -> fallenLogFeatureConfig.max_size),
                            Codec.FLOAT.fieldOf("mushroom_chance").orElse(0F).forGetter(fallenLogFeatureConfig -> fallenLogFeatureConfig.mushroomChance),
                            BlockStateProvider.TYPE_CODEC.fieldOf("mushroom_block").orElse(SimpleBlockStateProvider.of(Blocks.AIR)).forGetter(fallenLogFeatureConfig -> fallenLogFeatureConfig.mushroomBlock)
                    )
                    .apply(instance, FallenLogFeatureConfig::new)
    );

    public final BlockPredicate allowedPlacement;
    public final BlockStateProvider log;
    public final BlockStateProvider stump;
    public final int min_size;
    public final int max_size;
    public final float mushroomChance;
    public final BlockStateProvider mushroomBlock;

    public FallenLogFeatureConfig(BlockPredicate allowedPlacement, BlockStateProvider log, BlockStateProvider stump, int min_size, int max_size, float mushroomChance, BlockStateProvider mushroomBlock) {
        this.allowedPlacement = allowedPlacement;
        this.log = log;
        this.stump = stump;
        this.min_size = min_size;
        this.max_size = max_size;
        this.mushroomChance = mushroomChance;
        this.mushroomBlock = mushroomBlock;
    }
}
