package database.users;

import database.Assignment;
import database.Subject;

import java.util.List;

public interface Users extends Comparable<Users> {

    void showOptions();

    int getID();

    String getUsername();

    default int compareTo(Users other) {
        return getID() - other.getID();
    }

    default void showSubjects(List<Subject> subjects) {}

    default void showAssignments(List<Subject> subjects) {}

    default void createUser(List<Users> users) {}

    default void removeUser(List<Users> users) {}

    default void showStudents(List<Users> users) {}

    default void createAssignment(List<Subject> subjects) {}

    default void setGrade(List<Subject> subjects) {}
}
