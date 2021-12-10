public class StartThread extends Thread{
    private final String name;

    public StartThread(String name) {
        this.name = name;
    }

    public void run() {
        Thread.currentThread().setName(name);
        while (!Thread.currentThread().isInterrupted()) {

        }
    }
}
