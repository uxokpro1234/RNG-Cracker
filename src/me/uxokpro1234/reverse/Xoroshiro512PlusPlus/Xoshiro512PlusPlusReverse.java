package me.uxokpro1234.reverse.Xoroshiro512PlusPlus;

import java.util.BitSet;
public final class Xoshiro512PlusPlusReverse {

    private static final int WORDS = 8;
    private static final int BITS = WORDS * 64;

    private static final BitSet[] INV_MATRIX = invert(buildMatrix());

    private final long[] s = new long[WORDS];

    public Xoshiro512PlusPlusReverse(long[] state) {
        System.arraycopy(state, 0, s, 0, WORDS);
    }

    public void previous() {
        s[7] = Long.rotateRight(s[7], 21);
        s[6] ^= (s[1] << 11);
        long[] out = new long[WORDS];

        for (int i = 0; i < BITS; i++) {
            if (bit(s, i)) {
                out[i >>> 6] ^= 1L << (i & 63);
            }
        }
        applyInverse(out);
    }

    private void applyInverse(long[] out) {
        long[] prev = new long[WORDS];

        for (int i = 0; i < BITS; i++) {
            if (INV_MATRIX[i].intersects(bits(out))) {
                prev[i >>> 6] ^= 1L << (i & 63);
            }
        }

        System.arraycopy(prev, 0, s, 0, WORDS);
    }

    public long[] getState() {
        return s.clone();
    }

    private static BitSet[] buildMatrix() {
        BitSet[] m = identity();

        apply(m, 2, 0);
        apply(m, 5, 1);
        apply(m, 1, 2);
        apply(m, 7, 3);
        apply(m, 3, 4);
        apply(m, 4, 5);
        apply(m, 0, 6);
        apply(m, 6, 7);

        applyShift(m, 6, 1, 11);
        applyRotate(m, 7, 21);

        return m;
    }

    private static void apply(BitSet[] m, int a, int b) {
        for (int i = 0; i < 64; i++)
            m[a * 64 + i].xor(m[b * 64 + i]);
    }

    private static void applyShift(BitSet[] m, int a, int b, int k) {
        for (int i = k; i < 64; i++)
            m[a * 64 + i].xor(m[b * 64 + i - k]);
    }

    private static void applyRotate(BitSet[] m, int a, int k) {
        BitSet[] copy = m.clone();
        for (int i = 0; i < 64; i++)
            m[a * 64 + i] = (BitSet) copy[a * 64 + ((i - k + 64) & 63)].clone();
    }

    private static BitSet[] invert(BitSet[] m) {
        BitSet[] inv = identity();

        for (int i = 0; i < BITS; i++) {
            if (!m[i].get(i)) {
                for (int j = i + 1; j < BITS; j++) {
                    if (m[j].get(i)) {
                        BitSet tmp = m[i]; m[i] = m[j]; m[j] = tmp;
                        tmp = inv[i]; inv[i] = inv[j]; inv[j] = tmp;
                        break;
                    }
                }
            }

            for (int j = 0; j < BITS; j++) {
                if (j != i && m[j].get(i)) {
                    m[j].xor(m[i]);
                    inv[j].xor(inv[i]);
                }
            }
        }
        return inv;
    }

    private static BitSet[] identity() {
        BitSet[] m = new BitSet[BITS];
        for (int i = 0; i < BITS; i++) {
            m[i] = new BitSet(BITS);
            m[i].set(i);
        }
        return m;
    }

    private static BitSet bits(long[] s) {
        BitSet b = new BitSet(BITS);
        for (int i = 0; i < BITS; i++)
            if (bit(s, i)) b.set(i);
        return b;
    }

    private static boolean bit(long[] s, int i) {
        return ((s[i >>> 6] >>> (i & 63)) & 1) != 0;
    }
}