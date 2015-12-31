// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package karob.bigtrees;

import java.util.Random;

import karob.bigtrees.compat.BlockPos;
import karob.bigtrees.compat.IWorldGenerator;
import karob.bigtrees.compat.WorldWrapper;
import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.ITreeConfigurable;
import karob.bigtrees.config.Population;
import karob.bigtrees.config.TreeConfiguration;
import karob.bigtrees.generators.KWorldGenCyprusTree;
import karob.bigtrees.generators.KWorldGenHatTree;
import karob.bigtrees.generators.KWorldGenTallTree;
import karob.bigtrees.generators.WorldGenBigBirch;
import karob.bigtrees.generators.WorldGenBigPine;
import karob.bigtrees.generators.WorldGenBlockOak;
import karob.bigtrees.generators.WorldGenDesertTree;
import karob.bigtrees.generators.WorldGenGreatOak;
import karob.bigtrees.generators.WorldGenSwampOak;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.FMLLog;

public class KTreeDecorate {
	public static int numberoftrees = 0;

	public KTreeDecorate() {
		// currentRealm = -12349;
	}

	public static boolean decorate(WorldWrapper world, Random rand, BlockPos position, BiomeGenBase biome) {
		int posX = position.getX();
		int posZ = position.getY();
		
		numberoftrees = KTreeCfgTrees.getTreeConfigurations().size();
		int seed = (int) world.getSeed();
		
		int i = 0;
		for (TreeConfiguration treeConfiguration : KTreeCfgTrees.getTreeConfigurations()) {
			Population biomePopulation = getBiomePopulation(biome, treeConfiguration);
			
			int currentTreeLocality = KGetLocality.locality(posX + rand.nextInt(16) + 8, posZ + rand.nextInt(16) + 8, seed + 128 * i++ + 64, 168);
			
			if (!shouldGenerateTree(treeConfiguration, currentTreeLocality)) {
				continue;
			}

			for (int currentTree = 0; currentTree < biomePopulation.getTreesPerChunk(); currentTree++) {
				BlockPos treePosition = position.random2dChunkMove(rand);
				treePosition = world.getHeight(treePosition);
				
				/* In case we wander outside out original biome, don't generate there.
				 * This should stop trees in desert edges and such.
				 */
				BiomeGenBase currentBiome = world.getBiomeGenForCoords(treePosition);
				if (currentBiome.biomeID != biome.biomeID) {
					continue;
				}
				
				if (rand.nextInt(100) > biomePopulation.getPercentageChancePerTree()) {
					continue;
				}
				
				FMLLog.info("[BigTrees] Generating at %s, tree: %s, wood: %s, leaves: %s", treePosition, treeConfiguration.getName(), treeConfiguration.getWood(), treeConfiguration.getLeaf());
				
				IWorldGenerator generator = getGenerator(treeConfiguration.getAlgorithm());
				((ITreeConfigurable)generator).setTreeConfiguration(treeConfiguration);
				generator.generate(world, rand, treePosition);
			}
		}

		return false;
	}
	

	private static Population getBiomePopulation(BiomeGenBase biome,
			TreeConfiguration treeConfiguration) {
		return KTreeCfgBiomes.getTreeDensityForBiomeType(biome, treeConfiguration);
	}
	
	private static IWorldGenerator getGenerator(Algorithm algorithm) {
		switch (algorithm) {
		case TallOak:
			return new KWorldGenTallTree(false);
		case BlockOak:
			return new WorldGenBlockOak(false);
		case GreatOak:
			return new WorldGenGreatOak(false); 
		case SwampOak:
			return new WorldGenSwampOak(false);
		case BigPine:
			return new WorldGenBigPine(false);
		case BigBirch:
			return new WorldGenBigBirch(false);
		case Dead:
			return new WorldGenDesertTree(false);
		case Cyprus:
			return new KWorldGenCyprusTree(false);
		case Hat:
			return new KWorldGenHatTree(false);
		}
		
		throw new IllegalArgumentException("Unknown algorithm: " + algorithm.toString());
	}

	private static boolean shouldGenerateTree(TreeConfiguration treeConfiguration, int currentTreeLocality) {
		if (!treeConfiguration.hasNoiseField()) {
			return true;
		}
		
		return isInRange(currentTreeLocality, treeConfiguration.getMinNoiseValue(), treeConfiguration.getMaxNoiseValue());
	}
	
	private static boolean isInRange(int value, int min, int max) {
		return value > min && value < max;
	}
}
