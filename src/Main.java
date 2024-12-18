public class Main {
    public static void main(String[] args) {
        SchoolApp schoolApp = new SchoolApp();
        Thread thread = new Thread(schoolApp);
        //Thread thread1 = new Thread(schoolApp);
        thread.start();
        //thread1.start();
    }
}