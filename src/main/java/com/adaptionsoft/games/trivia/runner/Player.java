package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.trivia.runner.Question.QuestionType;

public class Player {
    private String name;
    private QuestionType category;
    private int place;
    private int purse;
    private boolean inPenaltyBox;
    private boolean isCurrentPlayer;

    public Player(String name) {
        this.name = name;
    }

    public void setCategory(QuestionType category) {
        this.category = category;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        isCurrentPlayer = currentPlayer;
    }

    public String getName() {
        return name;
    }
}
