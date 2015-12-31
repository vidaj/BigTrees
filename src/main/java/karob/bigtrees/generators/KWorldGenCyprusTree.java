// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package karob.bigtrees.generators;

import java.util.Random;

import karob.bigtrees.KTreeCfg;
import karob.bigtrees.compat.WorldWrapper;
import karob.bigtrees.config.BlockAndMeta;
//import net.minecraft.src.KTreeCfg;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves, 
//            BlockGrass

public class KWorldGenCyprusTree extends AbstractWorldGenerator
{

    private int check[][][];
    private boolean planted;
//    private int bbx[][];
//    private int bby[][];
//    private int bbz[][];
    private int baseY;
    
	

    public KWorldGenCyprusTree(boolean flag)
    {
		super(flag);
        planted = flag;
        check = new int[9][9][9];
//        bbx = new int[100][100];
        rootRand = 0;
        rootAlt = 0;
//        KTreeCfg.init();
    }
    
    @Override
    public boolean generate(WorldWrapper world, Random random, int i, int j, int k)
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
        BlockAndMeta id;
        //If tree is generated, require base to be of certain blocktypes.
        if(!planted){
	    	if (!isSupportedBaseBlock(i, j - 1, k)) {
				return false;
			}
        	
          //bottom block of tree must be empty
          id = this.getBlock(i, j, k);
          if (!id.isAir() && !id.areEqual(leaf)) { return false; }
        }
//world.lightUpdates = false;
//        growBranch(i,   j, k,   (double)l, rand.nextFloat()*Math.PI/2.0, Math.PI/2.0*0.8,true);
//        l = 30;
        double pitch = Math.PI/2.0*0.8;
        double dir = rand.nextFloat(); double spin = 2.0*Math.PI*0.618;
        double grow = 10.0; double shrink = 0.618;
        double len = (double)l;
//        double y = 30.0;
        double rootSlope = -Math.PI/3.0;
        j += 4;
        growRoot(i-1,j  ,k  ,4.5/8.0,rootSlope);
        growRoot(i-1,j  ,k+1,3.5/8.0,rootSlope);
        growRoot(i  ,j  ,k+2,2.5/8.0,rootSlope);
        growRoot(i+1,j  ,k+2,1.5/8.0,rootSlope);
        growRoot(i+2,j  ,k+1,0.5/8.0,rootSlope);
        growRoot(i+2,j  ,k  ,7.5/8.0,rootSlope);
        growRoot(i+1,j  ,k-1,6.5/8.0,rootSlope);
        growRoot(i  ,j  ,k-1,5.5/8.0,rootSlope);
        j -= 4;
        for(int n = 0; n < l; n ++){
          if(n < l - 2){
            this.setBlockAndMetadata(i, j+n, k,     wood);
            this.setBlockAndMetadata(i+1, j+n, k,   wood);
            this.setBlockAndMetadata(i+1, j+n, k+1, wood);
            this.setBlockAndMetadata(i, j+n, k+1,   wood);
          }else{
            this.setBlockAndMetadata(i, j+n, k,     leaf);
            this.setBlockAndMetadata(i+1, j+n, k,   leaf);
            this.setBlockAndMetadata(i+1, j+n, k+1, leaf);
            this.setBlockAndMetadata(i, j+n, k+1,   leaf);
          }
          if(n <= 8){
            this.setBlockAndMetadata(i, j+n, k-1,   wood);
            this.setBlockAndMetadata(i+1, j+n, k-1, wood);
            this.setBlockAndMetadata(i+2, j+n, k,   wood);
            this.setBlockAndMetadata(i+2, j+n, k+1, wood);
            this.setBlockAndMetadata(i+1, j+n, k+2, wood);
            this.setBlockAndMetadata(i, j+n, k+2,   wood);
            this.setBlockAndMetadata(i-1, j+n, k+1, wood);
            this.setBlockAndMetadata(i-1, j+n, k,   wood);
          }
          if(n <= 3){
            this.setBlockAndMetadata(i-1, j+n, k-1, wood);
            this.setBlockAndMetadata(i-1, j+n, k+2, wood);
            this.setBlockAndMetadata(i+2, j+n, k+2, wood);
            this.setBlockAndMetadata(i+2, j+n, k-1, wood);
          }
          if(n <= 1){
            this.setBlockAndMetadata(i, j+n, k+3,   wood);
            this.setBlockAndMetadata(i+1, j+n, k+3, wood);
            this.setBlockAndMetadata(i, j+n, k-2,   wood);
            this.setBlockAndMetadata(i+1, j+n, k-2, wood);
            this.setBlockAndMetadata(i+3, j+n, k,   wood);
            this.setBlockAndMetadata(i+3, j+n, k+1, wood);
            this.setBlockAndMetadata(i-2, j+n, k,   wood);
            this.setBlockAndMetadata(i-2, j+n, k+1, wood);
          }
          if(n == 0){
            this.setBlockAndMetadata(i-2, j+n, k-1, wood);
            this.setBlockAndMetadata(i-1, j+n, k-2, wood);
            this.setBlockAndMetadata(i-2, j+n, k+2, wood);
            this.setBlockAndMetadata(i-1, j+n, k+3, wood);
            this.setBlockAndMetadata(i+3, j+n, k+2, wood);
            this.setBlockAndMetadata(i+2, j+n, k+3, wood);
            this.setBlockAndMetadata(i+3, j+n, k-1, wood);
            this.setBlockAndMetadata(i+2, j+n, k-2, wood);
          }
        }
/*
        for(int n = 0; n < 15; n ++){
          y *= 24.0/30.0;
          growBranch(i, j+30-(int)y, k, y*0.618, dir, pitch, 0.0, 0.0, 0);
          dir += spin;
          if(y*0.618 <= 1.0) break;
        }
*/
        for(int n = 5; n < l-2; n ++){
          if(rand.nextInt(l+10) < n)
            growBranch(i, j+n, k, (double)(l-n)*0.618, dir, pitch, 0.0, 0.0, 0);
          dir += spin;
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
      BlockAndMeta id;
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
          if (id.isAir() || id.areEqual(wood, leaf)) {
            plotWood(i, j, k, size);
          }else{
            break;
          }
        }
	int id1;
        if(len > 2.0 && len < blen){
          if(size == 0) id1 = rand.nextInt(8);
          else id1 = 0;
/*
          if(size == 0 && id == 1){
            size ++;
            growBranch(i, j, k, len, dir, Math.PI/2.0, Math.PI/2.0, Math.PI/2.0, size);
          }
*/
          if(id1 == 1) growBranch(i, j, k, len, dir + Math.PI/4.0, 0.0, 0.0, 0.0, 1);
          if(id1 == 2) growBranch(i, j, k, len, dir - Math.PI/4.0, 0.0, 0.0, 0.0, 1);
        }

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

