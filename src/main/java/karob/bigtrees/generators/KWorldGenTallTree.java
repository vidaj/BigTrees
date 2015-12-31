// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package karob.bigtrees.generators;

import java.util.Random;

import karob.bigtrees.KTreeCfg;
import karob.bigtrees.compat.WorldWrapper;
import karob.bigtrees.config.BlockAndMeta;
import karob.bigtrees.config.ITreeConfigurable;
import karob.bigtrees.config.TreeConfiguration;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
//import net.minecraft.src.KTreeCfg;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves, 
//            BlockGrass

public class KWorldGenTallTree extends AbstractWorldGenerator implements ITreeConfigurable {
	/*
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Idea: First grow trunk,
	 * placing blocks. Then pre-grow all branch data, then place blocks. Then
	 * grow all leaves. Don't allow branches to grow through wood.
	 */
	private int check[][][];
	private boolean planted;
	private double branchlessmin;
	private double branchlessmax;
	private double longestbranchp;
	private double branchrot;
	private double taplength;
	private double branchspace;
	private double pitch;
	private double curl;
	private int leafrad;
	private double subbranchdensity;
	private double subbranchinglength;
	private int subbranchingsize;
	private double subbranchangle;
	private double subbranchsize;

	// private int bbx[][];
	// private int bby[][];
	// private int bbz[][];

	public KWorldGenTallTree(boolean flag) {
		super(flag);
		planted = flag;
		check = new int[9][9][9];
		// bbx = new int[100][100];
		// KTreeCfg.init();
	}
	
	@Override
	public void setTreeConfiguration(TreeConfiguration treeConfiguration) {
		super.setTreeConfiguration(treeConfiguration);
		branchlessmin = treeConfiguration.getMinBranchless();
		branchlessmax = treeConfiguration.getMaxBranchless();
		longestbranchp = treeConfiguration.getLongestBranchPercentage();
		branchrot = treeConfiguration.getBranchRotation();
		taplength = treeConfiguration.getTapLength();
		branchspace = treeConfiguration.getBranchSpace();
		pitch = treeConfiguration.getPitch();
		curl = treeConfiguration.getCurl();
		leafrad = treeConfiguration.getLeafRadius();
		subbranchdensity = treeConfiguration.getSubBranchDensity();
		subbranchinglength = treeConfiguration.getSubBranchingLength();
		subbranchingsize = treeConfiguration.getSubBranchingSize();
		subbranchangle = treeConfiguration.getSubBranchAngle();
		subbranchsize = treeConfiguration.getSubBranchSize();
	}

