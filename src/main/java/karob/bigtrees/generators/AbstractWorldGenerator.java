package karob.bigtrees.generators;

import java.util.List;
import java.util.Random;

import karob.bigtrees.KTreeCfg;
import karob.bigtrees.compat.BlockPos;
import karob.bigtrees.compat.IWorldGenerator;
import karob.bigtrees.compat.WorldWrapper;
import karob.bigtrees.compat.WorldWrapper.BlockFlags;
import karob.bigtrees.config.BlockAndMeta;
import karob.bigtrees.config.ITreeConfigurable;
import karob.bigtrees.config.TreeConfiguration;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class AbstractWorldGenerator extends WorldGenerator implements IWorldGenerator, ITreeConfigurable{

	protected WorldWrapper worldObject;
	
	protected List<BlockAndMeta> baseblocks;
	
	protected BlockAndMeta leaf;
	
	protected BlockAndMeta wood;
	
	protected int rootRand;
    protected int rootAlt;
    protected Random rand;
    protected int trunkSize;
    protected int stuntmin;
    protected int heightmin;
    protected int heightmax;

	protected AbstractWorldGenerator(boolean doBlockNotify) {
		super(doBlockNotify);
	}
	
	@Override
	public void setTreeConfiguration(TreeConfiguration treeConfiguration) {
		wood = treeConfiguration.getWood();
    	leaf = treeConfiguration.getLeaf();
    	baseblocks = treeConfiguration.getBaseBlocks();
		heightmin = treeConfiguration.getMinHeight();
		heightmax = treeConfiguration.getMaxHeight();
		stuntmin = treeConfiguration.getMinStunt();
	}
	
	protected void setBlock(BlockPos pos, BlockAndMeta block) {
		try {
			worldObject.setBlock(pos, block, BlockFlags.BlockUpdate, BlockFlags.SendToClient);
		} catch (RuntimeException e) {
//			FMLLog.getLogger().error(new FormattedMessage("setBlock(%s, %s, %s)", new Object[]{ x, y, z }));
		}
	}
	
	protected void setBlock(int x, int y, int z, BlockAndMeta block) {
		setBlock(new BlockPos(x, y, z), block);
	}

	protected void setBlockAndMetadataWithNotify(BlockPos pos, BlockAndMeta block) {
		setBlock(pos, block);
//		try {
//			worldObject.setBlock(pos, block, BlockFlags.BlockUpdate, BlockFlags.SendToClient);
//		} catch (RuntimeException e) {
////			FMLLog.getLogger().error(new FormattedMessage("setBlockAndMetadataWithNotify(%s, %s, %s)", new Object[]{ x, y, z }));
//		}
	}
	
	protected void setBlockAndMetadataWithNotify(int x, int y, int z, BlockAndMeta blockAndMeta) {
		setBlockAndMetadataWithNotify(new BlockPos(x, y, z), blockAndMeta);
	}

	protected BlockAndMeta getBlock(BlockPos pos) {
		try {
			return worldObject.getBlock(pos);
		} catch (RuntimeException e) {
//			FMLLog.getLogger().error(new FormattedMessage("getBlock(%s, %s, %s)", new Object[]{ x, y, z }));
			return new BlockAndMeta(new Block(Material.barrier));
		}
	}
	
	protected boolean setToAir(BlockPos pos) {
		try {
			return worldObject.setToAir(pos);
		} catch (RuntimeException e) {
			return false;
		}
	}
	
	protected boolean isSupportedBaseBlock(int x, int y, int z) {
		return isSupportedBaseBlock(new BlockPos(x, y, z));
	}
	
	protected boolean isSupportedBaseBlock(BlockPos pos) {
		BlockAndMeta block = getBlock(pos);
		
		for (BlockAndMeta possibleBaseBlock : baseblocks) {
			if (possibleBaseBlock.areEqual(block)) {
				return true;
			}
		}
		
		return false;
	}
	
	protected int getMedium(int i, int j, int k){
        //Roots can grow through the following block types.
        Block canGrowOpen[] = {Blocks.air, Blocks.sapling, Blocks.flowing_water, Blocks.water, Blocks.flowing_lava, Blocks.lava, Blocks.log, Blocks.log2, Blocks.leaves, Blocks.leaves2};//more to be re-added
        Block canGrowSolid[] = {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel}; //more to be re-added
        BlockPos pos = new BlockPos(i, j, k);
        BlockAndMeta qq = this.getBlock(pos);
        int medium = 0;
        for(int m = 0; m < canGrowOpen.length; m++){
          if(qq.getBlock()==canGrowOpen[m]){
            medium = 1;
            break;
          }
        }
        if(medium==0){
          for(int m = 0; m < canGrowSolid.length; m++){
          if(qq.getBlock()==canGrowSolid[m]){
              medium = 2;
              break;
            }
          }
        }
        return medium;
    }
	
	protected BlockAndMeta getBlock(int i, int j, int k) {
		return getBlock(new BlockPos(i, j, k));
	}
	
	protected void setBlockAndMetadata(int i, int j, int k, BlockAndMeta block) {
		setBlock(new BlockPos(i, j, k), block);
	}
	
	// Grows roots
    void growRoot(int l, int m, int n, double theta, double phi)
    {
        if(KTreeCfg.rootsEnable == false) return;
/*        int rr = rand.nextInt(3);
        if(rootRand == 0){
          m -= rr;
          rootRand = rr;
        }else{
          rootRand = 0;
        }
*/
/*        switch(rootRand){
          case 1:
            rootRand = 2;
            break;
          case 2:
            m -= 1;
            rootRand = 3;
            break;
          case 3:
            //m -= 2;
            rootRand = 4;
            break;
          case 4:
            m -= 1;
            rootRand = 0;
            break;
          default:
            rootRand = 0;
        }
*/
/*
        rootRand ++;
        if(rootRand > 5){
          rootAlt = rand.nextInt(2);
          rootRand = 0;
        }else{
          if(rootAlt == 1){
            m --;
            rootAlt = 0;
          }else{
            rootAlt = 1;
          }
        }*/
        if(rootAlt == 1){
          rootRand = rand.nextInt(2);
          m -= rootRand;
          rootAlt = 2;
        }else if(rootAlt == 2){
          if(rootRand == 0)
            m -= 1;
          rootAlt = 0;
        }else if(rootAlt == 10){
          m -= rand.nextInt(2);
        }
        m += 1;
        phi -= (double)rand.nextFloat()*0.05;
        theta += (double)rand.nextFloat()*0.1 - 0.05;
        double direction = (2.0*Math.PI) * theta;
        double curl = rand.nextFloat()*0.4F - 0.2F;
        double pitch = (2.0*Math.PI) * phi;
        int length = 2 + (3*trunkSize) + rand.nextInt(2);
        double x, y, z;
        if(l > 0) x = (double)l + 0.5;
        else x = (double)l - 0.5;
        //double y = (double)basePos[1] + 0.5;
        y = (double)m + 0.5;
        if(n > 0) z = (double)n + 0.5;
        else z = (double)n - 0.5;
        double x2, y2, z2, hoz;
        int i = (int)x; int j = (int)y; int k = (int)z;
        int i2, j2, k2, di, dk;
        int med = getMedium(i, j, k); //Check the "Medium" of a block for root growing - solid, open, or forbidden.
        int cnt = 0;
        while(length > 0.0){
          length --;
//          direction = direction + curl;
          curl = curl + rand.nextFloat()*0.06F - 0.03F;
          if(med == 1){ //Root growing in openness.
            pitch = (pitch + Math.PI/2.0)*0.7 - Math.PI/2.0;
//            if(pitch > 0.0){
//              pitch = pitch - 10.0*Math.PI/180.0;
//            }else{
//              pitch = (pitch + Math.PI/2.0)*0.7 - Math.PI/2.0;
//            }
          }else{ //Root growing in solid.
            pitch = (pitch + Math.PI/2.0)*0.9 - Math.PI/2.0;
          }

          hoz = Math.cos(pitch);
          x2 = x + Math.cos(direction)*hoz;
          y2 = y + Math.sin(pitch);
          z2 = z + Math.sin(direction)*hoz;
          i2 = (int)x2; j2 = (int)y2; k2 = (int)z2;
        if(i2 != i || j2 != j || k2 != k){
        	BlockPos pos = new BlockPos(i, j, k);
          this.setBlock(pos, wood); //1);
          cnt ++;
          if(cnt < 4){
            if(j2 != j-1 || i2 != i || k2 != k) {
            this.setBlock(new BlockPos(i, j-1, k), wood);
            }
          }
          med = getMedium(i2, j2, k2);
          if(med != 0){ //Grow normal.
            x = x2; y = y2; z = z2; i = i2; j = j2; k = k2;
          }else{ //Try to grow down now.
            med = getMedium(i, j-1, k);
            if(med != 0){ //Grow down.
              y = y - 1.0; j = j - 1; pitch = -Math.PI/2.0;
            }else{ //Try to grow out now.
              x2 = x + Math.cos(direction);
              z2 = z + Math.sin(direction);
              i2 = (int)x2; k2 = (int)z2;
              med = getMedium(i2, j, k2);
              if(med != 0){ //Grow out.
                x = x2; z = z2; i = i2; k = k2; pitch = 0.0;
              }else{ //Try bending now.
                int dir = ((int)(direction*8.0/Math.PI)); //Integer direction - 16 = complete rotation.
                if(dir < 0) dir = 15 - (15-dir) % 16;
                else dir = dir % 16;
                int pol = dir % 2; //'Polarity' of bending root - preferred bending direction.
                di = i2 - i; dk = k2 - k;
                int[] tdir = {0, 0, 0, 0}; //Testing directions.
                if(di == 0 && dk == 0){
                  if(dir < 1){di=1;dk=0;}
                  else if(dir < 3){di=1;dk=1;}
                  else if(dir < 5){di=0;dk=1;}
                  else if(dir < 7){di=-1;dk=1;}
                  else if(dir < 9){di=-1;dk=0;}
                  else if(dir < 11){di=-1;dk=-1;}
                  else if(dir < 13){di=0;dk=-1;}
                  else if(dir < 15){di=1;dk=-1;}
                  else{di=1;dk=0;}
                }
                if(dk == 0){
                  if(di > 0){
                    if(pol==1){
                      tdir[0] = 2;
                      tdir[1] = 14;
                      tdir[2] = 4;
                      tdir[3] = 12;
                    }else{
                      tdir[0] = 14;
                      tdir[1] = 2;
                      tdir[2] = 12;
                      tdir[3] = 4;
                    }
                  }else{
                    if(pol==1){
                      tdir[0] = 6;
                      tdir[1] = 10;
                      tdir[2] = 4;
                      tdir[3] = 12;
                    }else{
                      tdir[0] = 10;
                      tdir[1] = 6;
                      tdir[2] = 12;
                      tdir[3] = 4;
                    }
                  }
                }else if(di == 0){
                  if(dk > 0){
                    if(pol==1){
                      tdir[0] = 2;
                      tdir[1] = 6;
                      tdir[2] = 0;
                      tdir[3] = 8;
                    }else{
                      tdir[0] = 6;
                      tdir[1] = 2;
                      tdir[2] = 8;
                      tdir[3] = 0;
                    }
                  }else{
                    if(pol==1){
                      tdir[0] = 10;
                      tdir[1] = 14;
                      tdir[2] = 8;
                      tdir[3] = 0;
                    }else{
                      tdir[0] = 14;
                      tdir[1] = 10;
                      tdir[2] = 0;
                      tdir[3] = 8;
                    }
                  }
                }else if(dk > 0){
                  if(di > 0){
                    if(pol==1){
                      tdir[0] = 0;
                      tdir[1] = 4;
                      tdir[2] = 14;
                      tdir[3] = 6;
                    }else{
                      tdir[0] = 4;
                      tdir[1] = 0;
                      tdir[2] = 6;
                      tdir[3] = 14;
                    }
                  }else{
                    if(pol==1){
                      tdir[0] = 4;
                      tdir[1] = 8;
                      tdir[2] = 2;
                      tdir[3] = 10;
                    }else{
                      tdir[0] = 8;
                      tdir[1] = 4;
                      tdir[2] = 10;
                      tdir[3] = 2;
                    }
                  }
                }else{
                  if(di > 0){
                    if(pol==1){
                      tdir[0] = 12;
                      tdir[1] = 0;
                      tdir[2] = 10;
                      tdir[3] = 2;
                    }else{
                      tdir[0] = 0;
                      tdir[1] = 12;
                      tdir[2] = 2;
                      tdir[3] = 10;
                    }
                  }else{
                    if(pol==1){
                      tdir[0] = 8;
                      tdir[1] = 12;
                      tdir[2] = 6;
                      tdir[3] = 14;
                    }else{
                      tdir[0] = 12;
                      tdir[1] = 8;
                      tdir[2] = 14;
                      tdir[3] = 6;
                    }
                  }
                }
                for(int q = 0; q < 4; q++){
                  if(tdir[q] == 0){
                    di = 1; dk = 0;
                  }else if(tdir[q] == 2){
                    di = 1; dk = 1;
                  }else if(tdir[q] == 4){
                    di = 0; dk = 1;
                  }else if(tdir[q] == 6){
                    di = -1; dk = 1;
                  }else if(tdir[q] == 8){
                    di = -1; dk = 0;
                  }else if(tdir[q] == 10){
                    di = -1; dk = -1;
                  }else if(tdir[q] == 12){
                    di = 0; dk = -1;
                  }else{
                    di = 1; dk = -1;
                  }
                  i2 = i + di; k2 = k + dk;
                  med = getMedium(i2, j, k2);
                  if(med != 0){
                    i = i2; k = k2; x = (double)i + 0.5; z = (double)k + 0.5;
                    pitch = 0;
                    direction = (double)tdir[q] * 2.0*Math.PI/16.0;
                    break;
                  }
                }
                if(med == 0) return; //Root cannot grow any further.
              }
            }
          }
        }
        }
//                while(direction < 0.0){direction = direction + 2.0*Math.PI;}
//                while(direction > 2.0*Math.PI){direction = direction - 2.0*Math.PI;}
    }
    
    @Override
	public boolean generate(World worldIn, Random rand,	net.minecraft.util.BlockPos position) {
		return generate(new WorldWrapper(worldIn), rand, new BlockPos(position));
	}
    
    public abstract boolean generate(WorldWrapper world, Random random, int x, int y, int z);
    
    @Override
    public boolean generate(WorldWrapper world, Random random, BlockPos pos) {
    	return generate(world, random, pos.getX(), pos.getY(), pos.getZ());
    }
}
