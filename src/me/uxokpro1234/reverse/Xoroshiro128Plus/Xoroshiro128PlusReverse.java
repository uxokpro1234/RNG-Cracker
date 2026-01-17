package me.uxokpro1234.reverse.Xoroshiro128Plus;

public final class Xoroshiro128PlusReverse {
    private long s0, s1;

    private static final long SPLITMIX_GAMMA = 0x9E3779B97F4A7C15L;

    public Xoroshiro128PlusReverse(long s0, long s1) {
        this.s0 = s0;
        this.s1 = s1;
    }

    public long nextLong() {
        long result = s0 + s1;

        long s1Copy = s1 ^ s0;
        s0 = Long.rotateLeft(s0, 55) ^ s1Copy ^ (s1Copy << 14);
        s1 = Long.rotateLeft(s1Copy, 36);

        return result;
    }

    /** Reverse one step of Xoroshiro128+ */
    public void previousStep() {
        long s1Copy = Long.rotateRight(s1, 36);
        s0 = invertS0(s0, s1Copy);
        s1 = s1Copy ^ s0;
    }

    /** Recover the original constructor seed */
    public long recoverOriginalSeed() {
        // s0 must be the INITIAL s0 (right after seeding)
        return invertSplitMix64(s0) - SPLITMIX_GAMMA;
    }

    /** Invert s0 = rotl(origS0, 55) ^ s1Copy ^ (s1Copy << 14) */
    private long invertS0(long s0Next, long s1Copy) {
        long x = s0Next ^ s1Copy ^ (s1Copy << 14);
        return Long.rotateRight(x, 55);
    }

    private static long invertSplitMix64(long z) {
        z = unxorshift(z, 31);
        z = unmul(z, 0x94D049BB133111EBL);
        z = unxorshift(z, 27);
        z = unmul(z, 0xBF58476D1CE4E5B9L);
        z = unxorshift(z, 30);
        return z;
    }

    private static long unxorshift(long x, int shift) {
        long r = x;
        for (int i = shift; i < 64; i <<= 1) {
            r ^= r >>> i;
        }
        return r;
    }

    private static long unmul(long x, long mul) {
        long inv = mul;
        for (int i = 0; i < 6; i++) {
            inv *= 2 - mul * inv; // Newton–Raphson inverse
        }
        return x * inv;
    }

    public long getS0() { return s0; }
    public long getS1() { return s1; }
}