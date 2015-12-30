package karob.bigtrees.config.defaults;

import net.minecraft.init.Blocks;
import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.BlockAndMeta;
import karob.bigtrees.config.TreeConfiguration;

public class SwampOakDefaults extends TreeConfiguration {
	public SwampOakDefaults() {
		algorithm = Algorithm.SwampOak;
		name = "Swamp Oak";
		minHeight = 28;
		maxHeight = 32;
		wood = new BlockAndMeta(Blocks.log, 0);
		leaf = new BlockAndMeta(Blocks.leaves, 0);
		minLocality = 55;
		maxLocality = 75;
	}
}
