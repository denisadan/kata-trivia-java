package com.adaptionsoft.games.trivia.runner.data;

public class Question {

    private final QuestionType type;
    private final String content;

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

