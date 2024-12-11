package database.users;
import database.util.Displayable;

import java.io.Serializable;

public class Administrator extends User implements Displayable, Serializable {

    public Administrator(String username) {
        super(User.getNextId(), username);
    }

    @Override
    public void display() {
        System.out.println("All students per class");
        System.out.println("All students");
    }

    @Override
    public void showOptions() {
        for (String s : this.options) {
            System.out.println(s);
        }
    }
}
