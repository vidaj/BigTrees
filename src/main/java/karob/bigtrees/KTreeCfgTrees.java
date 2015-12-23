package karob.bigtrees;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;

public class KTreeCfgTrees 
{

	public static Configuration config;
	public static int count = 0;//total number of trees defined after init has run
	//for all trees
	public static String[] treeName;
	public static int[] heightmin;
	public static int[] heightmax;
	public static int[] frequencymultiplyer;
	public static String[] woodName;
	public static Block[] woodBlock;
	public static int[] woodMeta;
	public static String[] leafName;
	public static Block[] leafBlock;
	public static int[] leafMeta;
	public static int[] stuntmin;
	public static String[] basetype1;
	public static String[] basetype2;
	public static Block[] baseBlock1;
	public static Block[] baseBlock2;
	public static int[] locality;
	public static int[] localitymin;
	public static int[] localitymax;
	public static int[] algore;
	//quantity of each tree type
	public static int tallTrees = 0;
	public static int hatTrees = 0;
	public static int greatoakTrees = 0;
	public static int swampoakTrees = 0;
	public static int blockoakTrees = 0;
	public static int greatpineTrees = 0;
	public static int greatbirchTrees = 0;
	public static int deadTrees = 0;
	public static int cyprusTrees = 0;
	//for tall trees
	public static double[] branchlessmin;
	public static double[] branchlessmax;
	public static double[] longestbranchp;
	public static double[] branchrot;
	public static double[] taplength;
	public static double[] branchspace;
	public static double[] pitch;
	public static double[] curl;
	public static int[] leafrad;
	public static double[] subbranchdensity;
	public static double[] subbranchinglength;
	public static int[] subbranchingsize;
	public static double[] subbranchangle;
	public static double[] subbranchsize;
		
