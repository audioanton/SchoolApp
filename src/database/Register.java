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

    public void setUsers(List<Users> users) {this.users = users;}

    public List<Subject> getSubjects() {return subjects;}

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
