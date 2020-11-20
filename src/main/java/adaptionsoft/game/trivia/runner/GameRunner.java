package adaptionsoft.game.trivia.runner;

import adaptionsoft.game.trivia.uglytrivia.Game;

import java.util.Random;

public class GameRunner {

    public static void main(String[] args) {
        Game game = new Game();

        game.addPlayer("Chet");
        game.addPlayer("Pat");
        game.addPlayer("Sue");
        game.initGame();

        Random rand = new Random();

        boolean notAWinner;

        do {
            game.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = game.answerIsWrong();
            } else {
                notAWinner = game.answerIsRight();
            }

        } while (notAWinner);

    }

}