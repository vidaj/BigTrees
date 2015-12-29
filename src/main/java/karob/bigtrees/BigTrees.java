package karob.bigtrees;

import java.io.File;

import karob.bigtrees.generators.TreeWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
//import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "bigtrees", name = "BigTrees", version = "1.7.2b")
public class BigTrees {

	public File config;
	// need this since the directory is not available in postInit
	public String configDir;
	private IWorldGenerator treeGenerator = new TreeWorldGenerator();

	@Instance("bigtrees")
	public static BigTrees instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		instance = this;

		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.TERRAIN_GEN_BUS.register(this);
		configDir = event.getModConfigurationDirectory() + File.separator
				+ "bigtrees";
		KTreeCfg.init(configDir);
		registerBlock(new BlockBTSapling().setBlockName("bt_bigSapling"),
				ItemBlockSapling.class);// this moved to pre-init to correct
										// texture loading issues
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		GameRegistry.addRecipe(
				new ItemStack(GameRegistry.findBlock("bigtrees",
						"bt_bigSapling"), 1, 0), new Object[] { "#", "X", "X",
						'#', new ItemStack(Blocks.sapling, 1, 0), 'X',
						new ItemStack(Blocks.log, 1, 0) });
		GameRegistry.addRecipe(
				new ItemStack(GameRegistry.findBlock("bigtrees",
						"bt_bigSapling"), 1, 1), new Object[] { "#", "X", "X",
						'#', new ItemStack(Blocks.sapling, 1, 2), 'X',
						new ItemStack(Blocks.log, 1, 2) });
		GameRegistry.addRecipe(
				new ItemStack(GameRegistry.findBlock("bigtrees",
						"bt_bigSapling"), 1, 2), new Object[] { "#", "X", "X",
						'#', new ItemStack(Blocks.sapling, 1, 1), 'X',
						new ItemStack(Blocks.log, 1, 1) });
		GameRegistry.addRecipe(
				new ItemStack(GameRegistry.findBlock("bigtrees",
						"bt_bigSapling"), 1, 4), new Object[] { " # ", " X ",
						"X X", '#', new ItemStack(Blocks.sapling, 1, 1), 'X',
						new ItemStack(Blocks.log, 1, 1) });
		GameRegistry.addRecipe(
				new ItemStack(GameRegistry.findBlock("bigtrees",
						"bt_bigSapling"), 1, 3), new Object[] { "###", " X ",
						" X ", '#', Items.stick, 'X',
						new ItemStack(Blocks.log, 1, 0) });
		GameRegistry.addRecipe(
				new ItemStack(GameRegistry.findBlock("bigtrees",
						"bt_bigSapling"), 1, 5), new Object[] { " # ", "#X#",
						'#', new ItemStack(Blocks.sapling, 1, 0), 'X',
						new ItemStack(Blocks.log, 1, 0) });// saplings and
															// recipes changed
															// to use only one
															// block id.
	}

	// This section was added to handle new configurations
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		BiomeDictionary.registerAllBiomesAndGenerateEvents();
		config = new File(configDir + File.separator + "treeconfigs");
		config.mkdir();
		KTreeCfgTrees.init(config);
		config = new File(configDir, "biomegroups.cfg");
		KTreeCfgBiomes.init(config);
	}

	// moved most of decoration to a new file
	@SubscribeEvent
	public boolean decorate(DecorateBiomeEvent.Decorate evt) {
		if (evt.type == DecorateBiomeEvent.Decorate.EventType.TREE) {
			
			int chunkX = evt.chunkX;
			int chunkZ = evt.chunkZ;
			BiomeGenBase biome = evt.world.getBiomeGenForCoords(chunkX, chunkZ);
			if (!KTreeCfg.passRealm(evt.world.provider.dimensionId)) {
				return true;
			}
			return KTreeDecorate.decorate(evt.world, evt.rand, chunkX, chunkZ,
					biome);
		}
		return false;
	}

	public static void registerBlock(Block b, String name) {
		GameRegistry.registerBlock(b, b.getUnlocalizedName());
		LanguageRegistry.addName(b, name);
	}

	public static void registerBlock(Block block,
			Class<? extends ItemBlock> itemBlockClass) {
		GameRegistry.registerBlock(block, itemBlockClass, block
				.getUnlocalizedName().replace("tile.", ""));
	}

}
