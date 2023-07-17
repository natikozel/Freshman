package com.main.app;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {

    @FXML
    private AnchorPane ap;
    @FXML
    private TextField questionPicked;
    @FXML
    private TextArea resultArea3;
    @FXML
    private TextField amountOfQuestions;
    @FXML
    private MenuButton menubox;
    @FXML
    private TextField correctAnswer;
    @FXML
    private TextField questionText;
    @FXML
    private TextField questionID;
    @FXML
    private TextField questionID2;
    @FXML
    private TextField newText;
    @FXML
    private TextField newAnswer;
    @FXML
    private BorderPane bp;

    @FXML
    private TextField wrnans1;

    @FXML
    private TextField wrnans2;

    @FXML
    private TextField wrnans3;
    @FXML
    private TextField correctAnswer1;

    @FXML
    private TextField questionText1;

    @FXML
    private TextField wrnans4;

    @FXML
    private TextArea resultArea;

    @FXML
    private TextArea resultArea2;


    @FXML
    private Button startbtn;
    private final ArrayList< Question > allQuestions;
    private final ArrayList< Question > newTest;

    public Controller() throws IOException, ClassNotFoundException {
        allQuestions = MainMethods.readEverythingFromBinaryFile();
        newTest = new ArrayList<>();
    }

    void createNewWindow(String view, String title) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(view));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickOpen() throws IOException {
        Stage stage = (Stage) startbtn.getScene().getWindow();
        stage.close();
        createNewWindow("view2.fxml", "Menu");
    }

    @FXML
    void method1(ActionEvent event) throws CloneNotSupportedException {
        print(resultArea, allQuestions);
    }

    @FXML
    void createOpenFieldQuestion(ActionEvent event) throws IOException, IndexOutOfBoundsException {
        if (!Objects.equals(questionText1.getText(), "") && questionText1.getText() != null && correctAnswer1.getText() != null && !Objects.equals(correctAnswer1.getText(), "")) {
            try {
                Question newQuestion = new OpenFieldQuestion(questionText1.getText(), correctAnswer1.getText());
                MainMethods.addQuestion(this.allQuestions, newQuestion);
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);
                JOptionPane.showMessageDialog(null, "Question added and saved.");
                Stage stage = (Stage) bp.getScene().getWindow();
                stage.close();
                createNewWindow("view2.fxml", "Menu");
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "An empty question is illegal. Try again.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "An empty question is illegal. Try again.");
        }
    }

    @FXML
    void createAmericanQuestion(ActionEvent event) throws IOException, IndexOutOfBoundsException {
        ArrayList< String > newList = new ArrayList<>();
        if (correctAnswer.getText() != null && !Objects.equals(correctAnswer.getText(), "")) {
            newList.add(correctAnswer.getText());
        }
        if (wrnans1.getText() != null && !Objects.equals(wrnans1.getText(), "")) {
            newList.add(wrnans1.getText());
        }
        if (wrnans2.getText() != null && !Objects.equals(wrnans2.getText(), "")) {
            newList.add(wrnans2.getText());
        }
        if (wrnans3.getText() != null && !Objects.equals(wrnans3.getText(), "")) {
            newList.add(wrnans3.getText());
        }
        if (wrnans4.getText() != null && !Objects.equals(wrnans4.getText(), "")) {
            newList.add(wrnans4.getText());
        }
        try {
            if (newList.get(0) != null) {
                Question newQuestion = new AmericanQuestion(questionText.getText(), newList);
                MainMethods.addQuestion(this.allQuestions, newQuestion);
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);
                JOptionPane.showMessageDialog(null, "Question added and saved.");
                Stage stage = (Stage) bp.getScene().getWindow();
                stage.close();
                createNewWindow("view2.fxml", "Menu");
            } else {
                JOptionPane.showMessageDialog(null, "An empty question is illegal. Try again.");
            }

        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "An empty question is illegal. Try again.");
        }
    }

    @FXML
    void method2(ActionEvent event) throws IOException {
        Stage stage = (Stage) menubox.getScene().getWindow();
        stage.close();
        createNewWindow("method2.fxml", "Create new Question");
    }

    @FXML
    void method4(ActionEvent event) throws IOException {
        Stage stage = (Stage) menubox.getScene().getWindow();
        stage.close();
        createNewWindow("method4.fxml", "Create exam manually");
    }

    @FXML
    void addQuestion(ActionEvent event) {
        if (questionPicked.getText() != null && !Objects.equals(questionPicked.getText(), "")) {
            questionPicked.setText(String.valueOf(Integer.parseInt(questionPicked.getText()) - 1));
            if (Integer.parseInt(questionPicked.getText()) < 0 || Integer.parseInt(questionPicked.getText()) > allQuestions.size() - 1) {
                JOptionPane.showMessageDialog(null, "Wrong input! Try again");
            } else {
                try {
                    for (int i = 0; i < newTest.size(); i++) {
                        if (newTest.get(i).equals(allQuestions.get(Integer.parseInt(questionPicked.getText()))))
                            throw new IOException();
                    }
                    newTest.add(allQuestions.get(Integer.parseInt(questionPicked.getText())));
                    JOptionPane.showMessageDialog(null, "Question added successfully, continue.");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "You already chose this question! try a different one.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a question's position");
        }
        questionPicked.clear();
    }

    @FXML
    void createAutomaticExam(ActionEvent event) throws IOException, CloneNotSupportedException {
        if (amountOfQuestions.getText() == null || Objects.equals(amountOfQuestions.getText(), "") || Integer.parseInt(amountOfQuestions.getText()) < 1 || Integer.parseInt(amountOfQuestions.getText()) > allQuestions.size()) {
            JOptionPane.showMessageDialog(null, "Please enter a number in range of available questions in the Repository.");
        } else if (newTest.size() >= allQuestions.size()) {
            JOptionPane.showMessageDialog(null, "There are no other questions left in the Repository...");
        } else {
            for (int i = 0; i < Integer.parseInt(amountOfQuestions.getText()); i++) {
                automaticExam(i, allQuestions, newTest);
            }
            try {
                MainMethods.selectionSort(newTest);
                MainMethods.sortByLetters(newTest);
                MainMethods.createTxtFiles(newTest);
                JOptionPane.showMessageDialog(null, "Exam created successfully. Files saved!");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error occurred, Try again!");
            }
        }
    }

    static void automaticExam(int i, ArrayList< Question > allQuestions, ArrayList< Question > newTest) throws
            CloneNotSupportedException {
        do {
            int randomQuestion;
            boolean res = true;
            do {
                randomQuestion = (int) (Math.random() * (allQuestions.size()));
                try {
                    if (newTest.size() == 0)
                        throw new MyException();
                    for (int j = 0; j < newTest.size(); j++) {
                        if (newTest.get(j).equals(allQuestions.get(randomQuestion))) {
                            break;
                        } else if (j == newTest.size() - 1 && !(newTest.get(j).equals(allQuestions.get(randomQuestion)))) {
                            res = false;
                        }
                    }
                } catch (MyException e) {
                    res = false;
                }
            } while (res);

            if (allQuestions.get(randomQuestion) instanceof AmericanQuestion that) {
                AmericanQuestion newQuestion = new AmericanQuestion(that.getQuestionText());
                newQuestion.setRightAnswer(that.getRightAnswer());
                for (int j = 0; j <= 3; j++) {
                    boolean bolli = true;
                    do {
                        String randomString = that.getSpecificAnswer((int) (Math.random() * that.getMultipleAnswers().size()));
                        try {
                            if (newQuestion.getMultipleAnswers().size() == 0)
                                throw new MyException();
                            for (int k = 0; k < newQuestion.getMultipleAnswers().size(); k++) {
                                if (newQuestion.getMultipleAnswers().get(k).equals(randomString)) {
                                    break;
                                } else if (k == newQuestion.getMultipleAnswers().size() - 1 && !(newQuestion.getMultipleAnswers().get(k).equals(randomString))) {
                                    newQuestion.setSpecificAnswer(randomString);
                                    if (newQuestion.getSpecificAnswer(j) != null)
                                        if (newQuestion.getSpecificAnswer(j).equals((that.getRightAnswer()))) {
                                            newQuestion.setRightAnswer(newQuestion.getSpecificAnswer((j)));
                                        }
                                    bolli = false;
                                }
                            }
                        } catch (MyException e) {
                            bolli = false;
                            newQuestion.setSpecificAnswer(randomString);
                        }
                    } while (bolli);
                }
                MainMethods.addAmericanQuestionLastAnswers(newQuestion);
                newTest.add(i, newQuestion);
            } else if (allQuestions.get(randomQuestion) instanceof OpenFieldQuestion) {
                newTest.add(i, allQuestions.get(randomQuestion));
            }
        } while (newTest.get(i) == null);
    }

    @FXML
    void createManualExam(ActionEvent event) throws IOException, CloneNotSupportedException {
        try {
            if (newTest.get(0) == null)
                throw new IndexOutOfBoundsException();
            MainMethods.sortByLetters(newTest);
            MainMethods.createTxtFiles(newTest);
            JOptionPane.showMessageDialog(null, "Exam created successfully. Files saved!");
        } catch (FileNotFoundException | IndexOutOfBoundsException  e) {
            JOptionPane.showMessageDialog(null, "Creation failed because no questions were selected!");
        }
    }

    @FXML
    void showNewTest(ActionEvent event) throws CloneNotSupportedException {
        if (newTest.size() == 0)
            JOptionPane.showMessageDialog(null, "Add at least one question first!");
        print(resultArea2, newTest);
    }

    @FXML
    void showNewTest2(ActionEvent event) throws CloneNotSupportedException {
        if (newTest.size() == 0)
            JOptionPane.showMessageDialog(null, "Add at least one question first!");
        print(resultArea3, newTest);
    }


    private void print(TextArea resultArea2, ArrayList< Question > newTest) throws
            CloneNotSupportedException {
        resultArea2.setWrapText(true);
        int k = 1;
        StringBuilder sb = new StringBuilder("");
        for (Question question : newTest) {
            if (question != null)
                sb.append(k++).append(") ").append(question.showAllQuestionAndAnswer());
            resultArea2.setText(String.valueOf(sb));

        }
    }

    @FXML
    void method3(ActionEvent event) throws IOException {
        Stage stage = (Stage) menubox.getScene().getWindow();
        stage.close();
        createNewWindow("method3.fxml", "Update Question/Answer");
    }

    @FXML
    void updateAnswer(ActionEvent event) throws NumberFormatException {
        try {
            if (MainMethods.IDExist(questionID2.getText(), allQuestions) != -1 && allQuestions.get(MainMethods.IDExist(questionID2.getText(), allQuestions)) instanceof OpenFieldQuestion that) {
                that.setAnswer(newAnswer.getText());
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);
                JOptionPane.showMessageDialog(null, "Answer updated successfully!");
            } else if (MainMethods.IDExist(questionID2.getText(), allQuestions) != -1 && allQuestions.get(MainMethods.IDExist(questionID2.getText(), allQuestions)) instanceof AmericanQuestion that) {
                that.setRightAnswer(newAnswer.getText());
                that.setSpecificAnswer(newAnswer.getText(), 0);
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);
                JOptionPane.showMessageDialog(null, "Answer updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "ID doesn't exist! Try again!");
            }
        } catch (NumberFormatException | IOException e) {
            JOptionPane.showMessageDialog(null, "Wrong input, try again!");
        }
    }

    @FXML
    void returnToMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) bp.getScene().getWindow();
        stage.close();
        createNewWindow("view2.fxml", "Menu");
    }

    @FXML
    void returnToMenu2(ActionEvent event) throws IOException {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
        createNewWindow("view2.fxml", "Menu");
    }


    @FXML
    void updateQuestion(ActionEvent event) {
        try {
            if (MainMethods.IDExist(questionID.getText(), allQuestions) != -1) {
                allQuestions.get(MainMethods.IDExist(questionID.getText(), allQuestions)).setQuestionText(newText.getText());
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);
                JOptionPane.showMessageDialog(null, "Question updated successfully!");
            } else
                JOptionPane.showMessageDialog(null, "ID doesn't exist! Try again!");
        } catch (NumberFormatException | IOException e) {
            JOptionPane.showMessageDialog(null, "Wrong input, try again!");
        }
    }


    @FXML
    void removeAnswer(ActionEvent event) {
        try {
            if (MainMethods.IDExist(questionID2.getText(), allQuestions) != -1 && allQuestions.get(MainMethods.IDExist(questionID2.getText(), allQuestions)) instanceof OpenFieldQuestion that) {
                ((OpenFieldQuestion) allQuestions.get(MainMethods.IDExist(questionID2.getText(), allQuestions))).setAnswer(null);
                JOptionPane.showMessageDialog(null, "Answer removed successfully!");
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);

            } else if (MainMethods.IDExist(questionID2.getText(), allQuestions) != -1 && allQuestions.get(MainMethods.IDExist(questionID2.getText(), allQuestions)) instanceof AmericanQuestion that) {
                ((AmericanQuestion) allQuestions.get(MainMethods.IDExist(questionID2.getText(), allQuestions))).removeSpecificAnswer(0);
                JOptionPane.showMessageDialog(null, "Answer removed successfully!");
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);

            } else
                JOptionPane.showMessageDialog(null, "ID doesn't exist! Try again!");
        } catch (NumberFormatException | IOException e) {
            JOptionPane.showMessageDialog(null, "Wrong input, try again!");
        }
    }

    @FXML
    void method5(ActionEvent actionevent) throws CloneNotSupportedException, IOException {
        clone(allQuestions);
        print(resultArea, allQuestions);
    }

    static void clone(ArrayList< Question > allQuestions) throws CloneNotSupportedException, IOException {
        ArrayList< Question > newQuestions = new ArrayList<>();
        for (int i = 0; i < allQuestions.size(); i++) {
            newQuestions.add(i, allQuestions.get(i).clone());
        }
        MainMethods.createTxtFiles(newQuestions);
        JOptionPane.showMessageDialog(null, "Exam was cloned and saved into Files Repository!");
    }

    public void method9(ActionEvent actionEvent) throws IOException {
        Platform.exit();
        MainMethods.saveEverythingIntoBinaryFile(allQuestions);
    }

}