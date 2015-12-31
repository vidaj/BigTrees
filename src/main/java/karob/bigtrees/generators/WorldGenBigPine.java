package karob.bigtrees.generators;

import java.util.Random;

import karob.bigtrees.compat.BlockPos;
import karob.bigtrees.compat.WorldWrapper;
import net.minecraft.world.World;

public class WorldGenBigPine extends KWorldGenBigTree{

	public WorldGenBigPine(boolean flag) {
		super(flag);
	}
	
	@Override
	public boolean generate(WorldWrapper world, Random random, BlockPos position)
    {
		heightAttenuation = 0.3D; //Trunk Percentage Height
        scaleWidth = 1.2D; //Branch Length
        trunkSize = 3; //Trunk Width
        heightLimitLimit = 3; //Height Variation
        leafDistanceLimit = 4; //Leaf Thickness
        return super.generate(world, random, position);
	}
}