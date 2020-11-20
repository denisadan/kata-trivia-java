package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.Question;
import com.adaptionsoft.games.trivia.runner.GameHandlerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class GameHandlerServiceTest {

	private final GameHandlerService gameHandlerService = new GameHandlerService();

	@Test
	public void trueQuestionsSize() {
		initialiseQuestions();
		assertEquals(50, gameHandlerService.getPopQuestions().size());
		gameHandlerService.askQuestion(Question.QuestionType.POP);
		assertEquals(49, gameHandlerService.getPopQuestions().size());
		gameHandlerService.addQuestion(new Question(Question.QuestionType.POP, "Popppppp!"));
		assertEquals(50, gameHandlerService.getPopQuestions().size());
	}

	private void initialiseQuestions() {
		for (int i = 0; i < 50; i++) {
			gameHandlerService.addQuestion(new Question(Question.QuestionType.POP, String.valueOf(i)));
			gameHandlerService.addQuestion(new Question(Question.QuestionType.ROCK, String.valueOf(i)));
			gameHandlerService.addQuestion(new Question(Question.QuestionType.SCIENCE, String.valueOf(i)));
			gameHandlerService.addQuestion(new Question(Question.QuestionType.SPORTS, String.valueOf(i)));
		}
	}
}
