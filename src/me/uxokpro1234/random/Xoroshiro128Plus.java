package me.uxokpro1234.random;

public final class Xoroshiro128Plus {
    public long s0;
    public long s1;

    public Xoroshiro128Plus(long seed) {
        SplitMix64 sm = new SplitMix64(seed);
        s0 = sm.nextLong();
        s1 = sm.nextLong();
    }

    public long nextLong() {
        long result = s0 + s1;

        long s1Copy = s1 ^ s0;
        s0 = Long.rotateLeft(s0, 55) ^ s1Copy ^ (s1Copy << 14);
        s1 = Long.rotateLeft(s1Copy, 36);

        return result;
    }
}