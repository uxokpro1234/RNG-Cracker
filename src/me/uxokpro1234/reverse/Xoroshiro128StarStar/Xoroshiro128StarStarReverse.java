package me.uxokpro1234.reverse.Xoroshiro128StarStar;

public final class Xoroshiro128StarStarReverse {
    private static final long GAMMA = 0x9E3779B97F4A7C15L;
    private static final long MUL1  = 0xBF58476D1CE4E5B9L;
    private static final long MUL2  = 0x94D049BB133111EBL;
    private static final long INV_MUL1 = 0x96DE1B173F119089L;
    private static final long INV_MUL2 = 0x319642B2D24D8EC3L;
    private long s0, s1;

    public Xoroshiro128StarStarReverse(long s0, long s1) {
        this.s0 = s0;
        this.s1 = s1;
    }

    public static long recoverSeedFromTwoOutputs(long out1, long out2) {
        long state1 = unmix(out1);
        // optional sanity check:
        long state2 = unmix(out2);
        if (state2 - state1 != GAMMA) {
            throw new IllegalStateException("Outputs are not consecutive SplitMix64 values");
        }
        return state1 - GAMMA;
    }

    private static long unmix(long z) {
        z = unxorshiftRight(z, 31);
        z *= INV_MUL2;
        z = unxorshiftRight(z, 27);
        z *= INV_MUL1;
        z = unxorshiftRight(z, 30);
        return z;
    }

    private static long unxorshiftRight(long x, int shift) {
        for (int i = shift; i < 64; i <<= 1) {
            x ^= x >>> i;
        }
        return x;
    }

    public void previousStep() {

        long t = Long.rotateRight(s1, 37);
        long x = s0 ^ t ^ (t << 16);
        long oldS0 = Long.rotateRight(x, 24);
        long oldS1 = t ^ oldS0;
        s0 = oldS0;
        s1 = oldS1;
    }

    public long getS0() { return s0; }
    public long getS1() { return s1; }
}