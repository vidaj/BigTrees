// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package karob.bigtrees;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class KTreeDecorate
{
    public static int numberoftrees = KTreeCfgTrees.count;

    public KTreeDecorate(){
//        currentRealm = -12349;
    }

//    public static int current;

    public static boolean decorate(World world, Random rand, int posX, int posZ, BiomeGenBase biome){
	int seed = (int)world.getSeed();
	int[] bigTreeDensity = new int [numberoftrees];
	int[] bigTreeType = new int [numberoftrees];
	int x = posX + 8 + rand.nextInt(16);
	int z = posZ + 8 + rand.nextInt(16);
	int y = world.getHeightValue(x, z);
	boolean generatetree = false;
        WorldGenerator worldgenerator;
        worldgenerator = biome.func_150567_a(rand);
	for (int i = 0; i < numberoftrees; i++){
		bigTreeDensity[i] = KGetLocality.locality(posX, posZ, seed + 128*i, 128);
        	bigTreeType[i] = KGetLocality.locality(posX, posZ, seed+128*i + 64, 168);
	}
	for (int current = 0; current < numberoftrees; current++){
	    for (int j = 0; j < KTreeCfgBiomes2.BiomeTreePopulations[biome.biomeID][current]; j++){
	     x = posX + rand.nextInt(16) + 8;
             z = posZ + rand.nextInt(16) + 8;
             y = world.getHeightValue(x, z);
             worldgenerator = biome.func_150567_a(rand);
	     if (KTreeCfgTrees.locality[current] > numberoftrees || KTreeCfgTrees.locality[current] < 1){KTreeCfgTrees.locality[current] = 0;generatetree = true;}
	     if (KTreeCfgTrees.locality[current] != 0){
	      if(bigTreeType[KTreeCfgTrees.locality[current]-1] > KTreeCfgTrees.localitymin[current] && bigTreeType[KTreeCfgTrees.locality[current]-1] < KTreeCfgTrees.localitymax[current]){
	       //if(bigTreeDensity[KTreeCfgTrees.locality[current]-1] - rand.nextInt(80) + KTreeCfgTrees.localitymin[current] - KTreeCfgTrees.localitymax[current]  < KTreeCfgBiomes2.BiomeTreePopulations[biome.biomeID][current]){
		generatetree = true;
	       //}
	      }
	     }
	     if (generatetree == true){
	       if (KTreeCfgTrees.algore[current] == 1){
	         if (rand.nextInt(numberoftrees*50) == 0) {
			KWorldGenTallTree tallTree = (new KWorldGenTallTree(false));
			tallTree.setConfigOptions(KTreeCfgTrees.woodBlock[current], KTreeCfgTrees.leafBlock[current], KTreeCfgTrees.woodMeta[current], KTreeCfgTrees.leafMeta[current], KTreeCfgTrees.baseBlock1[current], KTreeCfgTrees.baseBlock2[current], KTreeCfgTrees.heightmin[current], KTreeCfgTrees.heightmax[current], KTreeCfgTrees.stuntmin[current], KTreeCfgTrees.branchlessmin[current], KTreeCfgTrees.branchlessmax[current], KTreeCfgTrees.longestbranchp[current], KTreeCfgTrees.branchrot[current], KTreeCfgTrees.taplength[current], KTreeCfgTrees.branchspace[current], KTreeCfgTrees.pitch[current], KTreeCfgTrees.curl[current], KTreeCfgTrees.leafrad[current], KTreeCfgTrees.subbranchdensity[current], KTreeCfgTrees.subbranchinglength[current], KTreeCfgTrees.subbranchingsize[current], KTreeCfgTrees.subbranchangle[current], KTreeCfgTrees.subbranchsize[current]);
			tallTree.generate(world, rand, x, y, z);
	         }
	       }else if (KTreeCfgTrees.algore[current] == 2){
	         if (rand.nextInt(numberoftrees*50) == 0) {
			KWorldGenBigTree bigTree = (new KWorldGenBigTree(false));
			bigTree.setConfigOptions(KTreeCfgTrees.woodBlock[current], KTreeCfgTrees.leafBlock[current], KTreeCfgTrees.woodMeta[current], KTreeCfgTrees.leafMeta[current], KTreeCfgTrees.baseBlock1[current], KTreeCfgTrees.baseBlock2[current], KTreeCfgTrees.heightmin[current], KTreeCfgTrees.heightmax[current], KTreeCfgTrees.stuntmin[current]);
			bigTree.blockOakGenerate(world, rand, x, y, z);
	         }
	       }else if (KTreeCfgTrees.algore[current] == 3){
		 if (rand.nextInt(numberoftrees*50) == 0) {
			KWorldGenBigTree bigTree = (new KWorldGenBigTree(false));
			bigTree.setConfigOptions(KTreeCfgTrees.woodBlock[current], KTreeCfgTrees.leafBlock[current], KTreeCfgTrees.woodMeta[current], KTreeCfgTrees.leafMeta[current], KTreeCfgTrees.baseBlock1[current], KTreeCfgTrees.baseBlock2[current], KTreeCfgTrees.heightmin[current], KTreeCfgTrees.heightmax[current], KTreeCfgTrees.stuntmin[current]);
			bigTree.greatGenerate(world, rand, x, y, z);
		 }
	       }else if (KTreeCfgTrees.algore[current] == 4){
		 if (rand.nextInt(numberoftrees*50) == 0) {
			KWorldGenBigTree bigTree = (new KWorldGenBigTree(false));
			bigTree.setConfigOptions(KTreeCfgTrees.woodBlock[current], KTreeCfgTrees.leafBlock[current], KTreeCfgTrees.woodMeta[current], KTreeCfgTrees.leafMeta[current], KTreeCfgTrees.baseBlock1[current], KTreeCfgTrees.baseBlock2[current], KTreeCfgTrees.heightmin[current], KTreeCfgTrees.heightmax[current], KTreeCfgTrees.stuntmin[current]);
			bigTree.swampGenerate(world, rand, x, y, z);
		 }
	       }else if (KTreeCfgTrees.algore[current] == 5){
	         if (rand.nextInt(numberoftrees*50) == 0) {
			KWorldGenCyprusTree bigTree = (new KWorldGenCyprusTree(false));
			bigTree.setConfigOptions(KTreeCfgTrees.woodBlock[current], KTreeCfgTrees.leafBlock[current], KTreeCfgTrees.woodMeta[current], KTreeCfgTrees.leafMeta[current], KTreeCfgTrees.baseBlock1[current], KTreeCfgTrees.baseBlock2[current], KTreeCfgTrees.heightmin[current], KTreeCfgTrees.heightmax[current], KTreeCfgTrees.stuntmin[current]);
			bigTree.generate(world, rand, x, y, z);
		 }
	       }else if (KTreeCfgTrees.algore[current] == 6){
		 if (rand.nextInt(numberoftrees*50) == 0) {
			KWorldGenHatTree bigTree = (new KWorldGenHatTree(false));
			bigTree.setConfigOptions(KTreeCfgTrees.woodBlock[current], KTreeCfgTrees.leafBlock[current], KTreeCfgTrees.woodMeta[current], KTreeCfgTrees.leafMeta[current], KTreeCfgTrees.baseBlock1[current], KTreeCfgTrees.baseBlock2[current], KTreeCfgTrees.heightmin[current], KTreeCfgTrees.heightmax[current], KTreeCfgTrees.stuntmin[current]);
			bigTree.generate(world, rand, x, y, z);
		 }
	       }else if (KTreeCfgTrees.algore[current] == 7){
		 if (rand.nextInt(numberoftrees*50) == 0) {
			KWorldGenBigTree bigTree = (new KWorldGenBigTree(false));
			bigTree.setConfigOptions(KTreeCfgTrees.woodBlock[current], KTreeCfgTrees.leafBlock[current], KTreeCfgTrees.woodMeta[current], KTreeCfgTrees.leafMeta[current], KTreeCfgTrees.baseBlock1[current], KTreeCfgTrees.baseBlock2[current], KTreeCfgTrees.heightmin[current], KTreeCfgTrees.heightmax[current], KTreeCfgTrees.stuntmin[current]);
			bigTree.pineGenerate(world, rand, x, y, z);
		 }
	       }else if (KTreeCfgTrees.algore[current] == 8){
		 if (rand.nextInt(numberoftrees*50) == 0) {
			KWorldGenBigTree bigTree = (new KWorldGenBigTree(false));
			bigTree.setConfigOptions(KTreeCfgTrees.woodBlock[current], KTreeCfgTrees.leafBlock[current], KTreeCfgTrees.woodMeta[current], KTreeCfgTrees.leafMeta[current], KTreeCfgTrees.baseBlock1[current], KTreeCfgTrees.baseBlock2[current], KTreeCfgTrees.heightmin[current], KTreeCfgTrees.heightmax[current], KTreeCfgTrees.stuntmin[current]);
			bigTree.birchGenerate(world, rand, x, y, z);
		 }
	       }else if (KTreeCfgTrees.algore[current] == 9){
		 if (rand.nextInt(numberoftrees*50) == 0) {
			KWorldGenBigTree bigTree = (new KWorldGenBigTree(false));
			bigTree.setConfigOptions(KTreeCfgTrees.woodBlock[current], KTreeCfgTrees.leafBlock[current], KTreeCfgTrees.woodMeta[current], KTreeCfgTrees.leafMeta[current], KTreeCfgTrees.baseBlock1[current], KTreeCfgTrees.baseBlock2[current], KTreeCfgTrees.heightmin[current], KTreeCfgTrees.heightmax[current], KTreeCfgTrees.stuntmin[current]);
			bigTree.desertGenerate(world, rand, x, y, z);
		 }
	       }else {}
	     }
	    }
	}
    return false;
    }
}

