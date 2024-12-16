package database.users;


public class UserFactory {
    private int uniqueID;

    public UserFactory() {
        this.uniqueID = uniqueID;
    }

    public Users createUser(UsersTypes type, String username) {
        switch (type) {
            case ADMIN: return new Administrator(username, ++uniqueID);
            case TEACHER:return new Teacher(username, ++uniqueID);
            default: return null;
        }
    }

    public Users createUser(String username, String email_guardian1, String email_guardian2) {
        return new Student(username, ++uniqueID, email_guardian1, email_guardian2);
    }
}
