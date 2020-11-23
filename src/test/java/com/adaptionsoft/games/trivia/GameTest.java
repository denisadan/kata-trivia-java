package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.oldrunner.GameOriginal;
import com.adaptionsoft.games.trivia.runner.service.Game;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static junit.framework.Assert.assertEquals;

public class GameTest {

    @Test
    public void caracterizationTest() {
        for (int seed = 1; seed < 10_000; seed++) {
            String actualOutput = extractOutput(new Random(seed), new Game());
            String expectedOutput  = extractOutput(new Random(seed), new GameOriginal());
            assertEquals(expectedOutput, actualOutput);
        }
    }

    private String extractOutput(Random rand, IGame aGame) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PrintStream inmemory = new PrintStream(baos)) {
            System.setOut(inmemory);

            aGame.add("Chet");
            aGame.add("Pat");
            aGame.add("Sue");

            boolean notAWinner;
            do {
                aGame.roll(rand.nextInt(5) + 1);

                if (rand.nextInt(9) == 7) {
                    notAWinner = aGame.answerIsWrong();
                } else {
                    notAWinner = aGame.answerIsRight();
                }

            } while (notAWinner);
        }
        return new String(baos.toByteArray());
    }

}