	@Override
	public boolean generate(WorldWrapper world, Random random, int i, int j, int k) {
		worldObject = world;
		rand = random;
		int l;
		int m;
		BlockAndMeta zz;
		// boolean flag;
		l = random.nextInt(heightmax - heightmin) + heightmin; // Tree height
		// flag = true;
		if (j < 1)
			return false;
		// world.getClass();
		if (j + l + 1 > 256) {
			l = 256 - j - 2;
			if (l < stuntmin)
				return false;
		}
		/*
		 * label1: {
		 * 
		 * for(int i1 = j; i1 <= j + 1 + l; i1++) { byte byte0 = 1; if(i1 == j)
		 * { byte0 = 0; } if(i1 >= (j + 1 + l) - 2) { byte0 = 2; } for(int i2 =
		 * i - byte0; i2 <= i + byte0 && flag; i2++) { for(int l2 = k - byte0;
		 * l2 <= k + byte0 && flag; l2++) { if(i1 >= 0) { world.getClass();
		 * if(i1 < 256) { int j3 = world.getBlockId(i2, i1, l2); if(j3 != 0 &&
		 * j3 != Block.leaves.blockID) { flag = false; } continue; } } flag =
		 * false; }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * if(!flag) { return false; }
		 * 
		 * // Tree must grow on dirt/grass. int j1 = world.getBlockId(i, j - 1,
		 * k); if(j1 == Block.grass.blockID || j1 == Block.dirt.blockID) {
		 * world.getClass(); if(j < 256 - l - 1) { break label1; } } return
		 * false; } //end label1
		 */
		BlockAndMeta id;
		// If tree is generated, require base to be of certain blocktypes.
		if (!planted) {
			if (!isSupportedBaseBlock(i, j - 1, k)) {
				return false;
			}
			
			// bottom block of tree must be empty
			id = this.getBlock(i, j, k);
			if (!id.isAir() && !id.areEqual(leaf))
				return false;
		}
		// Now check for trunk growth area.
		int ll;
		for (ll = 0; ll <= l; ll++) {
			zz = this.getBlock(i, j + ll, k);
			if (!zz.isAir() && !zz.areEqual(wood, leaf))
				break;
		}
		if (ll < stuntmin)
			return false;
		// l = ll;
		// Set start of branching (m).
		int zzz = random
				.nextInt((int) (100 * (branchlessmax - branchlessmin) + branchlessmin));
		m = l * zzz / 100 + j;
		// First block of trunk.
		this.setBlockAndMetadata(i, j, k, wood);
		// Grow taproot
		if (KTreeCfg.rootsEnable == true) {
			for (int jj = 1; jj < ll * taplength / 100; jj++) {
				zz = this.getBlock(i, j - jj, k);
				if (!zz.isAir() && 
						!zz.areEqual(wood, leaf) && 
						!zz.areEqual(Blocks.grass, Blocks.dirt, Blocks.flowing_water, Blocks.water, Blocks.sand, Blocks.gravel)){
					break;
				} else {
					this.setBlockAndMetadata(i, j - jj, k, wood);
				}
			}
		}
		/*
		 * for(int k1 = (j - 3) + l; k1 <= j + l; k1++) { int j2 = k1 - (j + l);
		 * int i3 = 1 - j2 / 2; for(int k3 = i - i3; k3 <= i + i3; k3++) { int
		 * l3 = k3 - i; for(int i4 = k - i3; i4 <= k + i3; i4++) { int j4 = i4 -
		 * k; if((Math.abs(l3) != i3 || Math.abs(j4) != i3 || random.nextInt(2)
		 * != 0 && j2 != 0) && !Block.opaqueCubeLookup[world.getBlockId(k3, k1,
		 * i4)]) { world.setBlock(k3, k1, i4, Block.leaves.blockID); } }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * for(int l1 = 0; l1 < l; l1++) { int k2 = world.getBlockId(i, j + l1,
		 * k); if(k2 == 0 || k2 == Block.leaves.blockID) { world.setBlock(i, j +
		 * l1, k, Block.wood.blockID); } }
		 */

		// world.lightUpdates = false;
		// set initial branch direction
		double bd = random.nextFloat() * 2.0 * Math.PI;
		boolean growflag = true;
		// grow tree
		int jj = j;
		for (int j1 = j; j1 <= j + l; j1++) {
			// grow trunk taller if possible
			if (growflag) {
				id = this.getBlock(i, jj + 1, k);
				if (id.isAir() || id.areEqual(wood, leaf)) {
					jj++;
					this.setBlockAndMetadata(i, jj, k, wood);
				} else {
					growflag = false;
				}
			}
			// branch grower
			/*
			 * if(j1 >= m && random.nextInt(3) != 0){ //int ns =
			 * random.nextInt(2) * 2 - 1; //branch grows north or south? //int
			 * ew = random.nextInt(2) * 2 - 1; //branch grows east or west?
			 * //note - this may not actually be ingame north/south/east/west.
			 * //int bp = random.nextInt(10); //branch grows more ns or more ew?
			 * //set branch direction controllers according to angle (0 <= bd <
			 * 40) int ns, ew, bp; if(bd < 10){ ns = 1; ew = 1; bp = bd; }else
			 * if(bd < 20){ ns = 1; ew = -1; bp = 20 - bd; }else if(bd < 30){ ns
			 * = -1; ew = -1; bp = bd - 30; }else{ ns = -1; ew = 1; bp = 40 -
			 * bd; } //spin branches around by the golden mean bd = (bd + 25) %
			 * 40; //set branch length based on position on tree and tree height
			 * int bl = l * 2 / 3; bl = bl * ((j + l) - jj + l / 3) / (j + l - m
			 * + 1); //set branch upward growth - start angled down int uu = -l
			 * * 5 / 8; //grow branch out int ii = i; int jjj = jj; int kk = k;
			 * for(int ll = 0; ll <= bl; ll++){ //grow towards branch direction
			 * with randomness if(random.nextInt(14) - 2 < bp){ ii += ns; }else{
			 * kk += ew; } //grow up or down and curve upwards int rr =
			 * random.nextInt(l); if(uu < 0 && uu + rr < 0) jjj --; if(uu > 0 &&
			 * uu - rr > 0) jjj ++; uu ++; //set new block or stop growing
			 * branch id = world.getBlockId(ii, jjj, kk); if(id == 0 || id ==
			 * 18){ world.setBlockAndMetadata(ii, jjj, kk, 17, 0); }else{ break;
			 * } if(ll == bl) treeLeaf(world, ii, jjj, kk, 3); } }
			 */int branchs = (int) (100 * (branchspace));
			if (branchs < 0) {
				branchs = 0;
			}
			if (j1 >= m
					&& random.nextInt(branchs) < 100 * (j1 - m)
							/ (j + l - m + 1)) {
				// set branch length based on position on tree and tree height
				double bl = (double) l * longestbranchp;
				bl = bl * (double) ((j + l) - jj + 1)
						/ (double) (j + l - m + 1);
				// set branch upward growth - start angled down
				// int uu = -l * 5 / 8; uu = 0;
				treeBranch(i, jj, k, bd, bl, pitch, curl);
				// treeBranch(world, random, i, jj, k, (bd + 20) % 40, bl, uu,
				// l);
			}
			// change branching direction for next branch
			bd = bd + branchrot;
			// grow leaf puff at top of tree
			if (j1 == j + l && leafrad > 0)
				treeLeaf(i, jj, k, leafrad);
		}

		// world.lightUpdates = true;
		return true;
	}

