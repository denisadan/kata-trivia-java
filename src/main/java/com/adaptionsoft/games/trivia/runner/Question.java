package com.adaptionsoft.games.trivia.runner;

public class Question {

    private QuestionType type;
    private String content;

    public enum QuestionType {
        POP, SCIENCE, SPORTS, ROCK
    }

    public Question(QuestionType type, String content) {
        this.type = type;
        this.content = content;
    }

    public QuestionType getType() {
        return type;
    }
}

