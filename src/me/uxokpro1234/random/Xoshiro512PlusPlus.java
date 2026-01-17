package me.uxokpro1234.random;

public final class Xoshiro512PlusPlus {
    private final long[] s = new long[8];

    public Xoshiro512PlusPlus(long seed) {
        SplitMix64 sm = new SplitMix64(seed);
        for (int i = 0; i < 8; i++) {
            s[i] = sm.nextLong();
        }
    }

    public long nextLong() {
        long result = Long.rotateLeft(s[0] + s[2], 17) + s[2];

        long t = s[1] << 11;

        s[2] ^= s[0];
        s[5] ^= s[1];
        s[1] ^= s[2];
        s[7] ^= s[3];
        s[3] ^= s[4];
        s[4] ^= s[5];
        s[0] ^= s[6];
        s[6] ^= s[7];

        s[6] ^= t;
        s[7] = Long.rotateLeft(s[7], 21);

        return result;
    }
}