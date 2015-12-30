package karob.bigtrees.config;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;

public class TreeConfiguration {
	
	protected Algorithm algorithm;

	protected String name;

	protected int minHeight;

	protected int maxHeight;

	protected int frequencyMultiplier;

	protected BlockAndMeta wood;

	protected BlockAndMeta leaf;

	protected int minStunt;
	
	protected List<BlockAndMeta> baseBlocks;

	protected int locality;

	protected int minLocality;

	protected int maxLocality;
	
	//
	// For TallOak only
	//
	
	protected double minBranchless;

	protected double maxBranchless;

	protected double longestBranchPercentage;

	protected double branchRotation;

	protected double tapLength;

	protected double branchSpace;

	protected double pitch;

	protected double curl;

	protected int leafRadius;

	protected double subBranchDensity;

	protected double subBranchingLength;

	protected int subBranchingSize;

	protected double subBranchAngle;

	protected double subBranchSize;
	
	//
	// End for TallOak only
	//
	
	public TreeConfiguration() {
		frequencyMultiplier = 10;
		minStunt = 7;
		baseBlocks = new ArrayList<BlockAndMeta>();
		baseBlocks.add(new BlockAndMeta(Blocks.grass));
		baseBlocks.add(new BlockAndMeta(Blocks.dirt));
		locality = 1;
	}
	
	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public String getName() {
		return name;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public int getFrequencyMultiplier() {
		return frequencyMultiplier;
	}

	public BlockAndMeta getWood() {
		return wood;
	}

	public BlockAndMeta getLeaf() {
		return leaf;
	}

	public int getMinStunt() {
		return minStunt;
	}

	public List<BlockAndMeta> getBaseBlocks() {
		return baseBlocks;
	}

	public int getLocality() {
		return locality;
	}

	public int getMinLocality() {
		return minLocality;
	}

	public int getMaxLocality() {
		return maxLocality;
	}
	public double getMinBranchless() {
		return minBranchless;
	}

	public double getMaxBranchless() {
		return maxBranchless;
	}

	public double getLongestBranchPercentage() {
		return longestBranchPercentage;
	}

	public double getBranchRotation() {
		return branchRotation;
	}

	public double getTapLength() {
		return tapLength;
	}

	public double getBranchSpace() {
		return branchSpace;
	}

	public double getPitch() {
		return pitch;
	}

	public double getCurl() {
		return curl;
	}

	public int getLeafRadius() {
		return leafRadius;
	}

	public double getSubBranchDensity() {
		return subBranchDensity;
	}

	public double getSubBranchingLength() {
		return subBranchingLength;
	}

	public int getSubBranchingSize() {
		return subBranchingSize;
	}

	public double getSubBranchAngle() {
		return subBranchAngle;
	}

	public double getSubBranchSize() {
		return subBranchSize;
	}
	
	public void readConfig(Configuration config) {
		TreeConfiguration defaults = this;
		algorithm = Algorithm.valueOf(config.get("Tree Configuration Settings", "Algorithm" , defaults.algorithm == null ? "" : defaults.algorithm.name() ,"TallOak, BlockOak, GreatOak, SwampOak, Cyprus, Hat, BigPine, BigBirch, or Dead").getString());
		name = config
				.get("Tree Configuration Settings", "Unique Tree Name",
						defaults.getName(),
						"If it's not different from every other defined tree, settings will mix")
				.getString();
		minHeight = config.get("Tree Configuration Settings",
				"Height Min", defaults.getMinHeight(), "Minimum height in blocks").getInt();
		maxHeight = config.get("Tree Configuration Settings",
				"Height Max", defaults.getMaxHeight(), "Maximum height in blocks").getInt();
		frequencyMultiplier = config.get("Tree Configuration Settings",
				"Frequency", defaults.getFrequencyMultiplier(),
				"Used to generate default biome group densities").getInt();
		String woodName = config
				.get("Tree Configuration Settings",
						"Wood Name",
						getBlockString(defaults.getWood()),
						"Minecraft names are minecraft:log:meta and minecraft:log2:meta. For mod-added names, use modID:name, e.g., BiomesOPlenty:logs1. Meta values: 0-3 for upward facing logs. For wood name log:0=oak, 1=pine, 2=birch, 3=jungle. For log2, 0=acacia, 1=darkoak.")
				.getString();
		wood = new BlockAndMeta(woodName);
//		woodMeta = config
//				.get("Tree Configuration Settings",
//						"Wood Meta",
//						defaults.getWoodMeta(),
//						"0-3 for upward facing logs. For wood name log:0=oak, 1=pine, 2=birch, 3=jungle. For log2, 0=acacia, 1=darkoak.")
//				.getInt();
		String leafName = config
				.get("Tree Configuration Settings",
						"Leaf Name",
						getBlockString(defaults.getLeaf()),
						"Minecraft names are minecraft:leaves:meta and minecraft:leaves2:meta. For mod-added names, use modID:name, e.g., BiomesOPlenty:leaves1. Meta values: 0-3. For leaf name leaves:0=oak, 1=pine, 2=birch, 3=jungle. For leaves2, 0=acacia, 1=darkoak.")
				.getString();
		leaf = new BlockAndMeta(leafName);
//		leafMeta = config
//				.get("Tree Configuration Settings",
//						"Leaf Meta",
//						defaults.getLeafMeta(),
//						"values are 0-3. For leaf name leaves:0=oak, 1=pine, 2=birch, 3=jungle. For leaves2, 0=acacia, 1=darkoak.")
//				.getInt();
		minStunt = config
				.get("Tree Configuration Settings",
						"Stunt Minimum",
						defaults.getMinStunt(),
						"Trees are stunted when blocked by overhead obstacles, but no shorter than this value.")
				.getInt();
		
		
		String[] baseblockNames = config.get("Tree Configuration Settings",
				"Base Blocks", getBaseBlockNames(defaults.getBaseBlocks()), "block that the tree can grow on")
				.getStringList();
		baseBlocks = getBlocksFromNames(baseblockNames);
		
		locality = config
				.get("Tree Configuration Settings",
						"Locality Distribution Type",
						defaults.getLocality(),
						"noise field to determine where in biome tree can grow. 1 through number of trees. Any other integer to disable tree noise.")
				.getInt();
		minLocality = config.get("Tree Configuration Settings",
				"Locality Min", defaults.getMinLocality(), "0 to 144").getInt();
		maxLocality = config.get("Tree Configuration Settings",
				"Locality Max", defaults.getMaxLocality(), "0 to 144").getInt();
		
		if (algorithm == Algorithm.TallOak) {
			minBranchless = Double.parseDouble(config.get("Tree Configuration Settings", "Branchless Min", defaults.minBranchless,"Fraction of tree height which has no branches").getString());
			maxBranchless = Double.parseDouble(config.get("Tree Configuration Settings", "Branchless Max", defaults.maxBranchless,"Fraction of tree height which has no branches").getString());
			longestBranchPercentage = Double.parseDouble(config.get("Tree Configuration Settings", "Longest Branch Percentage", defaults.longestBranchPercentage,"as a percentage of tree height").getString());
			branchRotation = Double.parseDouble(config.get("Tree Configuration Settings", "Branch Rotation", defaults.branchRotation,"1.0 is a full rotation. The default is the golden mean: (sqrt(5)-1)/2").getString());
			tapLength = Double.parseDouble(config.get("Tree Configuration Settings", "Tap Root Length", defaults.tapLength,"Tap root length, as a percentage of tree height. Tap roots grow through wood, leaves, air, water, dirt, sand, and gravel.").getString());
			branchSpace = Double.parseDouble(config.get("Tree Configuration Settings", "Branch Spacing", defaults.branchSpace,"0 to 1, abstractly defines average spacing between branches").getString());
			pitch = Double.parseDouble(config.get("Tree Configuration Settings", "Branch Pitch", defaults.pitch,"Defines the initial growth angle of a branch. 0=horizontal, 1=45 degrees upwards, -1=45 deg down...").getString());
			curl = Double.parseDouble(config.get("Tree Configuration Settings", "Branch Curl", defaults.curl,"Defines the curling growth of a branch. Positive makes the branch curve upwards.").getString());
			leafRadius = config.get("Tree Configuration Settings", "Leaf Radius", defaults.leafRadius,"0 = no leaves A radius of 1, 2, or 3 grows non-intrusively. (They won't grow through walls and such.) Leaf radii of 4 or greater can grow through walls, though they won't destroy the walls. Leaves too far from wood will decay. Note leaves must have 4 added to their meta value to prevent them from decaying.").getInt();
			subBranchDensity = Double.parseDouble(config.get("Tree Configuration Settings", "SubBranch Density", defaults.subBranchDensity,"Density of branching off of branches.").getString());
			subBranchingLength = Double.parseDouble(config.get("Tree Configuration Settings", "Sub Branching Length", defaults.subBranchingLength,"Percent length of branch that can grow sub branches.").getString());
			subBranchingSize = config.get("Tree Configuration Settings", "Sub Branching Size", defaults.subBranchingSize,"Minimum branch size to grow sub branches, in meters/blocks.").getInt();
			subBranchAngle = Double.parseDouble(config.get("Tree Configuration Settings", "Sub Branch Angle", defaults.subBranchAngle,"Angle between branch and sub branches, degrees.").getString());
			subBranchSize = Double.parseDouble(config.get("Tree Configuration Settings", "Sub Branch Size", defaults.subBranchSize,"Size of sub branch as a percent of remaining length of parent branch.").getString());
		}
	}
	
	private String[] getBaseBlockNames(List<BlockAndMeta> baseBlocks) {
		List<String> result = new LinkedList<String>();
		
		for (BlockAndMeta block : baseBlocks) {
			result.add(block.toString());
		}
		
		return result.toArray(new String[result.size()]);
	}
	
	private String getBlockString(BlockAndMeta blockAndMeta) {
		if (blockAndMeta == null) {
			return "";
		}
		
		return blockAndMeta.toString();
	}
	
	private List<BlockAndMeta> getBlocksFromNames(String[] blockNames) {
		List<BlockAndMeta> result = new LinkedList<BlockAndMeta>();
		
		for (String blockName : blockNames) {
			result.add(new BlockAndMeta(blockName));
		}
		
		return result;
	}

	public void setLocality(int locality) {
		this.locality = locality;
	}
}
