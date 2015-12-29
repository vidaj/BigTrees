package karob.bigtrees.config.defaults;

import net.minecraft.init.Blocks;
import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.TreeConfiguration;

public class BlockOakDefaults extends TreeConfiguration{

	public BlockOakDefaults() {
		algorithm = Algorithm.BlockOak;
		name = "Block Oak";
		minHeight = 12;
		maxHeight = 18;
		wood = Blocks.log;
		woodMeta = 0;
		leaf = Blocks.leaves;
		leafMeta = 0;
		minLocality = 30;
		maxLocality = 50;
	}
}
