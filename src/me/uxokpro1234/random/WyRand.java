package me.uxokpro1234.random;

public final class WyRand {
    private long state;
    public WyRand(long seed) {
        state = seed;
    }

    public long nextLong() {
        state += 0xa0761d6478bd642fL;
        long z = state;
        z = (z ^ (z >>> 32)) * 0xe7037ed1a0b428dbL;
        return z ^ (z >>> 32);
    }
}