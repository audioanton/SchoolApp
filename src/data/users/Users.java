package data.users;

import data.Register;
import data.Subject;

import java.util.List;
import java.util.Scanner;

public interface Users extends Comparable<Users> {

    void showOptions();

    int getID();

    String getUsername();

    default int compareTo(Users other) {
        return getID() - other.getID();
    }

    default void showSubjects(List<Subject> subjects) {}

    default void showAssignments(List<Subject> subjects) {}

    default void editUser(Register register, UserFactory factory, Scanner scanner) {}

    default void showUsers(List<Users> users) {}

    default void createAssignment(Register register, Scanner scanner) {}

    default void setGrade(Register register, Scanner scanner) {}

    default void editStudentSubjects(Register register, Scanner scanner) {}
}
