package org.example;

import java.util.List;

public class Saver {
    private long id;
    private List<Card> playerCards;
    private List<Card> communityCards;
    private List<Card> botCards;

    public Saver(long id, List<Card> playerCards, List<Card> communityCards, List<Card> botCards) {
        this.id = id;
        this.playerCards = playerCards;
        this.communityCards = communityCards;
        this.botCards = botCards;
    }

    public long getId() {
        return id;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public List<Card> gelAllCards(){
        List<Card> allCards = null;
        allCards.addAll(playerCards);
        allCards.addAll(communityCards);
        return allCards;
    }

    public List<Card> getBotCards() {
        return botCards;
    }
}
