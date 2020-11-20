package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.Question;
import com.adaptionsoft.games.trivia.runner.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class QuestionServiceTest {

	private final QuestionService questionService = new QuestionService();

	@Test
	public void trueQuestionsSize() {
		initialiseQuestions();
		assertEquals(50, questionService.getPopQuestions().size());
		questionService.askQuestion(Question.QuestionType.Pop);
		assertEquals(49, questionService.getPopQuestions().size());
		questionService.addQuestion(new Question(Question.QuestionType.Pop, "Popppppp!"));
		assertEquals(50, questionService.getPopQuestions().size());
	}

	private void initialiseQuestions() {
		for (int i = 0; i < 50; i++) {
			questionService.addQuestion(new Question(Question.QuestionType.Pop, String.valueOf(i)));
			questionService.addQuestion(new Question(Question.QuestionType.Rock, String.valueOf(i)));
			questionService.addQuestion(new Question(Question.QuestionType.Science, String.valueOf(i)));
			questionService.addQuestion(new Question(Question.QuestionType.Sports, String.valueOf(i)));
		}
	}
}
