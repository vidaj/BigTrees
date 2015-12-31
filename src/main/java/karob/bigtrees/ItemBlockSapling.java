package karob.bigtrees;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSapling extends ItemBlock
{
	private static final String[] treeNames = new String[] {"BigOak", "BigBirch", "BigPine", "DeadTree", "Cyprus", "HatTree"};

	public ItemBlockSapling(Block block)
	{
		super(block);
		
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 15;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		
		if (meta < 0 || meta >= treeNames.length) 
		{
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + (new StringBuilder()).append(treeNames[meta]).append("Sapling").toString();
	}
}
