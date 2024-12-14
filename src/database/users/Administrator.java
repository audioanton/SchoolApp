package database.users;
import database.Register;
import database.Result;
import database.Subject;
import java.io.Serializable;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Administrator implements Serializable, Users {

    private final int ID;
    private final String username;

    public Administrator(String username, int ID) {
        this.username = username;
        this.ID = ID;
    }

    @Override
    public void showOptions() {
        System.out.println("Administrator menu:");
        System.out.println("1 - Save and Exit");
        System.out.println("2 - Show Users");
        System.out.println("3 - Edit User");
        System.out.println("4 - Edit Student Classes");
    }

    @Override
    public void showUsers(List<Users> users) {
        System.out.println(users.size());
        System.out.println("Students: ");
        for (Users user : users) {
            if (user instanceof Student) {
                System.out.println(user.getUsername() + ", ID: " + user.getID());
            }
        }
        System.out.println("Teachers:");
        for (Users user : users) {
            if (user instanceof Teacher) {
                System.out.println(user.getUsername() + ", ID: " + user.getID());
            }
        }
        System.out.println("Admins:");
        for (Users user : users) {
            if (user instanceof Administrator) {
                System.out.println(user.getUsername() + ", ID: " + user.getID());
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
    public void editUser(Register register, UserFactory factory, Scanner scanner) {
        String username = "";
        String type = "";
        int i = -1;
        int index = 0;

        System.out.println("Add (a) or remove (r) user:");
        boolean add = scanner.nextLine().trim().startsWith("a");
        System.out.println(register.getUsers().size());

        if (add) {
            System.out.println("Enter username:");
            username = scanner.nextLine();
            System.out.println("Admin(a) - Student (s) - Teacher (t):");
            type = scanner.nextLine().trim();
        }
        else {
            System.out.println("Enter ID:");
            i = scanner.nextInt();
            scanner.nextLine();
        }

        if (add) {
            if (type.toLowerCase().startsWith("a")) {
                register.getUsers().add(factory.createUser(UsersTypes.ADMIN, username));
                System.out.println("Administrator " + username + " added.");
            } else if (type.toLowerCase().startsWith("t")) {
                register.getUsers().add(factory.createUser(UsersTypes.TEACHER, username));
                System.out.println("Teacher " + username + " added.");
            } else if (type.toLowerCase().startsWith("s")) {
                System.out.println("Email of first guardian:");
                String s = scanner.nextLine().trim();
                System.out.println("Email of first guardian:");
                String s2 = scanner.nextLine().trim();
                register.getUsers().add(factory.createUser(username, s, s2));
                System.out.println("Student " + username + " added.");
            } else
                System.out.println("Invalid user type");
        }
        else {
            Collections.sort(register.getUsers());
            index = Collections.binarySearch(register.getUsers(), new Student(" ", i));
            if (index >= 0) {
                register.getUsers().remove(index);
                System.out.println("User removed.");
            }
        }
    }

    @Override
    public void editStudentClasses(Register register, Scanner scanner) {
        try {
            System.out.println("Student ID: ");
            int ID = scanner.nextInt();
            scanner.nextLine();
            int valid = Collections.binarySearch(register.getUsers(), new Student(" ", ID));
            if (valid < 0)
                throw new IllegalArgumentException("No such student");

            System.out.println("Add (a) or remove (r) Student?");
            boolean add = scanner.nextLine().toLowerCase().startsWith("a");
            System.out.println("Subject: ");
            String subject = scanner.nextLine().trim();
            Collections.sort(register.getSubjects());
            int index = Collections.binarySearch(register.getSubjects(), new Subject(subject, 0));

            if (index >= 0) {
                if (add) {
                    register.getSubjects().get(index).addNewStudent(Result.INCOMPLETE, ID);
                    System.out.println("Student added to ." + register.getSubjects().get(index).getTitle());
                }
                else {
                    register.getSubjects().get(index).getStudentResults().remove(ID);
                    System.out.println("Student removed from " + register.getSubjects().get(index).getTitle());
                }
            } else
                System.out.println("No such Subject.");
        } catch (InputMismatchException e) {
            System.out.println("Bad ID");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
