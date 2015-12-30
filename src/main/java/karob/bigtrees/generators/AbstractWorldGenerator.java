package karob.bigtrees.generators;

import java.util.List;

import karob.bigtrees.config.BlockAndMeta;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class AbstractWorldGenerator extends WorldGenerator {

	protected World worldObject;
	
	protected List<BlockAndMeta> baseblocks;

	protected AbstractWorldGenerator(boolean doBlockNotify) {
		super(doBlockNotify);
	}
	
	protected void setBlock(int x, int y, int z, Block block) {
		try {
			worldObject.setBlock(x, y, z, block, 0, 3);
		} catch (RuntimeException e) {
//			FMLLog.getLogger().error(new FormattedMessage("setBlock(%s, %s, %s)", new Object[]{ x, y, z }));
		}
	}

	protected void setBlockAndMetadata(int x, int y, int z, Block block, int meta) {
		try {
			worldObject.setBlock(x, y, z, block, meta, 3);
		} catch (RuntimeException e) {
//			FMLLog.getLogger().error(new FormattedMessage("setBlockAndMetadata(%s, %s, %s)", new Object[]{ x, y, z }));
		}
	}

	protected void setBlockAndMetadataWithNotify(int x, int y, int z, Block block, int meta) {
		try {
			worldObject.setBlock(x, y, z, block);
			worldObject.setBlockMetadataWithNotify(x, y, z, meta, 3);
		} catch (RuntimeException e) {
//			FMLLog.getLogger().error(new FormattedMessage("setBlockAndMetadataWithNotify(%s, %s, %s)", new Object[]{ x, y, z }));
		}
	}

	protected Block getBlock(int x, int y, int z) {
		try {
			return worldObject.getBlock(x, y, z);
		} catch (RuntimeException e) {
//			FMLLog.getLogger().error(new FormattedMessage("getBlock(%s, %s, %s)", new Object[]{ x, y, z }));
			return null;
		}
	}
	
	protected int getBlockMetadata(int x, int y, int z) {
		try {
			return worldObject.getBlockMetadata(x, y, z);
		} catch (RuntimeException e) {
//			FMLLog.getLogger().error(new FormattedMessage("getBlock(%s, %s, %s)", new Object[]{ x, y, z }));
			return 0;
		}
	}
	
	protected boolean isSupportedBaseBlock(int x, int y, int z) {
		Block block = getBlock(x, y, z);
		int meta = getBlockMetadata(x, y, z);
		
		for (BlockAndMeta possibleBaseBlock : baseblocks) {
			if (possibleBaseBlock.areEqual(block, meta)) {
				return true;
			}
		}
		
		return false;
	}
}
