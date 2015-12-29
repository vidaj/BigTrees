package karob.bigtrees.generators;

import java.util.Random;

import net.minecraft.world.World;

public class WorldGenDesertTree extends KWorldGenBigTree{

	public WorldGenDesertTree(boolean doBlockNotify) {
		super(doBlockNotify);
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		type = 1;
		return super.generate(world, random, i, j, k);
	}
	
	

}
