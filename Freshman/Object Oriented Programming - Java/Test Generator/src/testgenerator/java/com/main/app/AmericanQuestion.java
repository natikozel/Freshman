package com.main.app;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;


public class AmericanQuestion extends Question implements Serializable {
    private final MySet< String > multipleAnswers;
    private String rightAnswer;


    public AmericanQuestion(String questionText, String rightAnswer) {
        super(questionText);
        this.rightAnswer = rightAnswer;
        this.multipleAnswers = new MySet< String >();
        //this.multipleAnswers = new ArrayList<>(10);
        multipleAnswers.add((int) (Math.random() * multipleAnswers.size()), rightAnswer);
    }

    public AmericanQuestion(String questionText) {
        super(questionText);
        this.multipleAnswers = new MySet< String >();
        //this.multipleAnswers = new ArrayList< String >(10);
        this.rightAnswer = "";
    }

    /*public AmericanQuestion(String questionText, String[] answers) {
        super(questionText);
        this.multipleAnswers = answers;
        this.rightAnswer = answers[ 0 ];
    }*/

    public AmericanQuestion(String questionText, ArrayList< String > answers) {
        super(questionText);
        this.multipleAnswers = new MySet< String >();
        this.multipleAnswers.addAll(answers);
        this.rightAnswer = answers.get(0);
    }


    @Override
    public void addAnswer(String answer) {
        this.multipleAnswers.add(answer);
    }

    public void removeSpecificAnswer(int index) {
        this.multipleAnswers.remove(index);
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public boolean setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
        return this.rightAnswer.equals(rightAnswer);
    }

    public String getSpecificAnswer(int index) throws CloneNotSupportedException {
        this.multipleAnswers.clone();
        if (!(this.multipleAnswers.isEmpty())) { //new
            return (String) this.multipleAnswers.get(index);
        } else { //new
            return "null";
        }
    }

    public void setSpecificAnswer(String answer) {
        this.multipleAnswers.add(answer);
    }

    public void setSpecificAnswer(String answer, int i) {
        this.multipleAnswers.set(answer, i);
    }

    public MySet< String > getMultipleAnswers() throws CloneNotSupportedException {
        return this.multipleAnswers;
    }

    @Override
    public StringBuilder showAllQuestionAndAnswer() throws CloneNotSupportedException {// Method 1
        StringBuilder sb = new StringBuilder();
        char ch = 'a';
        sb.append("(ID = ").append(super.getId()).append(") ").append(this.getQuestionText()).append("  (").append(this.getRightAnswer()).append(")\n");
        for (int j = 0; j < this.getMultipleAnswers().size(); j++) {
            if (this.getMultipleAnswers().get(j) != null) {
                sb.append("     ").append(ch++).append(") ").append(this.getMultipleAnswers().get(j)).append("  (").append(this.checkAnswer((String) this.getMultipleAnswers().get(j))).append(")\n");
            }
        }
        return sb;
    }

    public boolean checkAnswer(String answer) {
        return this.rightAnswer.equals(answer);
    }

    /*public boolean setMultipleAnswers(ArrayList< String > multipleAnswers) {
        this.multipleAnswers = multipleAnswers;
        return this.multipleAnswers.equals(multipleAnswers);
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AmericanQuestion that)) return false;
        if (!super.equals(o)) return false;
        return getQuestionText().equals(that.getQuestionText());
    }

    @Override
    public String toString() {
        return super.toString() +
                "Answers=" + (multipleAnswers.toString());
    }

    @Override
    public boolean compareLettersLength(Question q) throws FileNotFoundException, CloneNotSupportedException {
        int counter = 0, counter2 = 0;
        for (int i = 0; i < this.getMultipleAnswers().size(); i++)
            counter += (int) ((String) this.getMultipleAnswers().get(i)).chars().count();
        if (q instanceof OpenFieldQuestion that)
            if (that.getAnswer() == null)
                return counter < 0;
            else
                return counter < (int) that.getAnswer().chars().count();
        else if (q instanceof AmericanQuestion that)
            for (int i = 0; i < that.getMultipleAnswers().size(); i++)
                counter2 += (int) ((String) that.getMultipleAnswers().get(i)).chars().count();
        return counter < counter2;
    }
}
