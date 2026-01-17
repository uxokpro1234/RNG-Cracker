package me.uxokpro1234.reverse.SplitMix64;

public final class SplitMix64Cracker {

    private static final long GAMMA = 0x9E3779B97F4A7C15L;
    private static final long MUL1  = 0xBF58476D1CE4E5B9L;
    private static final long MUL2  = 0x94D049BB133111EBL;
    private static final long INV_MUL1 = 0x96DE1B173F119089L;
    private static final long INV_MUL2 = 0x319642B2D24D8EC3L;

    // Doesn't do shi
    public static long recoverSeedFromFourOutputs(
            long o0, long o1, long o2, long o3
    ) {
        long seed = recoverSeedFromOutput(o0);

        long s = seed + GAMMA;

        if (mix(s) != o0) return -1;
        if (mix(s += GAMMA) != o1) return -1;
        if (mix(s += GAMMA) != o2) return -1;
        if (mix(s += GAMMA) != o3) return -1;

        return seed;
    }

    private static long recoverSeedFromOutput(long output) {
        long z = unxorshiftRight(output, 31);
        z *= INV_MUL2;
        z = unxorshiftRight(z, 27);
        z *= INV_MUL1;
        z = unxorshiftRight(z, 30);

        return z - GAMMA;
    }

    private static long next(long state) {
        long z = state;
        z = (z ^ (z >>> 30)) * MUL1;
        z = (z ^ (z >>> 27)) * MUL2;
        return z ^ (z >>> 31);
    }


    public static long recoverSeed(long output) {
        long stateAfterAdd = unmix(output);
        return stateAfterAdd - GAMMA;
    }

    private static long mix(long state) {
        long z = state;
        z = (z ^ (z >>> 30)) * MUL1;
        z = (z ^ (z >>> 27)) * MUL2;
        return z ^ (z >>> 31);
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
}