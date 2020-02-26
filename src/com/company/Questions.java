package com.company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Questions {
    private int questionId=1;
    private String questionText;
    private boolean questionAnswer;
    private int questionPoints;
    private ArrayList<Questions> questions = new ArrayList<>();

    public Questions(){}

    public Questions(int questionId, String questionText, String questionAnswer, int questionPoints) throws FileNotFoundException {
        this.questionId = questionId;
        this.questionText = questionText;
        validateAnswer(questionAnswer);
        this.questionPoints = questionPoints;
    }

    public void addQuestion(int questionId, String questionText, String questionAnswer, int questionPoints) throws FileNotFoundException {
        questions.add(new Questions(questionId, questionText, questionAnswer, questionPoints)); //add new question
        PrintWriter txt = new PrintWriter("question.txt");
        for(int x = 0; x<questions.size();x++){
            txt.print(questions.get(x).getquestionId()+ ". ");
            txt.println(questions.get(x).getquestionText() + "?");
            txt.println("Answer: "+ questions.get(x).isquestionAnswer());
            txt.println("Points: " + questions.get(x).getquestionPoints());
            txt.println("--------------------");
            txt.flush();

        }  // write questions from arraylist to txt file
        txt.close ();
    }

    public void validateAnswer(String answer){
        if (answer.toLowerCase().equals("yes"))
            setquestionAnswer(true);
        else if (answer.toLowerCase().equals("no"))
            setquestionAnswer(false);
    } // convert yes/no answers to true/false

    public void getQuestion() {
        if (questions.size()==0) // do not count default question
            System.out.println("There are no questions");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(i+1 + ". " + questions.get(i).getquestionText());
        }
    } // show all the questions


    public int getquestionId(){
        return questionId;
    }

    public String getquestionText() {
        return questionText;
    }

    public boolean isquestionAnswer(){
        return questionAnswer;
    }

    public int getquestionPoints() {
        return questionPoints;
    }

    public void setquestionAnswer(boolean questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public ArrayList<Questions> getQuestionsArray(){ return questions;
    }
}
