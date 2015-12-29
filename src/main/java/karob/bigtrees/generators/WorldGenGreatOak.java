package karob.bigtrees.generators;

import java.util.Random;

import net.minecraft.world.World;

public class WorldGenGreatOak extends KWorldGenBigTree{

	public WorldGenGreatOak(boolean flag) {
		super(flag);
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
    {
		type = 3;
		return super.generate(world, random, x, y, z);
	}
}
