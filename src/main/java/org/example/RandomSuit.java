package org.example;

import java.util.Random;

public class RandomSuit {
    public PokerCard suit;

    Random random = new Random();
    int randomIntSuit = random.nextInt(1,5);

    boolean clubs = (randomIntSuit == 1);
    boolean diamonds = (randomIntSuit == 2);
    boolean hearts = (randomIntSuit == 3);
    boolean spadess = (randomIntSuit == 4);
}
