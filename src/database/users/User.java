package database.users;

public abstract class User {
    private final String username;
    private final int id;
    private static int lastID = 10000;
    String[] options = {"1: Save and Exit.", "2: Show all data", "3: something else"};

    protected User(int id, String username) {
        this.id = id;
        this.username = username.trim();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public abstract void showOptions();

    public static int getNextId() {
        return ++lastID;
    }

}
