package karob.bigtrees.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import karob.bigtrees.KTreeCfgTrees;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Property;

public class BiomeConfiguration {

	private Set<BiomeDictionary.Type> includedBiomeTypes = new HashSet<BiomeDictionary.Type>();
	
	private Set<BiomeDictionary.Type> excludedBiomeTypes = new HashSet<BiomeDictionary.Type>();
	
	private Map<TreeConfiguration, Integer> treePopulation = new HashMap<TreeConfiguration, Integer>();
	
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

	private void handleTreePopulation(ConfigCategory child) {
		for (Entry<String, Property> entry : child.entrySet()) {
			String treeName = entry.getKey();
			int population = entry.getValue().getInt();
			
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
	
	public boolean matches(BiomeGenBase biome) {
		BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(biome);
		
		if (existsInSet(excludedBiomeTypes, types)) {
			return false;
		}
		
		return existsInSet(includedBiomeTypes, types);
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
	
	public int getPopulation(TreeConfiguration treeConfiguration) {
		if (treePopulation.containsKey(treeConfiguration)) {
			return treePopulation.get(treeConfiguration);
		}
		
		return 0;
	}
}
