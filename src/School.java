import database.users.*;

import java.util.*;

public class School {
    private Scanner scanner;
    List<User> users;
    User currentUser;

    public School() {
        scanner = new Scanner(System.in);
        users = new ArrayList<>();
        initUsers();
    }

    public void runProgram() {
        loadSchool();
        System.out.println(users.getFirst().getUsername() + " " + users.getFirst().getId()); //Todo: delete this
        while (currentUser == null) {
            try {
                validateUser();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                showOptions();
                String selection = scanner.nextLine().trim();
                makeChoice(selection);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void makeChoice(String selection) {

        if (selection.equals("1")) {
            saveAndExit();
        }
        else if (selection.equals("2")) {
            if (currentUser instanceof Student)
               ((Student) currentUser).display();
            else if (currentUser instanceof Teacher)
               ((Teacher) currentUser).display();
            else
                ((Administrator) currentUser).display();
        }
        else {
            System.out.println("Invalid selection.");
        }
    }

    public void validateUser() {
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter userID: ");
        String userID = scanner.nextLine();
        int id = 0;
        try {
            id = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            System.out.println("Invalid userID");
        }

        for (User user : users) {
            if (user.getId() == id && user.getUsername().equals(username)) {
                this.currentUser = user;
                break;
            }
        }
        if (currentUser == null) {
            throw new IllegalArgumentException("Incorrect username or ID");
        }
    }

    public void initUsers() {
        users.add(createUser(Users.ADMIN, "Jenny"));
        users.add(createUser(Users.TEACHER, "Sara"));
        users.add(createUser(Users.TEACHER, "Tom"));
        users.add(createUser(Users.STUDENT, "Sam"));
        users.add(createUser(Users.STUDENT, "Bingo"));
    }

    public void saveAndExit() {
        System.out.println("Exiting program");
        System.exit(0);
    }

    public void loadSchool() {}

    public void showOptions() {
        currentUser.showOptions();
    }

    public User createUser(Users type, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                throw new IllegalArgumentException("Username already exists");
        }

        return switch (type) {
            case Users.STUDENT -> new Student(username);
            case Users.TEACHER -> new Teacher(username);
            case Users.ADMIN -> new Administrator(username);
        };
    }
}
