package me.uxokpro1234;

import me.uxokpro1234.random.*;

public class Main {

    public static void main(String[] args) {

        long seed = System.nanoTime();
        // LCG
        LCG lcg = new LCG(seed);
        System.out.println("LCG:");
        System.out.println(lcg.nextInt());

        // SplitMix64
        SplitMix64 splitMix = new SplitMix64(seed);
        System.out.println("\nSplitMix64:");
        System.out.println(splitMix.nextLong());


        // Xoroshiro128+
        Xoroshiro128Plus xoroshiroPlus = new Xoroshiro128Plus(seed);
        System.out.println("\nXoroshiro128+:");
        System.out.println(xoroshiroPlus.nextLong());


        // Xoroshiro128**
        Xoroshiro128StarStar xoroshiroSS = new Xoroshiro128StarStar(seed);
        System.out.println("\nXoroshiro128**:");
        System.out.println(xoroshiroSS.nextLong());

        // Xoroshiro256++
        Xoroshiro256PlusPlus xoroshiro256PlusPlus256 = new Xoroshiro256PlusPlus(seed);
        System.out.println("\nXoroshiro256++:");
        System.out.println(xoroshiro256PlusPlus256.nextLong());

        // Xoroshiro256**
        Xoshiro256StarStar xoshiro256StarStar = new Xoshiro256StarStar(seed);
        System.out.println("\nXoroshiro256**:");
        System.out.println(xoshiro256StarStar.nextLong());

        // Xoroshiro512++
        Xoshiro512PlusPlus xoshiro512PlusPlus = new Xoshiro512PlusPlus(seed);
        System.out.println("\nXoroshiro512++:");
        System.out.println(xoshiro512PlusPlus.nextLong());

        // PCG32
        PCG32 pcg = new PCG32(seed, 54L);
        System.out.println("\nPCG32:");
        System.out.println(pcg.nextInt());

        // SFC64
        SFC64 sfc64 = new SFC64(seed);
        System.out.println("\nSFC64:");
        System.out.println(sfc64.nextLong());

        // WyRand
        WyRand wyRand = new WyRand(seed);
        System.out.println("\nWyRand:");
        System.out.println(wyRand.nextLong());

        // LCG (bad RNG, demo only)
        LCG lcgg = new LCG(seed);
        System.out.println("\nLCG:");
        System.out.println(lcgg.nextInt());

        // Bounded values
        System.out.println("\nBounded values (0 - 99) using Xoroshiro:");
        int value = nextIntBounded(xoroshiroPlus.nextLong(), 100);
        System.out.println(value);

        // Doubles [0,1)
        System.out.println("\nRandom doubles [0,1):");
        double d = toDouble(splitMix.nextLong());
        System.out.println(d);

    }

    public static int nextIntBounded(long value, int bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("bound must be positive");
        }
        long r = value >>> 1;
        return (int) (r % bound);
    }

    public static double toDouble(long value) {
        return (value >>> 11) * (1.0 / (1L << 53));
    }
}