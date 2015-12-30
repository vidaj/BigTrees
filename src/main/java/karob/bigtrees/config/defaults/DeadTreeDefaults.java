package karob.bigtrees.config.defaults;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.BlockAndMeta;
import karob.bigtrees.config.TreeConfiguration;

public class DeadTreeDefaults extends TreeConfiguration {
	public DeadTreeDefaults() {
		algorithm = Algorithm.Dead;
		name = "Dead Tree";
		minHeight = 13;
		maxHeight = 28;
		wood = new BlockAndMeta(Blocks.log, 0);
		leaf = new BlockAndMeta(Blocks.leaves, 0);
		baseBlocks = new ArrayList<BlockAndMeta>();
		baseBlocks.add(new BlockAndMeta(Blocks.grass));
		baseBlocks.add(new BlockAndMeta(Blocks.sand));
		locality = 1;
		minLocality = 0;
		maxLocality = 60;
	}
}
