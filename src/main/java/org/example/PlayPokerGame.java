package org.example;


public class PlayPokerGame {

    public PokerCards suit;

    public int rank;

    public PlayPokerGame(PokerCards suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public PokerCards getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }
}
