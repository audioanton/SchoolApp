package database.users;
import database.util.Displayable;

import java.io.Serializable;

public class Teacher extends User implements Displayable, Serializable {

    public Teacher(String username) {
        super(User.getNextId(), username);
    }
    @Override
    public void display() {
        System.out.println("All classes");
        System.out.println("All students in class");
    }

    @Override
    public void showOptions() {
        for (String s : this.options) {
            System.out.println(s);
        }
    }
}
