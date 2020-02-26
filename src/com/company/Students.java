package com.company;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Nasty
 */
public class Students extends User {

    private Date dateOfBirth ;
    private int totalPointsFromTests;
    private int timesTakenTest;
    private boolean failed;
    private ArrayList<Students> students = new ArrayList<>();

    public Students(){
    }

    public Students(int id, String name, String surname,Date dateOfBirth, int totalPointsFromTests, int timesTakenTest, boolean failed) throws FileNotFoundException {

        super(id, name, surname);
        this.dateOfBirth = dateOfBirth;
        this.timesTakenTest = timesTakenTest;
        this.totalPointsFromTests = totalPointsFromTests;
        this.failed = failed;
    }

    public void addStudent(int student_id, String name, String surname, Date birthday, int totalPointsFromTests, int timesTakenTest, boolean failed) throws FileNotFoundException {
        students.add(new Students(student_id, name, surname, birthday, totalPointsFromTests, timesTakenTest, failed)); //add new student
        PrintWriter txt = new PrintWriter("student.txt");
        for(int z=0;z<students.size();z++){
            int index = z+1;
            txt.println("Student: " + index);
            txt.println("ID: " + students.get(z).getStudent_id());
            txt.println("Name: " + students.get(z).getName());
            txt.println("Surname: "+students.get(z).getSurname());
            txt.println("Date of birth: "+ students.get(z).getDateOfBirth());
            txt.println("Total points: "+students.get(z).getTotalPointsFromTests());
            txt.println("Times taken test: " + students.get(z).getTimestakenTest());
            txt.println("--------------------");
            txt.flush();
        } //write student from arraylist to txt file
        txt.close ();
    }

    public void removeStudent (int id){
        if (getIndex(id)!=-1)
        students.remove(getIndex(id));
        else System.out.println("There is no student with ID: " + id);
    }

    @Override
    public int getIndex(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudent_id() == id) {
                return i;
            }
        }
        return super.getIndex();
    }

    @Override
    public String getFullName(int index) {
        return students.get(index).getName() + " " +students.get(index).getSurname();
    }

    public void getStudent(int id) {
        int i = getIndex(id);
        if (i != -1) {
            System.out.println("Id: " + id + "\nName: " + students.get(i).getName()
                    + "\nSurname: " + students.get(i).getSurname() + "\nDate of birth: "
                    + students.get(i).getDateOfBirth()
                    + "\nTotal points: " + students.get(i).getTotalPointsFromTests());
        } else
            System.out.println("There is no student with id: " + id);
    } //show student data by given student id

    public void getFailedStudent (){
        if (students.size()==0)
            System.out.println("There are no students");
        else
            System.out.println("Failed students: ");
        for (int i = 0; i<students.size(); i++){
            if (students.get(i).isFailed())
                System.out.println(getFullName(i));
        }
    }

    public void displayTPForLoggedInSt(int id) {
        int index = getIndex(id);
        System.out.println("Your total points are: "+students.get(index).getTotalPointsFromTests());
    } // show total points for logged in student

    public void displayBestWorstStudent() {
        if (students.size()==0)
            System.out.println("There are no students registered");
        else{
            int min = students.get(0).getTotalPointsFromTests();
            int max = students.get(0).getTotalPointsFromTests();

            int indexMax = 0;
            int indexMin = 0;

            for (int i = 0; i < students.size(); i++) {
                if (max < students.get(i).getTotalPointsFromTests()) {
                    max = students.get(i).getTotalPointsFromTests();
                    indexMax = i;
                }
                if (min > students.get(i).getTotalPointsFromTests()) {
                    min = students.get(i).getTotalPointsFromTests();
                    indexMin = i;
                }
            }
            System.out.println("Best student is " + students.get(indexMax).getName() + " " + students.get(indexMax).getSurname()
                    + ": " + max + "points.\nWorst student is " + students.get(indexMin).getName() + " " + students.get(indexMin).getSurname()
                    + ": " + min + "points.");
        }
    }// display student with highest/lowest points


    public int getStudent_id() {
        return super.getId();
    }

    public String getName() {
        return super.getName();
    }

    public String getSurname() {
        return super.getSurname();
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getTotalPointsFromTests() {
        return totalPointsFromTests;
    }

    public void setTotalPointsFromTests(int totalPointsFromTests) {
        this.totalPointsFromTests = totalPointsFromTests;
    }

    public int getTimestakenTest(){
        return timesTakenTest;
    }

    public void setTimesTakenTest(int timesTakenTest) {
        this.timesTakenTest = timesTakenTest;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public ArrayList<Students> getStudentsArray(){return students;}

}//end of class
