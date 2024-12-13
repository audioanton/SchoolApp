import database.Register;
import database.users.*;

import java.util.*;

public class School {
    private Scanner scanner;
    UserMaker user; //ersätta med ID?
    Register register;

    public School() {
        scanner = new Scanner(System.in);
    }

    public void runProgram() {
        loadSchool();

        while (user == null) {
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
                selectAction(selection);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void selectAction(String selection) {
        if (selection.equals("1")) {
            saveAndExit();
        }
        else if (selection.equals("2")) {
            user.showOptions();
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

        //validate is userID in register?
            //is id and username match?
            UsersTypes type = UsersTypes.ADMIN; //establish type //Student, Teacher

        //instans av fabriken "userFactory"
        user = new UserMaker(type, id, register); //factory istället.
        user.showOptions();

      //validate ID in database....
    }


    public void initUsers() {
        register.initRegister();

        //Factory-pattern add users.
    }

    public void saveAndExit() {
        System.out.println("Exiting program");
        System.exit(0);
    }

    public void loadSchool() {}

    public void showOptions() {
        user.showOptions();
    }

}
