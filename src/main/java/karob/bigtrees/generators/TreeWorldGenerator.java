package karob.bigtrees.generators;

import java.util.Random;

import karob.bigtrees.KTreeCfg;
import karob.bigtrees.KTreeDecorate;
import karob.bigtrees.compat.BlockPos;
import karob.bigtrees.compat.WorldWrapper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class TreeWorldGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World orgWorld,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16;
		int z = chunkZ * 16;
		
		WorldWrapper world = new WorldWrapper(orgWorld);

		int dimensionId = world.getDimensionId();
		if (!KTreeCfg.isValidDimension(dimensionId)) {
			return;
		}
		
		BlockPos position = new BlockPos(x, 0, z);
		BiomeGenBase biome = world.getBiomeGenForCoords(position);
		
		KTreeDecorate.decorate(world, random, position, biome);
	}

}
