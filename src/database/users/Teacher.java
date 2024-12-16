package database.users;
import database.Assignment;
import database.Register;
import database.Result;
import database.Subject;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Teacher implements Serializable, Users {
    private final String username;
    private final int ID;

    public Teacher(String username, int ID) {
        this.username = username;
        this.ID = ID;
    }

    @Override
    public void showOptions() {
        System.out.println("Teacher menu:");
        System.out.println("1 - Save and Exit");
        System.out.println("2 - Show Students");
        System.out.println("3 - Show Subjects");
        System.out.println("4 - Create Assignment");
        System.out.println("5 - Set Grade");
    }

    @Override
    public void showSubjects(List<Subject> subjects) {
        System.out.println("Teacher subjects:");
        for (Subject subject : subjects) {
            if (subject.getTeacherID() == this.ID) {
                System.out.printf("* %s:\n  Assignment: %s\n  Students: %s\n\n", subject.getTitle(), subject.getAssignment().getTitle(), subject.getStudentResults().keySet());
            }
        }
    }

    @Override
    public void showUsers(List<Users> users) {
        System.out.println("Students:");
        for (Users user : users) {
            if (user instanceof Student) {
                System.out.printf("* %s - ID: %s\n", user.getUsername(), user.getID());
            }
        }
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void createAssignment(Register register, Scanner scanner) {
        System.out.println("Create Assignment");
        int index = selectSubject(scanner, register);
        System.out.println("Enter Assignment title:");
        String title = scanner.nextLine().trim();
        System.out.println("Enter Assignment description:");
        String description = scanner.nextLine().trim();
        register.getSubjects().get(index).setAssignment(new Assignment(title, description));
        System.out.println("Assignment created.");
    }

    @Override
    public void setGrade(Register register, Scanner scanner) {
        System.out.println("Set Grade");
        int studentID = -1;
        int index = selectSubject(scanner, register);

        while (studentID < 0) {
            System.out.println("Student ID:");
            studentID = scanner.nextInt();
            scanner.nextLine();
            int i = Collections.binarySearch(register.getUsers(), new Student("", studentID));
            if (!(register.getUsers().get(i) instanceof Student)) {
                System.out.println("Student not found.");
                studentID = -1;
            }
        }

        System.out.println("Grade Assignment (a) or Subject (s):");
        String selection = scanner.nextLine().trim();
        System.out.println("Result - A / C / F:");
        String grade = scanner.nextLine().trim().toUpperCase();
        if (selection.startsWith("s")) {
            switch (grade) {
                case "A": register.getSubjects().get(index).putStudentResult(Result.A, studentID); break;
                case "C": register.getSubjects().get(index).putStudentResult(Result.C, studentID); break;
                case "F": register.getSubjects().get(index).putStudentResult(Result.F, studentID); break;
            }
        }
        else if (selection.startsWith("a")) {
            switch (grade) {
                case "A": register.getSubjects().get(index).getAssignment().setGrade(Result.A, studentID); break;
                case "C": register.getSubjects().get(index).getAssignment().setGrade(Result.C, studentID); break;
                case "F": register.getSubjects().get(index).getAssignment().setGrade(Result.F, studentID); break;
            }
        }
        System.out.println("Grade set.");
    }

    private int selectSubject(Scanner scanner, Register register) {
        int index = -1;
        while (index < 0) {
            System.out.println("Enter Subject:");
            String subject = scanner.nextLine().trim();
            Collections.sort(register.getSubjects());
            index = Collections.binarySearch(register.getSubjects(), new Subject(subject, 0));
            if (index < 0)
                System.out.println("Subject not found.");
            else if (register.getSubjects().get(index).getTeacherID() != this.ID) {
                System.out.println("Not your Subject");
                index = -1;
            }
        }
        return index;
    }
}
