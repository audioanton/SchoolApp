package database.users;

import database.Register;
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

    default void editUser(Register register, UserFactory factory) {}

    default void showUsers(List<Users> users) {}

    default void createAssignment(Register register) {}

    default void setGrade(Register register) {}

    default void editStudentClasses(Register register) {}
}
