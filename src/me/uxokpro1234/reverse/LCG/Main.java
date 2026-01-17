package me.uxokpro1234.reverse.LCG;


import me.uxokpro1234.random.LCG;

public class Main {
    public static void main(String[] args){
        LCG rng = new LCG(123456789L);
        int out = rng.nextInt();

        long recovered = LCGCracker.recoverSeedLower32(out);

        System.out.println("Original seed:  " + (123456789L & 0xFFFFFFFFL));
        System.out.println("Recovered seed: " + recovered);
    }
}