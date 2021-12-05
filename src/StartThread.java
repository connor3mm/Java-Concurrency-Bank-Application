public class StartThread extends Thread{
    private final String name;

    public StartThread(String name) {
        this.name = name;
    }

    public void run() {
        Thread.currentThread().setName(name);
        System.out.println("Created a new thread! with name: " + Thread.currentThread().getName());
        while(!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running in the background.");
        }
    }
}
