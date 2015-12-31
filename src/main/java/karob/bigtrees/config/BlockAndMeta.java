package karob.bigtrees.config;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockAndMeta {

	private Block block;
	
	private int meta;
	
	private boolean hasMeta;
	
	private IBlockState blockState;
	
	public BlockAndMeta(String codedBlockAndMetaString) {
		String[] nameItems = codedBlockAndMetaString.split(":");
		
		if (nameItems.length >= 2) {
			block = GameRegistry.findBlock(nameItems[0], nameItems[1]);
		}
		
		if (nameItems.length == 3) {
			meta = Integer.parseInt(nameItems[2]);
			hasMeta = true;
			blockState = block.getStateFromMeta(meta);
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
		this.blockState = block.getDefaultState();
	}
	
	public BlockAndMeta(Block block, int meta) {
		this.block = block;
		this.meta = meta;
		this.hasMeta = true;
		this.blockState = block.getStateFromMeta(meta);
	}
	
	public BlockAndMeta(IBlockState state) {
		this.block = state.getBlock();
		this.blockState = state;
		this.meta = block.getMetaFromState(state);
		if (this.meta != 0) {
			this.hasMeta = true;
		}
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
		ResourceLocation nameForObject = GameData.getBlockRegistry().getNameForObject(block);
		
		return nameForObject.toString();
	}
	
	public IBlockState toBlockState() {
		if (blockState != null) {
			return blockState;
		}
		
		return block.getStateFromMeta(meta);
	}
}