	void treeBranch(int i, int j, int k, double dir, double len, double up,
			double crv) {

		// WORK IN FLOATING DIR instead of integer DIR

		if (len < 1.0)
			return;
		double xx = Math.cos(dir);
		double yy = up;
		double zz = Math.sin(dir);
		double dd = Math.sqrt(xx * xx + yy * yy + zz * zz);
		int xpol, ypol, zpol;
		if (xx > 0.0)
			xpol = 1;
		else
			xpol = -1;
		if (yy > 0.0)
			ypol = 1;
		else
			ypol = -1;
		if (zz > 0.0)
			zpol = 1;
		else
			zpol = -1;
		xx = Math.abs(xx) / dd;
		yy = Math.abs(yy) / dd;
		zz = Math.abs(zz) / dd;
		double dx = 0.0;
		double dy = 0.0;
		double dz = 0.0;
		// grow branch out
		int ii = i;
		int jj = j;
		int kk = k;
		double ll;
		boolean chg;
		for (ll = len; ll > 0; ll -= 1.0) {
			// grow towards branch direction
			chg = false;
			dx += xx;
			if (dx >= 1.0) {
				ii += xpol;
				dx -= 1.0;
				chg = true;
			}
			dy += yy;
			if (dy >= 1.0) {
				jj += ypol;
				dy -= 1.0;
				chg = true;
			}
			dz += zz;
			if (dz >= 1.0) {
				kk += zpol;
				dz -= 1.0;
				chg = true;
			}
			// set new block or stop growing branch
			if (chg == true) {
				BlockAndMeta id = this.getBlock(ii, jj, kk);
				if (id.isAir() || id.areEqual(wood, leaf)) {
					this.setBlockAndMetadata(ii, jj, kk, wood);
				} else {
					break;
				}
			}
			// possibly grow a branch out of the branch
			// Change possibility
			if (rand.nextInt(100) < subbranchdensity && len >= subbranchingsize
					&& ll < len * subbranchinglength) {
				// parameters
				if (rand.nextInt(10) < 5) {
					treeBranch(ii, jj, kk, dir + subbranchangle, ll
							* subbranchsize, yy, crv);
				} else {
					treeBranch(ii, jj, kk, dir - subbranchangle, ll
							* subbranchsize, yy, crv);
				}
			}
			up += crv;
			xx = Math.cos(dir);
			yy = up;
			zz = Math.sin(dir);
			dd = Math.sqrt(xx * xx + yy * yy + zz * zz);
			if (xx > 0.0)
				xpol = 1;
			else
				xpol = -1;
			if (yy > 0.0)
				ypol = 1;
			else
				ypol = -1;
			if (zz > 0.0)
				zpol = 1;
			else
				zpol = -1;
			xx = Math.abs(xx) / dd;
			yy = Math.abs(yy) / dd;
			zz = Math.abs(zz) / dd;
		}
		// put leaves on branch ends
		// parameterize this /2.0?
		if (ll <= len / 2.0 && leafrad > 0) {
			int klen = (int) (len / 2.0);
			if (klen >= leafrad)
				treeLeaf(ii, jj, kk, leafrad);
			else if (klen >= 1)
				treeLeaf(ii, jj, kk, klen);
			else
				treeLeaf(ii, jj, kk, 1);
		}

	}

	void treeLeaf(int i, int j, int k, int r) {
		BlockAndMeta zz;
		if (r <= 0)
			return;
		// boolean d
		if (r <= 3) {
			for (int ii = -r - 1; ii <= r + 1; ii++) {
				for (int jj = -2; jj <= r + 1; jj++) {
					for (int kk = -r - 1; kk <= r + 1; kk++) {
						zz = this.getBlock(i + ii, j + jj, k + kk);
						if (zz.isAir())
							check[ii + 4][jj + 4][kk + 4] = 2;
						else if (zz.areEqual(leaf))
							check[ii + 4][jj + 4][kk + 4] = 1;
						else
							check[ii + 4][jj + 4][kk + 4] = 0;
					}
				}
			}
			leafShell(i, j, k, 1);
			if (r >= 2)
				leafShell(i, j, k, 2);
			if (r == 3)
				leafShell(i, j, k, 3);
		} else {
			int rr = r * r;
			for (int ii = -r; ii <= r; ii++) {
				for (int jj = -r / 2; jj <= r; jj++) {
					for (int kk = -r; kk <= r; kk++) {
						if (ii * ii + jj * jj + kk * kk <= rr) {
							if (this.getBlock(i + ii, j + jj, k + kk).isAir()) {
								if (rand.nextInt(3) == 0)
									this.setBlockAndMetadata(i + ii, j + jj, k + kk, leaf);
							}
						}
					}
				}
			}
		}
	}

