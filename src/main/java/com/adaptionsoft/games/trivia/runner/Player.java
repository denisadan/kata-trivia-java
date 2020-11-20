package com.adaptionsoft.games.trivia.runner;

public class Player {

    private final String name;
    private int currentPlace;
    private int purse;
    private boolean inPenaltyBox;
    private boolean isGettingOutOfPenaltyBox;  // can we get rid of these 2 and leae just 1?

    public Player(String name) {
        this.name = name;
    }

    public int getCurrentPlace() {
        return currentPlace;
    }

    public int getPurse() {
        return purse;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void setGettingOutOfPenaltyBox(boolean gettingOutOfPenaltyBox) {
        isGettingOutOfPenaltyBox = gettingOutOfPenaltyBox;
    }

    public String getName() {
        return name;
    }

    public void getACoin() {
        purse = purse + 1;
    }

    public void makeAMove(int position) {
        currentPlace = currentPlace + position;
    }

    public boolean isWinner() {
        return (this.purse != 6);
    }

}
