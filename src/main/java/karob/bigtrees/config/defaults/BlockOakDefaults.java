package karob.bigtrees.config.defaults;

import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.BlockAndMeta;
import karob.bigtrees.config.TreeConfiguration;
import net.minecraft.init.Blocks;

public class BlockOakDefaults extends TreeConfiguration{

	public BlockOakDefaults() {
		algorithm = Algorithm.BlockOak;
		name = "Block Oak";
		minHeight = 12;
		maxHeight = 18;
		wood = new BlockAndMeta(Blocks.log, 0);
		leaf = new BlockAndMeta(Blocks.leaves, 0);
		minNoiseValue = 30;
		maxNoiseValue = 50;
	}
}
