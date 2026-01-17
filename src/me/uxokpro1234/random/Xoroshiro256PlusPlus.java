package me.uxokpro1234.random;

public final class Xoroshiro256PlusPlus {
    public long s0;
    public long s1;
    public long s2;
    public long s3;

    public Xoroshiro256PlusPlus(long seed) {
        SplitMix64 sm = new SplitMix64(seed);
        s0 = sm.nextLong();
        s1 = sm.nextLong();
        s2 = sm.nextLong();
        s3 = sm.nextLong();
    }

    public long nextLong() {
        long result = Long.rotateLeft(s0 + s3, 23) + s0;

        long t = s1 << 17;

        s2 ^= s0;
        s3 ^= s1;
        s1 ^= s2;
        s0 ^= s3;

        s2 ^= t;
        s3 = Long.rotateLeft(s3, 45);

        return result;
    }
}