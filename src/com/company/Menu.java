package com.company;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private static Scanner in = new Scanner(System.in);
    private static Tutors t = new Tutors();
    private static Students s = new Students();
    private static Questions q = new Questions();
    private static int qId = 1;
    private static int TestTaken =0;

    public static void runTest (int index){
        String answer;
        int points = 0;
        int times = 0;
        if (q.getQuestionsArray().size()==0)
            System.out.println("There are no questions");
        else {
            TestTaken++;
            times = s.getStudentsArray().get(index).getTimestakenTest() + 1;
            s.getStudentsArray().get(index).setTimesTakenTest(times);
            System.out.println("Times taken a test: " + times);
        }


        for (int i= 0; i< q.getQuestionsArray().size(); i++){
            System.out.println(i +1 +". " + q.getQuestionsArray().get(i).getquestionText() + "?");
            answer = in.nextLine();
            while (true){
                if (!(answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("no"))) {
                    System.out.println("Please enter yes or no! ");
                    answer = in.nextLine();
                } else break;
            }
            if ((answer.toLowerCase().equals("yes") && q.getQuestionsArray().get(i).isquestionAnswer())||(answer.toLowerCase().equals("no") && !q.getQuestionsArray().get(i).isquestionAnswer()))
                points += q.getQuestionsArray().get(i).getquestionPoints();
        }
        s.getStudentsArray().get(index).setTotalPointsFromTests(points);
        if (q.getQuestionsArray().size()!=0 && s.getStudentsArray().get(index).getTotalPointsFromTests()==0){
            System.out.println("Sorry you failed.");
            s.getStudentsArray().get(index).setFailed(true);
        }


    } // run test

    public static void appMenu() throws FileNotFoundException {
        t.addTutor(1, "default", "tutor", "00000000"); //default tutor for starting the program
        //q.addQuestion(0, "Default question", "yes", 0); //default question to handle index out of bound errors

        while (true) {
            System.out.println("Are you a student or a tutor?");
            String whoRU = in.nextLine();
            if (whoRU.toLowerCase().equals("tutor")) {
                ttMenu();
                break;
            }else if(whoRU.toLowerCase().equals("student")) {
                stMenu();
                break;
            } else{
                System.out.println("Please enter either tutor or student!");
            }
        }
    }

    public static void stMenu() throws  FileNotFoundException{
        int id = 0;
        System.out.println("Please enter your ID:");
        while(true) {
            boolean isIdInt = in.hasNextInt();
            id = in.nextInt();
            if(isIdInt){
                if(s.getIndex(id)==-1){
                    System.out.println("There is no student with ID: "+id);
                    System.out.println("ID: ");
                    in.nextLine();
                    continue;
                }
                System.out.println("Welcome, " + s.getFullName(s.getIndex(id)) + "!");
                break;
            }else{
                System.out.println("Please enter your ID!");
                in.nextLine();
            }
        } //student log in

        while (true){
            System.out.println("Please choose one of the below option: (Option Number)" +
                    "\n1. Display all the questions." +
                    "\n2. Run a test." +
                    "\n3. Display total points." +
                    "\n4. Log out.");
            boolean isInt = in.hasNextInt();
            int option = in.nextInt();
            if (isInt){
                switch (option){
                    case 1:
                        q.getQuestion();
                        break;
                    case 2:
                        runTest(s.getIndex(id));
                        break;
                    case 3:
                        s.displayTPForLoggedInSt(id);
                        break;
                    case 4:
                        System.out.println("Logging out .....");
                        in.nextLine();
                        appMenu();
                        break;
                    default:
                        System.out.println("Please enter 1-4!");
                        continue;
                }
            }else
                System.out.println("Please enter a number!");
        } //student menu
    }

    public static void ttMenu() throws FileNotFoundException {
        System.out.println("Enter your ID: ");
        int id = 0;
        while(true){
            boolean isInt = in.hasNextInt();
            id = in.nextInt();
            if(isInt){
                if(t.getIndex(id)==-1){
                    System.out.println("There is no tutor with ID: "+ id);
                    System.out.println("ID: ");
                    in.nextLine();
                    continue;
                }
                System.out.println("Enter your password;");
                String password = in.next();
                if(password.equals(t.getPassword(t.getIndex(id))))
                    System.out.println("Welcome, "+ t.getFullName(t.getIndex(id)) );
                else {
                    System.out.println("Incorrect password! Try again!");
                    in.nextLine();
                    password = in.next();
                }break;
            }else{
                System.out.println("Please enter your ID!");
                in.nextLine();
            }
        } //tutor log in

        while (true){
            System.out.println("Please choose one of the below option: (Option Number)" +
                    "\n1.Add a new student.\n2.Add a new tutor\n3.Add a new question" +
                    "\n4.Display data for a student given ID\n5.Display all questions" +
                    "\n6.Display best and worst student\n7.Remove a student\n8. Display failed student" +
                    "\n9. Display number of times the test is taken\n10.Log out.");
            boolean isInt = in.hasNextInt();
            int option = in.nextInt();
            if (isInt){
                switch (option){
                    case 1:
                        int stId,year,month,day;
                        String stName, stSurname;
                        System.out.println("Name: ");
                        stName = in.next();
                        System.out.println("Surname: ");
                        stSurname = in.next();
                        System.out.println("Date of Birth: \nDay: ");
                        day = in.nextInt();
                        System.out.println("Month: ");
                        month = in.nextInt();
                        System.out.println("Year: ");
                        year= in.nextInt();
                        LocalDate dateOfBirth = LocalDate.of(year,month,day);
                        java.util.Date date = java.sql.Date.valueOf(dateOfBirth);
                        while (true) {
                            System.out.println("Student ID: ");
                            stId = in.nextInt();
                            if (s.getIndex(stId) == -1) {
                                s.addStudent(stId, stName, stSurname, date, 0, 0, false);
                                break;
                            } else
                                System.out.println("This ID is already registered!");
                        }
                        break;
                    case 2:
                        int ttId;
                        String ttName, ttSurname, password;
                        System.out.println("Name: ");
                        ttName = in.next();
                        System.out.println("Surname: ");
                        ttSurname = in.next();
                        while (true){
                            System.out.println("Password: ");
                            password = in.next();
                            t.validatePassword(password);
                            if (t.getPassword()!=null)
                                break;
                        }
                        while (true) {
                            System.out.println("Tutor ID: ");
                            ttId = in.nextInt();
                            if(t.getIndex(ttId)==-1) {
                                t.addTutor(ttId,ttName,ttSurname,password);
                                break;
                            } else
                                System.out.println("This ID is already registered!");
                        }
                        break;
                    case 3:
                        int points;
                        String qText, qAnswer;
                        System.out.println("Question ID: "+ qId);
                        System.out.println("Question: ");
                        in.nextLine();
                        qText = in.nextLine();
                        System.out.println("Answer: ");
                        qAnswer = in.nextLine();
                        while(true) {
                            if (!(qAnswer.toLowerCase().equals("yes") || qAnswer.toLowerCase().equals("no"))) {
                                System.out.println("Please enter yes or no! ");
                                qAnswer = in.nextLine();
                            } else break;
                        }
                        System.out.println("Points: ");
                        points = in.nextInt();
                        q.addQuestion(qId,qText,qAnswer,points);
                        qId++;
                        break;
                    case 4:
                        int enterId;
                        System.out.println("Enter student ID: ");
                        enterId = in.nextInt();
                        s.getStudent(enterId);
                        break;
                    case 5:
                        q.getQuestion();
                        break;
                    case 6:
                        s.displayBestWorstStudent();
                        break;
                    case 7:
                        int removeId;
                        System.out.println("Student ID: ");
                        removeId = in.nextInt();
                        s.removeStudent(removeId);
                        break;
                    case 8:
                        s.getFailedStudent();
                        break;
                    case 9:
                        System.out.println("Test is taken " + TestTaken + " times.");
                        break;
                    case 10:
                        System.out.println("Logging out .....");
                        in.nextLine();
                        appMenu();
                        break;
                    default:
                        System.out.println("Please enter 1-7!");
                        continue;
                }
            }else
                System.out.println("Please enter a number!");
        } // tutor menu

    }


}
