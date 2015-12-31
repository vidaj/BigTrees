package karob.bigtrees.config.defaults;

import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.BlockAndMeta;
import karob.bigtrees.config.TreeConfiguration;
import net.minecraft.init.Blocks;

public class CyprusDefaults extends TreeConfiguration {

	public CyprusDefaults() {
		algorithm = Algorithm.Cyprus;
		name = "Cyprus Oak";
		minHeight = 28;
		maxHeight = 32;
		wood = new BlockAndMeta(Blocks.log, 1);
		leaf = new BlockAndMeta(Blocks.leaves, 1);
		minNoiseValue = 0;
		maxNoiseValue = 90;
	}
}
