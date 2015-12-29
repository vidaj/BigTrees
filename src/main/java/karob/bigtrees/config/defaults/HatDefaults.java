package karob.bigtrees.config.defaults;

import net.minecraft.init.Blocks;
import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.TreeConfiguration;

public class HatDefaults extends TreeConfiguration{
	public HatDefaults() {
		algorithm = Algorithm.Hat;
		name = "Hat Tree";
		minHeight = 28;
		maxHeight = 32;
		wood = Blocks.log;
		woodMeta = 0;
		leaf = Blocks.leaves;
		leafMeta = 0;
		locality = 3;
		minLocality = 0;
		maxLocality = 90;
	}
}
