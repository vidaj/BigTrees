package karob.bigtrees.generators;

import java.util.Random;

import net.minecraft.world.World;

public class WorldGenDesertTree extends KWorldGenBigTree {

	public WorldGenDesertTree(boolean doBlockNotify) {
		super(doBlockNotify);
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		type = 1;

		heightAttenuation = 0.3D; // Trunk Percentage Height
		scaleWidth = 1.0D; // Branch Length
		trunkSize = 2; // Trunk Width
		heightLimitLimit = 3; // Height Variation
		leafDistanceLimit = 0; // Leaf Thickness

		if (trunkSize != 1) {
			scaleWidth = scaleWidth * 1.0D; // Double branch length on desert
											// trees.
			// heightLimit = KTreeCfg.vary(rand,KTreeCfg.deadHeight); //Tree
			// Height
		}
		leafDistanceLimit = 0; // No leaves on desert trees.
		// trunkBlock = Block.getBlockById(KTreeCfg.deadWoodType);
		// trunkMeta = KTreeCfg.deadWoodMeta;

		return super.generate(world, random, i, j, k);
	}

}
