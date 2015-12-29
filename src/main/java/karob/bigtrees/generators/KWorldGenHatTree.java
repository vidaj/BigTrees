// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package karob.bigtrees.generators;

import java.util.Random;

import karob.bigtrees.KTreeCfg;
import karob.bigtrees.config.ITreeConfigurable;
import karob.bigtrees.config.TreeConfiguration;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
//import net.minecraft.src.KTreeCfg;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves, 
//            BlockGrass

public class KWorldGenHatTree extends AbstractWorldGenerator implements ITreeConfigurable
{
    Random rand;
    int rootRand;
    int rootAlt;
    private int check[][][];
    private boolean planted;
//    private int bbx[][];
//    private int bby[][];
//    private int bbz[][];
    private int baseY;
    private Block hatWoodBlock;
    private Block hatLeafBlock;
    private Block hatBaseBlock1;
    private Block hatBaseBlock2;
    private int woodMeta;
    private int leafMeta;
    private int stuntmin;
    private int heightmin;
    private int heightmax;


    public KWorldGenHatTree(boolean flag)
    {
		super(flag);
        planted = flag;
        check = new int[9][9][9];
//        bbx = new int[100][100];
        rootRand = 0;
        rootAlt = 0;
//        KTreeCfg.init();
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        return generateCustom(world, random, i, j, k, 4, 30);
    }

