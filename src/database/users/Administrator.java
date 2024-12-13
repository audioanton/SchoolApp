package database.users;
import java.io.Serializable;
import java.util.List;

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
        System.out.println("1 = Save and Exit");
    }

    public void showStudents(List<Users> users) {}

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void createUser(List<Users> users) {

    }

    @Override
    public void removeUser(List<Users> users) {

    }

}
