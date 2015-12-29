package karob.bigtrees.config.defaults;

import net.minecraft.init.Blocks;
import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.TreeConfiguration;

public class BigPineDefaults extends TreeConfiguration {
	public BigPineDefaults() {
		algorithm = Algorithm.BigPine;
		name = "Big Pine";
		minHeight = 18;
		maxHeight = 22;
		wood = Blocks.log;
		woodMeta = 1;
		leaf = Blocks.leaves;
		leafMeta = 1;
		baseBlock1 = Blocks.grass;
		baseBlock2 = Blocks.gravel;
		locality = 4;
		minLocality = 60;
		maxLocality = 80;
	}
}
