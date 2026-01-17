package me.uxokpro1234.reverse.SplitMix64;

import me.uxokpro1234.random.SplitMix64;

public class Main {
    public static void main(String[] args){
        long seed = 123456789L;
        SplitMix64 rng = new SplitMix64(seed);

        long output = rng.nextLong();
        long crackedSeed = SplitMix64Cracker.recoverSeed(output);

        System.out.println("Output:        " + output);
        System.out.println("Cracked seed:  " + crackedSeed);
        System.out.println("Original seed: " + seed);
    }
}