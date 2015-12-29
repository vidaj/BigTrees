package karob.bigtrees.config.defaults;

import net.minecraft.init.Blocks;
import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.TreeConfiguration;

public class BigBirchDefaults extends TreeConfiguration {
	public BigBirchDefaults() {
		algorithm = Algorithm.BigBirch;
		name = "Big Birch";
		minHeight = 19;
		maxHeight = 24;
		wood = Blocks.log;
		woodMeta = 2;
		leaf = Blocks.leaves;
		leafMeta = 2;
		locality = 5;
		minLocality = 0;
		maxLocality = 60;
	}
}
