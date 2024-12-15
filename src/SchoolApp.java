import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import database.Register;
import database.Result;
import database.Subject;
import database.users.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class SchoolApp implements Runnable {
    Gson gson;
    private Scanner scanner;
    UserFactory userFactory;
    Users user;
    private Register register;
    private static Register sharedRegister;//blir inte synchroniserade...

    static SchoolApp instance; //blir singleton

    private SchoolApp() {
        scanner = new Scanner(System.in);
        userFactory = new UserFactory();
        sharedRegister = new Register();
    }

    public static synchronized SchoolApp getInstance() {
        if (instance == null)
            return new SchoolApp();
        return instance;
    }

    private void runProgram() {
        loadSchool();
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

    private void selectAction(String selection) {
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

    private void validateUser() {
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
            System.out.print(user.getUsername() + " " + user.getID() + ", ");
        }
        System.out.println();
    }

    private void initSubjects() {
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

    private void saveAndExit() {
        //add Factory Json

        try (FileWriter subjectWriter = new FileWriter("src/database/subjects.json");
             FileWriter userWriter = new FileWriter("src/database/users.json"))  {

            gson = new GsonBuilder().registerTypeAdapter(Users.class, new UsersSerializer()).setPrettyPrinting().create();
            gson.toJson(register.getSubjects(), subjectWriter);

            Type typeToken = new TypeToken<List<Users>>() {}.getType();
            gson.toJson(register.getUsers(), typeToken, userWriter);
            System.out.println("Data saved, Exiting program");
        } catch (IOException e) {
            throw new RuntimeException("Failed to save data" + e.getMessage());
        }
        System.exit(0);
    }

    private void loadSchool() {

        //Add factory Json
        File temp = new File("src/database/subjects.json");
        File temp2 = new File("src/database/users.json");

        if (temp.exists() && temp2.exists()) {
            System.out.println("Loading");

            try (FileReader subjectReader = new FileReader(temp);
                 FileReader userReader = new FileReader(temp2)) {
                gson = new GsonBuilder()
                        .registerTypeAdapter(Users.class, new UsersSerializer())
                        .setPrettyPrinting()
                        .create();

                Type subjectList = new TypeToken<ArrayList<Subject>>() {}.getType();
                register.setSubjects(gson.fromJson(subjectReader, subjectList));

                Type typeToken = new TypeToken<List<Users>>() {}.getType();
                register.setUsers(gson.fromJson(userReader, typeToken));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        register = sharedRegister;
        System.out.println(register); //inte samma, gör till Singleton???
        runProgram();
    }
}