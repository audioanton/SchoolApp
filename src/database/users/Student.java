package database.users;
import database.util.Displayable;

import java.io.Serializable;

public class Student extends User implements Displayable, Serializable {

    public Student(String username) {
        super(User.getNextId(), username);
    }

    @Override
    public void display() {
        System.out.println("All subjects");
        System.out.println("All assignments");
    }

    @Override
    public void showOptions() {
        for (String s : this.options) {
            System.out.println(s);
        }
    }

}
