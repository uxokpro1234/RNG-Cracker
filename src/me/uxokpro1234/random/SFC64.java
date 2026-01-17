package me.uxokpro1234.random;

public final class SFC64 {
    private long a, b, c, counter;
    public SFC64(long seed) {
        SplitMix64 sm = new SplitMix64(seed);
        a = sm.nextLong();
        b = sm.nextLong();
        c = sm.nextLong();
        counter = 1;
    }

    public long nextLong() {
        long result = a + b + counter++;
        a = b ^ (b >>> 11);
        b = c + (c << 3);
        c = Long.rotateLeft(c, 24) + result;
        return result;
    }
}