    public boolean generateCustom(World world, Random random, int i, int j, int k, int hatBase, int hatHeight) //hatHight is OVERRIDDEN
    {
        worldObject = world;
        rand = random;
        baseY = j;
        int l; int m; int zz;
        l = random.nextInt(heightmax-heightmin) + heightmin; //Tree height
        if(j < 1) return false;
        if(j + l + 1 > 256){
          l = 256 - j - 2;
          if(l < stuntmin) return false;
        }
        Block id;
        //If tree is generated, require base to be of certain blocktypes.
        if(!planted){
          if(hatBaseBlock1 != Blocks.air || hatBaseBlock2 != Blocks.air){
            boolean flag = false;
            id = this.getBlock(i, j - 1, k);
            if(hatBaseBlock1 != Blocks.air && id == hatBaseBlock1) flag = true;
            if(hatBaseBlock2 != Blocks.air && id == hatBaseBlock2) flag = true;
            if(flag == false) return false;
          }
          //bottom block of tree must be empty
          id = this.getBlock(i, j, k);
          if(id != Blocks.air && id != hatLeafBlock) return false;
        }
//        growBranch(i,   j, k,   (double)l, rand.nextFloat()*Math.PI/2.0, Math.PI/2.0*0.8,true);
//world.lightUpdates = false;
//        l = hatHeight;
        double pitch = Math.PI/2.0*0.8;
        double pbias = 0.0;
        double dir = rand.nextFloat()*Math.PI/2.0; double spin = 2.0*Math.PI*0.618;
        double grow = 10.0; double shrink = 0.618;
        double len = l;
        double y = l;
        double rootSlope = 0.0;
        growRoot(i,  j,k-1,5.3/8.0,rootSlope);
        growRoot(i+1,j,k-1,6.7/8.0,rootSlope);
        growRoot(i+2,j,k  ,7.3/8.0,rootSlope);
        growRoot(i+2,j,k+1,0.7/8.0,rootSlope);
        growRoot(i+1,j,k+2,1.3/8.0,rootSlope);
        growRoot(i,  j,k+2,2.7/8.0,rootSlope);
        growRoot(i-1,j,k+1,3.3/8.0,rootSlope);
        growRoot(i-1,j,k  ,4.7/8.0,rootSlope);
      if(hatBase == 4){
        for(int n = 0; n < l; n ++){
          this.setBlockAndMetadata(i, j+n, k-1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+1, j+n, k-1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+2, j+n, k, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+2, j+n, k+1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+1, j+n, k+2, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i, j+n, k+2, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-1, j+n, k+1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-1, j+n, k, hatWoodBlock, woodMeta);
        }
      }else if(hatBase == 5){
        for(int n = 0; n < l - 1; n ++){
          this.setBlockAndMetadata(i+2, j+n, k-1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+2, j+n, k, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+2, j+n, k+1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-2, j+n, k-1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-2, j+n, k, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-2, j+n, k+1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-1, j+n, k+2, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i, j+n, k+2, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+1, j+n, k+2, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-1, j+n, k-2, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i, j+n, k-2, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+1, j+n, k-2, hatWoodBlock, woodMeta);
        }
      }else if(hatBase == 7){
        for(int n = 0; n < l - 2; n ++){
          this.setBlockAndMetadata(i+3, j+n, k-1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+3, j+n, k, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+3, j+n, k+1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-3, j+n, k-1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-3, j+n, k, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-3, j+n, k+1, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-1, j+n, k+3, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i, j+n, k+3, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+1, j+n, k+3, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-1, j+n, k-3, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i, j+n, k-3, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+1, j+n, k-3, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+2, j+n, k+2, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i+2, j+n, k-2, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-2, j+n, k+2, hatWoodBlock, woodMeta);
          this.setBlockAndMetadata(i-2, j+n, k-2, hatWoodBlock, woodMeta);
        }
      }

        for(int n = 0; n < l; n ++){
//          y *= 24.0/30.0;
//          growBranch(i, j+30-(int)y, k, y*0.618, dir, pitch, 0.0, 0.0, 0);
//          len *= 0.8;
          growBranch(i, j+n, k, (double)(l-n)*0.8, dir, 0.0, pbias, 0.0, 1);
          growBranch(i, j+n, k, (double)(l-n)*0.8, dir+Math.PI, 0.0, pbias, 0.0, 1);
          dir += spin;
          pbias = (pbias - Math.PI/2.0) * 0.9 + Math.PI/2.0;
//          if(y*0.618 <= 1.0) break;
        }
/*
//        growBulk(i, j, k, l, dir, pitch, Math.PI/2.0, Math.PI/2.0*0.8, 0.0);
        dir += spin;
        y += grow; len *= shrink; grow *= shrink;
        growBulk(i, j+(int)y, k, len, dir, pitch, Math.PI/2.0, 0.0, grow);
        dir += spin;
        y += grow; len *= shrink; grow *= shrink;
        growBulk(i, j+(int)y, k, len, dir, pitch, Math.PI/2.0*0.4, Math.PI/2.0*0.4, grow);
        dir += spin;
        y += grow; len *= shrink; grow *= shrink;
        growBulk(i, j+(int)y, k, len, dir, pitch, Math.PI/2.0*0.6, Math.PI/2.0*0.6, grow);
        dir += spin;
        y += grow; len *= shrink; grow *= shrink;
        growBulk(i, j+(int)y, k, len, dir, pitch, Math.PI/2.0*0.6, Math.PI/2.0*0.6, grow);
*/
/*
        l = 25;
        j = j + 10;
        growBulk(i, j, k, l, pitch, 0.0, 0.0);
        growBulk(i, j, k, l, pitch, Math.PI/2.0, 0.0);
        growBulk(i, j, k, l, pitch, Math.PI/2.0*0.4, Math.PI/2.0*0.4);
        growBulk(i, j, k, l, pitch, Math.PI/2.0*0.6, Math.PI/2.0*0.6);
        l = 15;
        j = j + 10;
        growBulk(i, j, k, l, pitch, 0.0, 0.0);
        growBulk(i, j, k, l, pitch, Math.PI/2.0, 0.0);
        growBulk(i, j, k, l, pitch, Math.PI/2.0*0.4, Math.PI/2.0*0.4);
        growBulk(i, j, k, l, pitch, Math.PI/2.0*0.6, Math.PI/2.0*0.6);
        l = 5;
        j = j + 10;
        growBulk(i, j, k, l, pitch, 0.0, 0.0);
        growBulk(i, j, k, l, pitch, Math.PI/2.0, 0.0);
        growBulk(i, j, k, l, pitch, Math.PI/2.0*0.4, Math.PI/2.0*0.4);
        growBulk(i, j, k, l, pitch, Math.PI/2.0*0.6, Math.PI/2.0*0.6);
*/
/*        double dir = 0.0;
        while(dir < Math.PI*2.0){
          dir += rand.nextFloat()*0.2 + 0.4;
          growBranch(i, j, k, (double)l, dir, Math.PI/2.0);
        }
*/
        for(int n = 0; n < l - 1; n ++){
          this.setBlockAndMetadata(i,   j+n, k,   Blocks.air, 0);
          this.setBlockAndMetadata(i+1, j+n, k,   Blocks.air, 0);
          this.setBlockAndMetadata(i+1, j+n, k+1, Blocks.air, 0);
          this.setBlockAndMetadata(i,   j+n, k+1, Blocks.air, 0);
        }
      if(hatBase >= 5){
        for(int n = 0; n < l - 2; n ++){
          this.setBlockAndMetadata(i-1, j+n, k+1, Blocks.air, 0);
          this.setBlockAndMetadata(i-1, j+n, k,   Blocks.air, 0);
          this.setBlockAndMetadata(i-1, j+n, k-1, Blocks.air, 0);
          this.setBlockAndMetadata(i  , j+n, k-1, Blocks.air, 0);
          this.setBlockAndMetadata(i+1, j+n, k-1, Blocks.air, 0);
        }
      }
      if(hatBase == 7){
        for(int n = 0; n < l - 3; n ++){
          this.setBlockAndMetadata(i-2, j+n, k-1, Blocks.air, 0);
          this.setBlockAndMetadata(i-2, j+n, k,   Blocks.air, 0);
          this.setBlockAndMetadata(i-2, j+n, k+1, Blocks.air, 0);
          this.setBlockAndMetadata(i+2, j+n, k-1, Blocks.air, 0);
          this.setBlockAndMetadata(i+2, j+n, k,   Blocks.air, 0);
          this.setBlockAndMetadata(i+2, j+n, k+1, Blocks.air, 0);
          this.setBlockAndMetadata(i-1, j+n, k-2, Blocks.air, 0);
          this.setBlockAndMetadata(i,   j+n, k-2, Blocks.air, 0);
          this.setBlockAndMetadata(i+1, j+n, k-2, Blocks.air, 0);
          this.setBlockAndMetadata(i-1, j+n, k+2, Blocks.air, 0);
          this.setBlockAndMetadata(i,   j+n, k+2, Blocks.air, 0);
          this.setBlockAndMetadata(i+1, j+n, k+2, Blocks.air, 0);
        }
      }
//world.lightUpdates = true;
        return true;
    }

