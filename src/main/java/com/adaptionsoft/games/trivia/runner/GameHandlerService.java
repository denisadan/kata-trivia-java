package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.trivia.runner.Question.QuestionType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameHandlerService {

    private final List<Question> popQuestions;
    private final List<Question> rockQuestions;
    private final List<Question> sportsQuestions;
    private final List<Question> scienceQuestions;
    private final List<Player> players;

    public GameHandlerService() {
        popQuestions = new LinkedList<Question>();
        rockQuestions = new LinkedList<Question>();
        sportsQuestions = new LinkedList<Question>();
        scienceQuestions = new LinkedList<Question>();
        players = new ArrayList<Player>();
    }

    public List<Question> getPopQuestions() {
        return popQuestions;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public void askQuestion(QuestionType type) {
        LinkedList<Question> questionsByType = (LinkedList<Question>) getQuestionsByType(type);
        questionsByType.removeFirst();
    }

    public void addQuestion(Question q) {
        LinkedList<Question> questionsByType = (LinkedList<Question>) getQuestionsByType(q.getType());
        questionsByType.addLast(q);
    }

    public QuestionType getQuestionCategory(int place) {
        if (place == 0 || place == 4 || place == 8) return QuestionType.POP;
        if (place == 1 || place == 5 || place == 9) return QuestionType.SCIENCE;
        if (place == 2 || place == 6 || place == 10) return QuestionType.SPORTS;
        return QuestionType.ROCK;
    }

    private List<Question> getQuestionsByType(QuestionType type) {
        switch (type) {
            case POP:
                return popQuestions;
            case ROCK:
                return rockQuestions;
            case SPORTS:
                return sportsQuestions;
            case SCIENCE:
                return scienceQuestions;
            default:
                return new LinkedList<Question>();
        }

    }

}
