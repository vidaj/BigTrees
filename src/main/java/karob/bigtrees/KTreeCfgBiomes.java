package karob.bigtrees;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import karob.bigtrees.config.BiomeConfiguration;
import karob.bigtrees.config.BiomeConfiguration.Match;
import karob.bigtrees.config.Population;
import karob.bigtrees.config.TreeConfiguration;
import karob.bigtrees.config.defaults.Defaults;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class KTreeCfgBiomes {
	// this would be cool to have biome dependent:
	// public static int[][] TreeLogBlock;
	// public static int[][] TreeLeafBlock;
	
	private static List<BiomeConfiguration> biomeConfigurations = new LinkedList<BiomeConfiguration>();
	
	public static Population getTreeDensityForBiomeType(BiomeGenBase biome, TreeConfiguration treeConfiguration) {
		Population population = null;
		Population priorityPopulation = null;
		
		for (BiomeConfiguration biomeConfiguration : biomeConfigurations) {
			Match match = biomeConfiguration.matches(biome);
			Population thisTreesPopulation = biomeConfiguration.getPopulation(treeConfiguration);
			switch (match) {
			case Match:
				if (population == null || population.getPercentageChancePerTree() > thisTreesPopulation.getPercentageChancePerTree()) {
					population = thisTreesPopulation;
				}
				break;
			case PriorityMatch:
				if (priorityPopulation == null || priorityPopulation.getPercentageChancePerTree() > thisTreesPopulation.getPercentageChancePerTree()) {
					priorityPopulation = thisTreesPopulation;
				}
				break;
			default:
				break;	
			}
		}
		
		if (population == null && priorityPopulation == null) {
			return Population.NULL;
		}
		
		return priorityPopulation != null ? priorityPopulation : population;
	}

	public static void init(File configFile) {
		if (!configFile.exists()) {
			setDefaultConfigValues(configFile);
		}
		
		Configuration config = new Configuration(configFile);
		config.load();
		
		loadBiomeConfigs(config);
		loadGeneralSettings(config);
	}

	private static void loadBiomeConfigs(Configuration config) {
		ConfigCategory biomeConfigurationsFromFile = config.getCategory("biomeconfiguration");
		for (ConfigCategory biomeConfiguration : biomeConfigurationsFromFile.getChildren()) {
			biomeConfigurations.add(new BiomeConfiguration(biomeConfiguration));
		}
	}

	private static void loadGeneralSettings(Configuration config) {
		ConfigCategory generalSettings = config.getCategory("general");
		
		for(Map.Entry<String, Property> entry : generalSettings.entrySet()) {
			String key = entry.getKey();
			Property property = entry.getValue();
			
			if (key.equals("Enable roots")) {
				KTreeCfg.rootsEnable = property.getBoolean();
			}
			else if (key.equals("Enabled dimension ids")) {
				KTreeCfg.enabledDimensionIds = toSet(property.getIntList());
			}
			else if (key.equals("Disabled dimension ids")) {
				KTreeCfg.disabledDimensionIds = toSet(property.getIntList());
			} else if (key.equals("Disable vanilla trees")) {
				KTreeCfg.disableVanillaTrees = property.getBoolean();
			}
		}
	}
	
	private static Set<Integer> toSet(int[] array) {
		Set<Integer> set = new HashSet<Integer>();
		
		for (int number : array) {
			set.add(Integer.valueOf(number));
		}
		
		return set;
	}

	private static void setDefaultConfigValues(File configFile) {
		Map<TreeConfiguration, Population> defaultForestDensities = new HashMap<TreeConfiguration, Population>();
		defaultForestDensities.put(Defaults.TallOak,  new Population(50, 3));
		defaultForestDensities.put(Defaults.BlockOak, new Population(20, 2));
		defaultForestDensities.put(Defaults.GreatOak, new Population(5, 1));
		defaultForestDensities.put(Defaults.SwampOak, new Population(4, 1));
		defaultForestDensities.put(Defaults.BigPine,  new Population(9, 3));
		defaultForestDensities.put(Defaults.BigBirch, new Population(14, 1));
		defaultForestDensities.put(Defaults.Dead,     new Population(1, 1));
		
		Map<TreeConfiguration, Population> defaultSwampDensities = new HashMap<TreeConfiguration, Population>();
		defaultSwampDensities.put(Defaults.GreatOak, new Population(20, 1));
		defaultSwampDensities.put(Defaults.SwampOak, new Population(20, 1));
		defaultSwampDensities.put(Defaults.Cyprus,   new Population(20, 1));
		defaultSwampDensities.put(Defaults.Hat,      new Population(20, 1));
		defaultSwampDensities.put(Defaults.BigBirch, new Population(1, 1));
		
		Map<TreeConfiguration, Population> defaultPlainsDensities = new HashMap<TreeConfiguration, Population>();
		defaultPlainsDensities.put(Defaults.TallOak,  new Population(1, 1));
		defaultPlainsDensities.put(Defaults.BlockOak, new Population(2, 1));
		defaultPlainsDensities.put(Defaults.GreatOak, new Population(3, 1));
		defaultPlainsDensities.put(Defaults.BigPine,  new Population(1, 1));
		defaultPlainsDensities.put(Defaults.BigBirch, new Population(1, 1));
		
		Map<TreeConfiguration, Population> defaultMountainDensities = new HashMap<TreeConfiguration, Population>();
		defaultMountainDensities.put(Defaults.TallOak, new Population(10, 1));
		
		Map<TreeConfiguration, Population> defaultHillsDensities = new HashMap<TreeConfiguration, Population>();
		defaultHillsDensities.put(Defaults.TallOak,  new Population(15, 1));
		defaultHillsDensities.put(Defaults.BlockOak, new Population(26, 1));
		defaultHillsDensities.put(Defaults.GreatOak, new Population(24, 1));
		defaultHillsDensities.put(Defaults.BigPine,  new Population(27, 1));
		defaultHillsDensities.put(Defaults.BigBirch, new Population(10, 1));
		
		Map<TreeConfiguration, Population> defaultWaterDensities = new HashMap<TreeConfiguration, Population>();
		defaultWaterDensities.put(Defaults.TallOak,  new Population(5, 1));
		defaultWaterDensities.put(Defaults.GreatOak, new Population(5, 1));
		defaultWaterDensities.put(Defaults.SwampOak, new Population(2, 1));
		defaultWaterDensities.put(Defaults.BigPine,  new Population(3, 1));
		defaultWaterDensities.put(Defaults.BigBirch, new Population(5, 1));
		
		Map<TreeConfiguration, Population> defaultSandyDensities = new HashMap<TreeConfiguration, Population>();
		defaultSandyDensities.put(Defaults.Dead, new Population(3, 1));
		
		Map<TreeConfiguration, Population> defaultSnowyDensities = new HashMap<TreeConfiguration, Population>();
		defaultSnowyDensities.put(Defaults.TallOak, new Population(30, 1));
		defaultSnowyDensities.put(Defaults.BigPine, new Population(10, 1));
		
		Map<TreeConfiguration, Population> defaultJungleDensities = new HashMap<TreeConfiguration, Population>();
		defaultJungleDensities.put(Defaults.GreatOak, new Population(10, 1));
		defaultJungleDensities.put(Defaults.SwampOak, new Population(5, 1));
		
		Map<TreeConfiguration, Population> overriddenBirchForestDensities = new HashMap<TreeConfiguration, Population>();
		overriddenBirchForestDensities.put(Defaults.BigBirch, new Population(70, 3));
		
		Configuration config = new Configuration(configFile);
		config.load();
		
		ConfigCategory generalSettings = config.getCategory("general");
		Property enableRoots = new Property("Enable roots", "true", Property.Type.BOOLEAN);
		Property enabledDimensionIds = new Property("Enabled dimension ids", new String[] {"0"}, Property.Type.INTEGER);
		Property disabledDimensionIds = new Property("Disabled dimension ids", new String[] {"-1", "1"} , Property.Type.INTEGER);
		Property disableVanillaTrees = new Property("Disable vanilla trees", "false", Property.Type.BOOLEAN);
		addPropertiesToCategory(generalSettings, enableRoots, enabledDimensionIds, disabledDimensionIds, disableVanillaTrees);
		
		ConfigCategory mainParent = config.getCategory("biomeconfiguration");
		
		addCategory("forests", biomes(Type.FOREST), noExcludes(), defaultForestDensities, mainParent);
		addCategory("swamps", biomes(Type.SWAMP), noExcludes(), defaultSwampDensities, mainParent);
		addCategory("plains", biomes(Type.PLAINS), noExcludes(), defaultPlainsDensities, mainParent);
		addCategory("mountains", biomes(Type.MOUNTAIN), biomes(Type.SANDY, Type.WASTELAND, Type.MESA), defaultMountainDensities, mainParent);
		addCategory("hills", biomes(Type.HILLS), biomes(Type.SANDY, Type.WASTELAND, Type.MESA), defaultHillsDensities, mainParent);
		addCategory("waters", biomes(Type.WATER), noExcludes(), defaultWaterDensities, mainParent);
		addCategory("deserts", biomes(Type.SANDY, Type.WASTELAND, Type.MESA), biomes(Type.HILLS, Type.MOUNTAIN), defaultSandyDensities, mainParent);
		addCategory("snowy", biomes(Type.SNOWY), noExcludes(), defaultSnowyDensities, mainParent);
		addCategory("jungles", biomes(Type.JUNGLE), noExcludes(), defaultJungleDensities, mainParent);
		addBiomeOverride("birchForests", biomes(BiomeGenBase.birchForest, BiomeGenBase.birchForestHills), overriddenBirchForestDensities, mainParent);
		
		
		
		config.save();
	}
	
	private static void addCategory(String name, String[] includedBiomes, String[] excludedBiomes, Map<TreeConfiguration, Population> treePopulation, ConfigCategory parent) {
		ConfigCategory category = createNew(name, parent);
		addBiomes(includedBiomes, excludedBiomes, category);
		addTreePopulation(treePopulation, category);
	}
	
	private static void addBiomeOverride(String name, String[] biomeNames, Map<TreeConfiguration, Population> treePopulation, ConfigCategory parent) {
		ConfigCategory category = createNew(name, parent);
		addOverriddenBiomes(biomeNames, category);
		addTreePopulation(treePopulation, category);
	}
	
	private static ConfigCategory createNew(String name, ConfigCategory parent) {
		ConfigCategory category = new ConfigCategory(name, parent);
		category.setComment("This name has to be unique.");
		
		return category;
	}
	
	private static void addBiomes(String[] included, String[] excluded, ConfigCategory parent) {
		ConfigCategory biomes = new ConfigCategory("biometypes", parent);
		
		biomes.put("1", new Property("Included", included, Property.Type.STRING));
		biomes.put("2", new Property("Excluded", excluded, Property.Type.STRING));
	}
	
	private static void addOverriddenBiomes(String[] biomeNames, ConfigCategory parent) {
		ConfigCategory biomes = new ConfigCategory("biometypes", parent);
		biomes.put("1", new Property("Specific", biomeNames, Property.Type.STRING));
	}
	
	private static String[] biomes(BiomeDictionary.Type ... types) {
		List<String> strings = new LinkedList<String>();
		for(BiomeDictionary.Type type : types) {
			strings.add(type.name());
		}
		
		return strings.toArray(new String[strings.size()]);
	}
	
	private static String[] biomes(BiomeGenBase ... biomes) {
		List<String> strings = new LinkedList<String>();
		for(BiomeGenBase biome : biomes) {
			strings.add(biome.biomeName);
		}
		
		return strings.toArray(new String[strings.size()]);
	}
	
	private static String[] noExcludes() {
		return new String[0];
	}
	
	private static void addTreePopulation(Map<TreeConfiguration, Population> populations, ConfigCategory parent) {
		ConfigCategory treePopulation = new ConfigCategory("treepopulation", parent);
		treePopulation.setComment("Contains population for each tree. Trees are specified by the name in the corresponding tree configuration file.");
		
		for(Map.Entry<TreeConfiguration, Population> entry : populations.entrySet()) {
			ConfigCategory treeCategory = new ConfigCategory(entry.getKey().getName(), treePopulation);
			
			Population population = entry.getValue();
			Property percentageChancePerTree = createProperty(Population.PercentageChancePerTreeConfigKey, population.getPercentageChancePerTree());
			Property treesPerChunk = createProperty(Population.TreesPerChunkConfigKey, population.getTreesPerChunk());
			
			addPropertiesToCategory(treeCategory, percentageChancePerTree, treesPerChunk);
		}
	}

	private static void addPropertiesToCategory(ConfigCategory category, Property ... properties) {
		int index = 1;
		for (Property property : properties) {
			category.put(Integer.toString(index++), property);
		}
	}
	
	private static Property createProperty(String name, int value) {
		return new Property(name, Integer.toString(value), Property.Type.INTEGER);
	}
}
