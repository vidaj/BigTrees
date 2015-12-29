package karob.bigtrees.config.defaults;

import net.minecraft.init.Blocks;
import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.TreeConfiguration;

public class CyprusDefaults extends TreeConfiguration {

	public CyprusDefaults() {
		algorithm = Algorithm.Cyprus;
		name = "Cyprus Oak";
		minHeight = 28;
		maxHeight = 32;
		wood = Blocks.log;
		woodMeta = 1;
		leaf = Blocks.leaves;
		leafMeta = 1;
		locality = 2;
		minLocality = 0;
		maxLocality = 90;
	}
}
