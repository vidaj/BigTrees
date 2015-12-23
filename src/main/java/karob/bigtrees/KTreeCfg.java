// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package karob.bigtrees;

import java.io.File;

import java.util.Random;
//import net.minecraft.src.KGetOptions;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves, 
//            BlockGrass

public class KTreeCfg
{
    public static int outbreakPreventionProtocol;

    public static boolean rootsEnable;
    public static boolean tallTreeEnable;
    public static boolean oldTreeEnable;
    public static boolean swampTreeEnable;
    public static boolean hatTreeEnable;
    public static boolean cyprusTreeEnable;
    public static boolean deadTreeEnable;
    public static boolean thickPineEnable;
    public static boolean birchTreeEnable; //birch

    public static boolean classicTreeHeights;
    public static boolean classicJungle;

    public static int oakHeightMin;
    public static int oakHeightVar;
    public static int birchHeightMin;
    public static int birchHeightVar;
    public static int pine1HeightMin;
    public static int pine1HeightVar;
    public static int pine2HeightMin;
    public static int pine2HeightVar;
    public static int swampHeightMin;
    public static int swampHeightVar;
    public static int jungle1HeightMin;
    public static int jungle1HeightVar;
    public static int jungle2HeightMin;
    public static int jungle2HeightVar;

    public static boolean bigTreeRealmsFlag;
    public static int[] bigTreeRealmsExceptions;
    public static boolean bigTreeRecipeRealmsFlag;
    public static int[] bigTreeRecipeRealmsExceptions;

    public static int oldTreePopulation;
    public static int oldTreeJunglePopulation;
    public static int bigTreePopulation;
    public static double swampTreePercentage;
    public static double hatTreePercentage;
    public static double cyprusTreePercentage;
    public static double deadTreePercentage;
    public static int thickPinePopulation;
    public static int mixedPinePopulation;
    public static int hasPinePopulation;
    public static boolean variablePineDensity;
    public static double birchTreePercentage; //birch

    public static int tallWoodType;
    public static int tallWoodMeta;
    public static int tallLeafType;
    public static int tallLeafMeta;
    public static int tallHeightMin;
    public static int tallHeightVar;
    public static int tallTrunkMin;
    public static int tallTrunkVar;
    public static int tallStuntMin;
    public static int tallBaseType1;
    public static int tallBaseType2;
    public static double tallBranchLength;
    public static double tallBranchRotation;
    public static int tallTapRootLength;
    public static int tallBranchSpacing;
    public static double tallBranchPitch;
    public static double tallBranchCurl;
    public static int tallLeafRadius;
    public static int tallSubBranchDensity;
    public static double tallSubBranchSection;
    public static double tallSizeForSub;
    public static double tallSubBranchAngle;
    public static double tallSubBranchSize;

    public static int hatWoodType;
    public static int hatWoodMeta;
    public static int hatLeafType;
    public static int hatLeafMeta;
    public static int hatHeightMin;
    public static int hatHeightVar;
    public static int hatStuntMin;
    public static int hatBaseType1;
    public static int hatBaseType2;

    public static int cyprusWoodType;
    public static int cyprusWoodMeta;
    public static int cyprusLeafType;
    public static int cyprusLeafMeta;
    public static int cyprusHeightMin;
    public static int cyprusHeightVar;
    public static int cyprusStuntMin;
    public static int cyprusBaseType1;
    public static int cyprusBaseType2;

    public static int stubWoodType;
    public static int stubWoodMeta;
    public static int oak1WoodType;
    public static int oak1WoodMeta;
    public static int oak1LeafType;
    public static int oak1LeafMeta;
    public static int pine1WoodType;
    public static int pine1WoodMeta;
    public static int pine1LeafType;
    public static int pine1LeafMeta;
    public static int oak2WoodType;
    public static int oak2WoodMeta;
    public static int oak2LeafType;
    public static int oak2LeafMeta;
    public static int swoakWoodType;
    public static int swoakWoodMeta;
    public static int swoakLeafType;
    public static int swoakLeafMeta;
    public static int deadWoodType;
    public static int deadWoodMeta;
    public static int birchWoodType; //birch
    public static int birchWoodMeta;
    public static int birchLeafType;
    public static int birchLeafMeta;

