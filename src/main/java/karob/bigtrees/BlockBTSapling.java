package karob.bigtrees;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
//import net.minecraft.client.renderer.texture.IconRegister;
//import net.minecraft.util.Icon;
//import net.minecraftforge.common.ForgeDirection;

public class BlockBTSapling extends BlockSapling{

//    private int treeType;
//    
//private static final String[] treeNames = new String[] {"BigOak", "BigBirch", "BigPine", "DeadTree", "Cyprus", "HatTree"};
//    
//    private int[] growTimes = {
//            3, 3, 3, 3, 3, 3
//    };
//    
//    private int x_offset;
//    private int z_offset;
//    
//    private Block[][] neighborBuffer = new Block[7][7];
//    private int[][] neighborBugger = new int[7][7];
//
//    public BlockBTSapling()
//    {   
//        //super(par1);
////        neighborBuffer = new int[7][7];
//        this.setHardness(0.0F);
//	this.setStepSound(Block.soundTypeGrass);
//        float var3 = 0.4F;
//        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
//        this.setCreativeTab(CreativeTabs.tabDecorations);
//        //treeType = type;
//        
//        //System.out.println("Highlands Saplings texture file: " + this.currentTexture);
//    }
//
//    	@Override
//	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
//	{
//		for (int i = 0; i < treeNames.length; i++) {
//			list.add(new ItemStack(block, 1, i));
//		}
//	}
//
//    /**
//     * Ticks the block if it's been scheduled
//     */
//	@Override
//	//TODO:		updateTick()
//	public void updateTick(World world, BlockPos pos, Random random)
//	{
//		if (!world.isRemote)
//		{
//			if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0) 
//			{
//				//TODO: growTree()
//				this.func_149878_d(world, x, y, z, random);
//			}
//		}
//	}
//
//    
//    @Override
//    public void func_149879_c(World p_149879_1_, int p_149879_2_, int p_149879_3_, int p_149879_4_, Random p_149879_5_)
//    {
//        int l = p_149879_1_.getBlockMetadata(p_149879_2_, p_149879_3_, p_149879_4_);
//
//            this.func_149878_d(p_149879_1_, p_149879_2_, p_149879_3_, p_149879_4_, p_149879_5_);
//    }    
//
//
//    /*public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
//    {
//        //System.out.println("Sapling activated!  " + (player.inventory.getCurrentItem().getItemDamage()==15) + "  " + (player.inventory.getCurrentItem().itemID==Item.dyePowder.itemID));
//        
//        if(!KTreeCfg.passRecipeRealm(par1World.provider.dimensionId)) return false;
//        
//        if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItemDamage()==15 && player.inventory.getCurrentItem().item==Item.dye)
//        {
//            if(player.capabilities.isCreativeMode == true)
//                growTree(par1World,par2,par3,par4,new Random());
//            else{
//            //increase metadata by one if it is not ready to grow yet
//            if(par1World.getBlockMetadata(par2, par3, par4) < growTimes[treeType]){
//                par1World.setBlock(par2, par3, par4, this, par1World.getBlockMetadata(par2, par3, par4)+1, 2);
//                if (!par1World.isRemote)
//                {
//                    par1World.playAuxSFX(2005, par2, par3, par4, 0);
//                }
//            }
//            else growTree(par1World,par2,par3,par4,new Random());
//            
//            //reduce bonemeal stack size by one
//            player.inventory.getCurrentItem().stackSize--;
//            }
//            return true;
//        }
//        return false;
//    }*/
//    
//	@Override
//	//TODO:		growTree()
//	public void func_149878_d(World par1World, int i, int j, int k, Random r)
//	{
////		resetNeighborBuffer();
////		int meta = par1World.getBlockMetadata(i, j, k) & 15;
////		Object obj = null;
////	    if(meta == 0){
////	            if(checkTreeCoin4x4(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta)){
////	                    clearTreeCoin4x4(par1World, i, j, k);
////			    KWorldGenBigTree tree = (new KWorldGenBigTree(true));
////			    tree.setConfigOptions(Blocks.log, Blocks.leaves, 0, 0, Blocks.dirt, Blocks.grass, 28, 32, 13);
////	                    if(!tree.greatGenerate(par1World, r, x_offset, j, z_offset)){
////            	            	resetTreeCoin4x4(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta);
////                	    }
////            	    }else if(checkTreeRing4x4(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta)){
////                    	clearTreeRing4x4(par1World, i, j, k);
////			KWorldGenBigTree tree = (new KWorldGenBigTree(true));
////			tree.setConfigOptions(Blocks.log, Blocks.leaves, 0, 0, Blocks.dirt, Blocks.grass, 28, 32, 13);
////                	if(!tree.swampGenerate(par1World, r, x_offset, j, z_offset)){
////                    		resetTreeRing4x4(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta);
////                	}
////            	    }else if(checkTreeBlock2x2(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta)){
////                    	clearTreeBlock2x2(par1World, i, j, k);
////			KWorldGenBigTree tree = (new KWorldGenBigTree(true));
////			tree.setConfigOptions(Blocks.log, Blocks.leaves, 0, 0, Blocks.dirt, Blocks.grass, 12, 18, 8);
////                	if(!tree.blockOakGenerate(par1World, r, x_offset, j, z_offset)){
////                    		resetTreeBlock2x2(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta);
////                	}
////            	    }else{
////                    	par1World.setBlockToAir(i, j, k);
////			KWorldGenTallTree tree = (new KWorldGenTallTree(true));
////			tree.setConfigOptions(Blocks.log, Blocks.leaves, 0, 0, Blocks.dirt, Blocks.grass, 28, 32, 13,0.23, 0.32, 0.60, 0.618034, 0.50, 0.60, 0.0, 0.08, 3, 0.12, 0.60, 4, 30, 0.75);
////                	if(!tree.generate(par1World, r, i, j, k)){
////                    		par1World.setBlock(i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"));
////                	}
////            	    }
////// BIRCH SAPLINGS
////        }else if(meta == 1){
////        	if(checkTreeBlock2x2(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta)){
////        	        clearTreeBlock2x2(par1World, i, j, k);
////			KWorldGenBigTree tree = (new KWorldGenBigTree(true));
////			tree.setConfigOptions(Blocks.log, Blocks.leaves, 2, 2, Blocks.dirt, Blocks.grass, 19, 24, 13);
////               		if(!tree.birchGenerate(par1World, r, x_offset, j, z_offset)){
////               		    resetTreeBlock2x2(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta);
////               		}
////        	}
////// PINE SAPLINGS
////        }else if(meta == 2){
////        	if(checkTreeCross3x3(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta)){
////               		clearTreeCross3x3(par1World, i, j, k);
////			KWorldGenBigTree tree = (new KWorldGenBigTree(true));
////			tree.setConfigOptions(Blocks.log, Blocks.leaves, 1, 1, Blocks.dirt, Blocks.grass, 18, 22, 13);
////              		if(!tree.pineGenerate(par1World, r, x_offset, j, z_offset)){
////               			resetTreeCross3x3(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta);
////              		}
////        	}
////// DEAD SAPLINGS
////        }else if(meta == 3){
////        	if(checkTreeBlock2x2(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta)){
////               		clearTreeBlock2x2(par1World, i, j, k);
////			KWorldGenBigTree tree = (new KWorldGenBigTree(true));
////			tree.setConfigOptions(Blocks.log, Blocks.leaves, 0, 0, Blocks.dirt, Blocks.grass, 13, 17, 10);
////              		if(!tree.desertGenerate(par1World, r, x_offset, j, z_offset)){
////               			resetTreeBlock2x2(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta);
////               		}
////        	}
////// CYPRUS SAPLINGS
////        }else if(meta == 4){
////        	if(checkTreeBlock2x2(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta)){
////               		clearTreeBlock2x2(par1World, i, j, k);
////			KWorldGenCyprusTree tree = (new KWorldGenCyprusTree(true));
////			tree.setConfigOptions(Blocks.log, Blocks.leaves, 1, 1, Blocks.dirt, Blocks.grass, 28, 32, 13);
////               		if(!tree.generate(par1World, r, x_offset, j, z_offset)){
////               			resetTreeBlock2x2(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta);
////              		}
////        	}
////// HAT TREE SAPLINGS
////        }else if(meta == 5){
////        	if(checkTreeRing4x4(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta)){
////               		clearTreeRing4x4(par1World, i, j, k);
////			KWorldGenHatTree tree = (new KWorldGenHatTree(true));
////			tree.setConfigOptions(Blocks.log, Blocks.leaves, 0, 0, Blocks.dirt, Blocks.grass, 28, 32, 13);
////              		if(!tree.generate(par1World, r, x_offset, j, z_offset)){
////               			resetTreeRing4x4(par1World, i, j, k, GameRegistry.findBlock("bigtrees", "bt_bigSapling"), meta);
////               		}
////        	}
////        }
//    }
//		
//
//
///*
//    public void growTree(World par1World, int i, int j, int k, Random r){
//        int typeId = par1World.getBlockId(i, j, k);
//        int growId = 0;
//
//        if(typeId == KTreeCfg.bigOakSaplingId){
//
//            if(checkTreeCoin4x4(par1World, i, j, k, KTreeCfg.bigOakSaplingId)){
//                (new KWorldGenBigTree(true)).greatGenerate(par1World, r, i, j, k);
//            }else if(checkTreeRing4x4(par1World, i, j, k, KTreeCfg.bigOakSaplingId)){
//                (new KWorldGenBigTree(true)).swampGenerate(par1World, r, i, j, k);
//            }else if(checkTreeBlock2x2(par1World, i, j, k, KTreeCfg.bigOakSaplingId)){
//                (new KWorldGenBigTree(true)).blockOakGenerate(par1World, r, i, j, k);
//            }else{
//                (new KWorldGenTallTree(true)).generate(par1World, r, i, j, k);
//            }
//        }else if(typeId == KTreeCfg.bigPineSaplingId){
//            if(checkTreeBlock2x2(par1World, i, j, k, KTreeCfg.bigPineSaplingId)){
//                (new KWorldGenBigTree(true)).pineGenerate(par1World, r, i, j, k);
//            }
//        }else if(typeId == KTreeCfg.deadTreeSaplingId){
//            if(checkTreeBlock2x2(par1World, i, j, k, KTreeCfg.deadTreeSaplingId)){
//                (new KWorldGenBigTree(true)).desertGenerate(par1World, r, i, j, k);
//            }
//        }else if(typeId == KTreeCfg.cyprusSaplingId){
//            if(checkTreeBlock2x2(par1World, i, j, k, KTreeCfg.cyprusSaplingId)){
//                (new KWorldGenCyprusTree(true)).generate(par1World, r, i, j, k);
//            }
//        }else if(typeId == KTreeCfg.hatTreeSaplingId){
//            if(checkTreeRing4x4(par1World, i, j, k, KTreeCfg.hatTreeSaplingId)){
//                (new KWorldGenHatTree(true)).generate(par1World, r, i, j, k);
//            }
//        }
//        switch(growId){
//            case 1: (new KWorldGenBigTree(true)).greatGenerate(par1World, r, i, j, k);
//                break;
//            case 2: (new KWorldGenBigTree(true)).swampGenerate(par1World, r, i, j, k);
//                break;
//            case 3: (new KWorldGenBigTree(true)).blockOakGenerate(par1World, r, i, j, k);
//                break;
//            case 4: (new KWorldGenTallTree(true)).generate(par1World, r, i, j, k);
//                break;
//            case 5: (new KWorldGenBigTree(true)).pineGenerate(par1World, r, i, j, k);
//                break;
//            case 6: (new KWorldGenBigTree(true)).desertGenerate(par1World, r, i, j, k);
//                break;
//            case 7: (new KWorldGenCyprusTree(true)).generate(par1World, r, i, j, k);
//                break;
//            case 8: (new KWorldGenHatTree(true)).generate(par1World, r, i, j, k);
//                break;
//            default:
//                break;
//        }
//    }*/
//
//    public boolean checkTreeBlock2x2(World wo, int x, int y, int z, Block id, int meta){
//        if(wo.getBlock(x, y, z - 1) == id && wo.getBlockMetadata(x, y, z - 1) == meta){
//            if(wo.getBlock(x - 1, y, z) == id && wo.getBlock(x - 1, y, z - 1) == id && wo.getBlockMetadata(x - 1, y, z) == meta && wo.getBlockMetadata(x - 1, y, z - 1) == meta){
//                x_offset = x - 1;
//                z_offset = z - 1;
//                return true;
//            }
//            if(wo.getBlock(x + 1, y, z) == id && wo.getBlock(x + 1, y, z - 1) == id && wo.getBlockMetadata(x + 1, y, z) == meta && wo.getBlockMetadata(x + 1, y, z - 1) == meta){
//                x_offset = x;
//                z_offset = z - 1;
//                return true;
//            }
//        }
//        if(wo.getBlock(x, y, z + 1) == id && wo.getBlockMetadata(x, y, z + 1) == meta){
//            if(wo.getBlock(x - 1, y, z) == id && wo.getBlock(x - 1, y, z + 1) == id && wo.getBlockMetadata(x - 1, y, z) == meta && wo.getBlockMetadata(x - 1, y, z + 1) == meta){
//                x_offset = x - 1;
//                z_offset = z;
//                return true;
//            }
//            if(wo.getBlock(x + 1, y, z) == id && wo.getBlock(x + 1, y, z + 1) == id && wo.getBlockMetadata(x + 1, y, z) == meta && wo.getBlockMetadata(x + 1, y, z + 1) == meta){
//                x_offset = x;
//                z_offset = z;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void clearTreeBlock2x2(World wo, int x, int y, int z){
//        wo.setBlockToAir(x_offset, y, z_offset);
//        wo.setBlockToAir(x_offset + 1, y, z_offset);
//        wo.setBlockToAir(x_offset + 1, y, z_offset + 1);
//        wo.setBlockToAir(x_offset, y, z_offset + 1);
//    }
//
//    public void resetTreeBlock2x2(World wo, int x, int y, int z, Block id, int meta){
//        wo.setBlock(x_offset, y, z_offset, id, meta, 3);
//        wo.setBlock(x_offset + 1, y, z_offset, id, meta, 3);
//        wo.setBlock(x_offset + 1, y, z_offset + 1, id, meta, 3);
//        wo.setBlock(x_offset, y, z_offset + 1, id, meta, 3);
//    }
//
//    public boolean checkTreeCross3x3(World wo, int x, int y, int z, Block id, int meta){
//        int summation = 0;
//        if(wo.getBlock(x, y, z - 1) == id && wo.getBlockMetadata(x, y, z - 1) == meta){
//            summation = summation + 1;
//            if(wo.getBlock(x - 1, y, z - 1) == id && wo.getBlock(x + 1, y, z - 1) == id && wo.getBlock(x, y, z - 2) == id && wo.getBlockMetadata(x - 1, y, z - 1) == meta && wo.getBlockMetadata(x + 1, y, z - 1) == meta && wo.getBlockMetadata(x, y, z - 2) == meta){
//                x_offset = x;
//                z_offset = z - 1;
//                return true;
//            }
//        }
//        if(wo.getBlock(x, y, z + 1) == id && wo.getBlockMetadata(x, y, z + 1) == meta){
//            summation = summation + 1;
//            if(wo.getBlock(x - 1, y, z + 1) == id && wo.getBlock(x + 1, y, z + 1) == id && wo.getBlock(x, y, z + 2) == id && wo.getBlockMetadata(x - 1, y, z + 1) == meta && wo.getBlockMetadata(x + 1, y, z + 1) == meta && wo.getBlockMetadata(x, y, z + 2) == meta){
//                x_offset = x;
//                z_offset = z + 1;
//                return true;
//            }
//        }
//        if(wo.getBlock(x - 1, y, z) == id && wo.getBlockMetadata(x - 1, y, z) == meta){
//            summation = summation + 1;
//            if(wo.getBlock(x - 1, y, z - 1) == id && wo.getBlock(x - 1, y, z + 1) == id && wo.getBlock(x - 2, y, z) == id && wo.getBlockMetadata(x - 1, y, z - 1) == meta && wo.getBlockMetadata(x - 1, y, z + 1) == meta && wo.getBlockMetadata(x - 2, y, z) == meta){
//                x_offset = x - 1;
//                z_offset = z;
//                return true;
//            }
//        }
//        if(wo.getBlock(x + 1, y, z) == id && wo.getBlockMetadata(x + 1, y, z) == meta){
//            summation = summation + 1;
//            if(wo.getBlock(x + 1, y, z - 1) == id && wo.getBlock(x + 1, y, z + 1) == id && wo.getBlock(x + 2, y, z) == id && wo.getBlockMetadata(x + 1, y, z - 1) == meta && wo.getBlockMetadata(x + 1, y, z + 1) == meta && wo.getBlockMetadata(x + 2, y, z) == meta){
//                x_offset = x + 1;
//                z_offset = z;
//                return true;
//            }
//        }
//        if(summation == 4){
//            x_offset = x;
//            z_offset = z;
//            return true;
//        }
//        return false;
//    }
//
//    public void clearTreeCross3x3(World wo, int x, int y, int z){
//        wo.setBlockToAir(x_offset, y, z_offset);
//        wo.setBlockToAir(x_offset + 1, y, z_offset);
//        wo.setBlockToAir(x_offset - 1, y, z_offset);
//        wo.setBlockToAir(x_offset, y, z_offset + 1);
//        wo.setBlockToAir(x_offset, y, z_offset - 1);
//    }
//
//    public void resetTreeCross3x3(World wo, int x, int y, int z, Block id, int meta){
//        wo.setBlock(x_offset, y, z_offset, id, meta, 3);
//        wo.setBlock(x_offset + 1, y, z_offset, id, meta, 3);
//        wo.setBlock(x_offset - 1, y, z_offset , id, meta, 3);
//        wo.setBlock(x_offset, y, z_offset + 1, id, meta, 3);
//        wo.setBlock(x_offset, y, z_offset - 1, id, meta, 3);
//    }
//
//    public boolean checkTreeCoin4x4(World wo, int x, int y, int z, Block id, int meta){
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, 0, 1)) return true;
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, -1, 1)) return true;
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, 1, 0)) return true;
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, 0, 0)) return true;
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, -1, 0)) return true;
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, -2, 0)) return true;
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, 1, -1)) return true;
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, 0, -1)) return true;
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, -1, -1)) return true;
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, -2, -1)) return true;
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, 0, -2)) return true;
//        if(checkTreeCoin4x4_sub(wo, x, y, z, id, meta, -1, -2)) return true;
//        return false;
//    }
//
//    public boolean checkTreeCoin4x4_sub(World wo, int x, int y, int z, Block id, int meta, int ox, int oz){
//        if(checkNeighbor(wo, x, y, z, id, meta, ox + 0, oz - 1)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 1, oz - 1)
//            && checkNeighbor(wo, x, y, z, id, meta, ox - 1, oz + 0)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 0, oz + 0)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 1, oz + 0)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 2, oz + 0)
//            && checkNeighbor(wo, x, y, z, id, meta, ox - 1, oz + 1)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 0, oz + 1)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 1, oz + 1)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 2, oz + 1)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 0, oz + 2)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 1, oz + 2)){
//            x_offset = x + ox;
//            z_offset = z + oz;
//            return true;
//        }
//        return false;
//    }
//
//    public void clearTreeCoin4x4(World wo, int x, int y, int z){
//        wo.setBlockToAir(x_offset, y, z_offset);
//        wo.setBlockToAir(x_offset + 1, y, z_offset);
//        wo.setBlockToAir(x_offset + 1, y, z_offset + 1);
//        wo.setBlockToAir(x_offset, y, z_offset + 1);
//
//        wo.setBlockToAir(x_offset - 1, y, z_offset);
//        wo.setBlockToAir(x_offset, y, z_offset - 1);
//        wo.setBlockToAir(x_offset + 1, y, z_offset - 1);
//        wo.setBlockToAir(x_offset + 2, y, z_offset);
//        wo.setBlockToAir(x_offset + 2, y, z_offset - 1);
//        wo.setBlockToAir(x_offset + 1, y, z_offset - 2);
//        wo.setBlockToAir(x_offset, y, z_offset - 2);
//        wo.setBlockToAir(x_offset - 1, y, z_offset - 1);
//    }
//
//    public void resetTreeCoin4x4(World wo, int x, int y, int z, Block id, int meta){
//        wo.setBlock(x_offset, y, z_offset, id, meta, 3);
//        wo.setBlock(x_offset + 1, y, z_offset, id, meta, 3);
//        wo.setBlock(x_offset + 1, y, z_offset + 1, id, meta, 3);
//        wo.setBlock(x_offset, y, z_offset + 1, id, meta, 3);
//
//        wo.setBlock(x_offset - 1, y, z_offset, id, meta, 3);
//        wo.setBlock(x_offset, y, z_offset - 1, id, meta, 3);
//        wo.setBlock(x_offset + 1, y, z_offset - 1, id, meta, 3);
//        wo.setBlock(x_offset + 2, y, z_offset, id, meta, 3);
//        wo.setBlock(x_offset + 2, y, z_offset - 1, id, meta, 3);
//        wo.setBlock(x_offset + 1, y, z_offset - 2, id, meta, 3);
//        wo.setBlock(x_offset, y, z_offset - 2, id, meta, 3);
//        wo.setBlock(x_offset - 1, y, z_offset - 1, id, meta, 3);
//    }
//
//    public boolean checkTreeRing4x4(World wo, int x, int y, int z, Block id, int meta){
//        if(checkTreeRing4x4_sub(wo, x, y, z, id, meta, 0, 1)) return true;
//        if(checkTreeRing4x4_sub(wo, x, y, z, id, meta, -1, 1)) return true;
//        if(checkTreeRing4x4_sub(wo, x, y, z, id, meta, 1, 0)) return true;
//        if(checkTreeRing4x4_sub(wo, x, y, z, id, meta, -2, 0)) return true;
//        if(checkTreeRing4x4_sub(wo, x, y, z, id, meta, 1, -1)) return true;
//        if(checkTreeRing4x4_sub(wo, x, y, z, id, meta, -2, -1)) return true;
//        if(checkTreeRing4x4_sub(wo, x, y, z, id, meta, 0, -2)) return true;
//        if(checkTreeRing4x4_sub(wo, x, y, z, id, meta, -1, -2)) return true;
//        return false;
//    }
//
//    public boolean checkTreeRing4x4_sub(World wo, int x, int y, int z, Block id, int meta, int ox, int oz){
//        if(checkNeighbor(wo, x, y, z, id, meta, ox + 0, oz - 1)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 1, oz - 1)
//            && checkNeighbor(wo, x, y, z, id, meta, ox - 1, oz + 0)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 2, oz + 0)
//            && checkNeighbor(wo, x, y, z, id, meta, ox - 1, oz + 1)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 2, oz + 1)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 0, oz + 2)
//            && checkNeighbor(wo, x, y, z, id, meta, ox + 1, oz + 2)){
//            x_offset = x + ox;
//            z_offset = z + oz;
//            return true;
//        }
//        return false;
//    }
//
//    public void clearTreeRing4x4(World wo, int x, int y, int z){
//        wo.setBlockToAir(x_offset - 1, y, z_offset);
//        wo.setBlockToAir(x_offset, y, z_offset - 1);
//        wo.setBlockToAir(x_offset + 1, y, z_offset - 1);
//        wo.setBlockToAir(x_offset + 2, y, z_offset);
//        wo.setBlockToAir(x_offset + 2, y, z_offset - 1);
//        wo.setBlockToAir(x_offset + 1, y, z_offset - 2);
//        wo.setBlockToAir(x_offset, y, z_offset - 2);
//        wo.setBlockToAir(x_offset - 1, y, z_offset - 1);
//    }
//
//    public void resetTreeRing4x4(World wo, int x, int y, int z, Block id, int meta){
//        wo.setBlock(x_offset - 1, y, z_offset, id, meta, 3);
//        wo.setBlock(x_offset, y, z_offset - 1, id, meta, 3);
//        wo.setBlock(x_offset + 1, y, z_offset - 1, id, meta, 3);
//        wo.setBlock(x_offset + 2, y, z_offset, id, meta, 3);
//        wo.setBlock(x_offset + 2, y, z_offset - 1, id, meta, 3);
//        wo.setBlock(x_offset + 1, y, z_offset - 2, id, meta, 3);
//        wo.setBlock(x_offset, y, z_offset - 2, id, meta, 3);
//        wo.setBlock(x_offset - 1, y, z_offset - 1, id, meta, 3);
//    }
//
//    public void resetNeighborBuffer(){
//        for(int i = 0; i <= 6; i ++){
//            for(int j = 0; j <= 6; j ++){
//                neighborBuffer[i][j] = null;
//		neighborBugger[i][j] = -1;
//            }
//        }
//    }
//
//    public boolean checkNeighbor(World wo, int x, int y, int z, Block id, int meta, int ox, int oz){
//        Block temp = neighborBuffer[ox + 3][oz + 3];
//	int temp1 = neighborBugger[ox + 3][oz + 3];
//        if(temp == null || temp1 == -1){
//            temp = wo.getBlock(x + ox, y, z + oz);
//            neighborBuffer[ox + 3][oz + 3] = temp;
//	    temp1 = wo.getBlockMetadata(x + ox, y, z + oz);
//	    neighborBugger[ox + 3][oz + 3] = temp1;
//        }
//        return (temp == id && temp1 == meta);
//    }
//
//	@Override
//	//TODO     damageDropped()
//	public int damageDropped(int meta)
//	{
//		return meta & 15;
//	}
//
//	@Override
//	//TODO:	   getDamageValue()
//	public int getDamageValue(World world, int x, int y, int z)
//	{
//		return world.getBlockMetadata(x, y, z) & 15;
//	}
    
}
