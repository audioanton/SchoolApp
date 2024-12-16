public class Main {
    public static void main(String[] args) {
        SchoolApp schoolApp = new SchoolApp();
        Thread thread = new Thread(schoolApp);
        thread.start();
    }
}