package karob.bigtrees.config.defaults;

import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.BlockAndMeta;
import karob.bigtrees.config.TreeConfiguration;
import net.minecraft.init.Blocks;

public class BigPineDefaults extends TreeConfiguration {
	public BigPineDefaults() {
		algorithm = Algorithm.BigPine;
		name = "Big Pine";
		minHeight = 18;
		maxHeight = 22;
		wood = new BlockAndMeta(Blocks.log, 1);
		leaf = new BlockAndMeta(Blocks.leaves, 1);
		baseBlocks.add(new BlockAndMeta(Blocks.gravel));
		baseBlocks.add(new BlockAndMeta(Blocks.dirt, 2));
		locality = 4;
		minLocality = 60;
		maxLocality = 80;
	}
}
