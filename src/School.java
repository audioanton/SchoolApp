import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import database.Register;
import database.Result;
import database.Subject;
import database.users.*;

import java.lang.reflect.Type;
import java.util.*;

public class School {
    Gson gson;
    private Scanner scanner;
    UserFactory userFactory;
    Users user;
    Register register;

    public School() {
        scanner = new Scanner(System.in);
        userFactory = new UserFactory();
        register = new Register();
    }

    public void runProgram() {
        //loadSchool();
        initUsers();
        initSubjects();

        while (user == null) {
            try {
                validateUser();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                user.showOptions();
                String selection = scanner.nextLine().trim();
                System.out.println();
                selectAction(selection);
                System.out.println();
                returnToMenu();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void returnToMenu() {
        System.out.println("Press \'E\' to exit, or any other key to return to menu.");
        String s = scanner.nextLine().trim();
        if (s.equalsIgnoreCase("e"))
            saveAndExit();
    }

    public void selectAction(String selection) {
        if (selection.equals("1")) {
            saveAndExit();
        }
        else if (selection.equals("2") && user instanceof Student) {
            user.showAssignments(register.getSubjects());
        }
        else if (selection.equals("2") && !(user instanceof Student)) {
            user.showUsers(register.getUsers());
        }
        else if (selection.equals("3") && !(user instanceof Administrator)) {
            user.showSubjects(register.getSubjects());
        }
        else if (selection.equals("3") && user instanceof Administrator) {
            user.editUser(register, userFactory, scanner);
        }
        else if (selection.equals("4") && user instanceof Administrator) {
            user.editStudentClasses(register, scanner);
        }
        else if (selection.equals("4") && user instanceof Teacher) {
            user.createAssignment(register, scanner);
        }
        else if (selection.equals("5") && user instanceof Teacher) {
            user.setGrade(register, scanner);
        }
        else {
            System.out.println("Invalid Selection.");
        }
    }

    public void validateUser() {
        int id;
        String username;

        try {
            System.out.println("Please enter your username: ");
            username = scanner.nextLine();
            System.out.println("Please enter userID: ");
            String userID = scanner.nextLine();
            id = Integer.parseInt(userID);
            user = establishCurrentUser(id, username);
        } catch (NumberFormatException e) {
            System.out.println("Invalid userID");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    private Users establishCurrentUser(int ID, String username) {
        Collections.sort(register.getUsers());
        int index = Collections.binarySearch(register.getUsers(), new Student(username, ID));
        if (index >= 0 && register.getUsers().get(index).getUsername().equals(username))
            return register.getUsers().get(index);
        else
            throw new NullPointerException("No such user");
    }

    public void initUsers() {
        if (register.getUsers().isEmpty()) {
            register.getUsers().add(userFactory.createUser("Jamie", "1@gmail.com", "2@gmail.com"));
            register.getUsers().add(userFactory.createUser("Kylie", "1@gmail.com", "2@gmail.com"));
            register.getUsers().add(userFactory.createUser("Riley", "1@gmail.com", "2@gmail.com"));
            register.getUsers().add(userFactory.createUser("Baney", "1@gmail.com", "2@gmail.com"));
            register.getUsers().add(userFactory.createUser("Laney", "1@gmail.com", "2@gmail.com"));

            register.getUsers().add(userFactory.createUser(UsersTypes.TEACHER, "Bob"));
            register.getUsers().add(userFactory.createUser(UsersTypes.TEACHER, "Rob"));
            register.getUsers().add(userFactory.createUser(UsersTypes.TEACHER, "Gob"));

            register.getUsers().add(userFactory.createUser(UsersTypes.ADMIN, "Mogo"));
        }

        for (Users user : register.getUsers()) {
            System.out.println(user.getUsername() + " " + user.getID());
        }
    }

    public void initSubjects() {
        if (register.getSubjects().isEmpty()) {
            register.getSubjects().add(new Subject("Geography", 6));
            register.getSubjects().add(new Subject("Math", 6));
            register.getSubjects().add(new Subject("English", 7));
            register.getSubjects().add(new Subject("P.E", 7));
            register.getSubjects().add(new Subject("Music", 8));
            register.getSubjects().add(new Subject("History", 8));

            for (Subject subject : register.getSubjects()) {
                for (Users user : register.getUsers()) {
                    if (!(user instanceof Student))
                        continue;
                    subject.addNewStudent(Result.INCOMPLETE, user.getID());
                }
            }
        }
    }

    public void saveAndExit() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(register.getSubjects());
        System.out.println(json);
        System.out.println("Exiting program");
        loadSchool(json);
        //System.exit(0);
    }

    public void loadSchool(String json) {
        //ta bort parameter - använd fil
        Type subjectList = new TypeToken<ArrayList<Subject>>() {}.getType();
        List<Subject> subjects = gson.fromJson(json, subjectList); //gör register.getSubjects(); istället
        System.out.println("Deserialized list:");
        //for (Subject subject : subjects) {
        //    System.out.printf("%s\n%s\n,%s\n-%s\n", subject.getTitle(), subject.getTeacherID(), subject.getStudentResults() , subject.getAssignment().getStudentResults());
        //}

        //serialisera //de serialisera List<Users>
    }

}
