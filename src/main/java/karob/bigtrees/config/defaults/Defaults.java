package karob.bigtrees.config.defaults;

import java.util.LinkedList;
import java.util.List;

import karob.bigtrees.config.TreeConfiguration;

public class Defaults {

	private static final List<TreeConfiguration> defaults = new LinkedList<TreeConfiguration>();
	
	public static final TreeConfiguration BigBirch = new BigBirchDefaults();
	public static final TreeConfiguration BigPine = new BigPineDefaults();
	public static final TreeConfiguration BlockOak = new BlockOakDefaults();
	public static final TreeConfiguration Cyprus = new CyprusDefaults();
	public static final TreeConfiguration Dead = new DeadTreeDefaults();
	public static final TreeConfiguration GreatOak = new GreatOakDefaults();
	public static final TreeConfiguration Hat = new HatDefaults();
	public static final TreeConfiguration SwampOak = new SwampOakDefaults();
	public static final TreeConfiguration TallOak = new TallOakTreeDefaults();
	
	static {
		defaults.add(BigBirch);
		defaults.add(BigPine);
		defaults.add(BlockOak);
		defaults.add(Cyprus);
		defaults.add(Dead);
		defaults.add(GreatOak);
		defaults.add(Hat);
		defaults.add(SwampOak);
		defaults.add(TallOak);
	}
	
	public static List<TreeConfiguration> getDefaults() {
		return defaults;
	}
}