	public static void init(File configFile) 
	{
		File[] files = configFile.listFiles();
		if (files.length > 9){
		treeName = new String [files.length];
		heightmin = new int [files.length];
		heightmax = new int [files.length];
		frequencymultiplyer = new int [files.length];
		woodName = new String [files.length];
		woodBlock = new Block [files.length];
		woodMeta = new int [files.length];
		leafName = new String [files.length];
		leafBlock = new Block [files.length];
		leafMeta = new int [files.length];
		stuntmin = new int [files.length];
		basetype1 = new String [files.length];
		basetype2 = new String [files.length];
		baseBlock1 = new Block [files.length];
		baseBlock2 = new Block [files.length];
		locality = new int [files.length+1];
		localitymin = new int [files.length+1];
		localitymax = new int [files.length+1];
		algore = new int [files.length];
		branchlessmin = new double [files.length];
		branchlessmax = new double [files.length];
		longestbranchp = new double [files.length];
		branchrot = new double [files.length];
		taplength = new double [files.length];
		branchspace = new double [files.length];
		pitch = new double [files.length];
		curl = new double [files.length];
		leafrad = new int [files.length];
		subbranchdensity = new double [files.length];
		subbranchinglength = new double [files.length];
		subbranchingsize = new int [files.length];
		subbranchangle = new double [files.length];
		subbranchsize = new double [files.length];}
		else{
		treeName = new String [9];
		heightmin = new int [9];
		heightmax = new int [9];
		frequencymultiplyer = new int [9];
		woodName = new String [9];
		woodBlock = new Block [9];
		woodMeta = new int [9];
		leafName = new String [9];
		leafBlock = new Block [9];
		leafMeta = new int [9];
		stuntmin = new int [9];
		basetype1 = new String [9];
		basetype2 = new String [9];
		baseBlock1 = new Block [9];
		baseBlock2 = new Block [9];
		locality = new int [9];
		localitymin = new int [9];
		localitymax = new int [9];
		algore = new int [9];
		branchlessmin = new double [9];
		branchlessmax = new double [9];
		longestbranchp = new double [9];
		branchrot = new double [9];
		taplength = new double [9];
		branchspace = new double [9];
		pitch = new double [9];
		curl = new double [9];
		leafrad = new int [9];
		subbranchdensity = new double [9];
		subbranchinglength = new double [9];
		subbranchingsize = new int [9];
		subbranchangle = new double [9];
		subbranchsize = new double [9];}
		if (files.length == 0){
			File filename = new File(configFile + File.separator + "TallOak.cfg");
			config = new Configuration(filename);
			config.load();
			config.get("Tree Configuration Settings", "Algorithm" , "TallOak" ,"TallOak, BlockOak, GreatOak, SwampOak, Cyprus, Hat, BigPine, BigBirch, or Dead").getString();
			configureTallOak(config);
			filename = new File (configFile + File.separator + "BlockOak.cfg");
			config = new Configuration(filename);
			config.load();
			config.get("Tree Configuration Settings", "Algorithm" , "BlockOak" ,"TallOak, BlockOak, GreatOak, SwampOak, Cyprus, Hat, BigPine, BigBirch, or Dead").getString();
			configureBlockOak(config);
			filename = new File (configFile + File.separator + "GreatOak.cfg");
			config = new Configuration(filename);
			config.load();
			config.get("Tree Configuration Settings", "Algorithm" , "GreatOak" ,"TallOak, BlockOak, GreatOak, SwampOak, Cyprus, Hat, BigPine, BigBirch, or Dead").getString();
			configureGreatOak(config);
			filename = new File (configFile + File.separator + "SwampOak.cfg");
			config = new Configuration(filename);
			config.load();
			config.get("Tree Configuration Settings", "Algorithm" , "SwampOak" ,"TallOak, BlockOak, GreatOak, SwampOak, Cyprus, Hat, BigPine, BigBirch, or Dead").getString();
			configureSwampOak(config);
			filename = new File (configFile + File.separator + "Cyprus.cfg");
			config = new Configuration(filename);
			config.load();
			config.get("Tree Configuration Settings", "Algorithm" , "Cyprus" ,"TallOak, BlockOak, GreatOak, SwampOak, Cyprus, Hat, BigPine, BigBirch, or Dead").getString();
			configureCyprus(config);
			filename = new File (configFile + File.separator + "Hat.cfg");
			config = new Configuration(filename);
			config.load();
			config.get("Tree Configuration Settings", "Algorithm" , "Hat" ,"TallOak, BlockOak, GreatOak, SwampOak, Cyprus, Hat, BigPine, BigBirch, or Dead").getString();
			configureHat(config);
			filename = new File (configFile + File.separator + "BigPine.cfg");
			config = new Configuration(filename);
			config.load();
			config.get("Tree Configuration Settings", "Algorithm" , "BigPine" ,"TallOak, BlockOak, GreatOak, SwampOak, Cyprus, Hat, BigPine, BigBirch, or Dead").getString();
			configureBigPine(config);
			filename = new File (configFile + File.separator + "BigBirch.cfg");
			config = new Configuration(filename);
			config.load();
			config.get("Tree Configuration Settings", "Algorithm" , "BigBirch" ,"TallOak, BlockOak, GreatOak, SwampOak, Cyprus, Hat, BigPine, BigBirch, or Dead").getString();
			configureBigBirch(config);
			filename = new File (configFile + File.separator + "Dead.cfg");
			config = new Configuration(filename);
			config.load();
			config.get("Tree Configuration Settings", "Algorithm" , "Dead" ,"TallOak, BlockOak, GreatOak, SwampOak, Cyprus, Hat, BigPine, BigBirch, or Dead").getString();
			configureDead(config);
			tallTrees = 1;
			hatTrees = 1;
			greatoakTrees = 1;
			swampoakTrees = 1;
			blockoakTrees = 1;
			greatpineTrees = 1;
			greatbirchTrees = 1;
			deadTrees = 1;
			cyprusTrees = 1;
			count = 9;
		}
		else{
			for (int i = 0; i < files.length; i++){
				try{
				config = new Configuration(files[i]);
				String Algorithm = config.get("Tree Configuration Settings", "Algorithm" , "" ,"TallOak, BlockOak, GreatOak, SwampOak, Cyprus, Hat, BigPine, BigBirch, or Dead").getString();
				if (Algorithm.equals("TallOak")){configureTallOak(config);}
				else if (Algorithm.equals("BlockOak")){configureBlockOak(config);}
				else if (Algorithm.equals("GreatOak")){configureGreatOak(config);}
				else if (Algorithm.equals("SwampOak")){configureSwampOak(config);}
				else if (Algorithm.equals("Cyprus")){configureCyprus(config);}
				else if (Algorithm.equals("Hat")){configureHat(config);}
				else if (Algorithm.equals("BigPine")){configureBigPine(config);}
				else if (Algorithm.equals("BigBirch")){configureBigBirch(config);}
				else if (Algorithm.equals("Dead")){configureDead(config);}
				else {System.out.println("Problem reading Bigtrees tree config file:" + files[i].getName() + ", skipping it.");}
				}catch (Exception e){System.out.println("Problem reading Bigtrees tree config file:" + files[i].getName() + ", skipping it.");}
			}
		}
	}

