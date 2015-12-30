package karob.bigtrees.config.defaults;

import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.BlockAndMeta;
import karob.bigtrees.config.TreeConfiguration;
import net.minecraft.init.Blocks;

public class TallOakTreeDefaults extends TreeConfiguration {
	public TallOakTreeDefaults() {
		algorithm = Algorithm.TallOak;
		name = "Tall Oak";
		minHeight = 13;
		maxHeight = 28;
		wood = new BlockAndMeta(Blocks.log, 0);
		leaf = new BlockAndMeta(Blocks.leaves, 0);
		locality = 1;
		minLocality = 0;
		maxLocality = 40;
		
		minBranchless = 0.23;
		maxBranchless = 0.32;
		longestBranchPercentage = 0.60;
		branchRotation = 0.618034;
		tapLength = 0.5;
		branchSpace = 0.6;
		pitch = 0.0;
		curl = 0.08;
		leafRadius = 3;
		subBranchDensity = 0.12;
		subBranchingLength = 0.60;
		subBranchingSize = 4;
		subBranchAngle = 30;
		subBranchSize = 0.75;
	}
}
