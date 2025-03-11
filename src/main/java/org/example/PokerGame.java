package org.example;


public class PokerGame {

    public PokerCard suit;

    public String rank;

    boolean isClub;
    boolean isDiamond;
    boolean isHeart;
    boolean isSpades;

    public PokerGame(PokerCard suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        String suit2 = suit.toString();
        isClub = suit2.equals("club");
        isDiamond = suit2.equals("diamond");
        isHeart = suit2.equals("heart");
        isSpades = suit2.equals("spades");
    }

    public PokerCard getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }


}