	public static void configureTallOak(Configuration config)
	{	
		treeName[count] = config.get("Tree Configuration Settings", "Unique Tree Name" , "Tall Oak " + Integer.toString(tallTrees),"If it's not different from every other defined tree, settings will mix").getString();
		heightmin[count] = config.get("Tree Configuration Settings", "Height Min" , 13,"Minimum height in blocks").getInt();
		heightmax[count] = config.get("Tree Configuration Settings", "Height Max" , 28,"Maximum height in blocks").getInt();
		woodName[count] = config.get("Tree Configuration Settings", "Wood Name" , "log","Minecraft names are log and log2. For mod-added names, use modID:name, e.g., BiomesOPlenty:logs1.").getString();
		woodMeta[count] = config.get("Tree Configuration Settings", "Wood Meta" , 0,"0-3 for upward facing logs. For wood name log:0=oak, 1=pine, 2=birch, 3=jungle. For log2, 0=acacia, 1=darkoak.").getInt();
		frequencymultiplyer[count] = config.get("Tree Configuration Settings", "Frequency" , 10,"Used to generate default biome group densities").getInt();
		leafName[count] = config.get("Tree Configuration Settings", "Leaf Name" , "leaves","Minecraft names are leaves and leaves2. For mod-added names, use modID:name, e.g., BiomesOPlenty:leaves1.").getString();
		leafMeta[count] = config.get("Tree Configuration Settings", "Leaf Meta" , 0,"values are 0-3. For leaf name leaves:0=oak, 1=pine, 2=birch, 3=jungle. For leaves2, 0=acacia, 1=darkoak.").getInt();
		stuntmin[count] = config.get("Tree Configuration Settings", "Stunt Minimum", 7,"Trees are stunted when blocked by overhead obstacles, but no shorter than this value.").getInt();
		basetype1[count] = config.get("Tree Configuration Settings", "Base Type 1", "grass","Block tree can grow on").getString();
		basetype2[count] = config.get("Tree Configuration Settings", "Base Type 2", "dirt","Other block tree can grow on").getString();
		locality[count] = config.get("Tree Configuration Settings", "Locality Distribution Type", 1,"noise field to determine where in biome tree can grow. 1 through number of trees. Any other integer to disable tree noise.").getInt();
		localitymin[count] = config.get("Tree Configuration Settings", "Locality Min", 0,"0 to 144").getInt();
		localitymax[count] = config.get("Tree Configuration Settings", "Locality Max", 40,"0 to 144").getInt();
		branchlessmin[count] = Double.parseDouble(config.get("Tree Configuration Settings", "Branchless Min", 0.23,"Fraction of tree height which has no branches").getString());
		branchlessmax[count] = Double.parseDouble(config.get("Tree Configuration Settings", "Branchless Max", 0.32,"Fraction of tree height which has no branches").getString());
		longestbranchp[count] = Double.parseDouble(config.get("Tree Configuration Settings", "Longest Branch Percentage", 0.60,"as a percentage of tree height").getString());
		branchrot[count] = Double.parseDouble(config.get("Tree Configuration Settings", "Branch Rotation", 0.618034,"1.0 is a full rotation. The default is the golden mean: (sqrt(5)-1)/2").getString());
		taplength[count] = Double.parseDouble(config.get("Tree Configuration Settings", "Tap Root Length", 0.5,"Tap root length, as a percentage of tree height. Tap roots grow through wood, leaves, air, water, dirt, sand, and gravel.").getString());
		branchspace[count] = Double.parseDouble(config.get("Tree Configuration Settings", "Branch Spacing", 0.6,"0 to 1, abstractly defines average spacing between branches").getString());
		pitch[count] = Double.parseDouble(config.get("Tree Configuration Settings", "Branch Pitch", 0.0,"Defines the initial growth angle of a branch. 0=horizontal, 1=45 degrees upwards, -1=45 deg down...").getString());
		curl[count] = Double.parseDouble(config.get("Tree Configuration Settings", "Branch Curl", 0.08,"Defines the curling growth of a branch. Positive makes the branch curve upwards.").getString());
		leafrad[count] = config.get("Tree Configuration Settings", "Leaf Radius", 3,"0 = no leaves A radius of 1, 2, or 3 grows non-intrusively. (They won't grow through walls and such.) Leaf radii of 4 or greater can grow through walls, though they won't destroy the walls. Leaves too far from wood will decay. Note leaves must have 4 added to their meta value to prevent them from decaying.").getInt();
		subbranchdensity[count] = Double.parseDouble(config.get("Tree Configuration Settings", "SubBranch Density", 0.12,"Density of branching off of branches.").getString());
		subbranchinglength[count] = Double.parseDouble(config.get("Tree Configuration Settings", "Sub Branching Length", 0.60,"Percent length of branch that can grow sub branches.").getString());
		subbranchingsize[count] = config.get("Tree Configuration Settings", "Sub Branching Size", 4,"Minimum branch size to grow sub branches, in meters/blocks.").getInt();
		subbranchangle[count] = Double.parseDouble(config.get("Tree Configuration Settings", "Sub Branch Angle", 30,"Angle between branch and sub branches, degrees.").getString());
		subbranchsize[count] = Double.parseDouble(config.get("Tree Configuration Settings", "Sub Branch Size", 0.75,"Size of sub branch as a percent of remaining length of parent branch.").getString());
		String[] v = woodName[count].split(":");
		if (v.length == 2) {woodBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {woodBlock[count] = Block.getBlockFromName(v[0]);}
		v = leafName[count].split(":");
		if (v.length == 2) {leafBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {leafBlock[count] = Block.getBlockFromName(v[0]);}
		v = basetype1[count].split(":");
		if (v.length == 2) {baseBlock1[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock1[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock1[count] == null) {baseBlock1[count] = Blocks.dirt;}
		v = basetype2[count].split(":");
		if (v.length == 2) {baseBlock2[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock2[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock2[count] == null) {baseBlock2[count] = Blocks.grass;}
		algore[count] = 1;
		count++;
		tallTrees++;
		config.save();
	}

	public static void configureBlockOak(Configuration config)
	{
		treeName[count] = config.get("Tree Configuration Settings", "Unique Tree Name" , "Block Oak " + Integer.toString(blockoakTrees),"If it's not different from every other defined tree, settings will mix").getString();
		heightmin[count] = config.get("Tree Configuration Settings", "Height Min" , 12,"Minimum height in blocks").getInt();
		heightmax[count] = config.get("Tree Configuration Settings", "Height Max" , 18,"Maximum height in blocks").getInt();
		frequencymultiplyer[count] = config.get("Tree Configuration Settings", "Frequency" , 10,"Used to generate default biome group densities").getInt();
		woodName[count] = config.get("Tree Configuration Settings", "Wood Name" , "log","Minecraft names are log and log2. For mod-added names, use modID:name, e.g., BiomesOPlenty:logs1.").getString();
		woodMeta[count] = config.get("Tree Configuration Settings", "Wood Meta" , 0,"0-3 for upward facing logs. For wood name log:0=oak, 1=pine, 2=birch, 3=jungle. For log2, 0=acacia, 1=darkoak.").getInt();
		leafName[count] = config.get("Tree Configuration Settings", "Leaf Name" , "leaves","Minecraft names are leaves and leaves2. For mod-added names, use modID:name, e.g., BiomesOPlenty:leaves1.").getString();
		leafMeta[count] = config.get("Tree Configuration Settings", "Leaf Meta" , 0,"values are 0-3. For leaf name leaves:0=oak, 1=pine, 2=birch, 3=jungle. For leaves2, 0=acacia, 1=darkoak.").getInt();
		stuntmin[count] = config.get("Tree Configuration Settings", "Stunt Minimum", 7,"Trees are stunted when blocked by overhead obstacles, but no shorter than this value.").getInt();
		basetype1[count] = config.get("Tree Configuration Settings", "Base Type 1", "grass","block that the tree can grow on").getString();
		basetype2[count] = config.get("Tree Configuration Settings", "Base Type 2", "dirt","block that the tree can grow on").getString();
		locality[count] = config.get("Tree Configuration Settings", "Locality Distribution Type", 1,"noise field to determine where in biome tree can grow. 1 through number of trees. Any other integer to disable tree noise.").getInt();
		localitymin[count] = config.get("Tree Configuration Settings", "Locality Min", 30,"0 to 144").getInt();
		localitymax[count] = config.get("Tree Configuration Settings", "Locality Max", 50,"0 to 144").getInt();
		String[] v = woodName[count].split(":");
		if (v.length == 2) {woodBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {woodBlock[count] = Block.getBlockFromName(v[0]);}
		v = leafName[count].split(":");
		if (v.length == 2) {leafBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {leafBlock[count] = Block.getBlockFromName(v[0]);}
		v = basetype1[count].split(":");
		if (v.length == 2) {baseBlock1[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock1[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock1[count] == null) {baseBlock1[count] = Blocks.dirt;}
		v = basetype2[count].split(":");
		if (v.length == 2) {baseBlock2[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock2[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock2[count] == null) {baseBlock2[count] = Blocks.grass;}
		algore[count] = 2;
		count++;
		blockoakTrees++;
		config.save();
	}

	public static void configureGreatOak(Configuration config)
	{
		treeName[count] = config.get("Tree Configuration Settings", "Unique Tree Name" , "Great Oak " + Integer.toString(greatoakTrees),"If it's not different from every other defined tree, settings will mix").getString();
		heightmin[count] = config.get("Tree Configuration Settings", "Height Min" , 28,"Minimum height in blocks").getInt();
		heightmax[count] = config.get("Tree Configuration Settings", "Height Max" , 32,"Maximum height in blocks").getInt();
		frequencymultiplyer[count] = config.get("Tree Configuration Settings", "Frequency" , 10,"Used to generate default biome group densities").getInt();
		woodName[count] = config.get("Tree Configuration Settings", "Wood Name" , "log","Minecraft names are log and log2. For mod-added names, use modID:name, e.g., BiomesOPlenty:logs1.").getString();
		woodMeta[count] = config.get("Tree Configuration Settings", "Wood Meta" , 0,"0-3 for upward facing logs. For wood name log:0=oak, 1=pine, 2=birch, 3=jungle. For log2, 0=acacia, 1=darkoak.").getInt();
		leafName[count] = config.get("Tree Configuration Settings", "Leaf Name" , "leaves","Minecraft names are leaves and leaves2. For mod-added names, use modID:name, e.g., BiomesOPlenty:leaves1.").getString();
		leafMeta[count] = config.get("Tree Configuration Settings", "Leaf Meta" , 0,"values are 0-3. For leaf name leaves:0=oak, 1=pine, 2=birch, 3=jungle. For leaves2, 0=acacia, 1=darkoak.").getInt();
		stuntmin[count] = config.get("Tree Configuration Settings", "Stunt Minimum", 7,"Trees are stunted when blocked by overhead obstacles, but no shorter than this value.").getInt();
		basetype1[count] = config.get("Tree Configuration Settings", "Base Type 1", "grass","block that the tree can grow on").getString();
		basetype2[count] = config.get("Tree Configuration Settings", "Base Type 2", "dirt","block that the tree can grow on").getString();
		locality[count] = config.get("Tree Configuration Settings", "Locality Distribution Type", 1,"noise field to determine where in biome tree can grow. 1 through number of trees. Any other integer to disable tree noise.").getInt();
		localitymin[count] = config.get("Tree Configuration Settings", "Locality Min", 45,"0 to 144").getInt();
		localitymax[count] = config.get("Tree Configuration Settings", "Locality Max", 65,"0 to 144").getInt();
		String[] v = woodName[count].split(":");
		if (v.length == 2) {woodBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {woodBlock[count] = Block.getBlockFromName(v[0]);}
		v = leafName[count].split(":");
		if (v.length == 2) {leafBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {leafBlock[count] = Block.getBlockFromName(v[0]);}
		v = basetype1[count].split(":");
		if (v.length == 2) {baseBlock1[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock1[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock1[count] == null) {baseBlock1[count] = Blocks.dirt;}
		v = basetype2[count].split(":");
		if (v.length == 2) {baseBlock2[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock2[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock2[count] == null) {baseBlock2[count] = Blocks.grass;}
		algore[count] = 3;
		count++;
		greatoakTrees++;
		config.save();
	}

	public static void configureSwampOak(Configuration config)
	{
		treeName[count] = config.get("Tree Configuration Settings", "Unique Tree Name" , "Swamp Oak " + Integer.toString(swampoakTrees),"If it's not different from every other defined tree, settings will mix").getString();
		heightmin[count] = config.get("Tree Configuration Settings", "Height Min" , 28,"Minimum height in blocks").getInt();
		heightmax[count] = config.get("Tree Configuration Settings", "Height Max" , 32,"Maximum height in blocks").getInt();
		frequencymultiplyer[count] = config.get("Tree Configuration Settings", "Frequency" , 10,"Used to generate default biome group densities").getInt();
		woodName[count] = config.get("Tree Configuration Settings", "Wood Name" , "log","Minecraft names are log and log2. For mod-added names, use modID:name, e.g., BiomesOPlenty:logs1.").getString();
		woodMeta[count] = config.get("Tree Configuration Settings", "Wood Meta" , 0,"0-3 for upward facing logs. For wood name log:0=oak, 1=pine, 2=birch, 3=jungle. For log2, 0=acacia, 1=darkoak.").getInt();
		leafName[count] = config.get("Tree Configuration Settings", "Leaf Name" , "leaves","Minecraft names are leaves and leaves2. For mod-added names, use modID:name, e.g., BiomesOPlenty:leaves1.").getString();
		leafMeta[count] = config.get("Tree Configuration Settings", "Leaf Meta" , 0,"values are 0-3. For leaf name leaves:0=oak, 1=pine, 2=birch, 3=jungle. For leaves2, 0=acacia, 1=darkoak.").getInt();
		stuntmin[count] = config.get("Tree Configuration Settings", "Stunt Minimum", 7,"Trees are stunted when blocked by overhead obstacles, but no shorter than this value.").getInt();
		basetype1[count] = config.get("Tree Configuration Settings", "Base Type 1", "grass","block that the tree can grow on").getString();
		basetype2[count] = config.get("Tree Configuration Settings", "Base Type 2", "dirt","block that the tree can grow on").getString();
		locality[count] = config.get("Tree Configuration Settings", "Locality Distribution Type", 1,"noise field to determine where in biome tree can grow. 1 through number of trees. Any other integer to disable tree noise.").getInt();
		localitymin[count] = config.get("Tree Configuration Settings", "Locality Min", 55,"0 to 144").getInt();
		localitymax[count] = config.get("Tree Configuration Settings", "Locality Max", 75,"0 to 144").getInt();
		String[] v = woodName[count].split(":");
		if (v.length == 2) {woodBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {woodBlock[count] = Block.getBlockFromName(v[0]);}
		v = leafName[count].split(":");
		if (v.length == 2) {leafBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {leafBlock[count] = Block.getBlockFromName(v[0]);}
		v = basetype1[count].split(":");
		if (v.length == 2) {baseBlock1[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock1[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock1[count] == null) {baseBlock1[count] = Blocks.dirt;}
		v = basetype2[count].split(":");
		if (v.length == 2) {baseBlock2[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock2[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock2[count] == null) {baseBlock2[count] = Blocks.grass;}
		algore[count] = 4;
		count++;
		swampoakTrees++;
		config.save();
	}

	public static void configureCyprus(Configuration config)
	{
		treeName[count] = config.get("Tree Configuration Settings", "Unique Tree Name" , "Cyprus Oak " + Integer.toString(cyprusTrees),"If it's not different from every other defined tree, settings will mix").getString();
		heightmin[count] = config.get("Tree Configuration Settings", "Height Min" , 28,"Minimum height in blocks").getInt();
		heightmax[count] = config.get("Tree Configuration Settings", "Height Max" , 32,"Maximum height in blocks").getInt();
		frequencymultiplyer[count] = config.get("Tree Configuration Settings", "Frequency" , 10,"Used to generate default biome group densities").getInt();
		woodName[count] = config.get("Tree Configuration Settings", "Wood Name" , "log","Minecraft names are log and log2. For mod-added names, use modID:name, e.g., BiomesOPlenty:logs1.").getString();
		woodMeta[count] = config.get("Tree Configuration Settings", "Wood Meta" , 1,"0-3 for upward facing logs. For wood name log:0=oak, 1=pine, 2=birch, 3=jungle. For log2, 0=acacia, 1=darkoak.").getInt();
		leafName[count] = config.get("Tree Configuration Settings", "Leaf Name" , "leaves","Minecraft names are leaves and leaves2. For mod-added names, use modID:name, e.g., BiomesOPlenty:leaves1.").getString();
		leafMeta[count] = config.get("Tree Configuration Settings", "Leaf Meta" , 1,"values are 0-3. For leaf name leaves:0=oak, 1=pine, 2=birch, 3=jungle. For leaves2, 0=acacia, 1=darkoak.").getInt();
		stuntmin[count] = config.get("Tree Configuration Settings", "Stunt Minimum", 7,"Trees are stunted when blocked by overhead obstacles, but no shorter than this value.").getInt();
		basetype1[count] = config.get("Tree Configuration Settings", "Base Type 1", "grass","block that the tree can grow on").getString();
		basetype2[count] = config.get("Tree Configuration Settings", "Base Type 2", "dirt","block that the tree can grow on").getString();
		locality[count] = config.get("Tree Configuration Settings", "Locality Distribution Type", 2,"noise field to determine where in biome tree can grow. 1 through number of trees. Any other integer to disable tree noise.").getInt();
		localitymin[count] = config.get("Tree Configuration Settings", "Locality Min", 0,"0 to 144").getInt();
		localitymax[count] = config.get("Tree Configuration Settings", "Locality Max", 90,"0 to 144").getInt();
		String[] v = woodName[count].split(":");
		if (v.length == 2) {woodBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {woodBlock[count] = Block.getBlockFromName(v[0]);}
		v = leafName[count].split(":");
		if (v.length == 2) {leafBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {leafBlock[count] = Block.getBlockFromName(v[0]);}
		v = basetype1[count].split(":");
		if (v.length == 2) {baseBlock1[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock1[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock1[count] == null) {baseBlock1[count] = Blocks.dirt;}
		v = basetype2[count].split(":");
		if (v.length == 2) {baseBlock2[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock2[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock2[count] == null) {baseBlock2[count] = Blocks.grass;}
		algore[count] = 5;
		count++;
		cyprusTrees++;
		config.save();
	}

	public static void configureHat(Configuration config)
	{
		treeName[count] = config.get("Tree Configuration Settings", "Unique Tree Name" , "Hat Tree " + Integer.toString(hatTrees),"If it's not different from every other defined tree, settings will mix").getString();
		heightmin[count] = config.get("Tree Configuration Settings", "Height Min" , 28,"Minimum height in blocks").getInt();
		heightmax[count] = config.get("Tree Configuration Settings", "Height Max" , 32,"Maximum height in blocks").getInt();
		frequencymultiplyer[count] = config.get("Tree Configuration Settings", "Frequency" , 10,"Used to generate default biome group densities").getInt();
		woodName[count] = config.get("Tree Configuration Settings", "Wood Name" , "log","Minecraft names are log and log2. For mod-added names, use modID:name, e.g., BiomesOPlenty:logs1.").getString();
		woodMeta[count] = config.get("Tree Configuration Settings", "Wood Meta" , 0,"0-3 for upward facing logs. For wood name log:0=oak, 1=pine, 2=birch, 3=jungle. For log2, 0=acacia, 1=darkoak.").getInt();
		leafName[count] = config.get("Tree Configuration Settings", "Leaf Name" , "leaves","Minecraft names are leaves and leaves2. For mod-added names, use modID:name, e.g., BiomesOPlenty:leaves1.").getString();
		leafMeta[count] = config.get("Tree Configuration Settings", "Leaf Meta" , 0,"values are 0-3. For leaf name leaves:0=oak, 1=pine, 2=birch, 3=jungle. For leaves2, 0=acacia, 1=darkoak.").getInt();
		stuntmin[count] = config.get("Tree Configuration Settings", "Stunt Minimum", 7,"Trees are stunted when blocked by overhead obstacles, but no shorter than this value.").getInt();
		basetype1[count] = config.get("Tree Configuration Settings", "Base Type 1", "grass","block that the tree can grow on").getString();
		basetype2[count] = config.get("Tree Configuration Settings", "Base Type 2", "dirt","block that the tree can grow on").getString();
		locality[count] = config.get("Tree Configuration Settings", "Locality Distribution Type", 3,"noise field to determine where in biome tree can grow. 1 through number of trees. Any other integer to disable tree noise.").getInt();
		localitymin[count] = config.get("Tree Configuration Settings", "Locality Min", 0,"0 to 144").getInt();
		localitymax[count] = config.get("Tree Configuration Settings", "Locality Max", 90,"0 to 144").getInt();
		String[] v = woodName[count].split(":");
		if (v.length == 2) {woodBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {woodBlock[count] = Block.getBlockFromName(v[0]);}
		v = leafName[count].split(":");
		if (v.length == 2) {leafBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {leafBlock[count] = Block.getBlockFromName(v[0]);}
		v = basetype1[count].split(":");
		if (v.length == 2) {baseBlock1[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock1[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock1[count] == null) {baseBlock1[count] = Blocks.dirt;}
		v = basetype2[count].split(":");
		if (v.length == 2) {baseBlock2[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock2[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock2[count] == null) {baseBlock2[count] = Blocks.grass;}
		algore[count] = 6;
		count++;
		hatTrees++;
		config.save();
	}

	public static void configureBigPine(Configuration config)
	{
		treeName[count] = config.get("Tree Configuration Settings", "Unique Tree Name" , "Big Pine " + Integer.toString(greatpineTrees),"If it's not different from every other defined tree, settings will mix").getString();
		heightmin[count] = config.get("Tree Configuration Settings", "Height Min" , 18,"Minimum height in blocks").getInt();
		heightmax[count] = config.get("Tree Configuration Settings", "Height Max" , 22,"Maximum height in blocks").getInt();
		frequencymultiplyer[count] = config.get("Tree Configuration Settings", "Frequency" , 40,"Used to generate default biome group densities").getInt();
		woodName[count] = config.get("Tree Configuration Settings", "Wood Name" , "log","Minecraft names are log and log2. For mod-added names, use modID:name, e.g., BiomesOPlenty:logs1.").getString();
		woodMeta[count] = config.get("Tree Configuration Settings", "Wood Meta" , 1,"0-3 for upward facing logs. For wood name log:0=oak, 1=pine, 2=birch, 3=jungle. For log2, 0=acacia, 1=darkoak.").getInt();
		leafName[count] = config.get("Tree Configuration Settings", "Leaf Name" , "leaves","Minecraft names are leaves and leaves2. For mod-added names, use modID:name, e.g., BiomesOPlenty:leaves1.").getString();
		leafMeta[count] = config.get("Tree Configuration Settings", "Leaf Meta" , 1,"values are 0-3. For leaf name leaves:0=oak, 1=pine, 2=birch, 3=jungle. For leaves2, 0=acacia, 1=darkoak.").getInt();
		stuntmin[count] = config.get("Tree Configuration Settings", "Stunt Minimum", 7,"Trees are stunted when blocked by overhead obstacles, but no shorter than this value.").getInt();
		basetype1[count] = config.get("Tree Configuration Settings", "Base Type 1", "grass","block that the tree can grow on").getString();
		basetype2[count] = config.get("Tree Configuration Settings", "Base Type 2", "gravel","block that the tree can grow on").getString();
		locality[count] = config.get("Tree Configuration Settings", "Locality Distribution Type", 4,"noise field to determine where in biome tree can grow. 1 through number of trees. Any other integer to disable tree noise.").getInt();
		localitymin[count] = config.get("Tree Configuration Settings", "Locality Min", 80,"0 to 144").getInt();
		localitymax[count] = config.get("Tree Configuration Settings", "Locality Max", 60,"0 to 144").getInt();
		String[] v = woodName[count].split(":");
		if (v.length == 2) {woodBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {woodBlock[count] = Block.getBlockFromName(v[0]);}
		v = leafName[count].split(":");
		if (v.length == 2) {leafBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {leafBlock[count] = Block.getBlockFromName(v[0]);}
		v = basetype1[count].split(":");
		if (v.length == 2) {baseBlock1[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock1[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock1[count] == null) {baseBlock1[count] = Blocks.dirt;}
		v = basetype2[count].split(":");
		if (v.length == 2) {baseBlock2[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock2[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock2[count] == null) {baseBlock2[count] = Blocks.grass;}
		algore[count] = 7;
		count++;
		greatpineTrees++;
		config.save();
	}

	public static void configureBigBirch(Configuration config)
	{
		treeName[count] = config.get("Tree Configuration Settings", "Unique Tree Name" , "Big Birch " + Integer.toString(greatbirchTrees),"If it's not different from every other defined tree, settings will mix").getString();
		heightmin[count] = config.get("Tree Configuration Settings", "Height Min" , 19,"Minimum height in blocks").getInt();
		heightmax[count] = config.get("Tree Configuration Settings", "Height Max" , 24,"Maximum height in blocks").getInt();
		frequencymultiplyer[count] = config.get("Tree Configuration Settings", "Frequency" , 10,"Used to generate default biome group densities").getInt();
		woodName[count] = config.get("Tree Configuration Settings", "Wood Name" , "log","Minecraft names are log and log2. For mod-added names, use modID:name, e.g., BiomesOPlenty:logs1.").getString();
		woodMeta[count] = config.get("Tree Configuration Settings", "Wood Meta" , 2,"0-3 for upward facing logs. For wood name log:0=oak, 1=pine, 2=birch, 3=jungle. For log2, 0=acacia, 1=darkoak.").getInt();
		leafName[count] = config.get("Tree Configuration Settings", "Leaf Name" , "leaves","Minecraft names are leaves and leaves2. For mod-added names, use modID:name, e.g., BiomesOPlenty:leaves1.").getString();
		leafMeta[count] = config.get("Tree Configuration Settings", "Leaf Meta" , 2,"values are 0-3. For leaf name leaves:0=oak, 1=pine, 2=birch, 3=jungle. For leaves2, 0=acacia, 1=darkoak.").getInt();
		stuntmin[count] = config.get("Tree Configuration Settings", "Stunt Minimum", 7,"Trees are stunted when blocked by overhead obstacles, but no shorter than this value.").getInt();
		basetype1[count] = config.get("Tree Configuration Settings", "Base Type 1", "grass","block that the tree can grow on").getString();
		basetype2[count] = config.get("Tree Configuration Settings", "Base Type 2", "dirt","block that the tree can grow on").getString();
		locality[count] = config.get("Tree Configuration Settings", "Locality Distribution Type", 5,"noise field to determine where in biome tree can grow. 1 through number of trees. Any other integer to disable tree noise.").getInt();
		localitymin[count] = config.get("Tree Configuration Settings", "Locality Min", 0,"0 to 144").getInt();
		localitymax[count] = config.get("Tree Configuration Settings", "Locality Max", 60,"0 to 144").getInt();
		String[] v = woodName[count].split(":");
		if (v.length == 2) {woodBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {woodBlock[count] = Block.getBlockFromName(v[0]);}
		v = leafName[count].split(":");
		if (v.length == 2) {leafBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {leafBlock[count] = Block.getBlockFromName(v[0]);}
		v = basetype1[count].split(":");
		if (v.length == 2) {baseBlock1[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock1[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock1[count] == null) {baseBlock1[count] = Blocks.dirt;}
		v = basetype2[count].split(":");
		if (v.length == 2) {baseBlock2[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock2[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock2[count] == null) {baseBlock2[count] = Blocks.grass;}
		algore[count] = 8;
		count++;
		greatbirchTrees++;
		config.save();
	}

	public static void configureDead(Configuration config)
	{
		treeName[count] = config.get("Tree Configuration Settings", "Unique Tree Name" , "Dead Tree " + Integer.toString(deadTrees),"If it's not different from every other defined tree, settings will mix").getString();
		heightmin[count] = config.get("Tree Configuration Settings", "Height Min" , 13,"Minimum height in blocks").getInt();
		heightmax[count] = config.get("Tree Configuration Settings", "Height Max" , 28,"Maximum height in blocks").getInt();
		frequencymultiplyer[count] = config.get("Tree Configuration Settings", "Frequency" , 10,"Used to generate default biome group densities").getInt();
		woodName[count] = config.get("Tree Configuration Settings", "Wood Name" , "log","Minecraft names are log and log2. For mod-added names, use modID:name, e.g., BiomesOPlenty:logs1.").getString();
		woodMeta[count] = config.get("Tree Configuration Settings", "Wood Meta" , 0,"0-3 for upward facing logs. For wood name log:0=oak, 1=pine, 2=birch, 3=jungle. For log2, 0=acacia, 1=darkoak.").getInt();
		leafName[count] = config.get("Tree Configuration Settings", "Leaf Name" , "air","For dead tree algorithm, the leave data is unused").getString();
		leafMeta[count] = config.get("Tree Configuration Settings", "Leaf Meta" , 0,"values are 0-3. For leaf name leaves:0=oak, 1=pine, 2=birch, 3=jungle. For leaves2, 0=acacia, 1=darkoak.").getInt();
		stuntmin[count] = config.get("Tree Configuration Settings", "Stunt Minimum", 7,"Trees are stunted when blocked by overhead obstacles, but no shorter than this value.").getInt();
		basetype1[count] = config.get("Tree Configuration Settings", "Base Type 1", "grass","block that the tree can grow on").getString();
		basetype2[count] = config.get("Tree Configuration Settings", "Base Type 2", "sand","block that the tree can grow on").getString();
		locality[count] = config.get("Tree Configuration Settings", "Locality Distribution Type", 1,"noise field to determine where in biome tree can grow. 1 through number of trees. Any other integer to disable tree noise.").getInt();
		localitymin[count] = config.get("Tree Configuration Settings", "Locality Min", 0,"0 to 144").getInt();
		localitymax[count] = config.get("Tree Configuration Settings", "Locality Max", 60,"0 to 144").getInt();
		String[] v = woodName[count].split(":");
		if (v.length == 2) {woodBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {woodBlock[count] = Block.getBlockFromName(v[0]);}
		v = leafName[count].split(":");
		if (v.length == 2) {leafBlock[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {leafBlock[count] = Block.getBlockFromName(v[0]);}
		v = basetype1[count].split(":");
		if (v.length == 2) {baseBlock1[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock1[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock1[count] == null) {baseBlock1[count] = Blocks.dirt;}
		v = basetype2[count].split(":");
		if (v.length == 2) {baseBlock2[count] = GameRegistry.findBlock(v[0], v[1]);}
		else {baseBlock2[count] = Block.getBlockFromName(v[0]);}
		if (baseBlock2[count] == null) {baseBlock2[count] = Blocks.grass;}
		algore[count] = 9;
		count++;
		deadTrees++;
		config.save();
	}
}
