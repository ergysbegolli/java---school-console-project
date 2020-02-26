package com.company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tutors extends User {
    private String password;
    private ArrayList<Tutors> tutors = new ArrayList<>();

    public Tutors(){
    }

    public Tutors(int id, String name, String surname, String password) throws FileNotFoundException {
        super(id, name, surname);
        this.password = password;
    }

    public void addTutor(int tutor_id, String name, String surname, String password) throws FileNotFoundException {
        tutors.add(new Tutors(tutor_id, name, surname, password)); //add new tutor
        PrintWriter txt = new PrintWriter("tutor.txt");
        for (int y=0; y<tutors.size(); y++){
            int index = y+1;
            txt.println("Tutor " + index);
            txt.println("ID: " + tutors.get(y).getId());
            txt.println("Name: " + tutors.get(y).getName());
            txt.println("Surname: " + tutors.get(y).getSurname());
            txt.println("Password: " + tutors.get(y).password);
            txt.println("--------------------");
            txt.flush();
        } //write tutors from arraylist to txt file
        txt.close();
    }

    @Override
    public int getIndex(int id) {
        for (int i = 0; i < tutors.size(); i++) {
            if (tutors.get(i).getId() == id) {
                return i;
            }
        }
        return super.getIndex();
    } //check if there is a tutor with given id and return its index

    @Override
    public String getFullName (int index) {
        return tutors.get(index).getName() + " "+ tutors.get(index).getSurname();
    } // get tutor full name

    public String getPassword (int index) {
        return tutors.get(index).getPassword();
    }

    public void validatePassword(String password) {
        boolean passContainsNr = false;
        for (int i = 0; i < 10; i++) {
            if (password.contains(Integer.toString(i))) {
                passContainsNr = true;
            }
        }
        if (passContainsNr && password.length() > 7) {
            this.setPassword(password);
            System.out.println("Successfully set password.");
        } else if (password.length() > 7 && !passContainsNr) {
            System.out.println("Password must include a number!");
        } else if (passContainsNr && password.length() < 8) {
            System.out.println("Password must be at least 8 character long.");
        } else {
            System.out.println("Password must have more than 8 characters. It must include a number!");
        }
    } // check password's conditions

    public String getName() {
        return super.getName();
    }

    public String getSurname() {
        return super.getSurname();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

