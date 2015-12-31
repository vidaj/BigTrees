package karob.bigtrees.generators;

import java.util.Random;

import karob.bigtrees.compat.BlockPos;
import karob.bigtrees.compat.WorldWrapper;

public class WorldGenBigBirch extends KWorldGenBigTree{

	public WorldGenBigBirch(boolean flag) {
		super(flag);
	}
	
	@Override
	public boolean generate(WorldWrapper world, Random random, BlockPos position)
    {
		heightAttenuation = 0.3D; //Trunk Percentage Height
        scaleWidth = 1.0D; //Branch Length
        trunkSize = 2; //Trunk Width
        heightLimitLimit = 3; //Height Variation
        leafDistanceLimit = 4; //Leaf Thickness
        
		return super.generate(world, random, position);
	}
}