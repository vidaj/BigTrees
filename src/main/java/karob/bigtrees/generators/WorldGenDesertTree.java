package karob.bigtrees.generators;

import java.util.Random;

import karob.bigtrees.compat.BlockPos;
import karob.bigtrees.compat.WorldWrapper;
import net.minecraft.world.World;

public class WorldGenDesertTree extends KWorldGenBigTree {

	public WorldGenDesertTree(boolean doBlockNotify) {
		super(doBlockNotify);
	}

	@Override
	public boolean generate(WorldWrapper world, Random random, BlockPos position){
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

		return super.generate(world, random, position);
	}

}
