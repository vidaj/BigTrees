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
		heightAttenuation = 0.1D; //Trunk Percentage Height
        scaleWidth = 1.4D; //Branch Length
        trunkSize = 4; //Trunk Width
        heightLimitLimit = 4; //Height Variation
        leafDistanceLimit = 4; //Leaf Thickness
        
		return super.generate(world, random, x, y, z);
	}
}
