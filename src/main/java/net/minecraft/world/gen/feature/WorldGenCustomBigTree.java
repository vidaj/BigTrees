package net.minecraft.world.gen.feature;

import java.util.Random;

import cpw.mods.fml.common.FMLLog;
import karob.bigtrees.KTreeCfg;
import karob.bigtrees.config.ITreeConfigurable;
import karob.bigtrees.config.TreeConfiguration;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenCustomBigTree extends WorldGenBigTree implements
		ITreeConfigurable {

	private boolean leafNodeListGenerationAborted;

	private TreeConfiguration treeConfiguration;

	private int rootRand;
	private int rootAlt;
	private int tapRootRand;

	protected int type;

	public WorldGenCustomBigTree(boolean doBlockNotify) {
		super(doBlockNotify);
	}

	public void setTreeConfiguration(TreeConfiguration treeConfiguration) {
		this.treeConfiguration = treeConfiguration;
	}

	@Override
	void generateLeafNodeList() {

		if (basePos[1] + heightLimit > 256 - 4) {
			height = height / 2;
			heightLimit = heightLimit / 2;

			if (height >= heightLimit) {
				height = heightLimit - 1;
			}

			if (height < 1 || basePos[1] + heightLimit > 256 - 4) {
				leafNodeListGenerationAborted = true;
			}
		}

		if (!leafNodeListGenerationAborted) {
			super.generateLeafNodeList();
		}
	}

	@Override
	float layerSize(int i) {
		if (trunkSize == 0) {
			// 100% branch density
			return heightLimit - rand.nextFloat();
		} else if (trunkSize == 3) {
			// 100% branch density
			// if(field_881_b.nextFloat() > 1.0F) return -1.618F;
		} else if (trunkSize > 1) {
			// 70% branch density
			if (rand.nextFloat() > 0.70F)
				return -1.618F;
		}
		// Branch tips have to be at least 30% up the tree.
		if (trunkSize == 3) {
			if ((double) i < (double) (float) heightLimit * 0.2D) {
				return -1.618F;
			}
		}
		if (trunkSize < 3) {
			if ((double) i < (double) (float) heightLimit * 0.3D) {
				return -1.618F;
			}
		}
		if (trunkSize == 4) {
			if ((double) i < (double) (float) heightLimit * 0.16D) {
				return -1.618F;
			}
		}

		return super.layerSize(i);
	}

	@Override
	void generateTrunk() {
		// int qq = 17;
		// int qr = 0;
		// if(trunkSize == 3){
		// qr = 1;
		// }
		int i = basePos[0];
		int j = basePos[1];
		// if(trunkSize >= 1) j = j - 1;
		// if(trunkSize > 3) j = j - 1;
		// if(trunksize == 2) j = j + 1;
		int k = basePos[1] + height + 2;
		int l = basePos[2];
		int ai[] = { i, j, l };
		int ai1[] = { i, k, l };
		// Create various trunk sizes.
		/*
		 * placeBlockLine(ai, ai1, 17); if(trunkSize == 2) { ai[0]++; ai1[0]++;
		 * placeBlockLine(ai, ai1, 17); ai[2]++; ai1[2]++; placeBlockLine(ai,
		 * ai1, 17); ai[0]--; ai1[0]--; placeBlockLine(ai, ai1, 17); }
		 */
		if (trunkSize == 1) {
			placeBlockLine(ai, ai1);
		}
		if (trunkSize == 2) {
			rootAlt = 0;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1] - 2, ai[2], 5.0 / 8.0, -1.0 / 16.0);
			rootAlt = 1;
			growRoot(ai[0], ai[1], ai[2], 5.7 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]++;
			ai1[0]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1], ai[2], 6.3 / 8.0, -1.0 / 16.0);
			growRoot(ai[0], ai[1] - 2, ai[2], 7.0 / 8.0, -1.0 / 16.0);
			rootAlt = 1;
			growRoot(ai[0], ai[1], ai[2], 7.7 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[2]++;
			ai1[2]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1], ai[2], 0.3 / 8.0, -1.0 / 16.0);
			growRoot(ai[0], ai[1] - 2, ai[2], 1.0 / 8.0, -1.0 / 16.0);
			rootAlt = 1;
			growRoot(ai[0], ai[1], ai[2], 1.7 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]--;
			ai1[0]--;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1], ai[2], 2.3 / 8.0, -1.0 / 16.0);
			growRoot(ai[0], ai[1] - 2, ai[2], 3.0 / 8.0, -1.0 / 16.0);
			rootAlt = 1;
			growRoot(ai[0], ai[1], ai[2], 3.7 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			growRoot(ai[0], ai[1], ai[2] - 1, 4.3 / 8.0, -1.0 / 16.0);
		}
		if (trunkSize == 3) {
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			placeBlockLine(ai, ai1);
			/*
			 * ai[0]++; ai1[0]++; placeBlockLine(ai, ai1); ai[2]++; ai1[2]++;
			 * placeBlockLine(ai, ai1); ai[0]--; ai1[0]--; placeBlockLine(ai,
			 * ai1);
			 */
			ai[0]++;
			ai1[0]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			placeBlockLine(ai, ai1);
			ai[2]++;
			ai1[2]++;
			ai[0]--;
			ai1[0]--;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			placeBlockLine(ai, ai1);
			ai[2]--;
			ai1[2]--;
			ai[0]--;
			ai1[0]--;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			placeBlockLine(ai, ai1);
			ai[2]--;
			ai1[2]--;
			ai[0]++;
			ai1[0]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			placeBlockLine(ai, ai1);
		}
		if (trunkSize == 4) {
			rootAlt = 10;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1] + 1, ai[2], 5.0 / 8.0, -1.0 / 16.0);
			/*
			 * growRoot(ai[0],ai[1],ai[2],4.3/8.0,-1.0/16.0);
			 * growRoot(ai[0],ai[1],ai[2],5.7/8.0,-1.0/16.0); placeBlockLine(ai,
			 * ai1);
			 */ai[0]++;
			ai1[0]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1] + 1, ai[2], 7.0 / 8.0, -1.0 / 16.0);
			/*
			 * growRoot(ai[0],ai[1],ai[2],6.3/8.0,-1.0/16.0);
			 * growRoot(ai[0],ai[1],ai[2],7.7/8.0,-1.0/16.0); placeBlockLine(ai,
			 * ai1);
			 */ai[2]++;
			ai1[2]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1] + 1, ai[2], 1.0 / 8.0, -1.0 / 16.0);
			/*
			 * growRoot(ai[0],ai[1],ai[2],0.3/8.0,-1.0/16.0);
			 * growRoot(ai[0],ai[1],ai[2],1.7/8.0,-1.0/16.0); placeBlockLine(ai,
			 * ai1);
			 */ai[0]--;
			ai1[0]--;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1] + 1, ai[2], 3.0 / 8.0, -1.0 / 16.0);
			/*
			 * growRoot(ai[0],ai[1],ai[2],2.3/8.0,-1.0/16.0);
			 * growRoot(ai[0],ai[1],ai[2],3.7/8.0,-1.0/16.0); placeBlockLine(ai,
			 * ai1);
			 */
			ai[0]--;
			ai1[0]--;
			ai[2]--;
			ai1[2]--;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 4.4 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]++;
			ai1[0]++;
			ai[2]--;
			ai1[2]--;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 5.6 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]++;
			ai1[0]++;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 6.4 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]++;
			ai1[0]++;
			ai[2]++;
			ai1[2]++;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 7.6 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[2]++;
			ai1[2]++;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 0.4 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]--;
			ai1[0]--;
			ai[2]++;
			ai1[2]++;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 1.6 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]--;
			ai1[0]--;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 2.4 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]--;
			ai1[0]--;
			ai[2]--;
			ai1[2]--;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 3.6 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
		}
	}

	private void placeBlockLine(int[] ai, int[] ai1) {
		func_150530_a(ai, ai1, treeConfiguration.getWood());
	}

	void growTapRoot(int i, int j, int k, double flen) {
		if (KTreeCfg.rootsEnable == false)
			return;
		// int len = 2 + (3*trunkSize) + rand.nextInt(2);
		int med;
		int len = (int) ((6.0 + rand.nextFloat() * 6.0) * flen);
		if (len == tapRootRand || len == tapRootRand + 1
				|| len == tapRootRand - 1) {
			len = (int) ((6.0 + rand.nextFloat() * 6.0) * flen);
		}
		for (int jj = 1; jj <= len; jj++) {
			med = getMedium(i, j - jj, k);
			if (med == 1) {
				len -= 1;
			} else if (med == 0) {
				len = Math.min(len, jj - 1);
				break;
			}
		}
		tapRootRand = len;
		for (int jj = 1; jj <= len; jj++) {
			// zz = world.getBlockId(ai[0], ai[1] - jj, ai[2]);
			// if(zz != 0 && zz != trunkBlock && zz != trunkMeta && zz != 2 &&
			// zz != 3 && zz != 8 && zz != 9 && zz != 12 && zz != 13) break;
			// else
			setBlockAndNotifyAdequately(worldObj, i, j - jj, k,
					treeConfiguration.getWood(),
					treeConfiguration.getWoodMeta());
		}
	}

	void growRoot(int l, int m, int n, double theta, double phi) {
		if (KTreeCfg.rootsEnable == false)
			return;
		/*
		 * int rr = rand.nextInt(3); if(rootRand == 0){ m -= rr; rootRand = rr;
		 * }else{ rootRand = 0; }
		 */
		/*
		 * switch(rootRand){ case 1: rootRand = 2; break; case 2: m -= 1;
		 * rootRand = 3; break; case 3: //m -= 2; rootRand = 4; break; case 4: m
		 * -= 1; rootRand = 0; break; default: rootRand = 0; }
		 */
		/*
		 * rootRand ++; if(rootRand > 5){ rootAlt = rand.nextInt(2); rootRand =
		 * 0; }else{ if(rootAlt == 1){ m --; rootAlt = 0; }else{ rootAlt = 1; }
		 * }
		 */
		if (rootAlt == 1) {
			rootRand = rand.nextInt(2);
			m -= rootRand;
			rootAlt = 2;
		} else if (rootAlt == 2) {
			if (rootRand == 0)
				m -= 1;
			rootAlt = 0;
		} else if (rootAlt == 10) {
			m -= rand.nextInt(2);
		}
		m += 1;
		phi -= (double) rand.nextFloat() * 0.05;
		theta += (double) rand.nextFloat() * 0.1 - 0.05;
		double direction = (2.0 * Math.PI) * theta;
		double curl = rand.nextFloat() * 0.4F - 0.2F;
		double pitch = (2.0 * Math.PI) * phi;
		int length = 2 + (3 * trunkSize) + rand.nextInt(2);
		double x, y, z;
		if (l > 0)
			x = (double) l + 0.5;
		else
			x = (double) l - 0.5;
		// double y = (double)basePos[1] + 0.5;
		y = (double) m + 0.5;
		if (n > 0)
			z = (double) n + 0.5;
		else
			z = (double) n - 0.5;
		double x2, y2, z2, hoz;
		int i = (int) x;
		int j = (int) y;
		int k = (int) z;
		int i2, j2, k2, di, dk;
		int med = getMedium(i, j, k); // Check the "Medium" of a block for root
										// growing - solid, open, or forbidden.
		int cnt = 0;
		while (length > 0.0) {
			length--;
			// direction = direction + curl;
			curl = curl + rand.nextFloat() * 0.06F - 0.03F;
			if (med == 1) { // Root growing in openness.
				pitch = (pitch + Math.PI / 2.0) * 0.7 - Math.PI / 2.0;
				// if(pitch > 0.0){
				// pitch = pitch - 10.0*Math.PI/180.0;
				// }else{
				// pitch = (pitch + Math.PI/2.0)*0.7 - Math.PI/2.0;
				// }
			} else { // Root growing in solid.
				pitch = (pitch + Math.PI / 2.0) * 0.9 - Math.PI / 2.0;
			}

			hoz = Math.cos(pitch);
			x2 = x + Math.cos(direction) * hoz;
			y2 = y + Math.sin(pitch);
			z2 = z + Math.sin(direction) * hoz;
			i2 = (int) x2;
			j2 = (int) y2;
			k2 = (int) z2;
			if (i2 != i || j2 != j || k2 != k) {
				setBlockAndNotifyAdequately(worldObj, i, j, k,
						treeConfiguration.getWood(),
						treeConfiguration.getWoodMeta());
				cnt++;
				if (cnt < 4) {
					if (j2 != j - 1 || i2 != i || k2 != k)
						setBlockAndNotifyAdequately(worldObj, i, j - 1, k,
								treeConfiguration.getWood(),
								treeConfiguration.getWoodMeta());
				}
				med = getMedium(i2, j2, k2);
				if (med != 0) { // Grow normal.
					x = x2;
					y = y2;
					z = z2;
					i = i2;
					j = j2;
					k = k2;
				} else { // Try to grow down now.
					med = getMedium(i, j - 1, k);
					if (med != 0) { // Grow down.
						y = y - 1.0;
						j = j - 1;
						pitch = -Math.PI / 2.0;
					} else { // Try to grow out now.
						x2 = x + Math.cos(direction);
						z2 = z + Math.sin(direction);
						i2 = (int) x2;
						k2 = (int) z2;
						med = getMedium(i2, j, k2);
						if (med != 0) { // Grow out.
							x = x2;
							z = z2;
							i = i2;
							k = k2;
							pitch = 0.0;
						} else { // Try bending now.
							int dir = ((int) (direction * 8.0 / Math.PI)); // Integer
																			// direction
																			// -
																			// 16
																			// =
																			// complete
																			// rotation.
							if (dir < 0)
								dir = 15 - (15 - dir) % 16;
							else
								dir = dir % 16;
							int pol = dir % 2; // 'Polarity' of bending root -
												// preferred bending direction.
							di = i2 - i;
							dk = k2 - k;
							int[] tdir = { 0, 0, 0, 0 }; // Testing directions.
							if (di == 0 && dk == 0) {
								if (dir < 1) {
									di = 1;
									dk = 0;
								} else if (dir < 3) {
									di = 1;
									dk = 1;
								} else if (dir < 5) {
									di = 0;
									dk = 1;
								} else if (dir < 7) {
									di = -1;
									dk = 1;
								} else if (dir < 9) {
									di = -1;
									dk = 0;
								} else if (dir < 11) {
									di = -1;
									dk = -1;
								} else if (dir < 13) {
									di = 0;
									dk = -1;
								} else if (dir < 15) {
									di = 1;
									dk = -1;
								} else {
									di = 1;
									dk = 0;
								}
							}
							if (dk == 0) {
								if (di > 0) {
									if (pol == 1) {
										tdir[0] = 2;
										tdir[1] = 14;
										tdir[2] = 4;
										tdir[3] = 12;
									} else {
										tdir[0] = 14;
										tdir[1] = 2;
										tdir[2] = 12;
										tdir[3] = 4;
									}
								} else {
									if (pol == 1) {
										tdir[0] = 6;
										tdir[1] = 10;
										tdir[2] = 4;
										tdir[3] = 12;
									} else {
										tdir[0] = 10;
										tdir[1] = 6;
										tdir[2] = 12;
										tdir[3] = 4;
									}
								}
							} else if (di == 0) {
								if (dk > 0) {
									if (pol == 1) {
										tdir[0] = 2;
										tdir[1] = 6;
										tdir[2] = 0;
										tdir[3] = 8;
									} else {
										tdir[0] = 6;
										tdir[1] = 2;
										tdir[2] = 8;
										tdir[3] = 0;
									}
								} else {
									if (pol == 1) {
										tdir[0] = 10;
										tdir[1] = 14;
										tdir[2] = 8;
										tdir[3] = 0;
									} else {
										tdir[0] = 14;
										tdir[1] = 10;
										tdir[2] = 0;
										tdir[3] = 8;
									}
								}
							} else if (dk > 0) {
								if (di > 0) {
									if (pol == 1) {
										tdir[0] = 0;
										tdir[1] = 4;
										tdir[2] = 14;
										tdir[3] = 6;
									} else {
										tdir[0] = 4;
										tdir[1] = 0;
										tdir[2] = 6;
										tdir[3] = 14;
									}
								} else {
									if (pol == 1) {
										tdir[0] = 4;
										tdir[1] = 8;
										tdir[2] = 2;
										tdir[3] = 10;
									} else {
										tdir[0] = 8;
										tdir[1] = 4;
										tdir[2] = 10;
										tdir[3] = 2;
									}
								}
							} else {
								if (di > 0) {
									if (pol == 1) {
										tdir[0] = 12;
										tdir[1] = 0;
										tdir[2] = 10;
										tdir[3] = 2;
									} else {
										tdir[0] = 0;
										tdir[1] = 12;
										tdir[2] = 2;
										tdir[3] = 10;
									}
								} else {
									if (pol == 1) {
										tdir[0] = 8;
										tdir[1] = 12;
										tdir[2] = 6;
										tdir[3] = 14;
									} else {
										tdir[0] = 12;
										tdir[1] = 8;
										tdir[2] = 14;
										tdir[3] = 6;
									}
								}
							}
							for (int q = 0; q < 4; q++) {
								if (tdir[q] == 0) {
									di = 1;
									dk = 0;
								} else if (tdir[q] == 2) {
									di = 1;
									dk = 1;
								} else if (tdir[q] == 4) {
									di = 0;
									dk = 1;
								} else if (tdir[q] == 6) {
									di = -1;
									dk = 1;
								} else if (tdir[q] == 8) {
									di = -1;
									dk = 0;
								} else if (tdir[q] == 10) {
									di = -1;
									dk = -1;
								} else if (tdir[q] == 12) {
									di = 0;
									dk = -1;
								} else {
									di = 1;
									dk = -1;
								}
								i2 = i + di;
								k2 = k + dk;
								med = getMedium(i2, j, k2);
								if (med != 0) {
									i = i2;
									k = k2;
									x = (double) i + 0.5;
									z = (double) k + 0.5;
									pitch = 0;
									direction = (double) tdir[q] * 2.0
											* Math.PI / 16.0;
									break;
								}
							}
							if (med == 0)
								return; // Root cannot grow any further.
						}
					}
				}
			}
		}
		// while(direction < 0.0){direction = direction + 2.0*Math.PI;}
		// while(direction > 2.0*Math.PI){direction = direction - 2.0*Math.PI;}
	}

	private int getMedium(int i, int j, int k) {
		// Roots can grow through the following block types.
		Block canGrowOpen[] = { Blocks.air, Blocks.sapling,
				Blocks.flowing_water, Blocks.water, Blocks.flowing_lava,
				Blocks.lava, Blocks.log, Blocks.log2, Blocks.leaves,
				Blocks.leaves2 };// more to be re-added
		Block canGrowSolid[] = { Blocks.grass, Blocks.dirt, Blocks.sand,
				Blocks.gravel }; // more to be re-added
		Block qq = this.getBlock(i, j, k);
		int medium = 0;
		for (int m = 0; m < canGrowOpen.length; m++) {
			if (qq == canGrowOpen[m]) {
				medium = 1;
				break;
			}
		}
		if (medium == 0) {
			for (int m = 0; m < canGrowSolid.length; m++) {
				if (qq == canGrowSolid[m]) {
					medium = 2;
					break;
				}
			}
		}
		return medium;
	}

	Block getBlock(int par1, int par2, int par3) {
		try {
			return worldObj.getBlock(par1, par2, par3);
		} catch (RuntimeException e) {
			FMLLog.severe("[BigTrees][Error][getBlock]" + e.getMessage());
			return null;
		}
	}

	@Override
	boolean validTreeLocation() {
		Block block = getBlock(basePos[0], basePos[1] - 1, basePos[2]);

		return block.equals(treeConfiguration.getBaseBlock1())
				|| block.equals(treeConfiguration.getBaseBlock2());
	}

	@Override
	public void setScale(double d, double d1, double d2) {
		heightLimitLimit = (int) (d * 12D);
		/*
		 * if(d > 0.5D) { leafDistanceLimit = 5; } field_873_j = d1; field_872_k
		 * = d2;
		 */}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		worldObj = world;
		long l = random.nextLong();
		rand.setSeed(l);
		basePos[0] = i;
		basePos[1] = j;
		basePos[2] = k;

		if (!validTreeLocation()) {
			worldObj = null;
			return false;
		}

		int qq;
		boolean qbirch = false;
		/*
		 * if(heightLimit == 0) { heightLimit = 5 +
		 * rand.nextInt(heightLimitLimit); }
		 */
		if (type == 1)
			qq = rand.nextInt(8) + 86; // Dead desert trees.
		else if (type == 2)
			qq = 0; // Big swamp trees.
		else if (type == 3)
			qq = 0; // Great oak trees.
		else if (type == 4)
			qq = 30; // Large pine trees.
		else if (type == 5 || type == 6)
			qq = 80; // Block oak trees/ birch.
		else
			qq = rand.nextInt(94); // kam - sponge plant is turned off
		if (type == 6)
			qbirch = true;
		if (type == 11) {
			type = 1;
			qq = 87;
		}
		// qq=89;

		int heightmin = treeConfiguration.getMinHeight();
		int heightmax = treeConfiguration.getMaxHeight();
		int[] heightvector = { heightmin, heightmax - heightmin };
		heightLimit = KTreeCfg.vary(rand, heightvector);
		if (qq < 8) {
			// WIDE TREE
			// heightLimit = KTreeCfg.vary(rand,oak2Height); //Tree Height
			heightAttenuation = 0.1D; // Trunk Percentage Height
			scaleWidth = 1.4D; // Branch Length
			trunkSize = 4; // Trunk Width
			heightLimitLimit = 4; // Height Variation
			leafDistanceLimit = 4; // Leaf Thickness
			// trunkBlock = woodBlock;
			// trunkMeta = woodMeta;
			// leafBlock = leafBlock;
			// leafMeta = leafMeta;
		} else if (qq < 40) {
			// TALL TREE
			// heightLimit = KTreeCfg.vary(rand,KTreeCfg.pine1Height); //Tree
			// Height
			heightAttenuation = 0.3D; // Trunk Percentage Height
			scaleWidth = 1.2D; // Branch Length
			trunkSize = 3; // Trunk Width
			heightLimitLimit = 3; // Height Variation
			leafDistanceLimit = 4; // Leaf Thickness
			// trunkBlock = Block.getBlockById(KTreeCfg.pine1WoodType);
			// trunkMeta = KTreeCfg.pine1WoodMeta;
			// leafBlock = Block.getBlockById(KTreeCfg.pine1LeafType);
			// leafMeta = KTreeCfg.pine1LeafMeta;
		} else if (qq < 90) {
			// BIGGER TREE
			// heightLimit = KTreeCfg.vary(rand,KTreeCfg.oak1Height); //Tree
			// Height
			heightAttenuation = 0.3D; // Trunk Percentage Height
			scaleWidth = 1.0D; // Branch Length
			trunkSize = 2; // Trunk Width
			heightLimitLimit = 3; // Height Variation
			leafDistanceLimit = 4; // Leaf Thickness
			// trunkBlock = Block.getBlockById(KTreeCfg.oak1WoodType);
			// trunkMeta = KTreeCfg.oak1WoodMeta;
			// leafBlock = Block.getBlockById(KTreeCfg.oak1LeafType);
			// leafMeta = KTreeCfg.oak1LeafMeta;
			if (qbirch) {
				heightLimit = KTreeCfg.vary(rand, KTreeCfg.birchHeight);
				// trunkBlock = Block.getBlockById(KTreeCfg.birchWoodType);
				// trunkMeta = KTreeCfg.birchWoodMeta;
				// leafBlock = Block.getBlockById(KTreeCfg.birchLeafType);
				// leafMeta = KTreeCfg.birchLeafMeta;
			}
		} else if (qq < 95) {
			// DEAD TREE
			// heightLimit = KTreeCfg.vary(rand,KTreeCfg.stubHeight); //Tree
			// Height
			heightAttenuation = 0.3D; // Trunk Percentage Height
			scaleWidth = 1.0D; // Branch Length
			trunkSize = 2; // Trunk Width
			heightLimitLimit = 3; // Height Variation
			leafDistanceLimit = 0; // Leaf Thickness
			// trunkBlock = Block.getBlockById(KTreeCfg.stubWoodType);
			// trunkMeta = KTreeCfg.stubWoodMeta;
		} else {
			// VINEY FUNGUS
			heightLimit = 5; // Tree Height
			heightAttenuation = 0.1D; // Trunk Percentage Height
			scaleWidth = 2.0D; // Branch Length
			trunkSize = 0; // Trunk Width
			heightLimitLimit = 0; // Height Variation
			leafDistanceLimit = 0; // Leaf Thickness
			// trunkBlock = Blocks.sponge; //Sponge
			// leafBlock = Blocks.sponge;
		}
		if (type == 1) {
			if (trunkSize != 1) {
				scaleWidth = scaleWidth * 1.0D; // Double branch length on
												// desert trees.
				// heightLimit = KTreeCfg.vary(rand,KTreeCfg.deadHeight); //Tree
				// Height
			}
			leafDistanceLimit = 0; // No leaves on desert trees.
			// trunkBlock = Block.getBlockById(KTreeCfg.deadWoodType);
			// trunkMeta = KTreeCfg.deadWoodMeta;
		} else if (type == 2) {
			// heightLimit = KTreeCfg.vary(rand,KTreeCfg.swoakHeight); //Tree
			// Height
			heightAttenuation = 0.0D; // Lower branches on swamp trees.
			// trunkBlock = Block.getBlockById(KTreeCfg.swoakWoodType);
			// trunkMeta = KTreeCfg.swoakWoodMeta;
			// leafBlock = Block.getBlockById(KTreeCfg.swoakLeafType);
			// leafMeta = KTreeCfg.swoakLeafMeta;
		}
		if (heightLimitLimit > 0)
			heightLimit = heightLimit + rand.nextInt(heightLimitLimit * 2)
					- heightLimitLimit;
		if (!validTreeLocation()) {
			return false;
		} else {
			rootRand = rand.nextInt(4);
			generateLeafNodeList(); // Generate tree and branch arrays.
			if (leafNodeListGenerationAborted) {
				worldObj = null;
				return false;
			}
			// world.lightUpdates = false;
			generateLeaves(); // Grow leaves from branches.
			generateTrunk(); // Add trunk blocks to world.
			generateLeafNodeBases(); // Add branch blocks to world.
			// world.lightUpdates = true;
			return true;
		}
	}

	@Override
	protected void setBlockAndNotifyAdequately(World world, int x, int y,
			int z, Block block, int blockMeta) {
		if (block.isLeaves(world, x, y, z)) {
			block = treeConfiguration.getLeaf();
			blockMeta = treeConfiguration.getLeafMeta();
		} else if (block.isWood(world, x, y, z)) {
			block = treeConfiguration.getWood();
			blockMeta = treeConfiguration.getWoodMeta();
		}

		super.setBlockAndNotifyAdequately(world, x, y, z, block, blockMeta);
	}
}
