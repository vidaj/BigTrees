package karob.bigtrees;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import karob.bigtrees.config.Algorithm;
import karob.bigtrees.config.BiomeConfiguration;
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
	
	private static Map<BiomeDictionary.Type, Map<Algorithm, Integer>> treeDensityPerTreePerBiome;
	
	public static int getTreeDensityForBiomeType(BiomeGenBase biome, TreeConfiguration treeConfiguration) {
		int population = 0;
		
		for (BiomeConfiguration biomeConfiguration : biomeConfigurations) {
			if (biomeConfiguration.matches(biome)) {
				population = Math.max(population, biomeConfiguration.getPopulation(treeConfiguration));
			}
		}
		
		return population * treeConfiguration.getFrequencyMultiplier();
	}

	public static void init(File configFile) {
		if (!configFile.exists()) {
			setDefaultConfigValues(configFile);
		}
		
		Configuration config = new Configuration(configFile);
		config.load();
		
		ConfigCategory biomeConfigurationsFromFile = config.getCategory("biomeconfiguration");
		for (ConfigCategory biomeConfiguration : biomeConfigurationsFromFile.getChildren()) {
			biomeConfigurations.add(new BiomeConfiguration(biomeConfiguration));
		}
	}

	private static void setDefaultConfigValues(File configFile) {
		Map<TreeConfiguration, Integer> defaultForestDensities = new HashMap<TreeConfiguration, Integer>();
		defaultForestDensities.put(Defaults.TallOak, 50);
		defaultForestDensities.put(Defaults.BlockOak, 20);
		defaultForestDensities.put(Defaults.GreatOak, 5);
		defaultForestDensities.put(Defaults.SwampOak, 4);
		defaultForestDensities.put(Defaults.BigPine, 9);
		defaultForestDensities.put(Defaults.BigBirch, 14);
		defaultForestDensities.put(Defaults.Dead, 1);
		
		Map<TreeConfiguration, Integer> defaultSwampDensities = new HashMap<TreeConfiguration, Integer>();
		defaultSwampDensities.put(Defaults.GreatOak, 20);
		defaultSwampDensities.put(Defaults.SwampOak, 20);
		defaultSwampDensities.put(Defaults.Cyprus, 20);
		defaultSwampDensities.put(Defaults.Hat, 20);
		defaultSwampDensities.put(Defaults.BigBirch, 1);
		
		Map<TreeConfiguration, Integer> defaultPlainsDensities = new HashMap<TreeConfiguration, Integer>();
		defaultPlainsDensities.put(Defaults.TallOak, 1);
		defaultPlainsDensities.put(Defaults.BlockOak, 2);
		defaultPlainsDensities.put(Defaults.GreatOak, 3);
		defaultPlainsDensities.put(Defaults.BigPine, 1);
		defaultPlainsDensities.put(Defaults.BigBirch, 1);
		
		Map<TreeConfiguration, Integer> defaultMountainDensities = new HashMap<TreeConfiguration, Integer>();
		defaultMountainDensities.put(Defaults.TallOak, 10);
		
		Map<TreeConfiguration, Integer> defaultHillsDensities = new HashMap<TreeConfiguration, Integer>();
		defaultHillsDensities.put(Defaults.TallOak, 15);
		defaultHillsDensities.put(Defaults.BlockOak, 6);
		defaultHillsDensities.put(Defaults.GreatOak, 4);
		defaultHillsDensities.put(Defaults.BigPine, 7);
		defaultHillsDensities.put(Defaults.BigBirch, 10);
		
		Map<TreeConfiguration, Integer> defaultWaterDensities = new HashMap<TreeConfiguration, Integer>();
		defaultWaterDensities.put(Defaults.TallOak, 5);
		defaultWaterDensities.put(Defaults.GreatOak, 5);
		defaultWaterDensities.put(Defaults.SwampOak, 2);
		defaultWaterDensities.put(Defaults.BigPine, 3);
		defaultWaterDensities.put(Defaults.BigBirch, 5);
		
		Map<TreeConfiguration, Integer> defaultSandyDensities = new HashMap<TreeConfiguration, Integer>();
		defaultSandyDensities.put(Defaults.Dead, 3);
		
		Map<TreeConfiguration, Integer> defaultSnowyDensities = new HashMap<TreeConfiguration, Integer>();
		defaultSnowyDensities.put(Defaults.TallOak, 2);
		defaultSnowyDensities.put(Defaults.BigPine, 10);
		
		Map<TreeConfiguration, Integer> defaultJungleDensities = new HashMap<TreeConfiguration, Integer>();
		defaultJungleDensities.put(Defaults.GreatOak, 10);
		defaultJungleDensities.put(Defaults.SwampOak, 5);
		
		Configuration config = new Configuration(configFile);
		config.load();
		
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
		
		config.save();
	}
	
	private static void addCategory(String name, String[] includedBiomes, String[] excludedBiomes, Map<TreeConfiguration, Integer> treePopulation, ConfigCategory parent) {
		ConfigCategory category = createNew(name, parent);
		addBiomes(includedBiomes, excludedBiomes, category);
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
	
	private static String[] biomes(BiomeDictionary.Type ... types) {
		List<String> strings = new LinkedList<String>();
		for(BiomeDictionary.Type type : types) {
			strings.add(type.name());
		}
		
		return strings.toArray(new String[strings.size()]);
	}
	
	private static String[] noExcludes() {
		return new String[0];
	}
	
	private static void addTreePopulation(Map<TreeConfiguration, Integer> population, ConfigCategory parent) {
		ConfigCategory treePopulation = new ConfigCategory("treepopulation", parent);
		treePopulation.setComment("Contains population for each tree. Trees are specified by the name in the corresponding tree configuration file. Remember that these values are multiplied by 'frequencyMultiplier'");
		
		int index = 1;
		for(Map.Entry<TreeConfiguration, Integer> entry : population.entrySet()) {
			treePopulation.put(Integer.toString(index++), new Property(entry.getKey().getName(), Integer.toString(entry.getValue()), Property.Type.STRING));
		}
	}
}
