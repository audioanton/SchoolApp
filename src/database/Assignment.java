package database;


import java.io.Serializable;
import java.util.HashMap;

public class Assignment implements Serializable {
    private String title;
    private String description;
    private HashMap<Integer, Result> studentResults;

    public Assignment(String title, String description) {
        this.title = title;
        this.description = description;
        studentResults = new HashMap<>();
    }

    public HashMap<Integer, Result> getStudentResults() {
        return studentResults;
    }

    public void setStudentResults(HashMap<Integer, Result> studentResults) {
        this.studentResults = studentResults;
    }

    public String getTitle() {
        return title;
    }

    public void setGrade(Result result, int ID) {
        studentResults.put(ID, result);
    }

    @Override
    public String toString() {
        return String.format("* %s\n  %s\n", title, description);
    }

}
