package me.uxokpro1234.reverse.Xoroshiro128StarStar;

import me.uxokpro1234.random.Xoroshiro128StarStar;

public class Main {
    public static void main(String[] args) {
        long seed = 123456789L;

        Xoroshiro128StarStar rng = new Xoroshiro128StarStar(seed);

        rng.nextLong();
        rng.nextLong();

        Xoroshiro128StarStarReverse rev = new Xoroshiro128StarStarReverse(rng.s0, rng.s1);

        rev.previousStep();
        rev.previousStep();

        long recoveredS0 = rev.getS0();
        long recoveredS1 = rev.getS1();

        long recoveredSeed = Xoroshiro128StarStarReverse.recoverSeedFromTwoOutputs(recoveredS0, recoveredS1);

        System.out.println("Original seed:  " + seed);
        System.out.println("Recovered seed: " + recoveredSeed);
    }
}