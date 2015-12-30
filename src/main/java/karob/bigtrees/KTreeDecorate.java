// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package karob.bigtrees;

import java.util.Random;

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
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class KTreeDecorate {
	public static int numberoftrees = 0;

	public KTreeDecorate() {
		// currentRealm = -12349;
	}

	public static boolean decorate(World world, Random rand, int posX, int posZ, BiomeGenBase biome) {
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
				int x = posX + rand.nextInt(16) + 8;
				int z = posZ + rand.nextInt(16) + 8;
				int y = world.getHeightValue(x, z);
				
				/* In case we wander outside out original biome, don't generate there.
				 * This should stop trees in desert edges and such.
				 */
				BiomeGenBase currentBiome = world.getBiomeGenForCoords(x, z);
				if (currentBiome.biomeID != biome.biomeID) {
					continue;
				}
				
				if (rand.nextInt(100) > biomePopulation.getPercentageChancePerTree()) {
					continue;
				}
				
				WorldGenerator generator = getGenerator(treeConfiguration.getAlgorithm());
				((ITreeConfigurable)generator).setTreeConfiguration(treeConfiguration);
				generator.generate(world, rand, x, y, z);
			}
		}

		return false;
	}
	

	private static Population getBiomePopulation(BiomeGenBase biome,
			TreeConfiguration treeConfiguration) {
		return KTreeCfgBiomes.getTreeDensityForBiomeType(biome, treeConfiguration);
	}
	
	private static WorldGenerator getGenerator(Algorithm algorithm) {
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
		int locality = treeConfiguration.getLocality();
		
		if (locality == 0) {
			return true;
		}
		
		if (locality > numberoftrees || locality < 1) {
			treeConfiguration.setLocality(0);
			return true;
		}
		
		return isInRange(currentTreeLocality, treeConfiguration.getMinLocality(), treeConfiguration.getMaxLocality());
	}
	
	private static boolean isInRange(int value, int min, int max) {
		return value > min && value < max;
	}
}
