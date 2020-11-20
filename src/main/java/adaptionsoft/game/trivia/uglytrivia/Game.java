package adaptionsoft.game.trivia.uglytrivia;

import com.adaptionsoft.games.trivia.runner.GameHandlerService;
import com.adaptionsoft.games.trivia.runner.Player;
import com.adaptionsoft.games.trivia.runner.Question;
import com.adaptionsoft.games.trivia.runner.Question.QuestionType;

public class Game {
    private final GameHandlerService gameHandlerService = new GameHandlerService();
    int currentPlayer = 0;

    public void initGame() {
        for (int i = 0; i < 50; i++) {
            gameHandlerService.addQuestion(new Question(QuestionType.POP, String.valueOf(i)));
            gameHandlerService.addQuestion(new Question(QuestionType.SCIENCE, String.valueOf(i)));
            gameHandlerService.addQuestion(new Question(QuestionType.SPORTS, String.valueOf(i)));
            gameHandlerService.addQuestion(new Question(QuestionType.ROCK, String.valueOf(i)));
        }
    }

    public void addPlayer(String playerName) {
        gameHandlerService.addPlayer(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + gameHandlerService.getNumberOfPlayers());
    }

    public void roll(int roll) {
        Player player = getCurrentPlayer();

        System.out.println(player.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (player.isInPenaltyBox()) {
            playerTriesToGetOutOfPenalty(player, roll % 2 == 0);
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

        QuestionType category = getCategoryByPlace(player.getCurrentPlace());
        System.out.println("The category is " + category);
        gameHandlerService.askQuestion(category);
    }

    private String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

    private QuestionType getCategoryByPlace(int place) { // move this to game service?
        if (place == 0 || place == 4 || place == 8) return QuestionType.POP;
        if (place == 1 || place == 5 || place == 9) return QuestionType.SCIENCE;
        if (place == 2 || place == 6 || place == 10) return QuestionType.SPORTS;
        return QuestionType.ROCK;
    }

    public boolean answerIsRight() {
        Player player = getCurrentPlayer();
        boolean isWinner;

        if (!player.isInPenaltyBox() || player.isGettingOutOfPenaltyBox()) {
            playerAnswersCorrectly(player);
            isWinner = didPlayerWin(player);
            moveToNextPlayer();
        } else {
            moveToNextPlayer();
            isWinner = true;
        }

        return isWinner; // faulty logic?
    }

    public boolean answerIsWrong() {
        System.out.println("Question was incorrectly answered.");
        sendToPenaltyBox(getCurrentPlayer());
        moveToNextPlayer();
        return true; // can remove this ?
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

    private void playerTriesToGetOutOfPenalty(Player player, boolean cantGetOut) {
        if (cantGetOut) {
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

    private boolean didPlayerWin(Player player) {
        return (player.getPurse() != 6);
    }
}