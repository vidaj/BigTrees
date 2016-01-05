package karob.bigtrees;

import karob.bigtrees.compat.BlockPos;
import karob.bigtrees.generators.TreeWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
//import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Constants.ModId, name = Constants.ModName, version = Constants.ModVersion)
public class BigTrees {

	@Instance(Constants.ModId)
	public static BigTrees instance;
	
	private final TreeWorldGenerator treeWorldGenerator = new TreeWorldGenerator();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		instance = this;

		
		KTreeCfg.init(event.getModConfigurationDirectory());
		
		GameRegistry.registerWorldGenerator(treeWorldGenerator, 1);
		
		if (KTreeCfg.disableVanillaTrees) {
			MinecraftForge.EVENT_BUS.register(this);
			MinecraftForge.TERRAIN_GEN_BUS.register(this);
		}
		
//		registerBlock(new BlockBTSapling().setBlockName("bt_bigSapling"),ItemBlockSapling.class);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
//		GameRegistry.addRecipe(
//				new ItemStack(GameRegistry.findBlock("bigtrees",
//						"bt_bigSapling"), 1, 0), new Object[] { "#", "X", "X",
//						'#', new ItemStack(Blocks.sapling, 1, 0), 'X',
//						new ItemStack(Blocks.log, 1, 0) });
//		GameRegistry.addRecipe(
//				new ItemStack(GameRegistry.findBlock("bigtrees",
//						"bt_bigSapling"), 1, 1), new Object[] { "#", "X", "X",
//						'#', new ItemStack(Blocks.sapling, 1, 2), 'X',
//						new ItemStack(Blocks.log, 1, 2) });
//		GameRegistry.addRecipe(
//				new ItemStack(GameRegistry.findBlock("bigtrees",
//						"bt_bigSapling"), 1, 2), new Object[] { "#", "X", "X",
//						'#', new ItemStack(Blocks.sapling, 1, 1), 'X',
//						new ItemStack(Blocks.log, 1, 1) });
//		GameRegistry.addRecipe(
//				new ItemStack(GameRegistry.findBlock("bigtrees",
//						"bt_bigSapling"), 1, 4), new Object[] { " # ", " X ",
//						"X X", '#', new ItemStack(Blocks.sapling, 1, 1), 'X',
//						new ItemStack(Blocks.log, 1, 1) });
//		GameRegistry.addRecipe(
//				new ItemStack(GameRegistry.findBlock("bigtrees",
//						"bt_bigSapling"), 1, 3), new Object[] { "###", " X ",
//						" X ", '#', Items.stick, 'X',
//						new ItemStack(Blocks.log, 1, 0) });
//		GameRegistry.addRecipe(
//				new ItemStack(GameRegistry.findBlock("bigtrees",
//						"bt_bigSapling"), 1, 5), new Object[] { " # ", "#X#",
//						'#', new ItemStack(Blocks.sapling, 1, 0), 'X',
//						new ItemStack(Blocks.log, 1, 0) });// saplings and
//															// recipes changed
//															// to use only one
//															// block id.
	}

	// moved most of decoration to a new file
	@SubscribeEvent
	public boolean decorate(DecorateBiomeEvent.Decorate evt) {
		if (evt.type == DecorateBiomeEvent.Decorate.EventType.TREE) {
			evt.setResult(Result.DENY);
			return true;
		}

		return false;
	}
	
	private BlockPos getBlockPos(DecorateBiomeEvent evt) {
		return new BlockPos(evt.pos);
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
