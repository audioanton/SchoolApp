package database;

import database.users.Users;
import java.util.ArrayList;
import java.util.List;

public class Register {
    private List<Users> users;
    private List<Subject> subjects;

    private static volatile Register instance;

    private Register() {
        this.users = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    public static Register getInstance() {
        Register result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Register.class) {
            if (instance == null) {
                return new Register();
            }
            return instance;
        }
    }

    public List<Users> getUsers() {return users;}

    public void setUsers(List<Users> users) {this.users = users;}

    public List<Subject> getSubjects() {return subjects;}

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
