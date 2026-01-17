package me.uxokpro1234.reverse.Xoroshiro512PlusPlus;

import me.uxokpro1234.random.Xoshiro512PlusPlus;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        long seed = 123456789L;
        Xoshiro512PlusPlus rng = new Xoshiro512PlusPlus(seed);
        long[] originalState = rngState(rng);

        for (int i = 0; i < 10; i++) {
            rng.nextLong();
        }

        long[] observedState = rngState(rng);

        Xoshiro512PlusPlusReverse rev = new Xoshiro512PlusPlusReverse(observedState);

        for (int i = 0; i < 10; i++) {
            rev.previous();
        }

        long[] recoveredState = rev.getState();

        System.out.println("Original state:");
        print(originalState);

        System.out.println("\nRecovered state:");
        print(recoveredState);

        System.out.println("\nState matches:");
        System.out.println(Arrays.equals(originalState, recoveredState));
    }

    private static long[] rngState(Xoshiro512PlusPlus rng) {
        try {
            var f = Xoshiro512PlusPlus.class.getDeclaredField("s");
            f.setAccessible(true);
            return ((long[]) f.get(rng)).clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void print(long[] s) {
        for (int i = 0; i < s.length; i++) {
            System.out.printf("s[%d] = %d%n", i, s[i]);
        }
    }
}