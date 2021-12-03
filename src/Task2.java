import java.util.Set;

public class Task2 {

    private static void getThreads() {
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
    }


    public void searchByName(String name){

    }

    public static void main(String[] args) {
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
