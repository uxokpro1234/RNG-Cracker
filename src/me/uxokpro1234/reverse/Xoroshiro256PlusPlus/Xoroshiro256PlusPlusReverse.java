package me.uxokpro1234.reverse.Xoroshiro256PlusPlus;

import me.uxokpro1234.reverse.SplitMix64.SplitMix64Cracker;

public final class Xoroshiro256PlusPlusReverse {

    private long s0, s1, s2, s3;

    public Xoroshiro256PlusPlusReverse(long s0, long s1, long s2, long s3) {
        this.s0 = s0;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    /** EXACT inverse of one nextLong() */
    public void previousStep() {

        s3 = Long.rotateRight(s3, 45);
        long t = s1 << 17;
        s2 ^= t;

        long oldS0 = s0 ^ s3;
        long oldS1 = s1 ^ s2;
        long oldS2 = s2 ^ oldS0;
        long oldS3 = s3 ^ oldS1;

        s0 = oldS0;
        s1 = oldS1;
        s2 = oldS2;
        s3 = oldS3;
    }

    public long getS0() { return s0; }
    public long getS1() { return s1; }
    public long getS2() { return s2; }
    public long getS3() { return s3; }

    public static long recoverSeedFromConstructorState(
            long s0, long s1, long s2, long s3
    ) {
        return SplitMix64Cracker.recoverSeedFromFourOutputs(s0, s1, s2, s3);
    }
}