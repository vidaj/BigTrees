package karob.bigtrees.config.defaults;

import net.minecraft.init.Blocks;
import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.TreeConfiguration;

public class DeadTreeDefaults extends TreeConfiguration {
	public DeadTreeDefaults() {
		algorithm = Algorithm.Dead;
		name = "Dead Tree";
		minHeight = 13;
		maxHeight = 28;
		wood = Blocks.log;
		woodMeta = 0;
		leaf = Blocks.leaves;
		leafMeta = 0;
		baseBlock1 = Blocks.grass;
		baseBlock2 = Blocks.sand;
		locality = 1;
		minLocality = 0;
		maxLocality = 60;
	}
}
