package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.trivia.runner.Question.QuestionType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameHandlerService {

    private LinkedList<Question> popQuestions = new LinkedList<Question>();
    private LinkedList<Question> rockQuestions = new LinkedList<Question>();
    private LinkedList<Question> sportsQuestions = new LinkedList<Question>();
    private LinkedList<Question> scienceQuestions = new LinkedList<Question>();

    private ArrayList<Player> players = new ArrayList<Player>();

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
        LinkedList<Question> questionsByType = getQuestionsByType(type);
        questionsByType.removeFirst();
    }

    public void addQuestion(Question q) {
        LinkedList<Question> questionsByType = getQuestionsByType(q.getType());
        questionsByType.addLast(q);
    }

    private LinkedList<Question> getQuestionsByType(QuestionType type) {
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
