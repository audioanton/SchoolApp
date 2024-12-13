package database.users;
import database.Subject;
import database.util.Displayable;

import java.io.Serializable;
import java.util.List;

public class Teacher implements Displayable, Serializable, Users {
    private String username;
    private int ID;
    private List<Subject> subjects;

    public Teacher(String username, List<Subject> subjects, int ID) {
        this.username = username;
        this.subjects = subjects;
        this.ID = ID;
    }

    @Override
    public void display() {
        System.out.println("All subjects");
        System.out.println("All students per subject");
    }

    @Override
    public void showOptions() {
    }

    @Override
    public void showSubjects() {
    }

    public void showAssignments() {

    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public String getUsername() {
        return "";
    }
}
