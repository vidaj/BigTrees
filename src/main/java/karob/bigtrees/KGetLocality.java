// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package karob.bigtrees;


// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves, 
//            BlockGrass

public class KGetLocality
{
    public KGetLocality()
    {
    }

	//----------------------------------------
	// Lightweight locality mapping - kam
	public static int locality(int x, int y, int seed, int width){
		return localitySingle(x, y, seed, width)*7 + localitySingle(x, y, seed, width/2)*4 + localitySingle(x, y, seed, width/4);
		// Returns a value from 0 to 144.
	}

	// Single scale locality.
	public static int localitySingle(int x, int y, int seed, int width){
		int qa = localityAxis(x, seed, width);
		int qb = localityAxis(y, seed, width);
		if(Math.abs(qa-6) > Math.abs(qb-6)) return qa;
		else return qb;
		// Returns a value from 0 to 12.
	}

	// Single axis locality.
	public static int localityAxis(int coordinate, int seed, int width){
		int q, q1, q2;
		q = Math.abs(coordinate)+Math.abs(seed);
		q1 = (q/width) % width;
		q = q % width;
		q2 = (q1+1)*21 % 13;
		q1 = q1*21 % 13;
		return (q2-q1)*q/width + q1;
		// Returns a value from 0 to 12.
	}

}
