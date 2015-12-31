package karob.bigtrees.config.defaults;

import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.BlockAndMeta;
import karob.bigtrees.config.TreeConfiguration;
import net.minecraft.init.Blocks;

public class HatDefaults extends TreeConfiguration{
	public HatDefaults() {
		algorithm = Algorithm.Hat;
		name = "Hat Tree";
		minHeight = 28;
		maxHeight = 32;
		wood = new BlockAndMeta(Blocks.log, 0);
		leaf = new BlockAndMeta(Blocks.leaves, 0);
		minNoiseValue = 0;
		maxNoiseValue = 90;
	}
}