    private void growBulk(int i, int j, int k, double l, double dir, double pitch, double pbias, double pbias2, double grow){
        double y = 0.0; grow /= 8.0; double shrink = 0.9416; // 0.618^(1/8)
        growBranch(i+1, j+(int)y, k,   l, dir+0.0*Math.PI/4.0, pitch, pbias, pbias2, 0);
        y += grow;
        l *= shrink;
        growBranch(i+1, j+(int)y, k+1, l, dir+1.0*Math.PI/4.0, pitch, pbias, pbias2, 0);
        y += grow;
        l *= shrink;
        growBranch(i,   j+(int)y, k+1, l, dir+2.0*Math.PI/4.0, pitch, pbias, pbias2, 0);
        y += grow;
        l *= shrink;
        growBranch(i-1, j+(int)y, k+1, l, dir+3.0*Math.PI/4.0, pitch, pbias, pbias2, 0);
        y += grow;
        l *= shrink;
        growBranch(i-1, j+(int)y, k,   l, dir+4.0*Math.PI/4.0, pitch, pbias, pbias2, 0);
        y += grow;
        l *= shrink;
        growBranch(i-1, j+(int)y, k-1, l, dir+5.0*Math.PI/4.0, pitch, pbias, pbias2, 0);
        y += grow;
        l *= shrink;
        growBranch(i,   j+(int)y, k-1, l, dir+6.0*Math.PI/4.0, pitch, pbias, pbias2, 0);
        y += grow;
        l *= shrink;
        growBranch(i+1, j+(int)y, k-1, l, dir+7.0*Math.PI/4.0, pitch, pbias, pbias2, 0);
    }

    private void growBranch(int i, int j, int k, double len, double dir, double pitch, double pbias, double pbias2, int size){
//double pbias = rand.nextFloat()*Math.PI/2;
//pbias = Math.PI/2;
//pitch *= 0.8;
      double dx = 0.0;
      double dy = 0.0;
      double dz = 0.0;
      double dd;
      boolean step;
      double spin = 0.0;
      double heave = 0.0;
      Block id;
      double blen = len * 0.75;
      plotWood(i, j, k, size);
      while(len > 1.0){
        dy += Math.sin(pitch);
        dd = Math.cos(pitch);
        dx += Math.cos(dir)*dd;
        dz += Math.sin(dir)*dd;
        step = false;
        if(dx >= 1.0){
          i ++; dx -= 1.0; step = true;
        }else if(dx <= -1.0){
          i --; dx += 1.0; step = true;
        }
        if(dy >= 1.0){
          j ++; dy -= 1.0; step = true;
        }else if(dy <= -1.0){
          j --; dy += 1.0; step = true;
        }
        if(dz >= 1.0){
          k ++; dz -= 1.0; step = true;
        }else if(dz <= -1.0){
          k --; dz += 1.0; step = true;
        }
        if(step == true){
          id = this.getBlock(i, j, k);
          if(id == Blocks.air || id == hatWoodBlock || id == hatLeafBlock){
            plotWood(i, j, k, size);
          }else{
            break;
          }
        }
/*
        if(len > 2.0 && len < blen){
          if(size == 0) id = rand.nextInt(8);
          else id = rand.nextInt(15);
          if(size == 0 && id == 1){
            size ++;
            growBranch(i, j, k, len, dir, Math.PI/2.0, Math.PI/2.0, Math.PI/2.0, size);
          }
//          if(id == 1) growBranch(i, j, k, len, dir + Math.PI/4.0, 0.0, 0.0, 0.0, false);
//          if(id == 2) growBranch(i, j, k, len, dir - Math.PI/4.0, 0.0, 0.0, 0.0, false);
        }
*/
/*
        if(size == 0 && j < baseY + 10){
          //if(rand.nextFloat()*60 < len) plotProp(i, j, k);
          if(rand.nextInt(12) == 1) plotProp(i, j, k);
        }
*/
        spin += rand.nextFloat()*0.1 - 0.05;
        heave += rand.nextFloat()*0.1 - 0.05;
        dir += spin;
        if(pitch > pbias) heave -= 0.01;
        else heave += 0.01;
        if(heave > 0.2) heave = 0.2;
        if(heave < -0.2) heave = -0.2;
        pitch += heave;
//        pitch = pitch * 0.9;
        pitch = (pitch - pbias2)*0.8 + pbias2;
        len -= 1.0;
      }
      treeLeaf(i, j, k, 3);
    }

