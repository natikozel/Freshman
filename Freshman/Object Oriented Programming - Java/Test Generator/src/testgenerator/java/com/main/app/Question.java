package com.main.app;

import java.io.FileNotFoundException;
import java.io.Serializable;

public abstract class Question implements Cloneable, Serializable, Comparable< Question > {
    //public static final long SerialVersionUID = -1688495918374302795L;
    private int id;
    private static int NUMBER_OF_INSTANCES = 1001;
    private String questionText;

    public Question(String questionText) {
        this.id = NUMBER_OF_INSTANCES++;
        this.questionText = questionText;
    }

    public abstract void addAnswer(String answer);

    public int getId() {
        return id;
    }

    public boolean setId(int id) {
        this.id = id;
        return this.id == id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean setQuestionText(String questionText) {
        this.questionText = questionText;
        return this.questionText.equals(questionText);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question question)) return false;
        return getQuestionText().equals(question.getQuestionText());
    }

    public int compareTo(Question question) {
        return questionText.compareTo(question.questionText);
    }


    @Override
    public String toString() {
        return "Q(" + getId() + "): " + getQuestionText() + " \n";
    }

    public abstract StringBuilder showAllQuestionAndAnswer() throws CloneNotSupportedException;

    public abstract boolean compareLettersLength(Question a) throws FileNotFoundException, CloneNotSupportedException;

    @Override
    public Question clone() throws CloneNotSupportedException {
        return (Question) super.clone();
    }
}
