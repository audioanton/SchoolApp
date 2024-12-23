package data;


import java.io.Serializable;
import java.util.HashMap;

public class Subject implements Serializable, Comparable<Subject> {
    private String title;
    private int teacherID;
    private HashMap<Integer, Result> studentResults;
    private Assignment assignment;

    public Subject(String title, int teacherID) {
        studentResults = new HashMap<>();
        this.title = title;
        this.teacherID = teacherID;
        this.assignment = new Assignment("-", "-");
    }

    public int getTeacherID() {return teacherID;}

    public String getTitle() {return title;}

    public HashMap<Integer, Result> getStudentResults() {return studentResults;}

    public Assignment getAssignment() {return assignment;}

    public void addNewStudent(Result result, int ID) {
        studentResults.putIfAbsent(ID, result);
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public void putStudentResult(Result result, int ID) {
        studentResults.replace(ID, result);
    }

    @Override
    public String toString() {
        return String.format("%s\nAssignment: %s", title, assignment.getTitle());
    }

    @Override
    public int compareTo(Subject o) {
        return this.title.compareTo(o.title);
    }
}
