import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Task2 {

    private static Thread[] getThreads() {
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parentGroup;
        while ((parentGroup = rootGroup.getParent()) != null) {
            rootGroup = parentGroup;
        }


        Thread[] threads = new Thread[rootGroup.activeCount()+1];
        while (rootGroup.enumerate(threads, true ) == threads.length) {
            threads = new Thread[threads.length * 2];
        }

        int i=0;
        while (threads[i]!=null){
            System.out.println("Name of thread: " + threads[i].getName());
            System.out.println("ID of thread: " + threads[i].getId());
            System.out.println("Priority of thread: " + threads[i].getPriority());
            System.out.println("State of thread:" + threads[i].getState());
            System.out.println("Is Daemon: " + threads[i].isDaemon());
            System.out.println();
            i++;
        }

        return threads;
    }


    public static Thread searchByName(String name){
        for(Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(name)){
                return t;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the thread: ");
        String name = scanner.nextLine();
        Thread nameThread = searchByName(name);
        System.out.println(nameThread);

        while(true){
            getThreads();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println("-------------------------------------------");
        }
    }
}
