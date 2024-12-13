package database;

import database.users.Administrator;
import database.users.Student;
import database.users.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Register {
    private int lastUsedID;
    private List<Administrator> admins;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Subject> subjects;

    public Register() {
    }

    public void initRegister() {
        admins = new ArrayList<>();
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        lastUsedID = 1111;

        //replace with factory-pattern, for people and subject
        admins.add(new Administrator("Sara", this, getNextUniqueID()));

        teachers.add(new Teacher("Bengt", subjects, getNextUniqueID()));
        teachers.add(new Teacher("Simon", subjects, getNextUniqueID()));
        teachers.add(new Teacher("Jenny", subjects, getNextUniqueID()));

        students.add(new Student("Bernie", subjects, getNextUniqueID()));
        students.add(new Student("Durnie", subjects, getNextUniqueID()));
        students.add(new Student("Jamie", subjects, getNextUniqueID()));

    }

    public List<Administrator> getAdmins() {
        return admins;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public int getNextUniqueID() {return ++lastUsedID;}
}