    public static int[] stubHeight = new int[2];
    public static int[] oak1Height = new int[2];
    public static int[] pine1Height = new int[2];
    public static int[] oak2Height = new int[2];
    public static int[] swoakHeight = new int[2];
    public static int[] deadHeight = new int[2];
    public static int[] birchHeight = new int[2]; //birch

    public static int bigOakSaplingId;
    public static int bigPineSaplingId;
    public static int deadTreeSaplingId;
    public static int cyprusSaplingId;
    public static int hatTreeSaplingId;
    public static int bigBirchSaplingId; //birch

    private static boolean isInit = false;

    private static int currentRealm = -12349;
    private static boolean currentRealmPass = false;
    private static int currentRecipeRealm = -12349;
    private static boolean currentRecipeRealmPass = false;

    public KTreeCfg(){
//        currentRealm = -12349;
    }

    public static boolean passRealm(int a){
//      System.out.println((new StringBuilder()).append("REALM "+a+"'.").toString());
      if(currentRealm == a) return currentRealmPass;
      currentRealm = a;
      currentRealmPass = bigTreeRealmsFlag;
      for(int i = 0; i < bigTreeRealmsExceptions.length; i++){
        if(bigTreeRealmsExceptions[i] == a) currentRealmPass = !bigTreeRealmsFlag;
      }
      return currentRealmPass;
    }

    public static boolean passRecipeRealm(int a){
 //     System.out.println((new StringBuilder()).append("RECIPE REALM "+a+"'.").toString());
//      if(currentRecipeRealm == a) return currentRecipeRealmPass;
      currentRecipeRealm = a;
      currentRecipeRealmPass = bigTreeRecipeRealmsFlag;
      for(int i = 0; i < bigTreeRecipeRealmsExceptions.length; i++){
        if(bigTreeRecipeRealmsExceptions[i] == a) currentRecipeRealmPass = !bigTreeRecipeRealmsFlag;
      }
      return currentRecipeRealmPass;
    }

    public static int vary(Random rand, int[] opt){
      return rand.nextInt(opt[1])+opt[0];
    }

