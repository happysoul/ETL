package com.sql9.util;

public final class FastRandom {

	private static FastRandom random = new FastRandom();
	private static Object lock = new Object();
	private static int currentTimeMillis = ((int) System.currentTimeMillis());
	private int seed;

	public static FastRandom getSharedInstance() {
		return random;
	}

	public FastRandom() {
		int seed;
		synchronized (lock) {
			seed = currentTimeMillis + 1;
			currentTimeMillis = seed;
		}
		setSeed(seed);
	}

	public FastRandom(int seed) {
		setSeed(seed);
	}

	public void setSeed(int seed) {
		this.seed = Math.max(1, Math.abs(seed));
	}

	public final int nextInt() {
		int i = (int) ((16807 * ((long) this.seed)) % 2147483647L);
		this.seed = i;
		return i;
	}

	public final int nextInt(int min, int max) {
		return (nextInt() % ((max + 1) - min)) + min;
	}
}
