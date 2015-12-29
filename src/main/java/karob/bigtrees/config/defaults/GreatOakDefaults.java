package karob.bigtrees.config.defaults;

import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.TreeConfiguration;
import net.minecraft.init.Blocks;

public class GreatOakDefaults extends TreeConfiguration {

	public GreatOakDefaults() {
		algorithm = Algorithm.GreatOak;
		name = "Great Oak";
		minHeight = 28;
		maxHeight = 32;
		wood = Blocks.log;
		woodMeta = 0;
		leaf = Blocks.leaves;
		leafMeta = 0;
		minLocality = 45;
		maxLocality = 65;
	}

}
