package karob.bigtrees;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import karob.bigtrees.config.TreeConfiguration;
import karob.bigtrees.config.defaults.Defaults;
import net.minecraftforge.common.config.Configuration;

public class KTreeCfgTrees 
{
	private static List<TreeConfiguration> treeConfigurations;
	
	private static Map<String, TreeConfiguration> treeNameToConfiguration;
	
	public static List<TreeConfiguration> getTreeConfigurations() {
		return treeConfigurations;
	}
		
	public static void init(File configFile) 
	{
		File[] files = configFile.listFiles();
		
		treeConfigurations = new LinkedList<TreeConfiguration>();
		
		if (files.length == 0) {
			for (TreeConfiguration defaultConfiguration : Defaults.getDefaults()) {
				File filename = new File(configFile + File.separator + defaultConfiguration.getAlgorithm() + ".cfg");
				
				Configuration config = new Configuration(filename);
				config.load();
				defaultConfiguration.readConfig(config);
				treeConfigurations.add(defaultConfiguration);
				config.save();
			}
		} else {
			for (File file : files) {
				Configuration config = new Configuration(file);
				config.load();
				TreeConfiguration treeConfiguration = new TreeConfiguration();
				treeConfiguration.readConfig(config);
				treeConfigurations.add(treeConfiguration);
			}
		}
		
		mapTreeNameToConfiguration();
	}

	private static void mapTreeNameToConfiguration() {
		treeNameToConfiguration = new HashMap<String, TreeConfiguration>();
		for (TreeConfiguration treeConfiguration : treeConfigurations) {
			treeNameToConfiguration.put(treeConfiguration.getName(), treeConfiguration);
		}
	}

	public static TreeConfiguration getTreeConfiguration(String treeName) {
		return treeNameToConfiguration.get(treeName);
	}
}
