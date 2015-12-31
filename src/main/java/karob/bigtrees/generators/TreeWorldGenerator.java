package karob.bigtrees.generators;

import java.util.Random;

import karob.bigtrees.KTreeDecorate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class TreeWorldGenerator implements IWorldGenerator{

	
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
//		int x = (chunkX * 16) + random.nextInt(16);
//		int z = (chunkZ * 16) + random.nextInt(16);
//		
//		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
////		FMLLog.info("[BigTrees] Generating for x: " + x + ", z: " + z + ", Biome: " + biome.biomeName);
//		
//		KTreeDecorate.decorate(world, random, x, z, biome);
	}

}
