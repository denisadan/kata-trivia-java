package adaptionsoft.game.trivia.uglytrivia;

import com.adaptionsoft.games.trivia.runner.Player;
import com.adaptionsoft.games.trivia.runner.PlayerService;
import com.adaptionsoft.games.trivia.runner.Question;
import com.adaptionsoft.games.trivia.runner.Question.QuestionType;
import com.adaptionsoft.games.trivia.runner.QuestionService;

import java.util.ArrayList;

public class Game {
    private final PlayerService playerService = new PlayerService(new ArrayList<Player>());
    private final QuestionService questionService = new QuestionService();

    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            questionService.addQuestion(new Question(QuestionType.Pop, String.valueOf(i)));
            questionService.addQuestion(new Question(QuestionType.Science, String.valueOf(i)));
            questionService.addQuestion(new Question(QuestionType.Sports, String.valueOf(i)));
            questionService.addQuestion(new Question(QuestionType.Rock, String.valueOf(i)));
        }
    }

    public boolean add(String playerName) {
        playerService.addPlayer(new Player(playerName));

        int numberOfPlayers = playerService.getNumberOfPlayers();

        places[numberOfPlayers] = 0;
        purses[numberOfPlayers] = 0;
        inPenaltyBox[numberOfPlayers] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + numberOfPlayers);
        return true;
    }

    public void roll(int roll) {
        System.out.println(playerService.getPlayers().get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(playerService.getPlayers().get(currentPlayer) + " is getting out of the penalty box");
                places[currentPlayer] = places[currentPlayer] + roll;
                if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

                System.out.println(playerService.getPlayers().get(currentPlayer)
                        + "'s new location is "
                        + places[currentPlayer]);
                System.out.println("The category is " + getCategoryByPlace(places[currentPlayer]));
                questionService.askQuestion(getCategoryByPlace(places[currentPlayer]));
            } else {
                System.out.println(playerService.getPlayers().get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

            System.out.println(playerService.getPlayers().get(currentPlayer)
                    + "'s new location is "
                    + places[currentPlayer]);
            System.out.println("The category is " + getCategoryByPlace(places[currentPlayer]));
            questionService.askQuestion(getCategoryByPlace(places[currentPlayer]));
        }

    }

    private QuestionType getCategoryByPlace(int place) {
        if (place == 0 || place == 4 || place == 8) return QuestionType.Pop;
        if (place == 1 || place == 5 || place == 9) return QuestionType.Science;
        if (place == 2 || place == 6 || place == 10) return QuestionType.Sports;
        return QuestionType.Rock;
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                purses[currentPlayer]++;
                System.out.println(playerService.getPlayers().get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == playerService.getNumberOfPlayers()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == playerService.getNumberOfPlayers()) currentPlayer = 0;
                return true;
            }


        } else {

            System.out.println("Answer was corrent!!!!");
            purses[currentPlayer]++;
            System.out.println(playerService.getPlayers().get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == playerService.getNumberOfPlayers()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        Player player = playerService.getPlayers().get(currentPlayer);
        System.out.println(player.getName() + " was sent to the penalty box");
        player.setInPenaltyBox(true);

        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == playerService.getNumberOfPlayers()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}