	void leafShell(int i, int j, int k, int r) {
		int rr = r * r + 1; // The +1 fixes odd looking leaf poofs.
		int rrr = (r - 1) * (r - 1);
		int zz;
		for (int ii = 0; ii <= r; ii++) {
			for (int jj = 0; jj <= r; jj++) {
				for (int kk = 0; kk <= r; kk++) {
					zz = ii * ii + jj * jj + kk * kk;
					if (zz <= rr && zz > rrr) {
						plotLeaf(i, j, k, ii, jj, kk, r);
						plotLeaf(i, j, k, ii, jj, -kk, r);
						plotLeaf(i, j, k, -ii, jj, kk, r);
						plotLeaf(i, j, k, -ii, jj, -kk, r);
						if (jj < 1) {
							plotLeaf(i, j, k, ii, -jj, kk, r);
							plotLeaf(i, j, k, ii, -jj, -kk, r);
							plotLeaf(i, j, k, -ii, -jj, kk, r);
							plotLeaf(i, j, k, -ii, -jj, -kk, r);
						} else if (jj == 1) {
							if (rand.nextInt(3) > 0)
								plotLeaf(i, j, k, ii, -jj, kk, r);
							if (rand.nextInt(3) > 0)
								plotLeaf(i, j, k, ii, -jj, -kk, r);
							if (rand.nextInt(3) > 0)
								plotLeaf(i, j, k, -ii, -jj, kk, r);
							if (rand.nextInt(3) > 0)
								plotLeaf(i, j, k, -ii, -jj, -kk, r);
						}
					}
				}
			}
		}
	}

	void plotLeaf(int i, int j, int k, int ii, int jj, int kk, int r) {
		if (check[ii + 4][jj + 4][kk + 4] != 2)
			return;
		boolean flag = false;
		if (r == 1) {
			flag = true;
		} else {
			if (check[ii + 3][jj + 4][kk + 4] == 1)
				flag = true;
			if (check[ii + 5][jj + 4][kk + 4] == 1)
				flag = true;
			if (check[ii + 4][jj + 3][kk + 4] == 1)
				flag = true;
			if (check[ii + 4][jj + 5][kk + 4] == 1)
				flag = true;
			if (check[ii + 4][jj + 4][kk + 3] == 1)
				flag = true;
			if (check[ii + 4][jj + 4][kk + 5] == 1)
				flag = true;

			if (check[ii + 3][jj + 3][kk + 4] == 1)
				flag = true;
			if (check[ii + 3][jj + 5][kk + 4] == 1)
				flag = true;
			if (check[ii + 3][jj + 4][kk + 3] == 1)
				flag = true;
			if (check[ii + 3][jj + 4][kk + 5] == 1)
				flag = true;
			if (check[ii + 5][jj + 3][kk + 4] == 1)
				flag = true;
			if (check[ii + 5][jj + 5][kk + 4] == 1)
				flag = true;
			if (check[ii + 5][jj + 4][kk + 3] == 1)
				flag = true;
			if (check[ii + 5][jj + 4][kk + 5] == 1)
				flag = true;
			if (check[ii + 4][jj + 3][kk + 3] == 1)
				flag = true;
			if (check[ii + 4][jj + 3][kk + 5] == 1)
				flag = true;
			if (check[ii + 4][jj + 5][kk + 3] == 1)
				flag = true;
			if (check[ii + 4][jj + 5][kk + 5] == 1)
				flag = true;

		}
		if (flag) {
			this.setBlockAndMetadata(i + ii, j + jj, k + kk, leaf);
			check[ii + 4][jj + 4][kk + 4] = 1;
		}
	}

	/*
	 * boolean plotLeaf(int i, int j, int k){ int zz = this.getBlockId(i, j, k);
	 * if(zz == 0 || zz == KTreeCfg.tallWoodType || zz ==
	 * KTreeCfg.tallLeafType){ this.setBlockAndMetadata(i, j, k,
	 * KTreeCfg.tallLeafType, leafMeta); return true; } return false; }
	 */

}
