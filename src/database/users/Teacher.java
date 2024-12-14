package database.users;
import database.Register;
import database.Subject;

import java.io.Serializable;
import java.util.List;

public class Teacher implements Serializable, Users {
    private final String username;
    private final int ID;

    public Teacher(String username, int ID) {
        this.username = username;
        this.ID = ID;
    }

    @Override
    public void showOptions() {
        System.out.println("Teacher menu:");
        System.out.println("1 = Save and Exit");
    }

    @Override
    public void showSubjects(List<Subject> subjects) {

    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void showAssignments(List<Subject> subjects) {
        Users.super.showAssignments(subjects);
    }

    @Override
    public void createAssignment(Register register) {

    }

    @Override
    public void setGrade(Register register) {
    }
}
