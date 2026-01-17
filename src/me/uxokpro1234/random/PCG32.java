package me.uxokpro1234.random;

public final class PCG32 {
    private long state;
    private final long inc;

    public PCG32(long seed, long seq) {
        state = 0;
        inc = (seq << 1) | 1;
        nextInt();
        state += seed;
        nextInt();
    }

    public int nextInt() {
        long old = state;
        state = old * 6364136223846793005L + inc;
        int xorshifted = (int)(((old >>> 18) ^ old) >>> 27);
        int rot = (int)(old >>> 59);
        return Integer.rotateRight(xorshifted, rot);
    }
}