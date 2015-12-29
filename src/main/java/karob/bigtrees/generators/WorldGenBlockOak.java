package karob.bigtrees.generators;

import java.util.Random;

import net.minecraft.world.World;

public class WorldGenBlockOak extends KWorldGenBigTree{

	public WorldGenBlockOak(boolean flag) {
		super(flag);
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
    {
		type = 5;
		return super.generate(world, random, x, y, z);
	}
}