    private void plotWood(int i, int j, int k, int size){
      this.setBlockAndMetadata(i, j, k, wood);
/*
      if(size <= 1){
        this.setBlockAndMetadata(i+1, j, k, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j, k+1, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j, k+1, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j+1, k, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j+1, k, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j+1, k+1, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j+1, k+1, cyprusWoodBlock, woodMeta);
      }
*/
/*      if(size > 20.0){
        this.setBlockAndMetadata(i+1, j, k, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i-1, j, k, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j+1, k, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j-1, k, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j, k+1, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j, k-1, cyprusWoodBlock, woodMeta);
      }else if(size > 10.0){
        this.setBlockAndMetadata(i+1, j, k, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j, k+1, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j, k+1, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j+1, k, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j+1, k, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i+1, j+1, k+1, cyprusWoodBlock, woodMeta);
        this.setBlockAndMetadata(i, j+1, k+1, cyprusWoodBlock, woodMeta);
      }
*/
    }

    void treeLeaf(int i, int j, int k, int r){
      int zz;
      if(r <= 0) return;
        int rr = r*r+1;
        for(int ii = -r; ii <= r; ii ++){
          for(int jj = 0; jj <= 1; jj ++){
            for(int kk = -r; kk <= r; kk ++){
              if(ii*ii+jj*jj+kk*kk <= rr){
                if(this.getBlock(i + ii, j + jj, k + kk).isAir()){
                  /*if(rand.nextInt(3) == 0)*/ this.setBlockAndMetadata(i + ii, j + jj, k + kk, leaf);
                }
              }
            }
          }
        }
    }

    void growRoot(int l, int m, int n, double theta, double phi)
    {
        if(KTreeCfg.rootsEnable == false) return;
      boolean dug;
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
        int length = 20 + rand.nextInt(4);
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
if(pitch < 0.0) dug = false;
else dug = true;
          if(med == 1){ //Root growing in openness.
            pitch = (pitch+Math.PI/2.0)*0.7 - Math.PI/2.0;
//            if(pitch > 0.0){
//              pitch = pitch - 10.0*Math.PI/180.0;
//            }else{
//              pitch = (pitch + Math.PI/2.0)*0.7 - Math.PI/2.0;
//            }
          }else{ //Root growing in solid.
            //if(med != 3) dug = true;
//            pitch = 0.0;
            pitch = (pitch-Math.PI/2.0)*0.7 + Math.PI/2.0;
          }

          hoz = Math.cos(pitch);
          x2 = x + Math.cos(direction)*hoz;
          y2 = y + Math.sin(pitch);
          z2 = z + Math.sin(direction)*hoz;
          i2 = (int)x2; j2 = (int)y2; k2 = (int)z2;
        if(i2 != i || j2 != j || k2 != k){
          this.setBlockAndMetadata(i, j, k, wood); //1);
          if(dug){
            if(this.getBlock(i-1, j, k).isAir()) return;
            if(this.getBlock(i+1, j, k).isAir()) return;
            if(this.getBlock(i, j, k-1).isAir()) return;
            if(this.getBlock(i, j, k+1).isAir()) return;
          }
          cnt ++;
          if(cnt < 4){
            if(j2 != j-1 || i2 != i || k2 != k)
            this.setBlockAndMetadata(i, j-1, k, wood);
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

	

}

