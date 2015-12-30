package karob.bigtrees.generators;

import java.util.Random;

import karob.bigtrees.KTreeCfg;
import net.minecraft.world.World;

public class WorldGenBigBirch extends KWorldGenBigTree{

	public WorldGenBigBirch(boolean flag) {
		super(flag);
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
    {
		type = 6;
		heightAttenuation = 0.3D; //Trunk Percentage Height
        scaleWidth = 1.0D; //Branch Length
        trunkSize = 2; //Trunk Width
        heightLimitLimit = 3; //Height Variation
        leafDistanceLimit = 4; //Leaf Thickness
        
        heightLimit = KTreeCfg.vary(rand,KTreeCfg.birchHeight);
		return super.generate(world, random, x, y, z);
	}
}