    private void plotProp(int i, int j, int k){
      int n = 1;
      Block id;
      while(n < 15){
        if(this.getBlock(i+1, j-n, k) == Blocks.fence) return;
        if(this.getBlock(i-1, j-n, k) == Blocks.fence) return;
        if(this.getBlock(i, j-n, k+1) == Blocks.fence) return;
        if(this.getBlock(i, j-n, k-1) == Blocks.fence) return;
        id = this.getBlock(i, j-n, k);
        if(id == Blocks.air || id == Blocks.tallgrass || id == Blocks.yellow_flower || id == Blocks.red_flower || id == Blocks.brown_mushroom || id == Blocks.red_mushroom || id == hatLeafBlock || id == hatWoodBlock)
          n ++;
        else if(id == Blocks.grass || id == Blocks.dirt || id == Blocks.stone || id == Blocks.sand || id == Blocks.gravel)
          break;
        else
          return;
      }
      for(int q = 1; q <= n; q ++){
        id = this.getBlock(i, j-q, k);
        if(id == Blocks.air || id == Blocks.tallgrass || id == Blocks.yellow_flower || id == Blocks.red_flower || id == Blocks.brown_mushroom || id == Blocks.red_mushroom)
          this.setBlockAndMetadata(i, j-q, k, Blocks.fence, 0);
      }
    }

    private void plotWood(int i, int j, int k, int size){
      this.setBlockAndMetadata(i, j, k, hatWoodBlock, woodMeta);
      if(size <= 1){
        this.setBlockAndMetadata(i+1, j, k, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j, k+1, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j, k+1, hatWoodBlock, woodMeta);
if(size == 0){
        this.setBlockAndMetadata(i, j+1, k, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j+1, k, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j+1, k+1, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j+1, k+1, hatWoodBlock, woodMeta);
}
      }
/*      if(size > 20.0){
        this.setBlockAndMetadata(i+1, j, k, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i-1, j, k, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j+1, k, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j-1, k, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j, k+1, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j, k-1, hatWoodBlock, woodMeta);
      }else if(size > 10.0){
        this.setBlockAndMetadata(i+1, j, k, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j, k+1, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j, k+1, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j+1, k, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j+1, k, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j+1, k+1, hatWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j+1, k+1, hatWoodBlock, woodMeta);
      }
*/
    }

    private void treeLeaf(int i, int j, int k, int r){
      int zz;
      boolean flag;
      if(r <= 0) return;
        int rr = r*r+1;
        int rrr = (r-1)*(r-1);
        for(int ii = 0; ii <= r; ii ++){
          for(int jj = -r/2; jj <= r; jj ++){
            for(int kk = 0; kk <= r; kk ++){
              zz = ii*ii+jj*jj+kk*kk;
              if(zz <= rr){
                flag = (zz >= rrr);
                treeLeafSpot(i+ii,j+jj,k+kk, flag);
                treeLeafSpot(i+ii,j+jj,k-kk, flag);
                treeLeafSpot(i-ii,j+jj,k+kk, flag);
                treeLeafSpot(i-ii,j+jj,k-kk, flag);
              }
            }
          }
        }
//      int ii, jj, kk;
      for(int n = -r; n <= r; n++){
//        ii = i + rand.nextInt(r*2+1)-r;
//        jj = j + rand.nextInt(2*2+1)-2;
//        kk = k + rand.nextInt(r*2+1)-r;
//        if(this.getBlockId(ii, jj, kk) == hatLeafBlock) plotVine(ii,jj,kk);
        if(this.getBlock(i+r, j, k) == hatLeafBlock){
          plotVine(i+r,j,k,1);
          plotVine(i+r,j,k,4);
        }
        if(this.getBlock(i, j, k+r) == hatLeafBlock){
          plotVine(i,j,k+r,2);
          plotVine(i,j,k+r,8);
        }
      }
    }

