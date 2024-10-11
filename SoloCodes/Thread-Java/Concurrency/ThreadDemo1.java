public class ThreadDemo1 implements Runnable {
    public static void main(String[] args) {
        ThreadDemo1 obj = new ThreadDemo1();
        Thread thread = new Thread(obj);
        thread.start();
        System.out.println("Outside of a thread");
    }

    public void run() {
        System.out.println("Running in a thread");
    }
}
