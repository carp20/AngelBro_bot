package org.example;


public class PokerGame {

    public PokerCard suit;

    public String rank;

    public boolean isClub() {
        return suit.equals(PokerCard.club);
    }

    public boolean isDiamond() {
        return suit.equals(PokerCard.diamond);
    }

    public boolean isHeart() {
        return suit.equals(PokerCard.heart);
    }

    public boolean isSpades() {
        return suit.equals(PokerCard.spades);
    }

    public PokerGame(PokerCard suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public PokerCard getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }


}