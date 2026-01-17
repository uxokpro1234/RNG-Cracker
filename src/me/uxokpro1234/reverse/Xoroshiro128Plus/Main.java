package me.uxokpro1234.reverse.Xoroshiro128Plus;


import me.uxokpro1234.random.Xoroshiro128Plus;

public class Main {
    public static void main(String[] args) {
        long seed = 123456789L;
        Xoroshiro128Plus rng = new Xoroshiro128Plus(seed);

        long val1 = rng.nextLong();
        long val2 = rng.nextLong();

        System.out.println("Forward:");
        System.out.println(val1);
        System.out.println(val2);

        Xoroshiro128PlusReverse rev = new Xoroshiro128PlusReverse(rng.s0, rng.s1);
        rev.previousStep();
        rev.previousStep();

        long recoveredSeed = rev.recoverOriginalSeed();
        System.out.println("Recovered seed: " + recoveredSeed);
        System.out.println("Reversed s0: " + rev.getS0());
        System.out.println("Reversed s1: " + rev.getS1());
    }
}