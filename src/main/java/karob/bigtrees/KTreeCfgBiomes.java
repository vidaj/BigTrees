package karob.bigtrees;

import java.io.File;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraft.world.*;

import karob.bigtrees.KTreeCfg;

public class KTreeCfgBiomes 
{
	public static Configuration config;
    	public static BiomeGenBase[] biomeArray;
	private static BiomeGenBase[] temp;
	private static String[] namearray;
    	public static String[] biomeNames;
	public static BiomeGenBase biomes;
	public static String biomestring;
	public static String[][] TreeGroups;
	public static int[][] TreeDensity;
	public static int defaultdensity[][];
	public static String [] GroupNameList;
	public static int Groups;
	public static int primaryGroups;
	public static int secondaryGroups;
	public static int[] Biomespergroup;
	//this would be cool to have biome dependent:
	//public static int[][] TreeLogBlock;
	//public static int[][] TreeLeafBlock;
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
        	biomeArray = BiomeGenBase.getBiomeGenArray();
		config.load();
		int i;//iterator
		int j=0;//j is the number of tags/groups
		int k;//iterator
		TreeGroups = new String [256][256];
		Biomespergroup = new int [256];
		GroupNameList = new String [256];
		int numberoftrees = KTreeCfgTrees.count;
		TreeDensity = new int [256][numberoftrees];
		defaultdensity = new int [256][numberoftrees];
        	biomeNames = new String[biomeArray.length];
		int biomeID;
		for (biomeID = 0; biomeID < biomeArray.length; biomeID++) {
            		biomes = biomeArray[biomeID];
			if (BiomeGenBase.getBiomeGenArray()[biomeID] != null){
				biomestring = biomes.getClass().getName() + "." + biomes.biomeName;
			 	String[] stuff = biomestring.split("\\.");
				stuff[0] = stuff[0].replace("net","minecraft");
				biomestring = stuff[0] + "." + biomes.biomeName;
				biomestring = biomestring.replace("(","");
				biomestring = biomestring.replace(")","");
				biomestring = biomestring.replace("+","plus");
				biomeNames[biomeID] = biomestring;
	    		}
		}
		//basic forge groups
		for (Type type : Type.values()){
			temp = BiomeDictionary.getBiomesForType(type);
			namearray = new String[temp.length];
			for (i=0; i< temp.length;i++){
				namearray[i]=temp[i].getClass().getName()  + "." + temp[i].biomeName;
				String[] stuff = namearray[i].split("\\.");
				stuff[0] = stuff[0].replace("net","minecraft");
				namearray[i]=stuff[0] + "." + temp[i].biomeName;
				namearray[i] = namearray[i].replace("(","");
				namearray[i] = namearray[i].replace(")","");
				namearray[i] = namearray[i].replace("+","plus");
			}
			String[] arrg;
			try{arrg = config.get("1: Forge Biome Groups", type.toString() , namearray ,"").getStringList();}
			catch (Exception e){System.out.println("Problem reading Bigtrees biome group config file!");arrg = namearray;}
			TreeGroups[j] =	arrg;
			if(arrg != null){Biomespergroup[j] = arrg.length;}
			else {Biomespergroup[j] = 0;}
			GroupNameList[j] = type.toString();
			j++;
		}
		//combinations of two forge groups
		primaryGroups = j;
		String forge2groups [] = new String [j*j];
		Type [] forgegroups = Type.values();
		for (biomeID = 0; biomeID < biomeArray.length ; biomeID++){
			if (BiomeGenBase.getBiomeGenArray()[biomeID] == null){continue;}
			Type[] biometypes = BiomeDictionary.getTypesForBiome(biomeArray[biomeID]);
			if (biometypes.length < 2){continue;}
			for (i = 0; i < primaryGroups; i++){for (k = 0; k < i; k++){
				if ( (forgegroups[i]==biometypes[0] && forgegroups[k]==biometypes[1]) || (forgegroups[i]==biometypes[1] && forgegroups[k]==biometypes[0]) ){
					forge2groups[i*primaryGroups+k] = 	biomeNames[biomeID] + "\n" + forge2groups[i*primaryGroups+k];	
				}
				else if (biometypes.length == 3){
					if (forgegroups[i]==biometypes[2]){
						if (forgegroups[k]==biometypes[0] || forgegroups[k]==biometypes[1]){
							forge2groups[i*primaryGroups+k] = 	biomeNames[biomeID] + "\n" + forge2groups[i*primaryGroups+k];								}
					}
					if (forgegroups[k]==biometypes[2]){
						if (forgegroups[i]==biometypes[0] || forgegroups[i]==biometypes[1]){
							forge2groups[i*primaryGroups+k] = 	biomeNames[biomeID] + "\n" + forge2groups[i*primaryGroups+k];								}
					}
				}		
			}}
		}
		int type1;
		int type2;
		for (i = 0; i < primaryGroups*primaryGroups; i++){
			if (forge2groups[i] != null){
				type1 = i/primaryGroups;
				type2 = i-type1*primaryGroups;
				String [] temp1=forge2groups[i].split("\n");
				String [] temp2=new String [temp1.length-1];
				for (k=0; k < temp1.length-1;k++){temp2[k]=temp1[k];}
				String [] arrg;
				try{arrg = config.get("2: Secondary Biome Groups", forgegroups[type1] + " AND " + forgegroups[type2] ,temp2  ,"overrides above groups").getStringList();}
				catch(Exception e) {System.out.println("Problem reading Bigtrees biome group config file!");arrg = temp2;}
				TreeGroups[j] = arrg;
				if(arrg != null){Biomespergroup[j] = arrg.length;}
				else {Biomespergroup[j] = 0;}
				GroupNameList[j]=forgegroups[type1] + " AND " + forgegroups[type2];
				j++;
			}
		}
		secondaryGroups = j-primaryGroups;
		//specification for custom groups
		String[] custom = new String[2];
		custom[0] = "Custom Forest Group";
		custom[1] = "Custom Mountain Group";
		String[] CustomGroup;
		try{CustomGroup = config.get("3: Custom Group Definition", "Defined Custom Groups", custom,"undefined custom biome groups will be ignored").getStringList();}
		catch(Exception e){System.out.println("Problem reading Bigtrees biome group config file!");CustomGroup = custom;}
		//custom groups
		for (i=0; i < CustomGroup.length; i++){
			try{String[] arrg = config.get("4: Custom Biome Groups", CustomGroup[i] , "" ,"overrides above groups").getStringList();
			TreeGroups[j] = arrg;		
			if(!(arrg == null)){Biomespergroup[j] = arrg.length;}
			else {Biomespergroup[j] = 0;}
			GroupNameList[j]=CustomGroup[i];
			j++;}
			catch(Exception e){System.out.println("Problem reading Bigtrees biome group config file!");}
			if (j>255){throw new RuntimeException("Bigtrees: cannot have more than 256 biome groups!");}
		}
		Groups = j;
		//Tree data for each group
		//String [] treeAlgorithms = {"Tall Oak", "Block Oak","Great Oak", "Swamp Oak", "Cyprus Tree", "Hat Tree", "Big Pine", "Big Birch", "Dead Tree"};
		for (i = 0; i < numberoftrees;i++){
			for (k = 0; k < Groups; k++){
			   
			   defaultdensity[k][i] = 0;
			   if (k < primaryGroups){
			   if (Type.values()[k] == Type.FOREST){
				if (KTreeCfgTrees.algore[i] == 1){defaultdensity[k][i] = 50;}
				else if (KTreeCfgTrees.algore[i] == 2){defaultdensity[k][i] = 20;}
				else if (KTreeCfgTrees.algore[i] == 3){defaultdensity[k][i] = 5;}
				else if (KTreeCfgTrees.algore[i] == 4){defaultdensity[k][i] = 4;}
				else if (KTreeCfgTrees.algore[i] == 5){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 6){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 7){defaultdensity[k][i] = 9;}
				else if (KTreeCfgTrees.algore[i] == 8){defaultdensity[k][i] = 14;}
				else if (KTreeCfgTrees.algore[i] == 9){defaultdensity[k][i] = 1;}
				else {defaultdensity[k][i] = 0;}
			   }
			   else if (Type.values()[k] == Type.SWAMP){
				if (KTreeCfgTrees.algore[i] == 1){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 2){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 3){defaultdensity[k][i] = 20;}
				else if (KTreeCfgTrees.algore[i] == 4){defaultdensity[k][i] = 20;}
				else if (KTreeCfgTrees.algore[i] == 5){defaultdensity[k][i] = 20;}
				else if (KTreeCfgTrees.algore[i] == 6){defaultdensity[k][i] = 20;}
				else if (KTreeCfgTrees.algore[i] == 7){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 8){defaultdensity[k][i] = 1;}
				else if (KTreeCfgTrees.algore[i] == 9){defaultdensity[k][i] = 0;}
				else {defaultdensity[k][i] = 0;}
			   }				
			   else if (Type.values()[k] == Type.PLAINS){
				if (KTreeCfgTrees.algore[i] == 1){defaultdensity[k][i] = 1;}
				else if (KTreeCfgTrees.algore[i] == 2){defaultdensity[k][i] = 2;}
				else if (KTreeCfgTrees.algore[i] == 3){defaultdensity[k][i] = 3;}
				else if (KTreeCfgTrees.algore[i] == 4){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 5){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 6){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 7){defaultdensity[k][i] = 1;}
				else if (KTreeCfgTrees.algore[i] == 8){defaultdensity[k][i] = 1;}
				else if (KTreeCfgTrees.algore[i] == 9){defaultdensity[k][i] = 0;}
				else {defaultdensity[k][i] = 0;}
			   }
			   else if (Type.values()[k] == Type.MOUNTAIN){
				if (KTreeCfgTrees.algore[i] == 1){defaultdensity[k][i] = 10;}
				else if (KTreeCfgTrees.algore[i] == 2){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 3){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 4){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 5){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 6){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 7){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 8){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 9){defaultdensity[k][i] = 0;}
				else {defaultdensity[k][i] = 0;}
			   }
			   else if (Type.values()[k] == Type.HILLS){
				if (KTreeCfgTrees.algore[i] == 1){defaultdensity[k][i] = 15;}
				else if (KTreeCfgTrees.algore[i] == 2){defaultdensity[k][i] = 6;}
				else if (KTreeCfgTrees.algore[i] == 3){defaultdensity[k][i] = 4;}
				else if (KTreeCfgTrees.algore[i] == 4){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 5){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 6){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 7){defaultdensity[k][i] = 7;}
				else if (KTreeCfgTrees.algore[i] == 8){defaultdensity[k][i] = 10;}
				else if (KTreeCfgTrees.algore[i] == 9){defaultdensity[k][i] = 0;}
				else {defaultdensity[k][i] = 0;}
			   }
			   else if (Type.values()[k] == Type.WATER){
				if (KTreeCfgTrees.algore[i] == 1){defaultdensity[k][i] = 5;}
				else if (KTreeCfgTrees.algore[i] == 2){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 3){defaultdensity[k][i] = 5;}
				else if (KTreeCfgTrees.algore[i] == 4){defaultdensity[k][i] = 2;}
				else if (KTreeCfgTrees.algore[i] == 5){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 6){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 7){defaultdensity[k][i] = 3;}
				else if (KTreeCfgTrees.algore[i] == 8){defaultdensity[k][i] = 5;}
				else if (KTreeCfgTrees.algore[i] == 9){defaultdensity[k][i] = 0;}
				else {defaultdensity[k][i] = 0;}
			   }
			   else if (Type.values()[k] == Type.DESERT || Type.values()[k] == Type.WASTELAND){
				if (KTreeCfgTrees.algore[i] == 1){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 2){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 3){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 4){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 5){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 6){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 7){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 8){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 9){defaultdensity[k][i] = 3;}
				else {defaultdensity[k][i] = 0;}
			   }
			   else if (Type.values()[k] == Type.FROZEN){
				if (KTreeCfgTrees.algore[i] == 1){defaultdensity[k][i] = 2;}
				else if (KTreeCfgTrees.algore[i] == 2){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 3){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 4){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 5){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 6){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 7){defaultdensity[k][i] = 10;}
				else if (KTreeCfgTrees.algore[i] == 8){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 9){defaultdensity[k][i] = 0;}
				else {defaultdensity[k][i] = 0;}
			   }
			   else if (Type.values()[k] == Type.JUNGLE){
				if (KTreeCfgTrees.algore[i] == 1){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 2){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 3){defaultdensity[k][i] = 10;}
				else if (KTreeCfgTrees.algore[i] == 4){defaultdensity[k][i] = 5;}
				else if (KTreeCfgTrees.algore[i] == 5){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 6){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 7){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 8){defaultdensity[k][i] = 0;}
				else if (KTreeCfgTrees.algore[i] == 9){defaultdensity[k][i] = 0;}
				else {defaultdensity[k][i] = 0;}
			   }
			   defaultdensity[k][i] = defaultdensity[k][i] * KTreeCfgTrees.frequencymultiplyer[i];
			   }
			}
		}
		for (i=0; i < primaryGroups; i++){
			for (k = 0; k < numberoftrees; k++){
			try{
			TreeDensity[i][k] = config.get("Tree populations for groups", GroupNameList[i] + " " + KTreeCfgTrees.treeName[k],defaultdensity[i][k]).getInt(); 
			}catch(Exception e){System.out.println("Problem reading Bigtrees config file!");for(k=0;k<numberoftrees;k++){TreeDensity[i][k]=defaultdensity[i][k];}}
			}
		}
		String[] pop= new String [numberoftrees];
		for (i=primaryGroups; i < Groups; i++){
		for (k=0; k < numberoftrees; k++){
		try{pop[k] = config.get("Tree populations for groups", GroupNameList[i] + " " + KTreeCfgTrees.treeName[k],"").getString();
			if ( !(pop[k].equals("")) ) {TreeDensity[i][k] = Integer.parseInt(pop[k]);}else{TreeDensity[i][k]= -1;}
		}catch(Exception e){System.out.println("Problem reading Bigtrees config file!");TreeDensity[i][k]=-1;}
		}
		}		
        	config.save();
				
	}
}
