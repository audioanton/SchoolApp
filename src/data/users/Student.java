package data.users;
import data.Subject;
import java.io.Serializable;
import java.util.List;

public class Student implements Serializable, Users {
    private String[] guardianContacts;
    private final int ID;
    private final String username;

    public Student(String username, int ID) {
        this.username = username;
        this.ID = ID;
    }

    public Student(String username, int ID, String email_1, String email_2) {
        this.username = username;
        this.ID = ID;
        this.guardianContacts = new String[]{email_1, email_2};
    }

    @Override
    public void showSubjects(List<Subject> subjects) {
        System.out.println(username + "'s subjects:");
        for (Subject subject : subjects) {
            if (subject.getStudentResults().containsKey(ID)) {
                System.out.printf("* %s, grade: %s\n", subject.getTitle(), subject.getStudentResults().get(ID));
            }
        }
    }

    @Override
    public void showAssignments(List<Subject> subjects) {
        System.out.println(username + "'s assignments:");
        for (Subject subject : subjects) {
            if (subject.getStudentResults().containsKey(ID))
                if (!subject.getAssignment().getTitle().equals("-"))
                    System.out.println(subject.getAssignment().toString());
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
    public void showOptions() {
        System.out.println("Student menu:");
        System.out.println("1 = Save and Exit");
        System.out.println("2 = Show Assignments");
        System.out.println("3 = Show Subjects");
    }
}
