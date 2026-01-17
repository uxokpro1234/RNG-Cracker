package me.uxokpro1234.reverse.Xoroshiro256PlusPlus;

import me.uxokpro1234.random.SplitMix64;
import me.uxokpro1234.random.Xoroshiro256PlusPlus;

public class Main {

    public static void main(String[] args) {

        long seed = 123456789L;

        Xoroshiro256PlusPlus rng = new Xoroshiro256PlusPlus(seed);

        rng.nextLong();
        rng.nextLong();
        rng.nextLong();

        Xoroshiro256PlusPlusReverse rev = new Xoroshiro256PlusPlusReverse(rng.s0, rng.s1, rng.s2, rng.s3);

        rev.previousStep();
        rev.previousStep();
        rev.previousStep();

        long rs0 = rev.getS0();
        long rs1 = rev.getS1();
        long rs2 = rev.getS2();
        long rs3 = rev.getS3();

        System.out.println("Recovered constructor state:");
        System.out.println("s0 = " + rs0);
        System.out.println("s1 = " + rs1);
        System.out.println("s2 = " + rs2);
        System.out.println("s3 = " + rs3);

        long recoveredSeed = Xoroshiro256PlusPlusReverse.recoverSeedFromConstructorState(rs0, rs1, rs2, rs3);

        System.out.println("Original seed:  " + seed);
        System.out.println("Recovered seed: " + recoveredSeed);

        System.out.println("\nVerification:");
        SplitMix64 sm = new SplitMix64(recoveredSeed);
        System.out.println(sm.nextLong() == rs0);
        System.out.println(sm.nextLong() == rs1);
        System.out.println(sm.nextLong() == rs2);
        System.out.println(sm.nextLong() == rs3);
    }
}