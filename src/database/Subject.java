package database;

import database.util.Displayable;

import java.io.Serializable;
import java.util.List;

public class Subject implements Displayable, Serializable {
    private int teacherID;
    private List<Integer> studentIDs;
    private Assignment assignment;

    @Override
    public void display() {

    }
}
