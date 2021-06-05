package com.example.personalitytest;

public class Question {
    private String question,answerA,answerB;

    public Question() {
    }

    public Question(String question, String answerA, String answerB) {
        this.question= question;
        this.answerA = answerA;
        this.answerB = answerB;

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }
}
