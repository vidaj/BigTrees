package karob.bigtrees.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import karob.bigtrees.KTreeCfgTrees;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLLog;

public class BiomeConfiguration {

	private Set<BiomeDictionary.Type> includedBiomeTypes = new HashSet<BiomeDictionary.Type>();
	
	private Set<BiomeDictionary.Type> excludedBiomeTypes = new HashSet<BiomeDictionary.Type>();
	
	private Map<TreeConfiguration, Population> treePopulation = new HashMap<TreeConfiguration, Population>();
	
	private Set<String> specificBiomes = new HashSet<String>();
	
	public BiomeConfiguration(ConfigCategory mainConfigSection) {
		for (ConfigCategory child : mainConfigSection.getChildren()) {
			if (child.getName().equals("biometypes")) {
				handleBiometypes(child);
			}
			if (child.getName().equals("treepopulation")) {
				handleTreePopulation(child);
			}
		}
	}

	private void handleTreePopulation(ConfigCategory treePopulationCategory) {
		for (ConfigCategory treeConfig: treePopulationCategory.getChildren()) {
			String treeName = treeConfig.getName();
			
			int percentageChancePerChunk = 0;
			int treesPerChunk = 0;
			for (Entry<String, Property> entry : treeConfig.entrySet()) {
				if (entry.getKey().equals(Population.PercentageChancePerTreeConfigKey)) {
					percentageChancePerChunk = entry.getValue().getInt();
				}
				else if (entry.getKey().equals(Population.TreesPerChunkConfigKey)) {
					treesPerChunk = entry.getValue().getInt();
				}
			}
			
			Population population = new Population(percentageChancePerChunk, treesPerChunk);
			
			treePopulation.put(KTreeCfgTrees.getTreeConfiguration(treeName), population);
		}
	}

	private void handleBiometypes(ConfigCategory child) {
		for(Entry<String, Property> entry : child.entrySet()) {
			Property property = entry.getValue();
			String propertyName = property.getName();
			
			if (propertyName.toLowerCase().equalsIgnoreCase("Included")) {
				includedBiomeTypes = toBiomeTypes(property.getStringList());
			} else if (propertyName.equalsIgnoreCase("Excluded")) {
				excludedBiomeTypes = toBiomeTypes(property.getStringList());
			} else if (propertyName.equalsIgnoreCase("Specific")) {
				for (String specificBiomeName : property.getStringList()) {
					specificBiomes.add(specificBiomeName.toLowerCase());
				}
			} else {
				FMLLog.getLogger().warn("Skipping biome configuration due to unknown property name '%s'", propertyName);
			}
		}
	}
	
	private Set<BiomeDictionary.Type> toBiomeTypes(String[] biomeTypeNames) {
		Set<BiomeDictionary.Type> result = new HashSet<BiomeDictionary.Type>();
		
		for (String biomeTypeName : biomeTypeNames) {
			result.add(BiomeDictionary.Type.valueOf(biomeTypeName));
		}
		
		return result;
	}
	
	public Match matches(BiomeGenBase biome) {
		BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(biome);
		
		if (hasBiomeSpecificOverride(biome)) {
			return Match.PriorityMatch;
		}
		
		if (existsInSet(excludedBiomeTypes, types)) {
			return Match.NoMatch;
		}
		
		return existsInSet(includedBiomeTypes, types) ? Match.Match : Match.NoMatch;
	}

	private boolean hasBiomeSpecificOverride(BiomeGenBase biome) {
		return specificBiomes.contains(biome.biomeName.toLowerCase());
	}
	
	private boolean existsInSet(Set<BiomeDictionary.Type> toSearch, BiomeDictionary.Type[] toMatch) {
		if (toSearch.isEmpty()) {
			return false;
		}
		
		for (BiomeDictionary.Type biomeType : toMatch) {
			if (toSearch.contains(biomeType)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Population getPopulation(TreeConfiguration treeConfiguration) {
		if (treePopulation.containsKey(treeConfiguration)) {
			return treePopulation.get(treeConfiguration);
		}
		
		return Population.NULL;
	}
	
	public enum Match {
		NoMatch, Match, PriorityMatch
	}
}
