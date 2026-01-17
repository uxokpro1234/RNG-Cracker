package me.uxokpro1234.reverse.WyRand;

import me.uxokpro1234.random.WyRand;

public class Main {
    public static void main(String[] args) {

        long seed = 123456789L;

        WyRand rng = new WyRand(seed);

        rng.nextLong();
        rng.nextLong();

        long leakedState;
        try {
            var f = WyRand.class.getDeclaredField("state");
            f.setAccessible(true);
            leakedState = f.getLong(rng);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        WyRandReverse rev = new WyRandReverse(leakedState);

        long s1 = rev.previousLong();
        long s0 = rev.previousLong();

        System.out.println("Original seed:  " + seed);
        System.out.println("Recovered seed: " + s0);
        System.out.println("Match: " + (seed == s0));
    }
}