package org.example;

public class Identify extends PlayPokerGame{

    public Identify(PokerCards suit, int rank) {
        super(suit, rank);
    }

    public PlayPokerGame card;

    public int rank = card.getRank();
    public PokerCards suit1 = card.getSuit();
    String suit2 = suit1.toString();
    boolean isClub = suit2.equals("club");
    boolean isDiamond = suit2.equals("diamond");
    boolean isHeart = suit2.equals("heart");
    boolean isSpades = suit2.equals("spades");


}
