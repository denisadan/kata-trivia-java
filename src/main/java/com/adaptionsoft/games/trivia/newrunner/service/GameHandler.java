package com.adaptionsoft.games.trivia.newrunner.service;

import com.adaptionsoft.games.trivia.newrunner.data.Player;
import com.adaptionsoft.games.trivia.newrunner.data.Question;
import com.adaptionsoft.games.trivia.newrunner.data.QuestionType;

import java.util.*;

import static com.adaptionsoft.games.trivia.newrunner.data.QuestionType.*;

public class GameHandler {
    private final Map<QuestionType, LinkedList<Question>> questionsMap = new EnumMap<>(QuestionType.class);
    private final List<Player> players;

    public GameHandler() {
        questionsMap.put(POP, new LinkedList<>());
        questionsMap.put(ROCK, new LinkedList<>());
        questionsMap.put(SCIENCE, new LinkedList<>());
        questionsMap.put(SPORTS, new LinkedList<>());
        players = new ArrayList<>();
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

    public Question askQuestion(QuestionType type) {
        LinkedList<Question> questionsByType = getQuestionsByType(type);
        return questionsByType.removeFirst();
    }

    public void addQuestion(Question q) {
        LinkedList<Question> questionsByType = getQuestionsByType(q.getType());
        questionsByType.addLast(q);
    }

    public void getAQuestion(int playerPlace) {
        QuestionType category = getQuestionCategory(playerPlace);
        System.out.println("The category is " + category.getLabel() + "\n" + askQuestion(category).getContent());
    }

    private QuestionType getQuestionCategory(int place) {
        if (place == 0 || place == 4 || place == 8) return POP;
        if (place == 1 || place == 5 || place == 9) return SCIENCE;
        if (place == 2 || place == 6 || place == 10) return SPORTS;
        return ROCK;
    }

    private LinkedList<Question> getQuestionsByType(QuestionType type) {
        return switch (type) {
            case POP -> questionsMap.get(POP);
            case ROCK -> questionsMap.get(ROCK);
            case SPORTS -> questionsMap.get(SPORTS);
            case SCIENCE -> questionsMap.get(SCIENCE);
        };
    }

}
