// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package karob.bigtrees;

import java.io.*;
//import net.minecraft.src.EnumOSHelper;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves, 
//            BlockGrass

public class KGetOptions{
    public static File optionsFile;
    public static String optionsFilename;
    public static String section;
    public static boolean sectionFlag;

    public KGetOptions(){
        sectionFlag = false;
    }

    public static void setFile(String s){
        optionsFilename = s;
//        optionsFile = new File(getMCDir(), optionsFilename);
        optionsFile = new File(optionsFilename);
        if(!optionsFile.exists())
        {
            System.out.println("BigTrees options file '"+optionsFilename+"' does not exist.");
            System.out.println("Creating '"+optionsFilename+"'.");
            optionsFile.getParentFile().mkdirs();
            autogenOptions(s);
        }else{
            System.out.println("Loading BigTrees options file '"+optionsFilename+"'.");
        }
        System.out.println("Picking cherries.");
    }

    public static void setSection(String s){
        section = s;
        sectionFlag = true;
    }

    public static void clearSection(String s){
        sectionFlag = false;
    }

    public static int getInteger(String trial, int safety){
        boolean startSection = !sectionFlag;
        try{
            if(!optionsFile.exists()){
                return safety;
            }
            BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
            for(String s = ""; (s = bufferedreader.readLine()) != null;){
                try{
                        String aa[] = s.split(" ");
                        if(aa[0].equals("SECTION")){
                            if(aa[1].equals(section)) startSection = true;
                            else startSection = false;
                        }
                    if(startSection==true){
                        String as[] = (s.replace(" ","")).split("=");
                        if(as[0].equals(trial)){
                            safety = Integer.parseInt(as[1].replace("%",""));
                            break;
                        }
                    }
                }
                catch(Exception exception1){
                    System.out.println((new StringBuilder()).append("Skipping bad '"+
                     optionsFilename+"' option: ").append(s).toString());
                }
            }
            bufferedreader.close();
            return safety;
        }
        catch(Exception exception){
            System.out.println("Failed to load '"+optionsFilename+"' options");
            exception.printStackTrace();
        }
        return safety;
    }

    public static int[] getIntegerArray(String trial, int safety[]){
        boolean startSection = !sectionFlag;
        try{
            if(!optionsFile.exists()){
                return safety;
            }
            BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
            for(String s = ""; (s = bufferedreader.readLine()) != null;){
                try{
                        String aa[] = s.split(" ");
                        if(aa[0].equals("SECTION")){
                            if(aa[1].equals(section)) startSection = true;
                            else startSection = false;
                        }
                    if(startSection==true){
                        String as[] = (s.replace(" ","")).split("=");
                        if(as[0].equals(trial)){
                            String bs[] = as[1].split(",");
                            safety = new int[bs.length];
                            for(int i = 0; i < bs.length; i ++){
                              safety[i] = Integer.parseInt(bs[i].replace("%",""));
                            }
                            break;
                        }
                    }
                }
                catch(Exception exception1){
                    System.out.println((new StringBuilder()).append("Skipping bad '"+
                     optionsFilename+"' option: ").append(s).toString());
                }
            }
            bufferedreader.close();
            return safety;
        }
        catch(Exception exception){
            System.out.println("Failed to load '"+optionsFilename+"' options");
            exception.printStackTrace();
        }
        return safety;
    }

    public static float getFloat(String trial, float safety){
        boolean startSection = !sectionFlag;
        try{
            if(!optionsFile.exists()){
                return safety;
            }
            BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
            for(String s = ""; (s = bufferedreader.readLine()) != null;){
                try{
                        String aa[] = s.split(" ");
                        if(aa[0].equals("SECTION")){
                            if(aa[1].equals(section)) startSection = true;
                            else startSection = false;
                        }
                    if(startSection==true){
                        String as[] = (s.replace(" ","")).split("=");
                        if(as[0].equals(trial)){
                            safety = Float.parseFloat(as[1].replace("%",""));
                            break;
                        }
                    }
                }
                catch(Exception exception1){
                    System.out.println((new StringBuilder()).append("Skipping bad '"+
                     optionsFilename+"' option: ").append(s).toString());
                }
            }
            bufferedreader.close();
            return safety;
        }
        catch(Exception exception){
            System.out.println("Failed to load '"+optionsFilename+"' options");
            exception.printStackTrace();
        }
        return safety;
    }

