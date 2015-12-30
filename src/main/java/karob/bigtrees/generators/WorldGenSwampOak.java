package karob.bigtrees.generators;

import java.util.Random;

import net.minecraft.world.World;

public class WorldGenSwampOak extends KWorldGenBigTree{

	public WorldGenSwampOak(boolean flag) {
		super(flag);
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
    {
		heightAttenuation = 0.1D; //Trunk Percentage Height
        scaleWidth = 1.4D; //Branch Length
        trunkSize = 4; //Trunk Width
        heightLimitLimit = 4; //Height Variation
        leafDistanceLimit = 4; //Leaf Thickness
        
      //heightLimit = KTreeCfg.vary(rand,KTreeCfg.swoakHeight); //Tree Height
        heightAttenuation = 0.0D; //Lower branches on swamp trees.
        //trunkBlock = Block.getBlockById(KTreeCfg.swoakWoodType);
        //trunkMeta = KTreeCfg.swoakWoodMeta;
        //leafBlock = Block.getBlockById(KTreeCfg.swoakLeafType);
        //leafMeta = KTreeCfg.swoakLeafMeta;
        
		return super.generate(world, random, x, y, z);
	}
}
