package com.adaptionsoft.games.trivia.runner;

public class Question {

    private final QuestionType type;
    private final String content;

    public enum QuestionType {
        POP("Pop"),
        SCIENCE("Science"),
        SPORTS("Sports"),
        ROCK("Rock");

        private final String label;

        QuestionType(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public Question(QuestionType type, String content) {
        this.type = type;
        this.content = content;
    }

    public QuestionType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

}

