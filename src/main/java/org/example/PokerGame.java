package org.example;

import java.util.ArrayList;
import java.util.List;

public class PokerGame {
    private Player player;
    private Player bot;
    private Deck deck;
    private List<Card> communityCards;

    public PokerGame() {
        bot = new Player("bot");
        player = new Player("player");
        deck = new Deck();
        communityCards = new ArrayList<>();
    }

    public void startGame() {
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());

        bot.addCard(deck.drawCard());
        bot.addCard(deck.drawCard());

        for (int i = 0; i < 5; i++) {
            communityCards.add(deck.drawCard());
        }
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public List<Card> getYourCards(){
        return player.getHand();
    }

    public List<Card> getBotCards(){
        return bot.getHand();
    }
}
