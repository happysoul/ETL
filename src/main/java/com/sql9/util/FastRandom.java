package com.sql9.util;

/* compiled from: Unknown Source */
public final class FastRandom {
    private static FastRandom _$2 = new FastRandom();
    private static Object _$3 = new Object();
    private static int _$4 = ((int) System.currentTimeMillis());
    private int _$1;

    public static FastRandom getSharedInstance() {
        return _$2;
    }

    public FastRandom() {
        int seed;
        synchronized (_$3) {
            seed = _$4 + 1;
            _$4 = seed;
        }
        setSeed(seed);
    }

    public FastRandom(int seed) {
        setSeed(seed);
    }

    public void setSeed(int seed) {
        this._$1 = Math.max(1, Math.abs(seed));
    }

    public final int nextInt() {
        int i = (int) ((16807 * ((long) this._$1)) % 2147483647L);
        this._$1 = i;
        return i;
    }

    public final int nextInt(int min, int max) {
        return (nextInt() % ((max + 1) - min)) + min;
    }
}
