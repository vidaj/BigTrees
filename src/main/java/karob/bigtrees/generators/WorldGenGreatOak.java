package karob.bigtrees.generators;

import java.util.Random;

import karob.bigtrees.compat.BlockPos;
import karob.bigtrees.compat.WorldWrapper;
import net.minecraft.world.World;

public class WorldGenGreatOak extends KWorldGenBigTree{

	public WorldGenGreatOak(boolean flag) {
		super(flag);
	}
	
	@Override
	public boolean generate(WorldWrapper world, Random random, BlockPos position)
    {
		heightAttenuation = 0.1D; //Trunk Percentage Height
        scaleWidth = 1.4D; //Branch Length
        trunkSize = 4; //Trunk Width
        heightLimitLimit = 4; //Height Variation
        leafDistanceLimit = 4; //Leaf Thickness
        
        return super.generate(world, random, position);
	}
}
