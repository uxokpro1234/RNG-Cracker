package me.uxokpro1234.reverse.LCG;

public final class LCGCracker {

    private static final long A_INV = 0xDFE05BCBL;
    private static final long C = 1013904223L;
    private static final long MASK = 0xFFFFFFFFL;

    /** Recovers the LOWER 32 bits of the seed from one output */
    public static long recoverSeedLower32(int output) {
        long out = output & MASK;
        return ((out - C) * A_INV) & MASK;
    }
    public static long reverseStep(long state) {
        long invA = 0xEEA56631FEE058C5L;
        long C = 1013904223L;
        return (state - C) * invA;
    }
}