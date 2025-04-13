package org.example;

import java.util.ArrayList;
import java.util.List;

public class CommunityPokerGame {
    private List<Player> players;
    private Deck deck;
    private List<Card> communityCards;

    public CommunityPokerGame() {
        players = new ArrayList<>();
        deck = new Deck();
        communityCards = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        for (Player player : players) {
            player.addCard(deck.drawCard());
            player.addCard(deck.drawCard());
        }

        for (int i = 0; i < 5; i++) {
            communityCards.add(deck.drawCard());
        }
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
