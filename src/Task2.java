import java.util.*;
import java.util.stream.Collectors;

public class Task2 {

    private static void getThreads() {
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parentGroup;
        while ((parentGroup = rootGroup.getParent()) != null) {
            rootGroup = parentGroup;
        }


        Thread[] threads = new Thread[rootGroup.activeCount() + 1];
        while (rootGroup.enumerate(threads, true) == threads.length) {
            threads = new Thread[threads.length * 2];
        }

        int i = 0;
        while (threads[i] != null) {
            System.out.println("Name of thread: " + threads[i].getName());
            System.out.println("ID of thread: " + threads[i].getId());
            System.out.println("Priority of thread: " + threads[i].getPriority());
            System.out.println("State of thread:" + threads[i].getState());
            System.out.println("Is Daemon: " + threads[i].isDaemon());
            System.out.println();
            i++;
        }

    }


    public static Thread searchByName(String name) {
//        for (Thread t : Thread.getAllStackTraces().keySet()) {
//            if (t.getName().equals(name)) {
//                return t;
//            }
//        }
//        return null;

        return Thread.getAllStackTraces().keySet().stream()
                .filter(nameFilter -> nameFilter.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static List<Thread> filterByThreadGroup(String name) {

//        HashMap<Integer, Thread> newHashMap = new HashMap<>();
//        int i = 0;
//        for (Thread t : Thread.getAllStackTraces().keySet()) {
//            if (t.getThreadGroup().getName().equals(name)) {
//                newHashMap.put(i, t);
//                i++;
//            }
//        }
//        return newHashMap;

        return Thread.getAllStackTraces().keySet().stream()
                .filter(groupName -> groupName.getThreadGroup().getName().equals(name))
                .collect(Collectors.toList());
    }

    private static void startNewThread(String nameOfThread) {
        StartThread newThreadClass = new StartThread(nameOfThread);
        Thread newThread = new Thread(newThreadClass);
        newThread.start();
    }


    public static Boolean stopThread(String threadName) {
        Thread threadToStop = searchByName(threadName);
        if(threadToStop == null) return false;
        threadToStop.interrupt();
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Task 2 Part 2");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the thread: ");
        String name = scanner.nextLine();

        //Search by name functionality
        Thread nameThread = searchByName(name);
        if (nameThread == null) {
            System.out.println("Could not find a thread with the name: " + name);
        } else {
            System.out.println(nameThread);
        }

        System.out.println("--------------------" + "\n");

        System.out.println("Enter the name of the thread group: ");
        String name2 = scanner.nextLine().toLowerCase();

        //Filter by threadgroup functionality
        List<Thread> threadList = filterByThreadGroup(name2);
        if (threadList.size() == 0) {
            System.out.println("Could not find a thread group with the name: " + name2);
        } else {
            threadList.forEach(System.out::println);
        }

//        HashMap<Integer, Thread> newHashMap2;
//        newHashMap2 = filterByThreadGroup(name2);
//
//        newHashMap2.forEach((key, value) -> System.out.println(key + " " + value));

        System.out.println("--------------------" + "\n");

        //Create new thread functionality
        System.out.println("Enter a name for the new thread: ");
        String nameOfThread = scanner.nextLine();

        startNewThread(nameOfThread);

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Stop thread functionality
        if(stopThread(nameOfThread)) {
            System.out.println("Successfully stopped thread with name: " + nameOfThread);
        } else {
            System.out.println("Thread: " + nameOfThread + " could not be stopped.");
        }

        System.out.println("Task 2 Part 1");
        System.out.println("Listing all threads");
        while (true) {
            getThreads();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("--------------------");
        }
    }


}
