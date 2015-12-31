package karob.bigtrees.compat;

import karob.bigtrees.config.BlockAndMeta;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class WorldWrapper {

	private World world;

	public WorldWrapper(World world) {
		this.world = world;
	}
	
	public BiomeGenBase getBiomeGenForCoords(BlockPos pos) {
		return world.getBiomeGenForCoords(pos.getX(), pos.getZ());
	}
	
	public int getDimensionId() {
		return world.provider.dimensionId;
	}
	
	public long getSeed() {
		return world.getSeed();
	}
	
	public BlockPos getHeight(BlockPos pos) {
		return new BlockPos(pos.getX(), world.getHeightValue(pos.getX(), pos.getZ()), pos.getZ());
	}
	
	public void setBlock(BlockPos pos, BlockAndMeta block, BlockFlags ... flags) {
		int flag = 0;
		for (BlockFlags f : flags) {
			flag &= f.value;
		}
		
		world.setBlock(pos.getX(), pos.getY(), pos.getZ(), block.getBlock());
		if (block.hasMeta()) {
			world.setBlockMetadataWithNotify(pos.getX(), pos.getY(), pos.getZ(), block.getMeta(), flag);
		}
	}
	
	public BlockAndMeta getBlock(BlockPos pos) {
		Block block = world.getBlock(pos.getX(), pos.getY(), pos.getZ());
		int meta = world.getBlockMetadata(pos.getX(), pos.getY(), pos.getZ());
		
		return new BlockAndMeta(block, meta);
	}
	
	public enum BlockFlags {
		NoAction(0),
		BlockUpdate(1),
		SendToClient(2),
		PreventReRendering(4);
		
		private final int value;
		private BlockFlags(int flag) {
			value = flag;
		}
		
	}

	public boolean setToAir(BlockPos pos) {
		return world.setBlockToAir(pos.getX(), pos.getY(), pos.getZ());
	}
}
