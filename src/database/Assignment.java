package database;


import java.io.Serializable;

public class Assignment implements Serializable {
    private String title;
    private String description;
    private Result result;

    public Assignment(String title, String description) {
        this.title = title;
        this.description = description;
        result = Result.INCOMPLETE;
    }

    public String getTitle() {
        return title;
    }

    public void setGrade(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("%s\n\n%s\n\nGrade: %s", title, description, result.toString());
    }

}
