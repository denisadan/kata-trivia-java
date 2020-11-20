package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.trivia.runner.Question.QuestionType;

import java.util.LinkedList;
import java.util.List;

public class QuestionService {

    private LinkedList<Question> popQuestions = new LinkedList<Question>();
    private LinkedList<Question> rockQuestions = new LinkedList<Question>();
    private LinkedList<Question> sportsQuestions = new LinkedList<Question>();
    private LinkedList<Question> scienceQuestions = new LinkedList<Question>();

    public void askQuestion(QuestionType type) {
        LinkedList<Question> questionsByType = getQuestionsByType(type);
        questionsByType.removeFirst();
    }

    public void addQuestion(Question q) {
        LinkedList<Question> questionsByType = getQuestionsByType(q.getType());
        questionsByType.addLast(q);
    }

    private LinkedList<Question> getQuestionsByType(QuestionType type) {
        switch (type) {
            case Pop:
                return popQuestions;
            case Rock:
                return rockQuestions;
            case Sports:
                return sportsQuestions;
            case Science:
                return scienceQuestions;
            default:
                return new LinkedList<Question>();
        }

    }

    public List<Question> getPopQuestions() {
        return popQuestions;
    }

}
