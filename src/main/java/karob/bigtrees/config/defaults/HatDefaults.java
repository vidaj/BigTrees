package karob.bigtrees.config.defaults;

import net.minecraft.init.Blocks;
import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.BlockAndMeta;
import karob.bigtrees.config.TreeConfiguration;

public class HatDefaults extends TreeConfiguration{
	public HatDefaults() {
		algorithm = Algorithm.Hat;
		name = "Hat Tree";
		minHeight = 28;
		maxHeight = 32;
		wood = new BlockAndMeta(Blocks.log, 0);
		leaf = new BlockAndMeta(Blocks.leaves, 0);
		locality = 3;
		minLocality = 0;
		maxLocality = 90;
	}
}
