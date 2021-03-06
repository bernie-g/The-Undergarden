package quek.undergarden.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractSmallTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class SmogstemFeature extends AbstractSmallTreeFeature<TreeFeatureConfig> {

    public SmogstemFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> p_i225820_1_) {
        super(p_i225820_1_);
    }

    @Override
    public boolean func_225557_a_(IWorldGenerationReader generationReader, Random rand, BlockPos pos, Set<BlockPos> trunk, Set<BlockPos> leaves, MutableBoundingBox boundingBox, TreeFeatureConfig config) {
        int i = config.baseHeight + rand.nextInt(config.heightRandA + 1) + rand.nextInt(config.heightRandB + 1);
        int j = config.trunkHeight >= 0 ? config.trunkHeight + rand.nextInt(config.trunkHeightRandom + 1) : i - (config.foliageHeight + rand.nextInt(config.foliageHeightRandom + 1));
        int k = config.foliagePlacer.func_225573_a_(rand, j, i, config);
        Optional<BlockPos> optional = this.func_227212_a_(generationReader, i, j, k, pos, config);
        if (!optional.isPresent()) {
            return false;
        } else {
            BlockPos blockpos = optional.get();
            this.setDirtAt(generationReader, blockpos.down(), blockpos);
            config.foliagePlacer.func_225571_a_(generationReader, rand, config, i, j, k, blockpos, leaves);
            this.func_227213_a_(generationReader, rand, i, blockpos, config.trunkTopOffset + rand.nextInt(config.trunkTopOffsetRandom + 1), trunk, boundingBox, config);
            return true;
        }
    }
}