    private void treeLeafSpot(int i, int j, int k, boolean flag){
      if(this.getBlock(i, j, k) == Blocks.air){
        this.setBlockAndMetadata(i, j, k, hatLeafBlock, leafMeta);
//        if(flag){
//          if(rand.nextInt(4) == 1) plotVine(i, j, k);
//plotVine(i, j, k);
//        }
      }
    }

    private void plotVine(int i, int j, int k, int l)
    {
//        int l;
/*        if(this.getBlockId(i-1, j, k) == 0) l = 8;
        else if(this.getBlockId(i+1, j, k) == 0) l = 2;
        else if(this.getBlockId(i, j, k-1) == 0) l = 1;
        else if(this.getBlockId(i, j, k+1) == 0) l = 4;
        else return;
*/
//        l = rand.nextInt(4)+1;
//        if(l == 3) l = 8;
        int ii, kk;
	Block id;
        if(l == 2){ ii=1; kk=0; }
        else if(l == 1){ ii=0; kk=-1; }
        else if(l == 4){ ii=0; kk=1; }
        else{ ii=-1; kk=0; l=8; }
        id = this.getBlock(i+ii, j, k+kk);
        for(int n = 0; n < 4; n ++){
          if(id == hatLeafBlock){
            i += ii; k += kk;
          }else{
            break;
          }
          id = this.getBlock(i+ii, j, k+kk);
        }
        if(id != Blocks.air) return;
        this.setBlockAndMetadataWithNotify(i, j, k, Blocks.vine, l);
        for(int i1 = 20; this.getBlock(i, --j, k) == Blocks.air && i1 > 0; i1--)
        {
            this.setBlockAndMetadataWithNotify(i, j, k, Blocks.vine, l);
        }

    }

    private int getMedium(int i, int j, int k){
        //Roots can grow through the following block types.
        Block canGrowOpen[] = {Blocks.air, Blocks.sapling, Blocks.flowing_water, Blocks.water, Blocks.flowing_lava, Blocks.lava, Blocks.log, Blocks.log2, Blocks.leaves, Blocks.leaves2};//more to be re-added
        Block canGrowSolid[] = {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel}; //more to be re-added
        Block qq = this.getBlock(i, j, k);
        int medium = 0;
        for(int m = 0; m < canGrowOpen.length; m++){
          if(qq==canGrowOpen[m]){
            medium = 1;
            break;
          }
        }
        if(medium==0){
          for(int m = 0; m < canGrowSolid.length; m++){
          if(qq==canGrowSolid[m]){
              medium = 2;
              break;
            }
          }
        }
        return medium;
    }

    void growRoot(int l, int m, int n, double theta, double phi)
    {
        if(KTreeCfg.rootsEnable == false) return;
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
        int length = 14 + rand.nextInt(2);
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
//            pitch = 0.0;
            pitch = (pitch + Math.PI/2.0)*0.9 - Math.PI/2.0;
          }

          hoz = Math.cos(pitch);
          x2 = x + Math.cos(direction)*hoz;
          y2 = y + Math.sin(pitch);
          z2 = z + Math.sin(direction)*hoz;
          i2 = (int)x2; j2 = (int)y2; k2 = (int)z2;
        if(i2 != i || j2 != j || k2 != k){
          this.setBlockAndMetadata(i, j, k, hatWoodBlock, woodMeta); //1);
          cnt ++;
          if(cnt < 4){
            if(j2 != j-1 || i2 != i || k2 != k)
            this.setBlockAndMetadata(i, j-1, k, hatWoodBlock, woodMeta);
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
	public void setTreeConfiguration(TreeConfiguration treeConfiguration) {
		hatWoodBlock = treeConfiguration.getWood();
    	hatLeafBlock = treeConfiguration.getLeaf();
    	hatBaseBlock1 = treeConfiguration.getBaseBlock1();
    	hatBaseBlock2 = treeConfiguration.getBaseBlock2();
		woodMeta = treeConfiguration.getWoodMeta();
		leafMeta = treeConfiguration.getLeafMeta();
		heightmin = treeConfiguration.getMinHeight();
		heightmax = treeConfiguration.getMaxHeight();
		stuntmin = treeConfiguration.getMinStunt();
	}

}