    public static boolean getBoolean(String trial, boolean safety){
        boolean startSection = !sectionFlag;
        try{
            if(!optionsFile.exists()){
                return safety;
            }
            BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
            for(String s = ""; (s = bufferedreader.readLine()) != null;){
                try{
                        String aa[] = s.split(" ");
                        if(aa[0].equals("SECTION")){
                            if(aa[1].equals(section)) startSection = true;
                            else startSection = false;
                        }
                    if(startSection==true){
                        String as[] = (s.replace(" ","")).split("=");
                        if(as[0].equals(trial)){
                            safety = as[1].equals("true");
                            break;
                        }
                    }
                }
                catch(Exception exception1){
                    System.out.println((new StringBuilder()).append("Skipping bad '"+
                     optionsFilename+"' option: ").append(s).toString());
                }
            }
            bufferedreader.close();
            return safety;
        }
        catch(Exception exception){
            System.out.println("Failed to load '"+optionsFilename+"' options");
            exception.printStackTrace();
        }
        return safety;
    }

    public static String getWord(String trial, String safety){
        //sample format: parameter = "82" or parameter = {"hello"}
        boolean startSection = !sectionFlag;
//        String temp;
        try{
            if(!optionsFile.exists()){
                return safety;
            }
            BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
            for(String s = ""; (s = bufferedreader.readLine()) != null;){
                try{
                        String aa[] = s.split(" ");
                        if(aa[0].equals("SECTION")){
                            if(aa[1].equals(section)) startSection = true;
                            else startSection = false;
                        }
                    if(startSection==true){
                        String as[] = s.split("=");
                        if((as[0].replace(" ","")).equals(trial)){
                            safety = as[1].trim();
                            break;
                        }
                    }
                }
                catch(Exception exception1){
                    System.out.println((new StringBuilder()).append("Skipping bad '"+
                     optionsFilename+"' option: ").append(s).toString());
                }
            }
            bufferedreader.close();
            return safety;
        }
        catch(Exception exception){
            System.out.println("Failed to load '"+optionsFilename+"' options");
            exception.printStackTrace();
        }
        return safety;
    }

