package com.adaptionsoft.games.trivia;

public interface IGame {

    void roll(int roll);

    void add(String playerName);

    boolean answerIsRight();

    boolean answerIsWrong();

}
