package me.uxokpro1234.random;

public final class LCG {
    private long state;

    public LCG(long seed) {
        state = seed;
    }

    public int nextInt() {
        state = state * 1664525 + 1013904223;
        return (int) state;
    }
}