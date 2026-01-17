package me.uxokpro1234.reverse.WyRand;

public final class WyRandReverse {

    private static final long INC = 0xa0761d6478bd642fL;
    private static final long MUL = 0xe7037ed1a0b428dbL;
    private static final long INV_MUL = 0x8f7011eea4d54f5bL; // check!

    private long state;

    public WyRandReverse(long state) {
        this.state = state;
    }

    /** Reverse one nextLong() */
    public long previousLong() {
        long z = state;
        z = unxorshift32(z);
        z = multiplyMod64(z, INV_MUL);
        z = unxorshift32(z);
        state = z - INC;
        return state;
    }

    private static long unxorshift32(long x) {
        long upper = x >>> 32;
        long lower = x & 0xFFFFFFFFL;
        lower ^= upper;
        return (upper << 32) | lower;
    }

    private static long multiplyMod64(long a, long b) {
        return a * b;
    }

    public long getState() {
        return state;
    }
}