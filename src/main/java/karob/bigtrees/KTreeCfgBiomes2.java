package karob.bigtrees;

import java.io.File;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraft.world.*;

import karob.bigtrees.KTreeCfgBiomes;

public class KTreeCfgBiomes2 
{
	public static Configuration config;
	public static BiomeGenBase biomes;
	public static String biomestring;
	public static int[][] BiomeTreePopulations;
	
	public static void init(File configFile) 
	{
		//Resuming configuration where previous file left off:		
		//Parse BiomesNames to BiomeIDs

		//Make biome name list, edit: moved to other file
		int biomeID;
		int i;//iterator
		int k;//iterator
		int numberoftrees = KTreeCfgTrees.count;
		config = new Configuration(configFile);
		config.load();
		BiomeTreePopulations = new int [KTreeCfgBiomes.biomeArray.length][numberoftrees];
//		// Match biome name list to biome groups
		for (biomeID=0; biomeID< KTreeCfgBiomes.biomeArray.length;  biomeID++){
			if (BiomeGenBase.getBiomeGenArray()[biomeID] != null){
			for (i = 0;i<KTreeCfgBiomes.Groups; i++){
				for (k = 0;k < KTreeCfgBiomes.Biomespergroup[i]; k++){
				if (KTreeCfgBiomes.biomeNames[biomeID].equals(KTreeCfgBiomes.TreeGroups[i][k])){
					for (int l = 0;l < numberoftrees; l++){if (KTreeCfgBiomes.TreeDensity[i][l] != -1){
					BiomeTreePopulations[biomeID][l] = KTreeCfgBiomes.TreeDensity[i][l];
				}}}}
			}}
		}
		// Get biome specific population overrides
		String[] pop = new String [numberoftrees];
		for (biomeID = 0; biomeID < KTreeCfgBiomes.biomeArray.length; biomeID++) {
		if (BiomeGenBase.getBiomeGenArray()[biomeID] != null){
		for (k = 0;k < numberoftrees; k++){
			try{
			pop[k] = config.get("Tree populations for biomes", KTreeCfgBiomes.biomeNames[biomeID] + " " +  KTreeCfgTrees.treeName[k],"").getString(); 
			}
			catch(Exception e){System.out.println("Problem reading Bigtrees individual biome config file!");for (k=0;k<numberoftrees;k++){pop[k]="";}}
		}
		for (k=0;k<numberoftrees;k++){if (!(pop[k].equals("")) ) {BiomeTreePopulations[biomeID][k] = Integer.parseInt(pop[k]);}}
		}}
       		config.save();
			
	}
}
