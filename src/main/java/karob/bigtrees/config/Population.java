package karob.bigtrees.config;

public class Population {
	
	public static final String TreesPerChunkConfigKey = "treesperchunk";
	
	public static final String PercentageChancePerTreeConfigKey = "percentagechancepertree";
	
	public static final Population NULL = new Population(0, 0);

	private int treesPerChunk;
	
	private int percentageChancePerTree;

	public Population(int percentageChancePerTree, int treesPerChunk) {
		this.percentageChancePerTree = percentageChancePerTree;
		this.treesPerChunk = treesPerChunk;
	}

	public int getTreesPerChunk() {
		return treesPerChunk;
	}

	public int getPercentageChancePerTree() {
		return percentageChancePerTree;
	}
	
	@Override
	public String toString() {
		return String.format("Population[treesPerChunk=%d, percentageChancePerTree=%d", treesPerChunk, percentageChancePerTree);
	}
}
