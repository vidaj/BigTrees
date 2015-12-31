package karob.bigtrees.compat;

import karob.bigtrees.config.BlockAndMeta;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class WorldWrapper {

	private World world;

	public WorldWrapper(World world) {
		this.world = world;
	}
	
	public BiomeGenBase getBiomeGenForCoords(BlockPos pos) {
		return world.getBiomeGenForCoords(pos.toPos());
	}
	
	public int getDimensionId() {
		return world.provider.getDimensionId();
	}
	
	public long getSeed() {
		return world.getSeed();
	}
	
	public BlockPos getHeight(BlockPos pos) {
		return new BlockPos(world.getHeight(pos.toPos()));
	}
	
	public void setBlock(BlockPos pos, BlockAndMeta block, BlockFlags ... flags) {
		int flag = 0;
		for (BlockFlags f : flags) {
			flag &= f.value;
		}
		
		world.setBlockState(pos.toPos(), block.toBlockState(), flag);
	}
	
	public BlockAndMeta getBlock(BlockPos pos) {
		IBlockState state = world.getBlockState(pos.toPos());
		return new BlockAndMeta(state);
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
		return world.setBlockToAir(pos.toPos());
	}
}
