package com.adaptionsoft.games.trivia.runner;

import java.util.ArrayList;

public class PlayerService {

    private ArrayList<Player> players;

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public PlayerService(ArrayList<Player> players) {
        this.players = players;
    }
}
