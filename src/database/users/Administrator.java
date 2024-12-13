package database.users;
import database.Register;
import database.util.Displayable;

import java.io.Serializable;

public class Administrator implements Displayable, Serializable, Users {
    private int ID;
    private String username;
    private Register register;

    public Administrator(String username, Register register, int ID) {
        this.username = username;
        this.register = register;
        this.ID = ID;
    }

    @Override
    public void display() {
        System.out.println("All students per class");
        System.out.println("All students");
    }

    @Override
    public void showOptions() {
    }

    @Override
    public void showSubjects() {
    }

    public void showStudents() {}

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void createUser(UsersTypes type, String username) {
    }
}
