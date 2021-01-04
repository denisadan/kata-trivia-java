package com.adaptionsoft.games.trivia.newrunner.service;

import com.adaptionsoft.games.trivia.IGame;
import com.adaptionsoft.games.trivia.newrunner.data.Player;
import com.adaptionsoft.games.trivia.newrunner.data.Question;
import com.adaptionsoft.games.trivia.newrunner.data.QuestionType;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Game implements IGame {

    private final GameHandler gameHandler = new GameHandler();
    private int currentPlayer = 0;

    public Game() {
        IntStream.range(0, 50).forEach(this::generateQuestionsForAllCategories);
    }

    public void add(String playerName) {
        gameHandler.addPlayer(new Player(playerName));
        System.out.println(playerName + " was added\nThey are player number " + gameHandler.getNumberOfPlayers());
    }

    public void roll(int roll) {
        Player player = getCurrentPlayer();
        System.out.println(player.getName() + " is the current player\nThey have rolled a " + roll);

        if (player.isInPenaltyBox()) {
            playerTriesToGetOutOfPenalty(player, roll % 2 == 1);
        }

        if (!player.isInPenaltyBox() || player.isGettingOutOfPenaltyBox()) {
            player.makeAMove(roll);
            System.out.println(player.getName() + "'s new location is " + player.getCurrentPlace());

            gameHandler.getAQuestion(player.getCurrentPlace());
        }
    }

    public boolean answerIsRight() {
        Player player = getCurrentPlayer();

        if (!player.isInPenaltyBox() || player.isGettingOutOfPenaltyBox()) {
            addCoin(player);
        }
        moveToNextPlayer();

        return player.isWinner();
    }

    public boolean answerIsWrong() {
        System.out.println("Question was incorrectly answered.");
        sendToPenaltyBox(getCurrentPlayer());
        moveToNextPlayer();
        return true;
    }

    private void addCoin(Player player) {
        player.getACoin();
        System.out.println("Answer was correct!!!!\n" + player.getName() + " now has " + player.getPurse() + " Gold Coins.");
    }

    private void moveToNextPlayer() {
        currentPlayer++;
        if (currentPlayer == gameHandler.getNumberOfPlayers()) {
            currentPlayer = 0;
        }
    }

    private void sendToPenaltyBox(Player player) {
        player.setInPenaltyBox(true);
        System.out.println(player.getName() + " was sent to the penalty box");
    }

    private void playerTriesToGetOutOfPenalty(Player player, boolean gettingOut) {
        player.setGettingOutOfPenaltyBox(gettingOut);
        System.out.println(gettingOut ? player.getName() + " is getting out of the penalty box" : player.getName() + " is not getting out of the penalty box");
    }

    private Player getCurrentPlayer() {
        return gameHandler.getPlayers().get(currentPlayer);
    }

    private void generateQuestionsForAllCategories(int content) {
        Arrays.stream(QuestionType.values()).forEach(j -> gameHandler.addQuestion(new Question(j, j.getLabel() + " Question " + content)));
    }

}