package karob.bigtrees;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
//import net.minecraft.src.KGetOptions;
import java.util.Set;

import cpw.mods.fml.common.discovery.asm.ModFieldVisitor;

public class KTreeCfg {

	public static boolean rootsEnable = true;
	public static Set<Integer> enabledDimensionIds = createDefaultEnabledDimensionsIds();
	public static Set<Integer> disabledDimensionIds = new HashSet<Integer>();

	public static boolean isValidDimension(int dimensionId) {
		Integer cachedInteger = Integer.valueOf(dimensionId);

		if (disabledDimensionIds.contains(cachedInteger)) {
			return false;
		}

		if (!enabledDimensionIds.isEmpty()
				&& !enabledDimensionIds.contains(cachedInteger)) {
			return false;
		}

		return true;
	}

	private static Set<Integer> createDefaultEnabledDimensionsIds() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(Integer.valueOf(0));
		return set;
	}

	public static int vary(Random rand, int[] opt) {
		return rand.nextInt(opt[1]) + opt[0];
	}

	public static void init(File modConfigurationDirectory) {
		String configDir = modConfigurationDirectory + File.separator + "bigtrees";
		
		File configDirectory = new File(configDir);
		configDirectory.mkdir();
		
		File treeConfigFolder = new File(configDir + File.separator	+ "treeconfigs");
		treeConfigFolder.mkdir();
		KTreeCfgTrees.init(treeConfigFolder);

		File biomeConfiguration = new File(configDir, "bigtrees.cfg");
		KTreeCfgBiomes.init(biomeConfiguration);
	}

}
