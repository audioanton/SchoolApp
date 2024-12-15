public class Main {
    public static void main(String[] args) {
        SchoolApp schoolApp = SchoolApp.getInstance();
        System.out.println(schoolApp);
        Thread t = new Thread(schoolApp);
        t.start();
    }
}