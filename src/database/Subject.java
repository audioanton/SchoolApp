package database;


import java.io.Serializable;
import java.util.HashMap;

public class Subject implements Serializable {
    private String title;
    private int teacherID;
    private HashMap<Integer, Result> studentResults;
    private Assignment assignment;

    public Subject(String title, int teacherID) {
        studentResults = new HashMap<>();
        this.title = title;
        this.teacherID = teacherID;
    }

    public int getTeacherID() {return teacherID;}

    public HashMap<Integer, Result> getStudentResults() {return studentResults;}

    public Assignment getAssignment() {return assignment;}
}