    public static String getString(String trial, String safety){
        //sample format: parameter = "82" or parameter = {"hello"}
        boolean startSection = !sectionFlag;
        String temp;
        try{
            if(!optionsFile.exists()){
                return safety;
            }
            BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
            for(String s = ""; (s = bufferedreader.readLine()) != null;){
                try{
                        String aa[] = s.split(" ");
                        if(aa[0].equals("SECTION")){
                            if(aa[1].equals(section)) startSection = true;
                            else startSection = false;
                        }
                    if(startSection==true){
                        String as[] = s.split("=");
                        if((as[0].replace(" ","")).equals(trial)){
                            temp = as[1].trim();
                            safety = temp.substring(1,temp.length()-1);
                            break;
                        }
                    }
                }
                catch(Exception exception1){
                    System.out.println((new StringBuilder()).append("Skipping bad '"+
                     optionsFilename+"' option: ").append(s).toString());
                }
            }
            bufferedreader.close();
            return safety;
        }
        catch(Exception exception){
            System.out.println("Failed to load '"+optionsFilename+"' options");
            exception.printStackTrace();
        }
        return safety;
    }
/*
    public static File getMCDir()
    {
        String s = "minecraft";
        String s1 = System.getProperty("user.home", ".");
        File file;
//        switch(EnumOSHelper.field_74533_a[getOs().ordinal()])
//        switch (EnumOSHelper.enumOSMappingHelperArray[getOs().ordinal()])
        switch (EnumOSHelper.field_90049_a[getOs().ordinal()])
        {
        case 1: // '\001'
        case 2: // '\002'
            file = new File(s1, (new StringBuilder()).append('.').append(s).append('/').toString());
            break;

        case 3: // '\003'
            String s2 = System.getenv("APPDATA");
            if(s2 != null)
            {
                file = new File(s2, (new StringBuilder()).append(".").append(s).append('/').toString());
            } else
            {
                file = new File(s1, (new StringBuilder()).append('.').append(s).append('/').toString());
            }
            break;

        case 4: // '\004'
            file = new File(s1, (new StringBuilder()).append("Library/Application Support/").append(s).toString());
            break;

        default:
            file = new File(s1, (new StringBuilder()).append(s).append('/').toString());
            break;
        }
        if(!file.exists() && !file.mkdirs())
        {
            throw new RuntimeException((new StringBuilder()).append("The working directory could not be created: ").append(file).toString());
        } else
        {
            return file;
        }
    }


    private static EnumOS getOs()
    {
        String s = System.getProperty("os.name").toLowerCase();
        if(s.contains("win"))
        {
            return EnumOS.WINDOWS;
        }
        if(s.contains("mac"))
        {
            return EnumOS.MACOS;
        }
        if(s.contains("solaris"))
        {
            return EnumOS.SOLARIS;
        }
        if(s.contains("sunos"))
        {
            return EnumOS.SOLARIS;
        }
        if(s.contains("linux"))
        {
            return EnumOS.LINUX;
        }
        if(s.contains("unix"))
        {
            return EnumOS.LINUX;
        } else
        {
            return EnumOS.UNKNOWN;
        }
    }
*/
    public static void autogenOptions(String s)
    {
        try
        {
            PrintWriter var1 = new PrintWriter(new FileWriter(optionsFile));
var1.println("----------------------------------------------------");
var1.println("Big Trees Mod Configuration File");
var1.println("----------------------------------------------------");
var1.println("This file must be present in the folder \".minecraft\" to take effect.");
var1.println("");
var1.println("ALL POSSIBLE OPTION COMBINATIONS ARE PRESENT IN THIS FILE. Don't bother trying options from one section in another section. It won't work. :)");
var1.println("");
var1.println("Handy reference information:");
var1.println("");
var1.println("Tree (block type 17) Meta Data");
var1.println("  0 = regular");
var1.println("  1 = conifer");
var1.println("  2 = birch");
var1.println("");
var1.println("Leaf (block type 18) Meta Data");
var1.println("  0 = regular");
var1.println("  1 = conifer");
var1.println("  2 = birch");
var1.println("  3 = jungle");
var1.println("  4 = regular, never decay");
var1.println("  5 = conifer, never decay");
var1.println("  6 = birch, never decay");
var1.println("  7 = jungle, never decay");
var1.println("");
var1.println("My mod reads this file by searching for the keyword \"SECTION\" followed by a section name, then in that section pulling out options followed by an equals sign then a value. Everything that does not follow such a format is ignored. Percent symbols are for your convenience and don't affect the way the mod interprets a value.");
var1.println("");
var1.println("To completely disable big trees tree generation (except when manually grown), set \"natural-generation\" to false and \"natural-generation-exceptions\" to none.");
var1.println("To disable all other changes to world generation, set \"classic-tree-heights\" to true and set \"classic-jungle\" to true.");
var1.println("");
var1.println("----------------------------------------------------");
var1.println("SECTION GENERAL");
var1.println("----------------------------------------------------");
var1.println("");
var1.println("The following options mean \"do not naturally generate big trees by default");
var1.println("but do generate them in dimension 0\"");
var1.println("  natural-generation = false");
var1.println("  natural-generation-exceptions = 0");
var1.println("The following options mean \"do allow manual big tree growth (from saplings)");
var1.println("in all dimensions\"");
var1.println("  manual-generation = true");
var1.println("  manual-generation-exceptions = none");
var1.println("If, for example, you wanted to not allow manual big tree growth in the");
var1.println("nether and some dimension 2, you could put:");
var1.println("//  manual-generation = true");
var1.println("//  manual-generation-exceptions = -1, 2");
var1.println("Or if you ONLY want players to be able to grow big trees in the normal");
var1.println("dimension, you would put:");
var1.println("//  manual-generation = false");
var1.println("//  manual-generation-exceptions = 0");
var1.println("");
var1.println("Do you want to use the default heights for vanilla Minecraft trees?");
var1.println("  classic-tree-heights = false");
var1.println("Do you want the classic Minecraft jungle instead of my modified jungle trees with longer branches?");
var1.println("  classic-jungle = false");
var1.println("");
var1.println("Settings pertaining to the Minecraft original small trees.");
var1.println("  Original Minecraft oak height range is 4 to 7.");
var1.println("    oak-height-minimum = 4");
var1.println("    oak-height-maximum = 7");
var1.println("  Original Minecraft birch height range is 5 to 8.");
var1.println("    birch-height-minimum = 8");
var1.println("    birch-height-maximum = 14");
var1.println("  Original Minecraft pine 1 height range is 7 to 12.");
var1.println("    pine-1-height-minimum = 11");
var1.println("    pine-1-height-maximum = 16");
var1.println("  Original Minecraft pine 2 height range is 6 to 10.");
var1.println("    pine-2-height-minimum = 10");
var1.println("    pine-2-height-maximum = 14");
var1.println("  Original Minecraft swamp tree height range is 5 to 9.");
var1.println("    swamp-height-minimum = 5");
var1.println("    swamp-height-maximum = 9");
var1.println("  Original Minecraft huge jungle height range is 10 to 30.");
var1.println("    jungle-1-height-minimum = 10");
var1.println("    jungle-1-height-maximum = 35");
var1.println("  Original Minecraft small jungle height range is 4 to 11.");
var1.println("    jungle-2-height-minimum = 4");
var1.println("    jungle-2-height-maximum = 11");
var1.println("");
var1.println("Settings for forest tree types distribution.");
var1.println("  Old (wide) trees.");
var1.println("    old-tree-enable = true");
var1.println("    old-tree-percentage = 25%");
var1.println("  Tall narrow trees.");
var1.println("    tall-tree-enable = true");
var1.println("    tall-tree-percentage = 25%");
var1.println("  The remaining percent of forest trees will be the classic small ones.");
var1.println("");
var1.println("  Large birch trees can also generate in forests.");
var1.println("  big-birch-enable = true");
var1.println("  big-birch-percentage = 0.5%");
var1.println("");
var1.println("Settings for jungle tree type distribution.");
var1.println("  Great oak and block oaks in the jungle.");
var1.println("    old-tree-jungle-percentage = 25%");
var1.println("  The remaining percent of (small) jungle trees will be the classic ones.");
var1.println("");
var1.println("Settings for swamp tree type distribution.");
var1.println("  Great big swamp trees.");
var1.println("    old-swamp-tree-enable = true");
var1.println("    old-swamp-tree-percentage = 20%");
var1.println("  Conical 'hat' trees.");
var1.println("    hat-tree-enable = true");
var1.println("    hat-tree-percentage = 20%");
var1.println("  Cyprus trees.");
var1.println("    cyprus-tree-enable = true");
var1.println("    cyprus-tree-percentage = 20%");
var1.println("  The remaining percent of swamp trees will be the classic small ones.");
var1.println("");
var1.println("Settings for desert tree type distribution.");
var1.println("  Large dead trees in desert. They spawn from some dead bushes during world generation.");
var1.println("    dead-tree-enable = true");
var1.println("    dead-tree-percentage = 5%");
var1.println("  The remaining percent of \"dead trees\" will be the tiny useless dead bushes.");
var1.println("");
var1.println("Settings for taiga tree type distribution.");
var1.println("  Vary the density of (all) trees in the taiga?");
var1.println("    variable-pine-density = true");
var1.println("  Large pine trees in taiga.");
var1.println("    thick-pine-enable = true");
var1.println("  Region with only thick (big) pines.");
var1.println("    thick-pine-percentage = 20%");
var1.println("  Region with thick and classic pines.");
var1.println("    mixed-pine-percentage = 20%");
var1.println("  Region of taiga with NO TREES.");
var1.println("    no-pine-percentage = 20%");
var1.println("  The remaining percent of pine trees will be the classic small ones.");
var1.println("");
var1.println("Other settings.");
var1.println("  If for some reason you don't want roots, set this to false.");
var1.println("    roots-enable = true");
var1.println("  Block IDs:");
var1.println("    big-oak-sapling-id   = 1301");
var1.println("    big-birch-sapling-id = 1302");
var1.println("    big-pine-sapling-id  = 1303");
var1.println("    dead-tree-sapling-id = 1304");
var1.println("    cyprus-sapling-id    = 1305");
var1.println("    hat-tree-sapling-id  = 1306");
var1.println("");
var1.println("----------------------------------------------------");
var1.println("SECTION OLDTREE");
var1.println("----------------------------------------------------");
var1.println("affects: dead tree stump, block oak, thick pine, great oak, large birch.");
var1.println("----------------------------------------------------");
var1.println("");
var1.println("DEAD LITTLE TREE STUB");
var1.println("  wood-type-1 = 17");
var1.println("  wood-meta-1 = 0");
var1.println("  height-minimum-1 = 3");
var1.println("  height-maximum-1 = 7");
var1.println("2x2 BLOCK OAK");
var1.println("  wood-type-2 = 17");
var1.println("  wood-meta-2 = 0");
var1.println("  leaf-type-2 = 18");
var1.println("  leaf-meta-2 = 0");
var1.println("  height-minimum-2 = 12");
var1.println("  height-maximum-2 = 18");
var1.println("3x3 THICK PINE");
var1.println("  wood-type-3 = 17");
var1.println("  wood-meta-3 = 1");
var1.println("  leaf-type-3 = 18");
var1.println("  leaf-meta-3 = 1");
var1.println("  height-minimum-3 = 18");
var1.println("  height-maximum-3 = 22");
var1.println("4x4 GREAT OAK");
var1.println("  wood-type-4 = 17");
var1.println("  wood-meta-4 = 0");
var1.println("  leaf-type-4 = 18");
var1.println("  leaf-meta-4 = 0");
var1.println("  height-minimum-4 = 28");
var1.println("  height-maximum-4 = 32");
var1.println("2x2 BIRCH TREE");
var1.println("  wood-type-5 = 17");
var1.println("  wood-meta-5 = 2");
var1.println("  leaf-type-5 = 18");
var1.println("  leaf-meta-5 = 2");
var1.println("  height-minimum-5 = 19");
var1.println("  height-maximum-5 = 24");
var1.println("");
var1.println("----------------------------------------------------");
var1.println("SECTION DEADTREE");
var1.println("----------------------------------------------------");
var1.println("affects: dead desert trees");
var1.println("----------------------------------------------------");
var1.println("");
var1.println("DEAD DESERT TREES");
var1.println("  wood-type = 17");
var1.println("  wood-meta = 0");
var1.println("  height-minimum = 13");
var1.println("  height-maximum = 17");
var1.println("");
var1.println("----------------------------------------------------");
var1.println("SECTION SWAMPTREE");
var1.println("----------------------------------------------------");
var1.println("affects: great swamp oak");
var1.println("----------------------------------------------------");
var1.println("GREAT SWAMP OAK");
var1.println("  wood-type = 17");
var1.println("  wood-meta = 0");
var1.println("  leaf-type = 18");
var1.println("  leaf-meta = 0");
var1.println("  height-minimum = 28");
var1.println("  height-maximum = 32");
var1.println("");
var1.println("----------------------------------------------------");
var1.println("SECTION CYPRUSTREE");
var1.println("----------------------------------------------------");
var1.println("affects: cyprus trees");
var1.println("----------------------------------------------------");
var1.println("BIG CYPRUS TREES IN SWAMP");
var1.println("  wood-type = 17");
var1.println("  wood-meta = 1");
var1.println("  leaf-type = 18");
var1.println("  leaf-meta = 1");
var1.println("  height-minimum = 28");
var1.println("  height-maximum = 32");
var1.println("");
var1.println("----------------------------------------------------");
var1.println("SECTION HATTREE");
var1.println("----------------------------------------------------");
var1.println("affects: hat trees");
var1.println("----------------------------------------------------");
var1.println("BIG HAT TREES IN SWAMP");
var1.println("  wood-type = 17");
var1.println("  wood-meta = 0");
var1.println("  leaf-type = 18");
var1.println("  leaf-meta = 0");
var1.println("  height-minimum = 28");
var1.println("  height-maximum = 32");
var1.println("");
var1.println("----------------------------------------------------");
var1.println("SECTION TALLTREE");
var1.println("----------------------------------------------------");
var1.println("affects: tall oaks");
var1.println("----------------------------------------------------");
var1.println("TALL OAK");
var1.println("  wood-type = 17");
var1.println("  wood-meta = 0");
var1.println("  leaf-type = 18");
var1.println("  leaf-meta = 0");
var1.println("HEIGHT RANGE OF TREE");
var1.println("  height-minimum = 13");
var1.println("  height-maximum = 28");
var1.println("BRANCHLESS PERCENTAGE OF TREE");
var1.println("  trunk-minimum = 23%");
var1.println("  trunk-maximum = 32%");
var1.println("MINIMUM ACCEPTABLE HEIGHT OF TREE");
var1.println("  //Trees are stunted when blocked by overhead obstacles, but no shorter than this value.");
var1.println("  stunt-minimum = 7");
var1.println("BLOCK TYPE TREE MUST GROW ON");
var1.println("  //Set to -1 to disable requirement.");
var1.println("  base-type-1 = 3");
var1.println("ALTERNATE BLOCK TYPE FOR TREE TO GROW ON");
var1.println("  //Set to -1 to disable requirement.");
var1.println("  base-type-2 = 2");
var1.println("LONGEST BRANCH LENGTH PERCENTAGE");
var1.println("  //as a percentage of tree height:");
var1.println("  branch-length = 60%");
var1.println("BRANCH ROTATION");
var1.println("  //1.0 is a full rotation. The default is the golden mean: (sqrt(5)-1)/2");
var1.println("  branch-rotation = 0.618034");
var1.println("TAP ROOT LENGTH");
var1.println("  //Tap root length, as a percentage of tree height.");
var1.println("  //Tap roots grow through wood, leaves, air, water, dirt, sand, and gravel.");
var1.println("  tap-root-length = 50%");
var1.println("BRANCH SPACING");
var1.println("  //0 to 100, abstractly defines average spacing between branches");
var1.println("  branch-spacing = 60%");
var1.println("BRANCH");
var1.println("  //Defines the initial growth angle of a branch. 0=horizontal, 1=45 degrees upwards, -1=45 deg down...");
var1.println("  branch-pitch = 0.0");
var1.println("  //Defines the curling growth of a branch. Positive makes the branch curve upwards.");
var1.println("  branch-curl = 0.08");
var1.println("LEAF RADIUS");
var1.println("  //0 = no leaves");
var1.println("  //A radius of 1, 2, or 3 grows non-intrusively. (They won't grow through walls and such.)");
var1.println("  //Leaf radii of 4 or greater can grow through walls, though they won't destroy the walls.");
var1.println("  //Leaves too far from wood will decay.");
var1.println("  //Note leaves must have 4 added to their meta value to prevent them from decaying.");
var1.println("  leaf-radius = 3");
var1.println("SUB BRANCHES");
var1.println("  //Density of branching off of branches.");
var1.println("  sub-branch-density = 12%");
var1.println("  //Percent length of branch that can grow sub branches.");
var1.println("  sub-branch-section = 60%");
var1.println("  //Minimum branch size to grow sub branches, in meters/blocks.");
var1.println("  size-for-sub = 4.0");
var1.println("  //Angle between branch and sub branches, degrees.");
var1.println("  sub-branch-angle = 30");
var1.println("  //Size of sub branch as a percent of remaining length of parent branch.");
var1.println("  sub-branch-size = 75%");
var1.println("");
var1.println("WARNING: Setting sub branch size to 100 and sub branch section to 100 could easily lock up the computer trying to draw an infinite number of branches! (Setting sub branch size to >100 is bad as well...)");
var1.println("");
            var1.close();
        }
        catch (Exception var3)
        {
            System.out.println("Failed to create '"+s+"'.");
            var3.printStackTrace();
        }
    }

}

