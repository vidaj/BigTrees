package karob.bigtrees.generators;

import java.util.Random;

import net.minecraft.world.World;

public class WorldGenBigBirch extends KWorldGenBigTree{

	public WorldGenBigBirch(boolean flag) {
		super(flag);
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
    {
		type = 6;
		return super.generate(world, random, x, y, z);
	}
}