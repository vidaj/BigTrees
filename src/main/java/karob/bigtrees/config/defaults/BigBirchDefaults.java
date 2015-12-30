package karob.bigtrees.config.defaults;

import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.BlockAndMeta;
import karob.bigtrees.config.TreeConfiguration;
import net.minecraft.init.Blocks;

public class BigBirchDefaults extends TreeConfiguration {
	public BigBirchDefaults() {
		algorithm = Algorithm.BigBirch;
		name = "Big Birch";
		minHeight = 19;
		maxHeight = 24;
		wood = new BlockAndMeta(Blocks.log, 2);
		leaf = new BlockAndMeta(Blocks.leaves, 2);
		locality = 5;
		minLocality = 0;
		maxLocality = 60;
	}
}
