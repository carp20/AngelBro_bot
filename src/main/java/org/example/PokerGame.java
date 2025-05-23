package org.example;

import java.util.ArrayList;
import java.util.List;

public class PokerGame {
    private Player player;
    private Player bot;
    private Deck deck;
    private List<Card> communityCards;

    public PokerGame() {
        bot = new Player("Bot");
        player = new Player("Player");
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

    public List<Card> getCards(Player player){
        return player.getHand();
    }

    public Player getPlayer(){
        return player;
    }

    public Player getBot(){
        return bot;
    }

}