    public static void init(String directory){
        if(isInit == true) return;
        isInit = true;
        KGetOptions.setFile(directory + File.separator + "kbigtrees.txt");
        KGetOptions.setSection("GENERAL");

        bigOakSaplingId = KGetOptions.getInteger("big-oak-sapling-id", 1301);
        bigBirchSaplingId = KGetOptions.getInteger("big-birch-sapling-id", 1302);
        bigPineSaplingId = KGetOptions.getInteger("big-pine-sapling-id", 1303);
        deadTreeSaplingId = KGetOptions.getInteger("dead-tree-sapling-id", 1304);
        cyprusSaplingId = KGetOptions.getInteger("cyprus-sapling-id", 1305);
        hatTreeSaplingId = KGetOptions.getInteger("hat-tree-sapling-id", 1306);

        outbreakPreventionProtocol = KGetOptions.getInteger("fire-prevention-protocol", 1);
        rootsEnable = KGetOptions.getBoolean("roots-enable", true);
        tallTreeEnable = KGetOptions.getBoolean("tall-tree-enable", true);
        oldTreeEnable = KGetOptions.getBoolean("old-tree-enable", true);
        swampTreeEnable = KGetOptions.getBoolean("old-swamp-tree-enable", true);
        hatTreeEnable = KGetOptions.getBoolean("hat-tree-enable", true);
        cyprusTreeEnable = KGetOptions.getBoolean("cyprus-tree-enable", true);
        deadTreeEnable = KGetOptions.getBoolean("dead-tree-enable", true);
        thickPineEnable = KGetOptions.getBoolean("thick-pine-enable", true);
        birchTreeEnable = KGetOptions.getBoolean("big-birch-enable", true);

        classicTreeHeights = KGetOptions.getBoolean("classic-tree-heights", false);
        classicJungle = KGetOptions.getBoolean("classic-jungle", false);

        int[] a1 = {123457};
        int[] a2 = {};
        int[] a3 = {0};
        bigTreeRealmsFlag = false;
        bigTreeRealmsExceptions = KGetOptions.getIntegerArray("big-tree-realms", a1);
        bigTreeRecipeRealmsFlag = true;
        bigTreeRecipeRealmsExceptions = a2;
        if(bigTreeRealmsExceptions[0] == 123457){
            String ss;
            bigTreeRealmsFlag = KGetOptions.getBoolean("natural-generation", false);
            ss = KGetOptions.getWord("natural-generation-exceptions", "");
            if(ss.toLowerCase().equals("none")) bigTreeRealmsExceptions = a2;
            else bigTreeRealmsExceptions = KGetOptions.getIntegerArray("natural-generation-exceptions", a3);
            bigTreeRecipeRealmsFlag = KGetOptions.getBoolean("manual-generation", true);
            ss = KGetOptions.getWord("manual-generation-exceptions", "");
            if(ss.toLowerCase().equals("none")) bigTreeRecipeRealmsExceptions = a2;
            else bigTreeRecipeRealmsExceptions = KGetOptions.getIntegerArray("manual-generation-exceptions", a2);
        }
//        currentRealm = 0;
//        currentRealmPass = passRealm(currentRealm);

      if(classicTreeHeights){
        oakHeightMin = 4;
        oakHeightVar = 7;
        birchHeightMin = 5;
        birchHeightVar = 8;
        pine1HeightMin = 7;
        pine1HeightVar = 12;
        pine2HeightMin = 6;
        pine2HeightVar = 10;
        swampHeightMin = 5;
        swampHeightVar = 9;
        jungle1HeightMin = 10;
        jungle1HeightVar = 30;
        jungle2HeightMin = 4;
        jungle2HeightVar = 11;
      }else{
        oakHeightMin = KGetOptions.getInteger("oak-height-minimum", 4);
        oakHeightVar = KGetOptions.getInteger("oak-height-maximum", 7) - oakHeightMin;
        birchHeightMin = KGetOptions.getInteger("birch-height-minimum", 8);
        birchHeightVar = KGetOptions.getInteger("birch-height-maximum", 14) - birchHeightMin;
        pine1HeightMin = KGetOptions.getInteger("pine-1-height-minimum", 11);
        pine1HeightVar = KGetOptions.getInteger("pine-1-height-maximum", 16) - pine1HeightMin;
        pine2HeightMin = KGetOptions.getInteger("pine-2-height-minimum", 10);
        pine2HeightVar = KGetOptions.getInteger("pine-2-height-maximum", 14) - pine2HeightMin;
        swampHeightMin = KGetOptions.getInteger("swamp-height-minimum", 5);
        swampHeightVar = KGetOptions.getInteger("swamp-height-maximum", 9) - swampHeightMin;
        jungle1HeightMin = KGetOptions.getInteger("jungle-1-height-minimum", 10);
        jungle1HeightVar = KGetOptions.getInteger("jungle-1-height-maximum", 35) - jungle1HeightMin;
        jungle2HeightMin = KGetOptions.getInteger("jungle-2-height-minimum", 4);
        jungle2HeightVar = KGetOptions.getInteger("jungle-2-height-maximum", 11) - jungle2HeightMin;
      }

double oldTreePercentage = KGetOptions.getFloat("old-tree-percentage", 25F);
double tallTreePercentage = KGetOptions.getFloat("tall-tree-percentage", 25F);
double bigTreePercentage = oldTreePercentage + tallTreePercentage;
if(oldTreeEnable == false) oldTreePercentage = 0.0;
if(tallTreeEnable == false) tallTreePercentage = 0.0;
if(bigTreePercentage == 0.0){
  oldTreePopulation = 72;
}else{
  oldTreePopulation = (int)(oldTreePercentage * 144.0 / bigTreePercentage);
}
bigTreePopulation = (int)(bigTreePercentage * 224.0 / 100.0 - 80.0);
variablePineDensity = KGetOptions.getBoolean("variable-pine-density", true);

oldTreeJunglePopulation = (int)(KGetOptions.getFloat("old-tree-jungle-percentage", 25F) * 224.0 / 100.0 - 80.0);

double thickPinePercentage = KGetOptions.getFloat("thick-pine-percentage", 20F);
double mixedPinePercentage = KGetOptions.getFloat("mixed-pine-percentage", 20F);
if(thickPineEnable == false){
  thickPinePercentage = 0.0;
  mixedPinePercentage = 0.0;
}
thickPinePopulation = (int)(thickPinePercentage * 1.44);
mixedPinePopulation = (int)(mixedPinePercentage * 1.44)+thickPinePopulation;
hasPinePopulation = (int)((100F - KGetOptions.getFloat("no-pine-percentage", 20F)) * 1.44);

        swampTreePercentage = KGetOptions.getFloat("old-swamp-tree-percentage", 20F)/100.0;
        hatTreePercentage = KGetOptions.getFloat("hat-tree-percentage", 20F)/100.0;
        cyprusTreePercentage = KGetOptions.getFloat("cyprus-tree-percentage", 20F)/100.0;
        deadTreePercentage = KGetOptions.getFloat("dead-tree-percentage", 5F)/100.0;
        if(swampTreeEnable == true){
          hatTreePercentage += swampTreePercentage;
          cyprusTreePercentage += swampTreePercentage;
        }
        if(KTreeCfg.hatTreeEnable == true){
          cyprusTreePercentage += hatTreePercentage;
        }
        birchTreePercentage = KGetOptions.getFloat("big-birch-percentage", 0.5F)/100.0;

        KGetOptions.setSection("TALLTREE");
        tallWoodType = KGetOptions.getInteger("wood-type", 17);
        tallWoodMeta = KGetOptions.getInteger("wood-meta", 0);
        tallLeafType = KGetOptions.getInteger("leaf-type", 18);
        tallLeafMeta = KGetOptions.getInteger("leaf-meta", 0);
        tallHeightMin = KGetOptions.getInteger("height-minimum", 13);
        tallHeightVar = KGetOptions.getInteger("height-maximum", 28) - tallHeightMin;
        tallTrunkMin = KGetOptions.getInteger("trunk-minimum", 23);
        tallTrunkVar = KGetOptions.getInteger("trunk-maximum", 32) - tallTrunkMin;
        tallStuntMin = KGetOptions.getInteger("stunt-minimum", 7);
        tallBaseType1 = KGetOptions.getInteger("base-type-1", 3);
        tallBaseType2 = KGetOptions.getInteger("base-type-2", 2);
        tallBranchLength = KGetOptions.getFloat("branch-length", 60F) / 100F;
        tallBranchRotation = KGetOptions.getFloat("branch-rotation", 0.618034F) * 2.0 * Math.PI;
        tallTapRootLength = KGetOptions.getInteger("tap-root-length", 50);
        tallBranchSpacing = KGetOptions.getInteger("branch-spacing", 60);
        tallBranchPitch = KGetOptions.getFloat("branch-pitch", 0.0F);
        tallBranchCurl = KGetOptions.getFloat("branch-curl", 0.08F);
        tallLeafRadius = KGetOptions.getInteger("leaf-radius", 3);
        tallSubBranchDensity = KGetOptions.getInteger("sub-branch-density", 12);
        tallSubBranchSection = KGetOptions.getFloat("sub-branch-section", 60F)/100F;
        tallSizeForSub = KGetOptions.getFloat("branch-curl", 4.0F);
        tallSubBranchAngle = KGetOptions.getFloat("sub-branch-angle", 30F)*Math.PI/180F;
        tallSubBranchSize = KGetOptions.getFloat("sub-branch-size", 75F)/100F;

        KGetOptions.setSection("HATTREE");
        hatWoodType = KGetOptions.getInteger("wood-type", 17);
        hatWoodMeta = KGetOptions.getInteger("wood-meta", 0);
        hatLeafType = KGetOptions.getInteger("leaf-type", 18);
        hatLeafMeta = KGetOptions.getInteger("leaf-meta", 0);
        hatHeightMin = KGetOptions.getInteger("height-minimum", 28);
        hatHeightVar = KGetOptions.getInteger("height-maximum", 32) - hatHeightMin;
        hatStuntMin = KGetOptions.getInteger("stunt-minimum", 7);
        hatBaseType1 = KGetOptions.getInteger("base-type-1", 3);
        hatBaseType2 = KGetOptions.getInteger("base-type-2", 2);

        KGetOptions.setSection("CYPRUSTREE");
        cyprusWoodType = KGetOptions.getInteger("wood-type", 17);
        cyprusWoodMeta = KGetOptions.getInteger("wood-meta", 1);
        cyprusLeafType = KGetOptions.getInteger("leaf-type", 18);
        cyprusLeafMeta = KGetOptions.getInteger("leaf-meta", 1);
        cyprusHeightMin = KGetOptions.getInteger("height-minimum", 28);
        cyprusHeightVar = KGetOptions.getInteger("height-maximum", 32) - cyprusHeightMin;
        cyprusStuntMin = KGetOptions.getInteger("stunt-minimum", 7);
        cyprusBaseType1 = KGetOptions.getInteger("base-type-1", 3);
        cyprusBaseType2 = KGetOptions.getInteger("base-type-2", 2);

        KGetOptions.setSection("OLDTREE");
        stubWoodType = KGetOptions.getInteger("wood-type-1", 17);
        stubWoodMeta = KGetOptions.getInteger("wood-meta-1", 0);
        stubHeight[0] = KGetOptions.getInteger("height-minimum-1", 3);
        stubHeight[1] = KGetOptions.getInteger("height-maximum-1", 7) - stubHeight[0];
        oak1WoodType = KGetOptions.getInteger("wood-type-2", 17);
        oak1WoodMeta = KGetOptions.getInteger("wood-meta-2", 0);
        oak1LeafType = KGetOptions.getInteger("leaf-type-2", 18);
        oak1LeafMeta = KGetOptions.getInteger("leaf-meta-2", 0);
        oak1Height[0] = KGetOptions.getInteger("height-minimum-2", 12);
        oak1Height[1] = KGetOptions.getInteger("height-maximum-2", 18) - oak1Height[0];
        pine1WoodType = KGetOptions.getInteger("wood-type-3", 17);
        pine1WoodMeta = KGetOptions.getInteger("wood-meta-3", 1);
        pine1LeafType = KGetOptions.getInteger("leaf-type-3", 18);
        pine1LeafMeta = KGetOptions.getInteger("leaf-meta-3", 1);
        pine1Height[0] = KGetOptions.getInteger("height-minimum-3", 18);
        pine1Height[1] = KGetOptions.getInteger("height-maximum-3", 22) - pine1Height[0];
        oak2WoodType = KGetOptions.getInteger("wood-type-4", 17);
        oak2WoodMeta = KGetOptions.getInteger("wood-meta-4", 0);
        oak2LeafType = KGetOptions.getInteger("leaf-type-4", 18);
        oak2LeafMeta = KGetOptions.getInteger("leaf-meta-4", 0);
        oak2Height[0] = KGetOptions.getInteger("height-minimum-4", 28);
        oak2Height[1] = KGetOptions.getInteger("height-maximum-4", 32) - oak2Height[0];
        birchWoodType = KGetOptions.getInteger("wood-type-5", 17);
        birchWoodMeta = KGetOptions.getInteger("wood-meta-5", 2);
        birchLeafType = KGetOptions.getInteger("leaf-type-5", 18);
        birchLeafMeta = KGetOptions.getInteger("leaf-meta-5", 2);
        birchHeight[0] = KGetOptions.getInteger("height-minimum-5", 19);
        birchHeight[1] = KGetOptions.getInteger("height-maximum-5", 24) - birchHeight[0];
        KGetOptions.setSection("SWAMPTREE");
        swoakWoodType = KGetOptions.getInteger("wood-type", 17);
        swoakWoodMeta = KGetOptions.getInteger("wood-meta", 0);
        swoakLeafType = KGetOptions.getInteger("leaf-type", 18);
        swoakLeafMeta = KGetOptions.getInteger("leaf-meta", 0);
        swoakHeight[0] = KGetOptions.getInteger("height-minimum", 28);
        swoakHeight[1] = KGetOptions.getInteger("height-maximum", 32) - swoakHeight[0];
        KGetOptions.setSection("DEADTREE");
        deadWoodType = KGetOptions.getInteger("wood-type", 17);
        deadWoodMeta = KGetOptions.getInteger("wood-meta", 0);
        deadHeight[0] = KGetOptions.getInteger("height-minimum", 13);
        deadHeight[1] = KGetOptions.getInteger("height-maximum", 17) - deadHeight[0];

        return;
    }
}
