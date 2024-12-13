package database.users;
import database.Subject;
import database.util.Displayable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Student implements Displayable, Serializable, Users {
    HashMap<String, String> guardians;
    List<Subject> subjects;


    public Student(String username, List<Subject> subjects, int ID) {

    }

    public void addGuardian(String name, String email) {
        if (guardians == null)
            guardians = new HashMap<>();
        guardians.put(name, email);
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

    @Override
    public void display() {

    }

    @Override
    public void showOptions() {
    }

}
