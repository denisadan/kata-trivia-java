package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.trivia.IGame;
import com.adaptionsoft.games.trivia.runner.Question.QuestionType;

import static com.adaptionsoft.games.trivia.runner.Question.QuestionType.*;

public class GameNew implements IGame {

    private final GameHandlerService gameHandlerService = new GameHandlerService();
    private int currentPlayer = 0;

    public GameNew() {
        for (int i = 0; i < 50; i++) {
            gameHandlerService.addQuestion(new Question(POP, "Pop Question " + i));
            gameHandlerService.addQuestion(new Question(SCIENCE, "Science Question " + i));
            gameHandlerService.addQuestion(new Question(SPORTS, "Sports Question " + i));
            gameHandlerService.addQuestion(new Question(ROCK, "Rock Question " + i));
        }
    }

    public void add(String playerName) {
        gameHandlerService.addPlayer(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + gameHandlerService.getNumberOfPlayers());
    }

    public void roll(int roll) {
        Player player = getCurrentPlayer();

        System.out.println(player.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (player.isInPenaltyBox()) {
            playerTriesToGetOutOfPenalty(player, roll);
        } else {
            player.makeAMove(roll);
        }
        if (player.isGettingOutOfPenaltyBox()) {
            player.makeAMove(roll);
        }

        if (player.getCurrentPlace() > 11) {
            player.makeAMove(-12);
        }

        System.out.println(getCurrentPlayerName() + "'s new location is " + player.getCurrentPlace());
        // did not refactor corectly, rethink this a bit :(
        QuestionType category = gameHandlerService.getQuestionCategory(player.getCurrentPlace());
        System.out.println("The category is " + category.getLabel());
        System.out.println(gameHandlerService.askQuestion(category).getContent());
    }

    public boolean answerIsRight() {
        Player player = getCurrentPlayer();

        if (!player.isInPenaltyBox() || player.isGettingOutOfPenaltyBox()) {
            playerAnswersCorrectly(player);
            moveToNextPlayer();
        } else {
            moveToNextPlayer();
            return true;
        }

        return player.isWinner(); // faulty logic?
    }

    public boolean answerIsWrong() {
        System.out.println("Question was incorrectly answered.");
        sendToPenaltyBox(getCurrentPlayer());
        moveToNextPlayer();
        return true; // can remove this ?
    }

    private String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

    private void playerAnswersCorrectly(Player player) {
        System.out.println("Answer was correct!!!!");
        player.getACoin();
        System.out.println(getCurrentPlayerName() + " now has " + player.getPurse() + " Gold Coins.");
    }

    private void moveToNextPlayer() {
        currentPlayer++;
        if (currentPlayer == gameHandlerService.getNumberOfPlayers()) {
            currentPlayer = 0;
        }
    }

    private void sendToPenaltyBox(Player player) {
        player.setInPenaltyBox(true);
        System.out.println(player.getName() + " was sent to the penalty box");
    }

    private void playerTriesToGetOutOfPenalty(Player player, int roll) {
        if (roll % 2 == 0) {
            System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
            player.setGettingOutOfPenaltyBox(false);
        } else {
            player.setGettingOutOfPenaltyBox(true);
            System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
        }
    }

    private Player getCurrentPlayer() {
        return gameHandlerService.getPlayers().get(currentPlayer);
    }

}