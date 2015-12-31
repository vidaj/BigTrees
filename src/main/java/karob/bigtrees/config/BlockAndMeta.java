package karob.bigtrees.config;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockAndMeta {
	
	private Block block;
	
	private int meta;
	
	private boolean hasMeta;
	
	public BlockAndMeta(String codedBlockAndMetaString) {
		String[] nameItems = codedBlockAndMetaString.split(":");
		
		if (nameItems.length >= 2) {
			block = GameRegistry.findBlock(nameItems[0], nameItems[1]);
		}
		
		if (nameItems.length == 3) {
			meta = Integer.parseInt(nameItems[2]);
			hasMeta = true;
		}
		
		if (block == null) {
			block = Block.getBlockFromName(nameItems[0]);
		}
	}
	
	public boolean isAir() {
		return block == Blocks.air;
	}
	
	public BlockAndMeta(Block block) {
		this.block = block;
	}
	
	public BlockAndMeta(Block block, int meta) {
		this.block = block;
		this.meta = meta;
		this.hasMeta = true;
	}
	
	public boolean areEqual(BlockAndMeta ... blocks) {
		
		for (BlockAndMeta other : blocks) {
			if (areEqual(this, other)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean areEqual(BlockAndMeta me, BlockAndMeta other) {
		if (me.block == other.block) {
			if (me.hasMeta) {
				return me.meta == other.meta;
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean areEqual(Block ... blocks) {
		
		for (Block other : blocks) {
			if (this.block == other) {
				return true;
			}
		}
		
		return false;
	}

	public Block getBlock() {
		return block;
	}

	public int getMeta() {
		return meta;
	}

	public boolean hasMeta() {
		return hasMeta;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getBlockName());
		if (hasMeta) {
			builder.append(":");
			builder.append(meta);
		}
		
		return builder.toString();
	}

	private String getBlockName() {
		if (block == null) {
			return "";
		}

		return GameData.getBlockRegistry().getNameForObject(block);
	}
}
