package database;

import database.users.Users;

import java.util.ArrayList;
import java.util.List;

public class Register {
    private List<Users> users;
    private List<Subject> subjects;

    public Register() {
        this.users = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    public List<Users> getUsers() {return users;}

    public List<Subject> getSubjects() {return subjects;}
}
