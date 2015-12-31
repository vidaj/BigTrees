package karob.bigtrees.compat;

import java.util.Random;

public interface IWorldGenerator {

	public boolean generate(WorldWrapper world, Random random, BlockPos pos);
}
