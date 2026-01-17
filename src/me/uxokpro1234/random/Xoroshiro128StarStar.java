package me.uxokpro1234.random;

public final class Xoroshiro128StarStar {
    public long s0;
    public long s1;

    public Xoroshiro128StarStar(long seed) {
        SplitMix64 sm = new SplitMix64(seed);
        s0 = sm.nextLong();
        s1 = sm.nextLong();
    }

    public long nextLong() {
        long result = Long.rotateLeft(s0 * 5, 7) * 9;

        long t = s1 ^ s0;
        s0 = Long.rotateLeft(s0, 24) ^ t ^ (t << 16);
        s1 = Long.rotateLeft(t, 37);

        return result;
    }
}